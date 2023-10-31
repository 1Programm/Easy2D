package com.programm.projects.easy2d.objects.api.components.shape;

import com.programm.projects.plus.maths.Vector2f;

public class Circle extends Shape {

    public float radius;

    public Circle(Vector2f position, float radius) {
        super(position);
        this.radius = radius;
    }

    public Circle(float x, float y, float radius) {
        this(new Vector2f(x, y), radius);
    }

    public Circle(float radius){
        this(0, 0, radius);
    }

    public Circle(){
        this(0.5f);
    }
}
