package com.programm.projects.easy2d.engine.simple;

import com.programm.project.easy2d.engine.api.ILogger;
import com.programm.project.easy2d.engine.api.IMouse;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SimpleMouse extends MouseAdapter implements IMouse {

    private final ILogger logger;
    private float x, y;
    private boolean left, mid, right;

    public SimpleMouse(ILogger logger) {
        this.logger = logger;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        setButton(e.getButton(), true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        setButton(e.getButton(), false);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    private void setButton(int btn, boolean state){
        if(btn == 1){
            left = state;
        }
        else if(btn == 2){
            mid = state;
        }
        else if(btn == 3){
            right = state;
        }
        else {
            logger.error("Unknown mouse button: [" + btn + "]!");
        }
    }

    @Override
    public float x() {
        return x;
    }

    @Override
    public float y() {
        return y;
    }

    @Override
    public boolean leftPressed() {
        return left;
    }

    @Override
    public boolean midPressed() {
        return mid;
    }

    @Override
    public boolean rightPressed() {
        return right;
    }
}
