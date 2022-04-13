package com.programm.projects.easy2d.objects.api;

import com.programm.projects.plus.maths.Vector2f;

public interface ICollisionInfo {

    IShape collisionPartner();

    float intersectionDistance();

    Vector2f resolutionVector();

}
