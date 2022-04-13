package com.programm.projects.easy2d.engine.simple;

import com.programm.project.easy2d.engine.api.ConsoleLogger;
import com.programm.project.easy2d.engine.api.ILogger;
import com.programm.project.easy2d.engine.api.IPencil;

import java.awt.*;

public class TestMain extends SimpleEngine {

    private static final ILogger log = new ConsoleLogger(ConsoleLogger.INFO);

    public static void main(String[] args) {
        TestMain engine = new TestMain();
        engine.doPrintFps();
        engine.setEngineOut(log);
        engine.start();
    }

    private static final float PLAYER_SPEED = 1.8f;
    private float x, y;

    public TestMain() {
        super("Test Engine", 600, 500, 30.0f);
    }

    @Override
    protected void init() {
        log.info("INIT");
    }

    @Override
    protected void onShutdown() {
        log.info("SHUTDOWN");
    }

    @Override
    public void update() {
        float vx = 0, vy = 0;

        if(keyboard.W()){
            vy -= PLAYER_SPEED;
        }

        if(keyboard.S()){
            vy += PLAYER_SPEED;
        }

        if(keyboard.A()){
            vx -= PLAYER_SPEED;
        }

        if(keyboard.D()){
            vx += PLAYER_SPEED;
        }

        x += vx;
        y += vy;
    }

    @Override
    public void render(IPencil pencil) {
        pencil.setColor(Color.WHITE);
        pencil.fillScreen();

        pencil.setColor(Color.BLACK);
        pencil.fillRectangle(x, y, 32, 32);
    }
}
