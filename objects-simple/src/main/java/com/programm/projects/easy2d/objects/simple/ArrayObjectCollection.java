package com.programm.projects.easy2d.objects.simple;

import com.programm.projects.easy2d.objects.api.GameObject;
import com.programm.projects.easy2d.objects.api.IObjectCollection;
import com.programm.projects.easy2d.objects.api.components.collision.CollisionHandler;
import com.programm.projects.easy2d.objects.api.components.collision.IntersectionInfo;
import com.programm.projects.easy2d.objects.api.components.collision.Collider;
import com.programm.projects.easy2d.objects.api.components.collision.RayCastInfo;
import com.programm.projects.easy2d.objects.api.components.shape.Shape;
import com.programm.projects.plus.maths.Vector2f;

import java.util.ArrayList;
import java.util.List;

public class ArrayObjectCollection implements IObjectCollection {

    private static void doCollision(IntersectionInfo info, Shape a, Vector2f aPos, Vector2f aScale, GameObject b){
        Collider bCollider = b.get(Collider.class);

        if(bCollider != null){
            Shape bShape = bCollider.shape;
            CollisionHandler.testIntersection(info,
            a, aPos.getX(), aPos.getY(), aScale.getX(), aScale.getY(),
            bShape, b.position.getX(), b.position.getY(), b.size.getX(), b.size.getY());

            if(info.collision()){
                info.collisionPartner = bCollider;
            }

//            if(a.canSolve(bCollider)){
//                a.solveCollision(info, aVel, bCollider, null);
//            }
//            else if(bCollider.canSolve(a)){
//                bCollider.solveCollision(info, null, a, aVel);
//            }
        }
    }

    protected class PartialObjectCollection implements IObjectCollection {
        private final List<Integer> objectIndices;

        public PartialObjectCollection(List<Integer> objectIndices) {
            this.objectIndices = objectIndices;
        }

        @Override
        public int size() {
            return objectIndices.size();
        }

        @Override
        public GameObject get(int i) {
            Integer index = objectIndices.get(i);
            if(index == null) return null;
            return objects.get(index);
        }

        @Override
        public void remove(int i) {
            Integer index = objectIndices.remove(i);
            objects.remove(index.intValue());
        }

        @Override
        public IObjectCollection getNearbys(Vector2f pos, float range) {
            return this;
        }

        @Override
        public void setIgnoreCollisionObject(GameObject o) {
            ArrayObjectCollection.this.setIgnoreCollisionObject(o);
        }

        @Override
        public void rayCastFindFirstObject(RayCastInfo info, Vector2f pos, Vector2f vel) {

        }

        @Override
        public void testAndSolveCollision(Shape shape, Vector2f pos, Vector2f scale, boolean fixed, float mass) {

        }

        @Override
        public void testCollision(IntersectionInfo info, Shape shape, Vector2f pos, Vector2f scale) {
            for(Integer index : objectIndices){
                GameObject a = objects.get(index);
                doCollision(info, shape, pos, scale, a);
                if(info.collision()) return;
            }
        }
    }

    private static final IntersectionInfo INTERSECTION_INFO = new IntersectionInfo();

    private final List<GameObject> objects = new ArrayList<>();
    private GameObject ignoreObjectForCollision;

    @Override
    public int size() {
        return objects.size();
    }

    @Override
    public GameObject get(int i) {
        return objects.get(i);
    }

    @Override
    public void remove(int i) {
        objects.remove(i);
    }

    public void add(GameObject obj){
        objects.add(obj);
    }

    @Override
    public PartialObjectCollection getNearbys(Vector2f pos, float range) {
        List<Integer> indices = new ArrayList<>();

        for(int i=0;i<objects.size();i++){
            GameObject obj = objects.get(i);

            float dist = obj.position.sub(pos).lengthSqrt();
            if(dist < range){
                indices.add(i);
            }
        }

        return new PartialObjectCollection(indices);
    }

    @Override
    public void setIgnoreCollisionObject(GameObject o) {
        this.ignoreObjectForCollision = o;
    }

    @Override
    public void rayCastFindFirstObject(RayCastInfo info, Vector2f pos, Vector2f vel) {

    }

    @Override
    public void testAndSolveCollision(Shape shape, Vector2f pos, Vector2f scale, boolean fixed, float mass) {
        INTERSECTION_INFO.reset();

        testCollision(INTERSECTION_INFO, shape, pos, scale);
        if(INTERSECTION_INFO.collision()) {
            CollisionHandler.solveCollision(INTERSECTION_INFO,
                    pos, fixed, mass,
                    INTERSECTION_INFO.collisionPartner.parentPosition(), INTERSECTION_INFO.collisionPartner.fixed, INTERSECTION_INFO.collisionPartner.mass);
        }
    }

    @Override
    public void testCollision(IntersectionInfo info, Shape shape, Vector2f pos, Vector2f scale) {
        for(int i=0;i<objects.size();i++){
            GameObject a = objects.get(i);
            if(a == ignoreObjectForCollision) continue;
            doCollision(info, shape, pos, scale, a);
            if(info.collision()) return;
        }
    }
}
