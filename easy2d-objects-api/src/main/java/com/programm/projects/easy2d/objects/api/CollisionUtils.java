package com.programm.projects.easy2d.objects.api;

import com.programm.projects.plus.maths.Vector2f;

public class CollisionUtils {

    private static class CollisionInfoImpl implements ICollisionInfo {

        private final IShape collisionPartner;
        private final float intersectionDist;
        private final Vector2f resVector;

        public CollisionInfoImpl(IShape collisionPartner, float intersectionDist, Vector2f resVector) {
            this.collisionPartner = collisionPartner;
            this.intersectionDist = intersectionDist;
            this.resVector = resVector;
        }

        @Override
        public IShape collisionPartner() {
            return collisionPartner;
        }

        @Override
        public float intersectionDistance() {
            return intersectionDist;
        }

        @Override
        public Vector2f resolutionVector() {
            return resVector;
        }
    }

    public static ICollisionInfo checkCollision(IShape a, IShape b){
        if(a instanceof IRect){
            IRect rectA = (IRect) a;
            if(b instanceof IRect){
                IRect rectB = (IRect) b;

                if(rectA.intersects(rectB)) {
                    float distX = rectB.x() - rectA.x();
                    float distY = rectB.y() - rectA.y();

                    float absX = Math.abs(distX);
                    float absY = Math.abs(distY);

                    float resX, resY;
                    float resDist;
                    if(absX > absY){
                        resY = 0;
                        if(distX >= 0){
                            resDist = rectA.width() - absX;
                            resX = -resDist;
                        }
                        else {
                            resDist = rectB.width() - absX;
                            resX = resDist;
                        }
                    }
                    else {
                        resX = 0;
                        if(distY >= 0){
                            resDist = rectA.height() - absY;
                            resY = -resDist;
                        }
                        else {
                            resDist = rectB.height() - absY;
                            resY = resDist;
                        }
                    }

                    return new CollisionInfoImpl(b, resDist, new Vector2f(resX, resY));
                }
            }
        }

        return null;
    }

}
