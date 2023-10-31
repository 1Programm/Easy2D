package com.programm.projects.easy2d.ui.wave.core.reactive;

import com.programm.libraries.reactiveproperties.ObservableValue;
import com.programm.libraries.reactiveproperties.core.ObjectProperty;
import com.programm.projects.easy2d.ui.wave.core.GlobalWaveDefaults;
import com.programm.projects.easy2d.ui.wave.core.WaveComponent;

import java.awt.*;

public class UIColorProperty extends ObjectProperty<Color> implements IUIProperty {

    private final ObservableValue<Color> optionals;
    private Color value;

    private WaveComponent component = null;
    private boolean recalculate = false;

    public UIColorProperty(Class<?> componentClass, String propertyName) {
        optionals = GlobalWaveDefaults.getOptionalExpression(componentClass, propertyName);
    }

    @Override
    protected void setDirectly(Color val) {
        value = val;
        if(component != null) {
            if(recalculate) component.requestRecalculate();
            else component.requestRedraw();
        }
    }

    @Override
    public Color get() {
        if(value == null) return optionals.get();
        return value;
    }

    @Override
    public UIColorProperty redraw(WaveComponent c) {
        component = c;
        return this;
    }

    @Override
    public UIColorProperty recalculate(WaveComponent c) {
        component = c;
        recalculate = true;
        return this;
    }

}
