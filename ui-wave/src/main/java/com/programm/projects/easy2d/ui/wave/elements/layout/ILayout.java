package com.programm.projects.easy2d.ui.wave.elements.layout;

import com.programm.projects.easy2d.engine.api.IPencil;
import com.programm.projects.easy2d.ui.wave.core.WaveComponent;
import com.programm.projects.easy2d.ui.wave.core.bounds.IBounds;
import com.programm.projects.easy2d.ui.wave.core.bounds.IEditableBounds;

import java.util.List;

public interface ILayout {

    int ALIGN_TOP           = 0;
    int ALIGN_LEFT          = 1;
    int ALIGN_BOTTOM        = 2;
    int ALIGN_RIGHT         = 3;
    int ALIGN_CENTER        = 4;

    void updateBoundsForChildren(IPencil pen, IBounds parent, List<WaveComponent> children, List<Object> childArgs, List<IEditableBounds> bounds);

    float minWidth(IPencil pen, List<WaveComponent> children, List<Object> childArgs);
    float minHeight(IPencil pen, List<WaveComponent> children, List<Object> childArgs);

}
