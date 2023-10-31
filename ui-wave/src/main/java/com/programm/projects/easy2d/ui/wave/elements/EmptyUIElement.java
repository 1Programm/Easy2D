package com.programm.projects.easy2d.ui.wave.elements;

import com.programm.projects.easy2d.engine.api.IPencil;
import com.programm.projects.easy2d.ui.wave.core.GlobalWaveDefaults;
import com.programm.projects.easy2d.ui.wave.core.WaveComponent;
import com.programm.projects.easy2d.ui.wave.core.bounds.IBounds;

public class EmptyUIElement extends WaveComponent {

    static {
        GlobalWaveDefaults.setRootBaseDefault(EmptyUIElement.class, "primary", null);
        GlobalWaveDefaults.setRootBaseDefault(EmptyUIElement.class, "secondary", null);
        GlobalWaveDefaults.setRootBaseDefault(EmptyUIElement.class, "disabledColor", null);
        GlobalWaveDefaults.setRootBaseDefault(EmptyUIElement.class, "visible", true);
        GlobalWaveDefaults.setRootBaseDefault(EmptyUIElement.class, "disabled", false);
    }

    private IBounds bounds;

    @Override
    public void render(IBounds bounds, IPencil pen, boolean forceRedraw) {
        this.bounds = bounds;
    }

    public IBounds getDrawnBounds(){
        return bounds;
    }
}
