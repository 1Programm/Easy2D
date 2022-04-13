package com.programm.projects.easy2d.engine.simple.objects;

import com.programm.project.easy2d.engine.api.IPencil;
import com.programm.projects.easy2d.engine.simple.SimpleEngine;
import com.programm.projects.easy2d.objects.api.IGameObject;
import com.programm.projects.easy2d.objects.api.IObjectCollection;
import com.programm.projects.easy2d.objects.api.IObjectContext;

import java.awt.*;

public abstract class SimpleObjectEngine extends SimpleEngine implements IObjectContext {

    private final ArrayObjectCollection objects;

    public SimpleObjectEngine(String title, int width, int height, float fps, boolean cached) {
        super(title, width, height, fps);
        if(cached) {
            this.objects = new CachedArrayObjectCollection(width, height, 10);
        }
        else {
            this.objects = new ArrayObjectCollection();
        }
    }

    public void addObject(IGameObject obj){
        objects.add(obj);
    }

    @Override
    public void update() {
        for(int i=0;i<objects.size();i++){
            IGameObject obj = objects.get(i);

            obj.update(this);

            if(obj.dead()) {
                objects.remove(i);
                i--;
            }
        }
    }

    @Override
    public void render(IPencil pencil) {
        pencil.setColor(Color.WHITE);
        pencil.fillScreen();

        for(int i=0;i<objects.size();i++){
            objects.get(i).render(pencil);
        }
    }

    @Override
    public IObjectCollection objects() {
        return objects;
    }
}
