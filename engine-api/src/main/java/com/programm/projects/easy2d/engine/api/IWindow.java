package com.programm.projects.easy2d.engine.api;

public interface IWindow {

    void init(IEngine engine, IKeyboard keyboard, IMouse mouse);

    void show();
    void close();
    void draw();

    void setTitle(String title);
    void setResizable(boolean resizable);
    void moveToScreen(int screen);

    boolean isCloseRequested();

    int x();
    IWindow x(int x);

    int y();
    IWindow y(int y);

    int width();
    IWindow width(int width);

    int height();
    IWindow height(int height);

    default IWindow position(int x, int y){
        return x(x).y(y);
    }

    default IWindow size(int width, int height) {
        return width(width).height(height);
    }

    default IWindow bounds(int x, int y, int width, int height) {
        return position(x, y).size(width, height);
    }

    Subscription onWindowResized(IWindowResizeListener listener);

}
