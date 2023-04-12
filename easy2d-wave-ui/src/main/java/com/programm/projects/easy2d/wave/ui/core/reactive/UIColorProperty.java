package com.programm.projects.easy2d.wave.ui.core.reactive;

import com.programm.libraries.reactiveproperties.ObservableValue;
import com.programm.libraries.reactiveproperties.core.ObjectProperty;
import com.programm.projects.easy2d.wave.ui.core.GlobalWaveDefaults;

import java.awt.*;

public class UIColorProperty extends ObjectProperty<Color> {

    private final ObservableValue<Color> optionals;
    private Color value;

    public UIColorProperty(Class<?> componentClass, String propertyName) {
        optionals = GlobalWaveDefaults.getOptionalExpression(componentClass, propertyName);
    }

    @Override
    protected void setDirectly(Color val) {
        value = val;
    }

    @Override
    public Color get() {
        if(value == null) return optionals.get();
        return value;
    }
}
