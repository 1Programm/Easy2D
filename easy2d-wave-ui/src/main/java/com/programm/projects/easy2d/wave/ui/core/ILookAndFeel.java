package com.programm.projects.easy2d.wave.ui.core;

public interface ILookAndFeel {

    <T extends WaveComponent> IWaveComponentRenderer<T> renderer(Class<?> cls);

}
