package com.programm.projects.easy2d.ui.wave.core.reactive;

import com.programm.libraries.reactiveproperties.ObservableValue;
import com.programm.libraries.reactiveproperties.core.BoolProperty;
import com.programm.projects.easy2d.ui.wave.core.GlobalWaveDefaults;
import com.programm.projects.easy2d.ui.wave.core.WaveComponent;

public class UIBoolProperty extends BoolProperty implements IUIProperty {

    private final ObservableValue<Boolean> optionals;
    private Boolean value;

    private WaveComponent component = null;
    private boolean recalculate = false;

    public UIBoolProperty(Class<?> componentClass, String propertyName) {
        optionals = GlobalWaveDefaults.getOptionalExpression(componentClass, propertyName);
    }

    @Override
    protected void setDirectly(Boolean val) {
        value = val;
        if(component != null) {
            if(recalculate) component.requestRecalculate();
            else component.requestRedraw();
        }
    }

    @Override
    public Boolean get() {
        if(value == null) return optionals.get();
        return value;
    }

    @Override
    public UIBoolProperty redraw(WaveComponent c) {
        component = c;
        return this;
    }

    @Override
    public UIBoolProperty recalculate(WaveComponent c) {
        component = c;
        recalculate = true;
        return this;
    }
}
