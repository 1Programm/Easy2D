package com.programm.projects.easy2d.wave.ui.elements;

import com.programm.libraries.reactiveproperties.NotifyListener;
import com.programm.project.easy2d.engine.api.IKeyboard;
import com.programm.project.easy2d.engine.api.IMouse;
import com.programm.project.easy2d.engine.api.IPencil;
import com.programm.projects.easy2d.wave.ui.core.*;
import com.programm.projects.easy2d.wave.ui.core.bounds.IBounds;
import com.programm.projects.easy2d.wave.ui.core.bounds.IEditableBounds;
import com.programm.projects.easy2d.wave.ui.core.bounds.ValueBounds;
import com.programm.projects.easy2d.wave.ui.core.utils.GFXUtils;
import com.programm.projects.easy2d.wave.ui.elements.layout.ILayout;
import com.programm.projects.easy2d.wave.ui.elements.layout.InheritLayout;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class View extends WaveComponent {

    private static class DefaultViewRenderer implements IWaveComponentRenderer<View> {

        @Override
        public void render(IBounds bounds, IPencil pen, View c) {
            Color secondary = c.secondary().get();
            if (secondary != null) {
                pen.setColor(secondary);
                IWaveComponent.fillBounds(pen, bounds);
            }

            Color color = GFXUtils.primaryOrDisabled(c);
            if (color != null) {
                pen.setColor(color);
                IWaveComponent.drawBounds(pen, bounds);
            }
        }
    }

    static {
        GlobalWaveDefaults.setBaseDefault(View.class, "renderer", new DefaultViewRenderer());
        GlobalWaveDefaults.setBaseDefault(View.class, "primary", null);
        GlobalWaveDefaults.setBaseDefault(View.class, "secondary", null);
        GlobalWaveDefaults.setBaseDefault(View.class, "disabledColor", null);
        GlobalWaveDefaults.setBaseDefault(View.class, "layout", new InheritLayout());
    }

    private final NotifyListener recalcListener = this::requestRecalculate;

    protected final List<WaveComponent> children = new ArrayList<>();
    protected final List<Object> childArgsList = new ArrayList<>();
    protected final List<IEditableBounds> childBoundsList = new ArrayList<>();
    protected ILayout layout;

    private Float minWidth, minHeight;

    public View(ILayout layout) {
        this.layout = layout;
    }

    public View() {}

    @Override
    public void render(IBounds bounds, IPencil pen, boolean forceRedraw) {
        if(forceRedraw || GlobalComponentUtils.isDirty(this)) {
            GlobalComponentUtils.setDirty(this, false);
            renderSelf(bounds, pen);
            if(layout != null) {
                layout.updateBoundsForChildren(pen, bounds, children, childArgsList, childBoundsList);
            }

            for(int i=0;i<children.size();i++){
                GlobalComponentUtils.shouldRecalculate(children.get(i)).set(false);
            }

            forceRedraw = true;
        }
        else {
            for(int i=0;i<children.size();i++){
                if(GlobalComponentUtils.isDirty(children.get(i))){
                    renderSelf(bounds, pen);
                    forceRedraw = true;
                    break;
                }
            }
        }

        if(GlobalComponentUtils.shouldRecalculate(this).get()){
            forceRedraw = true;
        }

        for(int i=0;i<children.size();i++){
            WaveComponent child = children.get(i);
            if(GlobalComponentUtils.shouldBeDeleted(child)){
                _removeChild(i);
                i--;
                continue;
            }

            if(!child.visible().get()) continue;
            IEditableBounds childBounds = childBoundsList.get(i);
            if(childBounds != null) {
                child.render(childBounds, pen, forceRedraw);
            }
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    protected void renderSelf(IBounds bounds, IPencil pen){
        IWaveComponentRenderer renderer = GlobalLookAndFeel.getRendererForComponent(this);
        renderer.render(bounds, pen, this);
    }

    @Override
    public Float minWidth(IPencil pen) {
        if(layout != null && minWidth == null){
            minWidth = layout.minWidth(pen, children, childArgsList);
        }

        return minWidth;
    }

    @Override
    public Float minHeight(IPencil pen) {
        if(layout != null && minHeight == null){
            minHeight = layout.minHeight(pen, children, childArgsList);
        }

        return minHeight;
    }

    @Override
    public void onMousePressed(IBounds bounds, IMouse mouse, int button) {
        callForAll((child, childBounds) -> child.onMousePressed(childBounds, mouse, button));
    }

    @Override
    public void onMouseReleased(IBounds bounds, IMouse mouse, int button) {
        callForAll((child, childBounds) -> child.onMouseReleased(childBounds, mouse, button));
    }

    @Override
    public void onMouseMoved(IBounds bounds, IMouse mouse) {
        callForAll((child, childBounds) -> child.onMouseMoved(childBounds, mouse));
    }

    @Override
    public void onMouseDragged(IBounds bounds, IMouse mouse, int button) {
        callForAll((child, childBounds) -> child.onMouseDragged(childBounds, mouse, button));
    }

    @Override
    public void onMouseScrolled(IBounds bounds, IMouse mouse, float scrollX, float scrollY) {
        callForAll((child, childBounds) -> child.onMouseScrolled(childBounds, mouse, scrollX, scrollY));
    }

    @Override
    public void onKeyPressed(IBounds bounds, IKeyboard keyboard, int key) {
        callForAll((child, childBounds) -> child.onKeyPressed(childBounds, keyboard, key));
    }

    @Override
    public void onKeyReleased(IBounds bounds, IKeyboard keyboard, int key) {
        callForAll((child, childBounds) -> child.onKeyReleased(childBounds, keyboard, key));
    }

    private void callForAll(BiConsumer<WaveComponent, IBounds> childConsumer){
        for(int i=0;i<children.size();i++){
            WaveComponent child = children.get(i);
            if(!child.visible().get()) continue;
            IBounds childBounds = childBoundsList.get(i);
            if(childBounds == null) continue;
            childConsumer.accept(child, childBounds);
        }
    }

    public void add(WaveComponent child, Object args){
        GlobalComponentUtils.shouldRecalculate(child).listen(recalcListener);

        children.add(child);
        childArgsList.add(args);
        childBoundsList.add(new ValueBounds(child));

        requestRecalculate();
    }

    public void add(WaveComponent child){
        add(child, null);
    }

    public boolean remove(WaveComponent child){
        for(int i=0;i<children.size();i++){
            if(children.get(i) == child){
                _removeChild(i);
                return true;
            }
        }

        return false;
    }

    private void _removeChild(int i){
        WaveComponent child = children.get(i);
        GlobalComponentUtils.shouldRecalculate(child).removeListener(recalcListener);

        children.remove(i);
        childArgsList.remove(i);
        childBoundsList.remove(i);

        requestRecalculate();
    }

    public void setLayout(ILayout layout){
        this.layout = layout;
        requestRecalculate();
    }

    @Override
    public void requestRecalculate() {
        super.requestRecalculate();
        minWidth = null;
        minHeight = null;
    }
}
