package com.programm.projects.easy2d.ui.wave.look.smooth;

import com.programm.projects.easy2d.ui.wave.core.GlobalWaveDefaults;
import com.programm.projects.easy2d.ui.wave.core.ILookAndFeel;
import com.programm.projects.easy2d.ui.wave.core.IWaveComponentRenderer;
import com.programm.projects.easy2d.ui.wave.core.WaveComponent;
import com.programm.projects.easy2d.ui.wave.elements.Button;
import com.programm.projects.easy2d.ui.wave.elements.Checkbox;
import com.programm.projects.easy2d.ui.wave.elements.Combobox;
import com.programm.projects.easy2d.ui.wave.elements.Label;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class LookAndFeelSmooth implements ILookAndFeel {

    static final Color COLOR_BACKGROUND_WHITE = new Color(255, 255, 255);
    static final Color COLOR_BACKGROUND_HOVER = new Color(240, 240, 240);
    static final Color COLOR_BACKGROUND_PRESSED = new Color(230, 230, 235);

    static final Color COLOR_BORDER_BLUE = new Color(88, 180, 210);

    static void overrideBaseDefault(Class<?> cls, String name, Object defaultValue){
        GlobalWaveDefaults.setDefault(cls, name, defaultValue, 1);
    }

    private static final Map<Class<?>, IWaveComponentRenderer<?>> RENDERERS = new HashMap<>();

    static {
        RENDERERS.put(Button.class, new SmoothRenderer_Button(10));
        RENDERERS.put(Checkbox.class, new SmoothRenderer_Checkbox(7));
        RENDERERS.put(Combobox.class, new SmoothRenderer_Combobox(10));
        RENDERERS.put(Label.class, new SmoothRenderer_Label(10));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends WaveComponent> IWaveComponentRenderer<T> renderer(Class<?> cls) {
        IWaveComponentRenderer<?> renderer = RENDERERS.get(cls);
        if(renderer == null) return null;

        return (IWaveComponentRenderer<T>) renderer;
    }
}
