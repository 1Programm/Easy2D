package com.programm.projects.easy2d.ui.wave.core.reactive;

import com.programm.libraries.reactiveproperties.ObservableValue;
import com.programm.libraries.reactiveproperties.core.IntProperty;
import com.programm.projects.easy2d.ui.wave.core.GlobalWaveDefaults;
import com.programm.projects.easy2d.ui.wave.core.WaveComponent;

public class UIIntProperty extends IntProperty implements IUIProperty {

    private final ObservableValue<Integer> optionals;
    private Integer value;

    private WaveComponent component = null;
    private boolean recalculate = false;

    public UIIntProperty(Class<?> componentClass, String propertyName) {
        optionals = GlobalWaveDefaults.getOptionalExpression(componentClass, propertyName);
    }

    @Override
    protected void setDirectly(Integer val) {
        value = val;
        if(component != null) {
            if(recalculate) component.requestRecalculate();
            else component.requestRedraw();
        }
    }

    @Override
    public Integer get() {
        if(value == null) return optionals.get();
        return value;
    }

    @Override
    public UIIntProperty redraw(WaveComponent c) {
        component = c;
        return this;
    }

    @Override
    public UIIntProperty recalculate(WaveComponent c) {
        component = c;
        recalculate = true;
        return this;
    }
}
