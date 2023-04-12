package com.programm.projects.easy2d.wave.ui.core;

import com.programm.libraries.reactiveproperties.core.BoolProperty;
import com.programm.libraries.reactiveproperties.core.ObjectProperty;
import com.programm.project.easy2d.engine.api.IPencil;
import com.programm.projects.easy2d.wave.ui.core.bounds.IBounds;

import java.awt.*;

public interface IWaveComponent {

    void render(IBounds bounds, IPencil pen, boolean forceRedraw);

    Float minWidth(IPencil pen);
    Float minHeight(IPencil pen);



    ObjectProperty<Color> primary();
    ObjectProperty<Color> secondary();
    ObjectProperty<Color> disabledColor();

    BoolProperty visible();
    BoolProperty disabled();





    static void drawBounds(IPencil pen, IBounds bounds){
        pen.drawRectangle(bounds.x(), bounds.y(), bounds.width(), bounds.height());
    }

    static void fillBounds(IPencil pen, IBounds bounds){
        pen.fillRectangle(bounds.x(), bounds.y(), bounds.width(), bounds.height());
    }

}
