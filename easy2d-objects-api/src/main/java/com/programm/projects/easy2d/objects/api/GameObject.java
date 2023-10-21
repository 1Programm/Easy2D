package com.programm.projects.easy2d.objects.api;

import com.programm.project.easy2d.engine.api.IContext;
import com.programm.project.easy2d.engine.api.IPencil;
import com.programm.projects.easy2d.objects.api.components.IComponent;
import com.programm.projects.easy2d.objects.api.components.IRenderableComponent;
import com.programm.projects.easy2d.objects.api.components.IUpdatableComponent;
import com.programm.projects.easy2d.objects.api.components.Mover;
import com.programm.projects.plus.maths.Vector2f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameObject {

    private class InnerObjectContext implements IObjectContext {

        @Override
        public GameObject gameObject() {
            return GameObject.this;
        }

        @Override
        public IGOH objectHandler() {
            return objectHandler;
        }

        @Override
        public IContext engine() {
            return engine;
        }
    }

    public final Vector2f position = new Vector2f();
    public final Vector2f size = new Vector2f();
    private boolean dead;

    private final List<IComponent> allComponents = new ArrayList<>();
    private final Map<Class<? extends IComponent>, IComponent> components = new HashMap<>();
    private final Map<Class<? extends IComponent>, List<IComponent>> parentComponentMappings = new HashMap<>();
    private final List<IUpdatableComponent> updatableComponents = new ArrayList<>();
    private final List<IRenderableComponent> renderableComponents = new ArrayList<>();

    private InnerObjectContext context;
    private final Mover mover = new Mover();

    protected IContext engine;
    protected IGOH objectHandler;

    public GameObject() {
        allComponents.add(mover);
        components.put(Mover.class, mover);
    }

    public GameObject(float x, float y, float w, float h){
        this();
        position.set(x, y);
        size.set(w, h);
    }

    public GameObject(float x, float y){
        this(x, y, 1, 1);
    }

    public void init(IContext engine, IGOH objectHandler){
        this.engine = engine;
        this.objectHandler = objectHandler;

        this.context = new InnerObjectContext();
        for(int i=0;i<allComponents.size();i++){
            allComponents.get(i).init(context);
        }
    }

    public void update() {
        for(int i=0;i<updatableComponents.size();i++){
            updatableComponents.get(i).update();
        }

        mover.update();
    }

    public void render(IPencil pen) {
        for(int i=0;i<renderableComponents.size();i++){
            renderableComponents.get(i).render(pen);
        }
    }

    public boolean isDead() {
        return dead;
    }

    public void die(){
        this.dead = true;
    }

    @SuppressWarnings("unchecked")
    public GameObject add(IComponent comp){
        if(!comp.canHaveMultiple() && components.containsKey(comp.getClass())) throw new IllegalStateException("Component [" + comp.getClass() + "] already in this object!");

        if(context != null) comp.init(context);

        allComponents.add(comp);

        if(comp instanceof IUpdatableComponent) {
            updatableComponents.add((IUpdatableComponent) comp);
        }
        if(comp instanceof IRenderableComponent){
            renderableComponents.add((IRenderableComponent) comp);
        }

        if(!comp.canHaveMultiple()) components.put(comp.getClass(), comp);

        Class<? extends IComponent> cur = comp.getClass();
        while(IComponent.class.isAssignableFrom(cur)) {
            parentComponentMappings.computeIfAbsent(cur, c -> new ArrayList<>()).add(comp);
            cur = (Class<? extends IComponent>) cur.getSuperclass();
        }

        return this;
    }

    @SuppressWarnings("unchecked")
    public <T extends IComponent> T get(Class<T> cls){
        IComponent comp = components.get(cls);
        if(comp == null) {
            List<IComponent> parentMappings = parentComponentMappings.get(cls);
            if(parentMappings != null && parentMappings.size() == 1){
                return (T) parentMappings.get(0);
            }

            return null;
        }
        return (T) comp;
    }
}
