package com.programm.projects.easy2d.objects.api;

import com.programm.projects.plus.maths.Vector2f;

public abstract class Entity extends GameObject {

    protected final Vector2f velocity;

    public Entity(Vector2f pos, Vector2f velocity) {
        super(pos);
        this.velocity = velocity;
    }

    public Entity(float x, float y) {
        this(new Vector2f(x, y), new Vector2f());
    }

    public Entity(float x, float y, float vx, float vy) {
        this(new Vector2f(x, y), new Vector2f(vx, vy));
    }

    @Override
    public void update(IObjectContext ctx) {
        if(velocity.getX() != 0 || velocity.getY() != 0) {
            this.pos.add(velocity);

            ICollisionInfo info = ctx.objects().checkCollision(this);

            if (info != null) {
                Vector2f resolution = info.resolutionVector();
                this.pos.add(resolution);
            }
        }
    }

    public Vector2f velocity(){
        return velocity;
    }

}
