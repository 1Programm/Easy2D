package com.programm.projects.easy2d.ui.wave.core;

import com.programm.libraries.reactiveproperties.core.BoolProperty;
import com.programm.libraries.reactiveproperties.core.BoolPropertyValue;
import com.programm.projects.easy2d.engine.api.IKeyboard;
import com.programm.projects.easy2d.engine.api.IMouse;
import com.programm.projects.easy2d.engine.api.IPencil;
import com.programm.projects.easy2d.ui.wave.core.bounds.IBounds;
import com.programm.projects.easy2d.ui.wave.core.bounds.IEditableBounds;
import com.programm.projects.easy2d.ui.wave.core.reactive.UIBoolProperty;
import com.programm.projects.easy2d.ui.wave.core.reactive.UIColorProperty;

import java.awt.*;

public abstract class WaveComponent implements IEditableBounds, IWaveComponent {

    static {
        GlobalWaveDefaults.setBaseDefault(WaveComponent.class, "primary", Color.BLACK);
        GlobalWaveDefaults.setBaseDefault(WaveComponent.class, "secondary", Color.WHITE);
        GlobalWaveDefaults.setBaseDefault(WaveComponent.class, "disabledColor", Color.LIGHT_GRAY);
        GlobalWaveDefaults.setBaseDefault(WaveComponent.class, "visible", true);
        GlobalWaveDefaults.setBaseDefault(WaveComponent.class, "disabled", false);
    }

    boolean dirty = true;
    final BoolProperty shouldRecalculate = new BoolPropertyValue(true);
    boolean shouldBeDeleted = false;

    private float x, y, width, height;
    boolean xUntouched = true, yUntouched = true, widthUntouched = true, heightUntouched = true;

    private final UIColorProperty primary = new UIColorProperty(this.getClass(), "primary");
    private final UIColorProperty secondary = new UIColorProperty(getClass(), "secondary");
    private final UIColorProperty disabledColor = new UIColorProperty(getClass(), "disabledColor");
    private final UIBoolProperty visible = new UIBoolProperty(getClass(), "visible");
    private final UIBoolProperty disabled = new UIBoolProperty(getClass(), "disabled");

    public WaveComponent() {
        primary.listen(this::requestRedraw);
        secondary.listen(this::requestRedraw);
        disabledColor.listen(this::requestRedraw);
        visible.listen(this::requestRecalculate);
        disabledColor.listen(this::requestRedraw);

        GlobalWaveDefaults.initDefaults(this.getClass(), this);
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void render(IBounds bounds, IPencil pen, boolean forceRedraw){
        if(forceRedraw || dirty){
            dirty = false;
            IWaveComponentRenderer renderer = GlobalLookAndFeel.getRendererForComponent(this);
            renderer.render(bounds, pen, this);
        }
    }

    public void requestRedraw(){
        dirty = true;
    }

    public void requestRecalculate() {
        dirty = true;
        shouldRecalculate.set(true);
    }

    public void removeFromParent(){
        shouldBeDeleted = true;
    }

    @Override
    public Float minWidth(IPencil pen){ return null; }

    @Override
    public Float minHeight(IPencil pen){ return null; }

    //Listeners
    public void onMousePressed(IBounds bounds, IMouse mouse, int button) {}
    public void onMouseReleased(IBounds bounds, IMouse mouse, int button) {}
    public void onMouseMoved(IBounds bounds, IMouse mouse) {}
    public void onMouseDragged(IBounds bounds, IMouse mouse, int button) {}
    public void onMouseScrolled(IBounds bounds, IMouse mouse, float scrollX, float scrollY) {}

    public void onKeyPressed(IBounds bounds, IKeyboard keyboard, int key) {}
    public void onKeyReleased(IBounds bounds, IKeyboard keyboard, int key) {}


    //Getter + Setter

    @Override
    public float x() {
        return x;
    }

    @Override
    public WaveComponent x(float x){
        this.x = x;
        this.xUntouched = false;
        requestRedraw();
        return this;
    }

    @Override
    public float y() {
        return y;
    }

    @Override
    public WaveComponent y(float y){
        this.y = y;
        this.yUntouched = false;
        requestRedraw();
        return this;
    }

    @Override
    public WaveComponent position(float x, float y){
        this.x = x;
        this.y = y;
        this.xUntouched = false;
        this.yUntouched = false;
        requestRedraw();
        return this;
    }

    @Override
    public float width() {
        return width;
    }

    @Override
    public WaveComponent width(float width){
        this.width = width;
        this.widthUntouched = false;
        requestRedraw();
        return this;
    }

    @Override
    public float height() {
        return height;
    }

    @Override
    public WaveComponent height(float height){
        this.height = height;
        this.heightUntouched = false;
        requestRedraw();
        return this;
    }

    @Override
    public WaveComponent size(float width, float height){
        this.width = width;
        this.height = height;
        this.widthUntouched = false;
        this.heightUntouched = false;
        requestRedraw();
        return this;
    }

    @Override
    public WaveComponent bounds(float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.xUntouched = false;
        this.yUntouched = false;
        this.widthUntouched = false;
        this.heightUntouched = false;
        requestRedraw();
        return this;
    }

    @Override
    public WaveComponent bounds(IBounds bounds) {
        return bounds(bounds.x(), bounds.y(), bounds.width(), bounds.height());
    }

    @Override
    public UIColorProperty primary() {
        return primary;
    }

    @Override
    public UIColorProperty secondary() {
        return secondary;
    }

    @Override
    public UIColorProperty disabledColor() {
        return disabledColor;
    }

    @Override
    public UIBoolProperty visible() {
        return visible;
    }

    @Override
    public UIBoolProperty disabled() {
        return disabled;
    }

}
