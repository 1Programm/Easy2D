package com.programm.projects.easy2d.ui.wave.core.reactive;

import com.programm.projects.easy2d.ui.wave.core.WaveComponent;

public interface IUIProperty {

    IUIProperty redraw(WaveComponent c);
    IUIProperty recalculate(WaveComponent c);

}
