package com.programm.project.easy2d.engine.api;

public interface IMouse {

    float x();
    float y();

    boolean leftPressed();
    boolean midPressed();
    boolean rightPressed();

    void onMousePressed(Runnable runnable);
    void onMouseReleased(Runnable runnable);

}
