package com.programm.projects.easy2d.ui.wave.core;

public interface ILookAndFeel {

    <T extends WaveComponent> IWaveComponentRenderer<T> renderer(Class<?> cls);

}
