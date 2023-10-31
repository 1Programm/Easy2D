package com.programm.projects.easy2d.ui.wave.elements;

import com.programm.libraries.reactiveproperties.NotifyListener;
import com.programm.libraries.reactiveproperties.core.FloatProperty;
import com.programm.projects.easy2d.engine.api.IKeyboard;
import com.programm.projects.easy2d.engine.api.IMouse;
import com.programm.projects.easy2d.engine.api.IPencil;
import com.programm.projects.easy2d.ui.wave.core.*;
import com.programm.projects.easy2d.ui.wave.core.bounds.IBounds;
import com.programm.projects.easy2d.ui.wave.core.bounds.IEditableBounds;
import com.programm.projects.easy2d.ui.wave.core.bounds.ValueBounds;
import com.programm.projects.easy2d.ui.wave.core.reactive.UIFloatProperty;
import com.programm.projects.easy2d.ui.wave.core.utils.GFXUtils;
import com.programm.projects.easy2d.ui.wave.elements.layout.ILayout;
import com.programm.projects.easy2d.ui.wave.elements.layout.InheritLayout;

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
//        GlobalWaveDefaults.setBaseDefault(View.class, "primary", null);
//        GlobalWaveDefaults.setBaseDefault(View.class, "secondary", null);
//        GlobalWaveDefaults.setBaseDefault(View.class, "disabledColor", null);
        GlobalWaveDefaults.setRootBaseDefault(View.class, "primary", null);
        GlobalWaveDefaults.setRootBaseDefault(View.class, "secondary", null);
        GlobalWaveDefaults.setRootBaseDefault(View.class, "disabledColor", null);
        GlobalWaveDefaults.setBaseDefault(View.class, "layout", new InheritLayout());

        GlobalWaveDefaults.setBaseDefault(View.class, "marginTop", 0f);
        GlobalWaveDefaults.setBaseDefault(View.class, "marginLeft", 0f);
        GlobalWaveDefaults.setBaseDefault(View.class, "marginBottom", 0f);
        GlobalWaveDefaults.setBaseDefault(View.class, "marginRight", 0f);
    }

    private final NotifyListener recalcListener = this::requestRecalculate;
    private final IEditableBounds boundsWithMargin = new ValueBounds();
    protected final UIFloatProperty marginTop = new UIFloatProperty(getClass(), "marginTop");
    protected final UIFloatProperty marginLeft = new UIFloatProperty(getClass(), "marginLeft");
    protected final UIFloatProperty marginBottom = new UIFloatProperty(getClass(), "marginBottom");
    protected final UIFloatProperty marginRight = new UIFloatProperty(getClass(), "marginRight");

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
        updateBoundsWithMargin(bounds);

        if(forceRedraw || GlobalComponentUtils.isDirty(this)) {
            GlobalComponentUtils.setDirty(this, false);
            renderSelf(bounds, pen);
            if(layout != null) {
                layout.updateBoundsForChildren(pen, boundsWithMargin, children, childArgsList, childBoundsList);
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

    private void updateBoundsWithMargin(IBounds bounds){
        float left = bounds.x() + marginLeft.get();
        float right = bounds.x() + bounds.width() - marginRight.get();
        if(right < left) right = left;

        float top = bounds.y() + marginTop.get();
        float bottom = bounds.y() + bounds.height() - marginBottom.get();
        if(bottom < top) bottom = top;

        boundsWithMargin.bounds(left, top, right - left, bottom - top);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    protected void renderSelf(IBounds bounds, IPencil pen){
        IWaveComponentRenderer renderer = GlobalLookAndFeel.getRendererForComponent(this);
        renderer.render(bounds, pen, this);
    }

    @Override
    public Float minWidth(IPencil pen) {
        if(layout != null && minWidth == null){
            float horizontalMargin = marginLeft.get() + marginRight.get();
            minWidth = layout.minWidth(pen, children, childArgsList) + horizontalMargin;
        }

        return minWidth;
    }

    @Override
    public Float minHeight(IPencil pen) {
        if(layout != null && minHeight == null){
            float verticalMargin = marginTop.get() + marginBottom.get();
            minHeight = layout.minHeight(pen, children, childArgsList) + verticalMargin;
        }

        return minHeight;
    }

    @Override
    public void onMousePressed(IBounds bounds, IMouse mouse, int button) {
        if(disabled().get()) return;
        callForAll((child, childBounds) -> child.onMousePressed(childBounds, mouse, button));
    }

    @Override
    public void onMouseReleased(IBounds bounds, IMouse mouse, int button) {
        if(disabled().get()) return;
        callForAll((child, childBounds) -> child.onMouseReleased(childBounds, mouse, button));
    }

    @Override
    public void onMouseMoved(IBounds bounds, IMouse mouse) {
        if(disabled().get()) return;
        callForAll((child, childBounds) -> child.onMouseMoved(childBounds, mouse));
    }

    @Override
    public void onMouseDragged(IBounds bounds, IMouse mouse, int button) {
        if(disabled().get()) return;
        callForAll((child, childBounds) -> child.onMouseDragged(childBounds, mouse, button));
    }

    @Override
    public void onMouseScrolled(IBounds bounds, IMouse mouse, float scrollX, float scrollY) {
        if(disabled().get()) return;
        callForAll((child, childBounds) -> child.onMouseScrolled(childBounds, mouse, scrollX, scrollY));
    }

    @Override
    public void onKeyPressed(IBounds bounds, IKeyboard keyboard, int key) {
        if(disabled().get()) return;
        callForAll((child, childBounds) -> child.onKeyPressed(childBounds, keyboard, key));
    }

    @Override
    public void onKeyReleased(IBounds bounds, IKeyboard keyboard, int key) {
        if(disabled().get()) return;
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

    public void margin(float top, float left, float bottom, float right){
        marginTop.set(top);
        marginLeft.set(left);
        marginBottom.set(bottom);
        marginRight.set(right);
    }

    public FloatProperty marginTop(){
        return marginTop;
    }

    public FloatProperty marginLeft(){
        return marginLeft;
    }

    public FloatProperty marginBottom(){
        return marginBottom;
    }

    public FloatProperty marginRight(){
        return marginRight;
    }

}
