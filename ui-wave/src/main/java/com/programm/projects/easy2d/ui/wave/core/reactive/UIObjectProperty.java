package com.programm.projects.easy2d.ui.wave.core.reactive;

import com.programm.libraries.reactiveproperties.ObservableValue;
import com.programm.libraries.reactiveproperties.core.ObjectProperty;
import com.programm.projects.easy2d.ui.wave.core.GlobalWaveDefaults;

public class UIObjectProperty <T> extends ObjectProperty<T> {

    private final ObservableValue<T> optionals;
    private T value;

    public UIObjectProperty(Class<?> componentClass, String propertyName) {
        optionals = GlobalWaveDefaults.getOptionalExpression(componentClass, propertyName);
    }

    @Override
    protected void setDirectly(T val) {
        value = val;
    }

    @Override
    public T get() {
        if(value == null) return optionals.get();
        return value;
    }
}
