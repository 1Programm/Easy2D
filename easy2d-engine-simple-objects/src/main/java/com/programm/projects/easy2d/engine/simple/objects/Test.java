package com.programm.projects.easy2d.engine.simple.objects;

import com.programm.project.easy2d.engine.api.IPencil;
import com.programm.projects.easy2d.objects.api.RectEntity;

import java.awt.*;

public class Test extends SimpleObjectEngine {

    private static class Player extends RectEntity {

        public Player(float x, float y, float vx, float vy, float w, float h) {
            super(x, y, vx, vy, w, h);
        }

        @Override
        public void render(IPencil pencil) {
            pencil.setColor(Color.BLACK);
            renderFilled(pencil);
        }
    }

    public Test(String title, int width, int height, float fps, boolean cached) {
        super(title, width, height, fps, cached);
    }

    @Override
    protected void init() {

    }


    public static void main(String[] args) {
        Test test = new Test("Test Engine", 300, 300, 30, false);
        test.addObject(new Player(100, 100, 1, 0, 32, 32));
        test.start();
    }

}
