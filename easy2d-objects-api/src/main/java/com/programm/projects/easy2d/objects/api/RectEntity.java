package com.programm.projects.easy2d.objects.api;

import com.programm.project.easy2d.engine.api.IPencil;
import com.programm.projects.plus.maths.Vector2f;

public abstract class RectEntity extends Entity implements IRect {

    private final Vector2f size;

    public RectEntity(Vector2f pos, Vector2f velocity, Vector2f size) {
        super(pos, velocity);
        this.size = size;
    }

    public RectEntity(float x, float y, float w, float h) {
        this(new Vector2f(x, y), new Vector2f(), new Vector2f(w, h));
    }

    public RectEntity(float x, float y, float vx, float vy, float w, float h) {
        this(new Vector2f(x, y), new Vector2f(vx, vy), new Vector2f(w, h));
    }

    protected void renderFilled(IPencil pencil){
        float x = x();
        float y = y();

        pencil.fillRectangle(x, y, size.getX(), size.getY());
    }

    @Override
    public ICollisionInfo checkCollision(IShape other) {
        return CollisionUtils.checkCollision(this, other);
    }

    @Override
    public Vector2f size() {
        return size;
    }
}
