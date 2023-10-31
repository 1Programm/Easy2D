package com.programm.projects.easy2d.objects.api;

import com.programm.projects.easy2d.objects.api.components.collision.IntersectionInfo;
import com.programm.projects.easy2d.objects.api.components.collision.RayCastInfo;
import com.programm.projects.easy2d.objects.api.components.shape.Shape;
import com.programm.projects.easy2d.objects.api.utils.MathUtils;
import com.programm.projects.plus.maths.Vector2f;

public interface ICollisionTester {

    void setIgnoreCollisionObject(GameObject o);

    void rayCastFindFirstObject(RayCastInfo info, Vector2f pos, Vector2f vel);

    void testAndSolveCollision(Shape shape, Vector2f pos, Vector2f scale, boolean fixed, float mass);

    void testCollision(IntersectionInfo info, Shape shape, Vector2f pos, Vector2f scale);

    default void testCollision(IntersectionInfo info, Shape shape, Vector2f pos){
        testCollision(info, shape, pos, MathUtils.VEC2_UNIT);
    }

}
