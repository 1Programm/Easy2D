package com.programm.projects.easy2d.objects.api;

import com.programm.project.easy2d.engine.api.IPencil;

public interface IGameObject extends IShape {

    void update(IObjectContext ctx);
    void render(IPencil pencil);

    boolean dead();

}
