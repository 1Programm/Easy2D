package com.programm.projects.easy2d.objects.api;

import com.programm.projects.plus.maths.Vector2f;

public interface IShape {

    Vector2f pos();

    default float x(){
        return pos().getX();
    }

    default float y(){
        return pos().getY();
    }

    ICollisionInfo checkCollision(IShape other);

}
