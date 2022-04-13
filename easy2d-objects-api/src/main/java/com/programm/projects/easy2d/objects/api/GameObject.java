package com.programm.projects.easy2d.objects.api;

import com.programm.projects.plus.maths.Vector2f;

public abstract class GameObject implements IGameObject {

    protected final Vector2f pos;
    private boolean dead = false;

    public GameObject(Vector2f pos) {
        this.pos = pos;
    }

    public GameObject(float x, float y){
        this.pos = new Vector2f(x, y);
    }

    @Override
    public boolean dead() {
        return dead;
    }

    public void die(){
        dead = true;
    }

    @Override
    public Vector2f pos() {
        return pos;
    }

}
