package com.programm.projects.easy2d.objects.api.components.shape;

import com.programm.projects.plus.maths.Vector2f;

public class Line extends Shape {

    public final Vector2f start;
    public final Vector2f end;

    public Line(Vector2f position, Vector2f start, Vector2f end) {
        super(position);
        this.start = start;
        this.end = end;
    }
}
