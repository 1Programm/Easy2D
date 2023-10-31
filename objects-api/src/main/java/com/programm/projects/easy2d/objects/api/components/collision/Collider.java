package com.programm.projects.easy2d.objects.api.components.collision;

import com.programm.projects.easy2d.objects.api.components.AbstractComponent;
import com.programm.projects.easy2d.objects.api.components.shape.Shape;
import com.programm.projects.plus.maths.Vector2f;

public class Collider extends AbstractComponent {

    public boolean fixed = false;
    public float mass = 1;

    public final Shape shape;

    public Collider(Shape shape) {
        this.shape = shape;
    }

    public Vector2f parentPosition(){
        return gameObject.position;
    }

    public Collider fixed(boolean fixed){
        this.fixed = fixed;
        return this;
    }

    public Collider mass(float mass){
        this.mass = mass;
        return this;
    }

}
