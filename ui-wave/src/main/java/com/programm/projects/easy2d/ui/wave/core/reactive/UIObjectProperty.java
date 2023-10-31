package com.programm.projects.easy2d.ui.wave.core.reactive;

import com.programm.libraries.reactiveproperties.ObservableValue;
import com.programm.libraries.reactiveproperties.core.ObjectProperty;
import com.programm.projects.easy2d.ui.wave.core.GlobalWaveDefaults;
import com.programm.projects.easy2d.ui.wave.core.WaveComponent;

public class UIObjectProperty <T> extends ObjectProperty<T> implements IUIProperty {

    private final ObservableValue<T> optionals;
    private T value;

    private WaveComponent component = null;
    private boolean recalculate = false;

    public UIObjectProperty(Class<?> componentClass, String propertyName) {
        optionals = GlobalWaveDefaults.getOptionalExpression(componentClass, propertyName);
    }

    @Override
    protected void setDirectly(T val) {
        value = val;
        if(component != null) {
            if(recalculate) component.requestRecalculate();
            else component.requestRedraw();
        }
    }

    @Override
    public T get() {
        if(value == null) return optionals.get();
        return value;
    }

    @Override
    public UIObjectProperty redraw(WaveComponent c) {
        component = c;
        return this;
    }

    @Override
    public UIObjectProperty recalculate(WaveComponent c) {
        component = c;
        recalculate = true;
        return this;
    }
}
