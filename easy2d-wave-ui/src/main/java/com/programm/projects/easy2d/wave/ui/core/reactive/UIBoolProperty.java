package com.programm.projects.easy2d.wave.ui.core.reactive;

import com.programm.libraries.reactiveproperties.ObservableValue;
import com.programm.libraries.reactiveproperties.core.BoolProperty;
import com.programm.projects.easy2d.wave.ui.core.GlobalWaveDefaults;

public class UIBoolProperty extends BoolProperty {

    private final ObservableValue<Boolean> optionals;
    private Boolean value;

    public UIBoolProperty(Class<?> componentClass, String propertyName) {
        optionals = GlobalWaveDefaults.getOptionalExpression(componentClass, propertyName);
    }

    @Override
    protected void setDirectly(Boolean val) {
        value = val;
    }

    @Override
    public Boolean get() {
        if(value == null) return optionals.get();
        return value;
    }
}
