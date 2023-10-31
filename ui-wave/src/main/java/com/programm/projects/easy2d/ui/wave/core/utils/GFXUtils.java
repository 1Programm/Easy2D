package com.programm.projects.easy2d.ui.wave.core.utils;

import com.programm.projects.easy2d.ui.wave.core.WaveComponent;

import java.awt.*;

public class GFXUtils {

    public static Color mixColor(Color base, Color other, boolean toggle){
        return (toggle && other != null) ? other : base;
    }

    public static Color mixColor(Color base, Color o1, boolean t1, Color o2, boolean t2){
        if(t1 && o1 != null) return o1;
        if(t2 && o2 != null) return o2;
        return base;
    }

    public static Color primaryOr(WaveComponent component, Color other, boolean toggle){
        return mixColor(component.primary().get(), other, toggle);
    }

    public static Color primaryOrDisabled(WaveComponent component){
        return primaryOr(component, component.disabledColor().get(), component.disabled().get());
    }

    public static Color secondaryOr(WaveComponent component, Color other, boolean toggle){
        return mixColor(component.secondary().get(), other, toggle);
    }

    public static Color secondaryOrDisabled(WaveComponent component){
        return secondaryOr(component, component.disabledColor().get(), component.disabled().get());
    }

}
