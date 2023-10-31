package com.programm.projects.easy2d.ui.wave.core.reactive;

import com.programm.libraries.reactiveproperties.ObservableValue;
import com.programm.libraries.reactiveproperties.core.StringProperty;
import com.programm.projects.easy2d.ui.wave.core.GlobalWaveDefaults;

public class UIStringProperty extends StringProperty {

    private final ObservableValue<String> optionals;
    private String value;

    public UIStringProperty(Class<?> componentClass, String propertyName) {
        optionals = GlobalWaveDefaults.getOptionalExpression(componentClass, propertyName);
    }

    @Override
    protected void setDirectly(String val) {
        value = val;
    }

    @Override
    public String get() {
        if(value == null) return optionals.get();
        return value;
    }
}
