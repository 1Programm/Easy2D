package com.programm.projects.easy2d.objects.api.components.shape;

import com.programm.projects.plus.maths.Vector2f;

public class Rect extends Shape {

    public final Vector2f size;

    public Rect(Vector2f position, Vector2f size) {
        super(position);
        this.size = size;
    }

    public Rect(float x, float y, float w, float h) {
        this(new Vector2f(x, y), new Vector2f(w, h));
    }

    public Rect(float w, float h) {
        this(0, 0, w, h);
    }

    public Rect() {
        this(1, 1);
    }

}
