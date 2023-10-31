package com.programm.projects.easy2d.wave.ui.core;

import com.programm.libraries.reactiveproperties.core.BoolProperty;
import com.programm.project.easy2d.engine.api.IPencil;

public class GlobalComponentUtils {

    public static boolean isDirty(WaveComponent component){
        return component.dirty;
    }

    public static BoolProperty shouldRecalculate(WaveComponent component){
        return component.shouldRecalculate;
    }

    public static boolean shouldBeDeleted(WaveComponent component){
        return component.shouldBeDeleted;
    }

//    public static boolean shouldRecalculate(List<WaveComponent> components){
//        for(int i=0;i<components.size();i++){
//            if(components.get(i).shouldRecalculate) return true;
//        }
//
//        return false;
//    }

    public static void setDirty(WaveComponent component, boolean dirty) {
        component.dirty = dirty;
    }

//    public static void setShouldRecalculate(WaveComponent component, boolean recalculate){
//        component.shouldRecalculate = recalculate;
//    }

    public static boolean xUntouched(WaveComponent component) {
        return component.xUntouched;
    }

    public static boolean yUntouched(WaveComponent component) {
        return component.yUntouched;
    }

    public static boolean widthUntouched(WaveComponent component) {
        return component.widthUntouched;
    }

    public static boolean heightUntouched(WaveComponent component) {
        return component.heightUntouched;
    }

    public static float getWidthConstrainedByMinMax(IPencil pen, WaveComponent component, float width){
        Float min = component.minWidth(pen);
        //Float max = component.maxWidth(pen);

        if(min != null){
            width = Math.max(width, min);
        }

//        if(max != null){
//            width = Math.min(width, max);
//        }

        return width;
    }

    public static float getWidthConstrainedByMinMax(IPencil pen, WaveComponent component){
        return getWidthConstrainedByMinMax(pen, component, component.width());
    }

    public static float getHeightConstrainedByMinMax(IPencil pen, WaveComponent component, float height){
        Float min = component.minHeight(pen);
        //Float max = component.maxHeight(pen);

        if(min != null){
            height = Math.max(height, min);
        }

//        if(max != null){
//            height = Math.min(height, max);
//        }

        return height;
    }

    public static float getHeightConstrainedByMinMax(IPencil pen, WaveComponent component){
        return getHeightConstrainedByMinMax(pen, component, component.height());
    }

    public static Float getWidthOrMinWidth(IPencil pen, WaveComponent component){
        if(widthUntouched(component)) return component.minWidth(pen);
        return component.width();
    }

    public static Float getHeightOrMinHeight(IPencil pen, WaveComponent component){
        if(heightUntouched(component)) return component.minHeight(pen);
        return component.height();
    }

    private GlobalComponentUtils(){}
}
