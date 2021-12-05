package com.programm.project.easy2d.api;

public interface IEngine {

    void setEngineOut(ILogger logger);

    void update();

    void render(IPencil pencil);

    ILogger logger();

}
