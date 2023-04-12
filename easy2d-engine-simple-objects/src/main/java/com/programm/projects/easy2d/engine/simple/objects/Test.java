package com.programm.projects.easy2d.engine.simple.objects;

import com.programm.project.easy2d.engine.api.IPencil;
import com.programm.projects.easy2d.objects.api.IObjectContext;
import com.programm.projects.easy2d.objects.api.RectEntity;

import java.awt.*;

public class Test extends SimpleObjectEngine {

    private static final float PLAYER_SPEED = 2.4f;

    private static class Player extends RectEntity {

        public Player(float x, float y, float w, float h) {
            super(x, y, w, h);
        }

        @Override
        public void update(IObjectContext ctx) {
            float vx = 0, vy = 0;
            if(ctx.keyboard().A()){
                vx -= PLAYER_SPEED;
            }
            if(ctx.keyboard().D()){
                vx += PLAYER_SPEED;
            }
            if(ctx.keyboard().W()){
                vy -= PLAYER_SPEED;
            }
            if(ctx.keyboard().S()){
                vy += PLAYER_SPEED;
            }

            velocity.set(vx, vy);

            super.update(ctx);
        }

        @Override
        public void render(IPencil pencil) {
            pencil.setColor(Color.BLUE);
            renderFilled(pencil);
        }
    }

    private static class Block extends RectEntity {

        public Block(float x, float y, float vx, float vy, float w, float h) {
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
        addObject(new Player(10, 10, 20, 20));
        addObject(new Block(100, 100, 0, 0, 32, 32));
        addObject(new Block(300, 100, 0, 0, 40, 70));

    }


    public static void main(String[] args) {
        Test test = new Test("Test Engine", 600, 500, 30, false);
        test.start();
    }

}
