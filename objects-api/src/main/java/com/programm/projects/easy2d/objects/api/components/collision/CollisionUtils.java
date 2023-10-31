package com.programm.projects.easy2d.objects.api.components.collision;

import com.programm.projects.plus.maths.Vector2f;

public class CollisionUtils {

//    public static boolean intersects(RectCollider a, RectCollider b){
//        return a.x() + a.width()  > b.x() && a.x() <= b.x() + b.width()
//            && a.y() + a.height() > b.y() && a.y() <= b.y() + b.height();
//    }
//
//    private static final Vector2f NULL_VEC = new Vector2f();
//
//    public static void testCollision(CollisionInfo info, Collider a, Vector2f aVel, Collider b, Vector2f bVel){
//        if(aVel == null) aVel = NULL_VEC;
//        if(bVel == null) bVel = NULL_VEC;
//
//        if(a instanceof RectCollider){
//            RectCollider rectA = (RectCollider) a;
//            if(b instanceof RectCollider){
//                RectCollider rectB = (RectCollider) b;
//                collision_r_r(info, rectA, aVel, rectB, bVel);
//                return;
//            }
//            else if(b instanceof CircleCollider){
//                CircleCollider circleB = (CircleCollider) b;
//                collision_r_c(info, rectA, aVel, circleB, bVel);
//                return;
//            }
//        }
//        else if(a instanceof CircleCollider){
//            CircleCollider circleA = (CircleCollider) a;
//            if(b instanceof RectCollider){
//                RectCollider rectB = (RectCollider) b;
//                collision_c_r(info, circleA, aVel, rectB, bVel);
//                return;
//            }
//            else if(b instanceof CircleCollider){
//                CircleCollider circleB = (CircleCollider) b;
////                collision_c_c(info, circleA, aVel, circleB, bVel);
//                return;
//            }
//        }
//
//        throw new IllegalStateException("Could not resolve collision for unknown colliders [" + a + ", " + b + "]");
//    }
//
//    public static void collision_r_r(CollisionInfo info, RectCollider a, Vector2f aVel, RectCollider b, Vector2f bVel){
//        if(aVel == null) aVel = NULL_VEC;
//        if(bVel == null) bVel = NULL_VEC;
//
//        float aw = a.width();
//        float ah = a.height();
//        float ax = a.x() + aVel.getX() - aw / 2f;
//        float ay = a.y() + aVel.getY() - ah / 2f;
//        float bw = b.width();
//        float bh = b.height();
//        float bx = b.x() + bVel.getX() - bw / 2f;
//        float by = b.y() + bVel.getY() - bh / 2f;
//
//        float distX = bx - ax;
//        float distY = by - ay;
//
//        float solveX;
//        float solveY;
//
//        if(distX >= 0){
//            if(distX >= aw) return;
//            solveX = bx - (ax + aw);
//        }
//        else {
//            if(distX * -1 >= bw) return;
//            solveX = (bx + bw) - ax;
//        }
//
//        if(distY >= 0){
//            if(distY >= ah) return;
//            solveY = by - (ay + ah);
//        }
//        else {
//            if(distY * -1 >= bh) return;
//            solveY = (by + bh) - ay;
//        }
//
//
//        float solveXAbs = Math.abs(solveX);
//        float solveYAbs = Math.abs(solveY);
//        float intersectionDist;
//        if(solveXAbs < solveYAbs){
//            solveY = 0;
//            intersectionDist = solveXAbs;
//        }
//        else {
//            solveX = 0;
//            intersectionDist = solveYAbs;
//        }
//
//        calculateResolution(info, intersectionDist, solveX, solveY, a, b);
////        float deltaB = a.mass / (a.mass + b.mass);
////        float deltaA = 1 - deltaB;
////
////        float resAX = solveX * deltaA;
////        float resAY = solveY * deltaA;
////
////        float resBX = -solveX * deltaB;
////        float resBY = -solveY * deltaB;
////
////        info.set(intersectionDist, a, resAX, resAY, b, resBX, resBY);
//    }
//
//    public static void collision_r_c(CollisionInfo info, RectCollider a, Vector2f aVel, CircleCollider b, Vector2f bVel){
//        if(aVel == null) aVel = NULL_VEC;
//        if(bVel == null) bVel = NULL_VEC;
//
//        float aw = a.width();
//        float ah = a.height();
//        float ax = a.x() + aVel.getX() - aw / 2f;
//        float ay = a.y() + aVel.getY() - ah / 2f;
//        float bx = b.x() + bVel.getX();
//        float by = b.y() + bVel.getY();
//        float br = b.radius();
//
//        float px = Math.max(ax, Math.min(ax + aw, bx));
//        float py = Math.max(ay, Math.min(ay + ah, by));
//
//        float distX = px - bx;
//        float distY = py - by;
//        float distSqare = distX * distX + distY * distY;
//        if(distSqare > br * br) return;
//
//        if(distSqare == 0) {
//            //We are inside the rectangle with the circle
//            return;
//        }
//
//        float distSqrt = (float)Math.sqrt(distSqare);
//        float nDistX = (distX / distSqrt) * br;
//        float nDistY = (distY / distSqrt) * br;
//        float resX = nDistX - distX;
//        float resY = nDistY - distY;
//
//        float intersectionDist = br - distSqrt;
//
//        calculateResolution(info, intersectionDist, resX, resY, a, b);
////        info.set(b, br - distSqrt, resX, resY);
//    }
//
//    public static void collision_c_r(CollisionInfo info, CircleCollider a, Vector2f aVel, RectCollider b, Vector2f bVel){
////        if(aVel == null) aVel = NULL_VEC;
////        if(bVel == null) bVel = NULL_VEC;
//
//        collision_r_c(info, b, bVel, a, aVel);
////        if(info.collision()){
//////            info.collisionPartner = b;
//////            info.resolutionVector.mul(-1);
////        }
//    }
//
//    public static void collision_c_c(CollisionInfo info, CircleCollider a, Vector2f aVel, CircleCollider b, Vector2f bVel){
//        if(aVel == null) aVel = NULL_VEC;
//        if(bVel == null) bVel = NULL_VEC;
//
//        float ax = a.x() + aVel.getX();
//        float ay = a.y() + aVel.getY();
//        float ar = a.radius();
//        float bx = b.x() + bVel.getX();
//        float by = b.y() + bVel.getY();
//        float br = b.radius();
//
//        float distX = ax - bx;
//        float distY = ay - by;
//        float dist = distX * distX + distY * distY;
//
//        float radiusSumDist = (ar + br) * (ar + br);
//
//        if(dist >= radiusSumDist) return;
//
//        float distLength = (float) Math.sqrt(dist);
//        float radiusSumDistLength = (float) Math.sqrt(radiusSumDist);
//
//        float intersectionDist = radiusSumDistLength - distLength;
//
//        float distXN = distX / distLength;
//        float distYN = distY / distLength;
//
//        float solveX = distXN * intersectionDist;
//        float solveY = distYN * intersectionDist;
//
//        calculateResolution(info, intersectionDist, solveX, solveY, a, b);
//    }
//
//
//
//    private static void calculateResolution(CollisionInfo info, float intersectionDist, float solveX, float solveY, Collider a, Collider b){
//        float deltaB;
//        float deltaA;
//
//        if(b.fixed){
//            deltaA = 1;
//            deltaB = 0;
//        }
//        else if(a.fixed){
//            deltaA = 0;
//            deltaB = 1;
//        }
//        else {
//            deltaB = a.mass / (a.mass + b.mass);
//            deltaA = 1 - deltaB;
//        }
//
//        float resAX = solveX * deltaA;
//        float resAY = solveY * deltaA;
//
//        float resBX = -solveX * deltaB;
//        float resBY = -solveY * deltaB;
//
//        info.set(intersectionDist, a, resAX, resAY, b, resBX, resBY);
//    }
}
