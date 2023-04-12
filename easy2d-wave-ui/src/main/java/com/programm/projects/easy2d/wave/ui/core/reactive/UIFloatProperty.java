package com.programm.projects.easy2d.wave.ui.core.reactive;

import com.programm.libraries.reactiveproperties.ObservableValue;
import com.programm.libraries.reactiveproperties.core.FloatProperty;
import com.programm.projects.easy2d.wave.ui.core.GlobalWaveDefaults;

public class UIFloatProperty extends FloatProperty {

    private final ObservableValue<Float> optionals;
    private Float value;

    public UIFloatProperty(Class<?> componentClass, String propertyName) {
        optionals = GlobalWaveDefaults.getOptionalExpression(componentClass, propertyName);
    }

    @Override
    protected void setDirectly(Float val) {
        value = val;
    }

    @Override
    public Float get() {
        if(value == null) return optionals.get();
        return value;
    }
}
