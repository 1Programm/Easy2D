package com.programm.projects.easy2d.wave.ui.core;

public class GlobalLookAndFeel {

    private static ILookAndFeel lookAndFeel;

    public static void setLookAndFeel(ILookAndFeel lookAndFeel) {
        GlobalLookAndFeel.lookAndFeel = lookAndFeel;
    }

    public static ILookAndFeel getLookAndFeel(){
        return lookAndFeel;
    }

    public static <T extends WaveComponent> IWaveComponentRenderer<T> getRendererForComponent(T component){
        Class<?> cls = component.getClass();
        IWaveComponentRenderer<T> renderer = null;

        if(lookAndFeel != null){
            renderer = lookAndFeel.renderer(cls);
        }

        if(renderer == null){
            renderer = GlobalWaveDefaults.getDefault(cls, "renderer");
        }

        if(renderer == null) throw new IllegalStateException("No renderer defined for class: [" + cls + "]!");

        return renderer;
    }

}
