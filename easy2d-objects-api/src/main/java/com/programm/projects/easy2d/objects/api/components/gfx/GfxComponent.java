package com.programm.projects.easy2d.objects.api.components.gfx;

import com.programm.projects.easy2d.objects.api.components.AbstractComponent;
import com.programm.projects.easy2d.objects.api.components.IRenderableComponent;

public abstract class GfxComponent extends AbstractComponent implements IRenderableComponent {

    @Override
    public boolean canHaveMultiple() {
        return true;
    }
}
