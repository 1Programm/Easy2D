package com.programm.projects.easy2d.objects.api.components;

import com.programm.projects.easy2d.objects.api.components.collision.Collider;
import com.programm.projects.plus.maths.Vector2f;

public class Mover extends AbstractComponent implements IUpdatableComponent {

    private final Vector2f velocity = new Vector2f();
//    private final CollisionInfo collisionInfo = new CollisionInfo();
//    private final IntersectionInfo intersectionInfo = new IntersectionInfo();

    @Override
    public void update(){
        if(velocity.getX() != 0 || velocity.getY() != 0) {
            Collider collider = gameObject.get(Collider.class);
            gameObject.position.add(velocity);
            if(collider != null){

                objectHandler.setIgnoreCollisionObject(gameObject);
                objectHandler.testAndSolveCollision(collider.shape, gameObject.position, gameObject.size, collider.fixed, collider.mass);
                objectHandler.setIgnoreCollisionObject(null);

//                objectHandler.solveCollision(intersectionInfo);
            }

            velocity.set(0, 0);
        }
    }

    public void move(float vx, float vy){
        velocity.add(vx, vy);
    }

    public void move(Vector2f vel){
        move(vel.getX(), vel.getY());
    }

}
