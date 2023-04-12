package com.programm.project.easy2d.engine.api;

public interface IWindow {

    void init(IEngine engine, IKeyboard keyboard, IMouse mouse);

    void show();
    void close();
    void draw();

    void setTitle(String title);
    void setResizable(boolean resizable);

    boolean isCloseRequested();
    int width();
    int height();

}
