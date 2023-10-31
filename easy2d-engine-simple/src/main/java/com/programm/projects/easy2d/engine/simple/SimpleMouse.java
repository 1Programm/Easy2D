package com.programm.projects.easy2d.engine.simple;

import com.programm.project.easy2d.engine.api.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.List;

public class SimpleMouse extends MouseAdapter implements IMouse {

    private final ILogger logger;
    private float x, y;
    private boolean left, mid, right;

    private final List<IMouseListener> mousePressedListeners = new ArrayList<>();
    private final List<IMouseListener> mouseReleasedListeners = new ArrayList<>();
    private final List<IMouseMoveListener> mouseMovedListeners = new ArrayList<>();
    private final List<IMouseDragListener> mouseDraggedListeners = new ArrayList<>();
    private final List<IMouseScrollListener> mouseScrolledListeners = new ArrayList<>();

    public SimpleMouse(ILogger logger) {
        this.logger = logger;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int btn = e.getButton();
        setButton(btn, true);

        notify(mousePressedListeners, btn);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int btn = e.getButton();
        setButton(btn, false);

        notify(mouseReleasedListeners, btn);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x = e.getX();
        y = e.getY();

        notify(mouseDraggedListeners, e.getButton());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();

        notify(mouseMovedListeners);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        for(int i=0;i<mouseScrolledListeners.size();i++){
            //X scroll is not supported in java swing
            mouseScrolledListeners.get(i).action(this, 0, e.getScrollAmount() * e.getUnitsToScroll());
        }
    }

    private <T extends IMouseListener> void notify(List<T> list, int button){
        for(int i=0;i<list.size();i++){
            list.get(i).action(this, button);
        }
    }

    private void notify(List<IMouseMoveListener> list){
        for(int i=0;i<list.size();i++){
            list.get(i).action(this);
        }
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

    @Override
    public Subscription onMousePressed(IMouseListener listener) {
        mousePressedListeners.add(listener);
        return new SimpleListSubscription(mousePressedListeners, listener);
    }

    @Override
    public Subscription onMouseReleased(IMouseListener listener) {
        mouseReleasedListeners.add(listener);
        return new SimpleListSubscription(mouseReleasedListeners, listener);
    }

    @Override
    public Subscription onMouseMoved(IMouseMoveListener listener) {
        mouseMovedListeners.add(listener);
        return new SimpleListSubscription(mouseMovedListeners, listener);
    }

    @Override
    public Subscription onMouseDragged(IMouseDragListener listener) {
        mouseDraggedListeners.add(listener);
        return new SimpleListSubscription(mouseDraggedListeners, listener);
    }

    @Override
    public Subscription onMouseScrolled(IMouseScrollListener listener) {
        mouseScrolledListeners.add(listener);
        return new SimpleListSubscription(mouseScrolledListeners, listener);
    }
}
