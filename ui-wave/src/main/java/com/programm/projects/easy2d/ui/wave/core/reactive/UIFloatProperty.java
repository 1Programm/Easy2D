package com.programm.projects.easy2d.ui.wave.core.reactive;

import com.programm.libraries.reactiveproperties.ObservableValue;
import com.programm.libraries.reactiveproperties.core.FloatProperty;
import com.programm.projects.easy2d.ui.wave.core.GlobalWaveDefaults;
import com.programm.projects.easy2d.ui.wave.core.WaveComponent;

public class UIFloatProperty extends FloatProperty implements IUIProperty {

    private final ObservableValue<Float> optionals;
    private Float value;

    private WaveComponent component = null;
    private boolean recalculate = false;

    public UIFloatProperty(Class<?> componentClass, String propertyName) {
        optionals = GlobalWaveDefaults.getOptionalExpression(componentClass, propertyName);
    }

    @Override
    protected void setDirectly(Float val) {
        value = val;
        if(component != null) {
            if(recalculate) component.requestRecalculate();
            else component.requestRedraw();
        }
    }

    @Override
    public Float get() {
        if(value == null) return optionals.get();
        return value;
    }

    @Override
    public UIFloatProperty redraw(WaveComponent c) {
        component = c;
        return this;
    }

    @Override
    public UIFloatProperty recalculate(WaveComponent c) {
        component = c;
        recalculate = true;
        return this;
    }
}
