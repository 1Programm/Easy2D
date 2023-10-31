package com.programm.projects.easy2d.objects.api.components.collision;

import com.programm.projects.easy2d.objects.api.IGOH;
import com.programm.projects.easy2d.objects.api.components.shape.Circle;
import com.programm.projects.easy2d.objects.api.components.shape.Rect;
import com.programm.projects.easy2d.objects.api.components.shape.Shape;
import com.programm.projects.plus.maths.Vector2f;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class CollisionHandler {

    public interface IIntersectionTester<A extends Shape, B extends Shape> {
        void testIntersection(IntersectionInfo info,
                  A a, float aX, float aY, float aW, float aH,
                  B b, float bX, float bY, float bW, float bH);
    }

    private static IGOH goh;

    private static final Map<Class<? extends Shape>, Map<Class<? extends Shape>, IIntersectionTester<?, ?>>> TESTER_MAP = new HashMap<>();
    static {
        registerIntersectionTester(Rect.class, Rect.class, CollisionHandler::intersect_rect_rect);
        registerIntersectionTester(Circle.class, Circle.class, CollisionHandler::intersect_circle_circle);
        registerIntersectionTester(Circle.class, Rect.class, CollisionHandler::intersect_circle_rect);
    }

    public static void init(IGOH goh){
        CollisionHandler.goh = goh;
    }

    public static <A extends Shape, B extends Shape> void registerIntersectionTester(Class<? extends Shape> a, Class<? extends Shape> b, IIntersectionTester<A, B> tester){
        Object oldTester = TESTER_MAP.computeIfAbsent(a, c -> new HashMap<>()).put(b, tester);

        if(oldTester != null){
            System.err.println("Intersection Tester [" + a.getSimpleName() + " - vs - " + b.getSimpleName() + "] overwritten!");
        }
    }

    @SuppressWarnings("unchecked")
    private static <A extends Shape, B extends Shape> IIntersectionTester<A, B> getTester(Class<? extends Shape> a, Class<? extends Shape> b, AtomicBoolean reverse){
        IIntersectionTester<?, ?> tester;

        Map<Class<? extends Shape>, IIntersectionTester<?, ?>> map = TESTER_MAP.get(a);

        if(map == null) {
            map = TESTER_MAP.get(b);
            if(map == null) return null;

            reverse.set(true);
            tester = map.get(a);
        }
        else {
            tester = map.get(b);

            if(tester == null) {
                map = TESTER_MAP.get(b);
                if(map == null) return null;

                reverse.set(true);
                tester = map.get(a);
            }
        }

        return (IIntersectionTester<A, B>) tester;
    }



    // ### INTERSECTION TESTS ### //

    private static void intersect_rect_rect(IntersectionInfo info,
                                            Rect a, float aX, float aY, float aW, float aH,
                                            Rect b, float bX, float bY, float bW, float bH){

//        float unitSize = goh.unitSize();

        float aw = a.size.getX() * aW;
        float ah = a.size.getY() * aH;
        float ax = (aX + a.position.getX()) - aw * 0.5f;
        float ay = (aY + a.position.getY()) - ah * 0.5f;

        float bw = b.size.getX() * bW;
        float bh = b.size.getY() * bH;
        float bx = (bX + b.position.getX()) - bw * 0.5f;
        float by = (bY + b.position.getY()) - bh * 0.5f;

        float distX = bx - ax;
        float distY = by - ay;

        float solveX;
        float solveY;

        if(distX >= 0){
            if(distX >= aw) return;
            solveX = bx - (ax + aw);
        }
        else {
            if(distX * -1 >= bw) return;
            solveX = (bx + bw) - ax;
        }

        if(distY >= 0){
            if(distY >= ah) return;
            solveY = by - (ay + ah);
        }
        else {
            if(distY * -1 >= bh) return;
            solveY = (by + bh) - ay;
        }


        float solveXAbs = Math.abs(solveX);
        float solveYAbs = Math.abs(solveY);
        float intersectionDist;
        if(solveXAbs < solveYAbs){
            solveY = 0;
            intersectionDist = solveXAbs;
        }
        else {
            solveX = 0;
            intersectionDist = solveYAbs;
        }

        info.set(intersectionDist, solveX, solveY);
    }

    private static void intersect_circle_circle(IntersectionInfo info,
                                              Circle a, float aX, float aY, float aW, float aH,
                                              Circle b, float bX, float bY, float bW, float bH){
        float ax = aX + a.position.getX();
        float ay = aY + a.position.getY();
        float ar = a.radius * Math.max(aW, aH);

        float bx = bX + b.position.getX();
        float by = bY + b.position.getY();
        float br = b.radius * Math.max(bW, bH);

        float distX = ax - bx;
        float distY = ay - by;
        float dist = distX * distX + distY * distY;
        float radiusSumDist = (ar + br) * (ar + br);
        if(dist >= radiusSumDist) return;
        float distLength = (float) Math.sqrt(dist);
        float radiusSumDistLength = (float) Math.sqrt(radiusSumDist);
        float intersectionDist = radiusSumDistLength - distLength;
        float distXN = distX / distLength;
        float distYN = distY / distLength;
        float solveX = distXN * intersectionDist;
        float solveY = distYN * intersectionDist;

        info.set(intersectionDist, solveX, solveY);
    }

    private static void intersect_circle_rect(IntersectionInfo info,
                                              Circle a, float aX, float aY, float aW, float aH,
                                              Rect b, float bX, float bY, float bW, float bH){
        float ax = aX + a.position.getX();
        float ay = aY + a.position.getY();
        float ar = a.radius * Math.max(aW, aH);

        float bw = b.size.getX() * bW;
        float bh = b.size.getY() * bH;
        float bx = (bX + b.position.getX()) - bw * 0.5f;
        float by = (bY + b.position.getY()) - bh * 0.5f;

        float px = Math.max(bx, Math.min(bx + bw, ax));
        float py = Math.max(by, Math.min(by + bh, ay));

        float distX = px - ax;
        float distY = py - ay;
        float distSqare = distX * distX + distY * distY;

        if(distSqare == 0) {
            //We are inside the rectangle with the circle
            float distLeft = (bx - ar) - ax;
            float distRight = (bx + bw + ar) - ax;
            float distTop = (by - ar) - ax;
            float distBottom = (by + bh + ar) - ax;

            float absLeft = Math.abs(distLeft);
            float absRight = Math.abs(distRight);
            float absTop = Math.abs(distTop);
            float absBottom = Math.abs(distBottom);

            if(Math.min(absLeft, absRight) < Math.min(absTop, absBottom)){
                if(absLeft < absRight){
                    info.set(absLeft, distLeft, 0);
                }
                else {
                    info.set(absRight, distRight, 0);
                }
            }
            else {
                if(absTop < absBottom){
                    info.set(absTop, 0, distTop);
                }
                else {
                    info.set(absBottom, 0, distBottom);
                }
            }

            return;
        }

        if(distSqare > ar * ar) return;

        float distSqrt = (float)Math.sqrt(distSqare);
        float nDistX = (distX / distSqrt) * ar;
        float nDistY = (distY / distSqrt) * ar;
//        float resX = nDistX - distX;
//        float resY = nDistY - distY;
        float resX = distX - nDistX;
        float resY = distY - nDistY;

        float intersectionDist = ar - distSqrt;

        info.set(intersectionDist, resX, resY);
    }






    // ### COLLISION TESTS ### //








    @SuppressWarnings({"unchecked", "rawtypes"})
    public static void testIntersection(IntersectionInfo info,
                                 Shape a, float aX, float aY, float aW, float aH,
                                 Shape b, float bX, float bY, float bW, float bH){

        AtomicBoolean reverse = new AtomicBoolean();
        IIntersectionTester tester = getTester(a.getClass(), b.getClass(), reverse);

        if(tester == null){
            throw new IllegalStateException("INVALID STATE: No tester found for the two shapes [" + a.getClass() + " - vs - " + b.getClass() + "] found!");
        }

        if(reverse.get()){
            tester.testIntersection(info, b, bX, bY, bW, bH, a, aX, aY, aW, aH);
            if(info.collision()){
                info.resolution.mul(-1);
            }
        }
        else {
            tester.testIntersection(info, a, aX, aY, aW, aH, b, bX, bY, bW, bH);
        }
    }

    public static void solveCollision(IntersectionInfo info, Vector2f aPos, boolean aFixed, float aMass, Vector2f bPos, boolean bFixed, float bMass) {
        if(info.collision()){
            if(bFixed){
                aPos.add(info.resolution.getX(), info.resolution.getY());
            }
            else if(aFixed){
                bPos.sub(info.resolution.getX(), info.resolution.getY());
            }
            else {
                float deltaB = aMass / (aMass + bMass);
                float deltaA = 1 - deltaB;

                aPos.add(info.resolution.getX() * deltaA, info.resolution.getY() * deltaA);
                bPos.sub(info.resolution.getX() * deltaB, info.resolution.getY() * deltaB);
            }
        }
    }

}
