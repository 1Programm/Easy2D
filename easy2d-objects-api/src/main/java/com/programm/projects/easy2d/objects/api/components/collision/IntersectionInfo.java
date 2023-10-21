package com.programm.projects.easy2d.objects.api.components.collision;

import com.programm.projects.plus.maths.Vector2f;

public class IntersectionInfo {

    private boolean collision;
    public Collider collisionPartner;
    public float intersectionDistance;

    public final Vector2f resolution = new Vector2f();

    public void reset(){
        if(collision) {
            set(false, 0, 0, 0);
            collisionPartner = null;
        }
    }

    private void set(boolean collision, float intersectionDistance, float resX, float resY) {
        this.collision = collision;
        this.intersectionDistance = intersectionDistance;
        this.resolution.set(resX, resY);
    }

    public void set(float intersectionDistance, float resX, float resY) {
        set(true, intersectionDistance, resX, resY);
    }

    public boolean collision(){
        return collision;
    }

    @Override
    public String toString() {
        return collision ?
                ("Intersection: " + intersectionDistance + ", Resolution: " + resolution + ")") :
                ("No Collision");
    }
}
