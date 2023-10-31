package com.programm.projects.easy2d.ui.wave.core.reactive;

import com.programm.libraries.reactiveproperties.ObservableValue;
import com.programm.libraries.reactiveproperties.core.IntProperty;
import com.programm.projects.easy2d.ui.wave.core.GlobalWaveDefaults;

public class UIIntProperty extends IntProperty {

    private final ObservableValue<Integer> optionals;
    private Integer value;

    public UIIntProperty(Class<?> componentClass, String propertyName) {
        optionals = GlobalWaveDefaults.getOptionalExpression(componentClass, propertyName);
    }

    @Override
    protected void setDirectly(Integer val) {
        value = val;
    }

    @Override
    public Integer get() {
        if(value == null) return optionals.get();
        return value;
    }
}
