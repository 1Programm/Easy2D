package com.programm.projects.easy2d.objects.api.components;

import com.programm.project.easy2d.engine.api.IPencil;

public interface IRenderableComponent extends IComponent {

    void render(IPencil pen);

    @Override
    default boolean canHaveMultiple() {
        return true;
    }
}
