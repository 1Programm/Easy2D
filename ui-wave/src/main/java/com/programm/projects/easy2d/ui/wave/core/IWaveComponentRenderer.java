package com.programm.projects.easy2d.ui.wave.core;

import com.programm.projects.easy2d.engine.api.IPencil;
import com.programm.projects.easy2d.ui.wave.core.bounds.IBounds;

public interface IWaveComponentRenderer<T extends WaveComponent> {

    void render(IBounds bounds, IPencil pen, T c);

}
