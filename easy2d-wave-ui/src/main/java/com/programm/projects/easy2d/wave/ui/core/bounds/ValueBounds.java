package com.programm.projects.easy2d.wave.ui.core.bounds;

public class ValueBounds implements IEditableBounds {

    private float x, y, width, height;

    public ValueBounds(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public ValueBounds(){
        this(0, 0, 0, 0);
    }

    public ValueBounds(IBounds bounds) {
        this(bounds.x(), bounds.y(), bounds.width(), bounds.height());
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
    public float width() {
        return width;
    }

    @Override
    public float height() {
        return height;
    }

    @Override
    public void x(float x) {
        this.x = x;
    }

    @Override
    public void y(float y) {
        this.y = y;
    }

    @Override
    public void width(float width) {
        this.width = width;
    }

    @Override
    public void height(float height) {
        this.height = height;
    }
}
