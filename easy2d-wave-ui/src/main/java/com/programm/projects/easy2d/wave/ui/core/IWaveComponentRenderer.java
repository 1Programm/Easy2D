package com.programm.projects.easy2d.wave.ui.core;

import com.programm.project.easy2d.engine.api.IPencil;
import com.programm.projects.easy2d.wave.ui.core.bounds.IBounds;

public interface IWaveComponentRenderer<T extends WaveComponent> {

    void render(IBounds bounds, IPencil pen, T c);

}
