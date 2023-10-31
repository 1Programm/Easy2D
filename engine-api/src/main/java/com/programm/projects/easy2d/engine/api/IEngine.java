package com.programm.projects.easy2d.engine.api;

public interface IEngine {

    void setEngineOut(ILogger logger);

    void update();

    void render(IPencil pencil);

}
