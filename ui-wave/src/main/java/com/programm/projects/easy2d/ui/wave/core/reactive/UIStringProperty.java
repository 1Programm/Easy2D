package com.programm.projects.easy2d.ui.wave.core.reactive;

import com.programm.libraries.reactiveproperties.ObservableValue;
import com.programm.libraries.reactiveproperties.core.StringProperty;
import com.programm.projects.easy2d.ui.wave.core.GlobalWaveDefaults;
import com.programm.projects.easy2d.ui.wave.core.WaveComponent;

public class UIStringProperty extends StringProperty implements IUIProperty {

    private final ObservableValue<String> optionals;
    private String value;

    private WaveComponent component = null;
    private boolean recalculate = false;

    public UIStringProperty(Class<?> componentClass, String propertyName) {
        optionals = GlobalWaveDefaults.getOptionalExpression(componentClass, propertyName);
    }

    @Override
    protected void setDirectly(String val) {
        value = val;
        if(component != null) {
            if(recalculate) component.requestRecalculate();
            else component.requestRedraw();
        }
    }

    @Override
    public String get() {
        if(value == null) return optionals.get();
        return value;
    }

    @Override
    public UIStringProperty redraw(WaveComponent c) {
        component = c;
        return this;
    }

    @Override
    public UIStringProperty recalculate(WaveComponent c) {
        component = c;
        recalculate = true;
        return this;
    }
}
