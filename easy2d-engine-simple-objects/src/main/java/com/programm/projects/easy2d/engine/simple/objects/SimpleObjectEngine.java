package com.programm.projects.easy2d.engine.simple.objects;

import com.programm.project.easy2d.engine.api.IPencil;
import com.programm.projects.easy2d.engine.simple.SimpleEngine;
import com.programm.projects.easy2d.objects.api.GameObject;
import com.programm.projects.easy2d.objects.api.IGOH;
import com.programm.projects.easy2d.objects.api.components.collision.CollisionHandler;
import com.programm.projects.easy2d.objects.api.components.collision.IntersectionInfo;
import com.programm.projects.easy2d.objects.api.components.collision.RayCastInfo;
import com.programm.projects.easy2d.objects.api.components.shape.Shape;
import com.programm.projects.plus.maths.Vector2f;

import java.awt.*;

public abstract class SimpleObjectEngine extends SimpleEngine implements IGOH {

    protected final ArrayObjectCollection objects;
    private float unitSize = 32;
    private boolean debugShowUnitRaster;

    public SimpleObjectEngine(String title, int width, int height, float fps, boolean cached) {
        super(title, width, height, fps);
        if(cached) {
            this.objects = new CachedArrayObjectCollection(width, height, 10);
        }
        else {
            this.objects = new ArrayObjectCollection();
        }

        CollisionHandler.init(this);
    }

    @Override
    public float unitSize() {
        return unitSize;
    }

    public SimpleObjectEngine unitSize(float unitSize){
        this.unitSize = unitSize;
        return this;
    }

    @Override
    public void addObject(GameObject obj){
        obj.init(this, this);
        objects.add(obj);
    }

    @Override
    public void update() {
        for(int i=0;i<objects.size();i++){
            GameObject obj = objects.get(i);

            obj.update();

            if(obj.isDead()) {
                objects.remove(i);
                i--;
            }
        }
    }

    @Override
    public void render(IPencil pencil) {
        pencil.setColor(Color.WHITE);
        pencil.fillScreen();

        if(debugShowUnitRaster){
            pencil.setColor(Color.LIGHT_GRAY);

            float winW = window().width();
            float winH = window().height();
            int countX = (int)(winW / unitSize);
            int countY = (int)(winH / unitSize);

            for(int x=0;x<countX;x++){
                float pos = x * unitSize;
                pencil.drawLine(pos, 0, pos, winH);
            }

            for(int y=0;y<countY;y++){
                float pos = y * unitSize;
                pencil.drawLine(0, pos, winW, pos);
            }
        }

        for(int i=0;i<objects.size();i++){
            objects.get(i).render(pencil);
        }
    }

    @Override
    public void setIgnoreCollisionObject(GameObject o) {
        objects.setIgnoreCollisionObject(o);
    }

    @Override
    public void rayCastFindFirstObject(RayCastInfo info, Vector2f pos, Vector2f vel) {
        info.collisions.clear();//reset
        objects.rayCastFindFirstObject(info, pos, vel);
    }

    @Override
    public void testAndSolveCollision(Shape shape, Vector2f pos, Vector2f scale, boolean fixed, float mass) {
        objects.testAndSolveCollision(shape, pos, scale, fixed, mass);
    }

    @Override
    public void testCollision(IntersectionInfo info, Shape shape, Vector2f pos, Vector2f scale) {
        info.reset();
        objects.testCollision(info, shape, pos, scale);
    }

    public SimpleObjectEngine debugShowUnitRaster(boolean debugShowUnitRaster){
        if(debugShowUnitRaster && unitSize < 10){
            System.err.println("Unit size must not be less than 10 or the raster cannot be shown!");
            return this;
        }

        this.debugShowUnitRaster = debugShowUnitRaster;
        return this;
    }
}
