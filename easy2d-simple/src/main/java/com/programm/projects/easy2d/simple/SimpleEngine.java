package com.programm.projects.easy2d.simple;

import com.programm.project.easy2d.api.*;

public abstract class SimpleEngine implements IEngine {

    private final SimpleWindow window;
    private final ProxyLogger logger;
    protected final IKeyboard keyboard;
    protected final IMouse mouse;
    private final float fps;
    private boolean printFps = false;
    private boolean running = false;

    public SimpleEngine(String title, int width, int height, float fps) {
        this.logger = new ProxyLogger();
        SimpleKeyboard simpleKeyboard = new SimpleKeyboard(logger);
        SimpleMouse simpleMouse = new SimpleMouse(logger);
        this.window = new SimpleWindow(title, width, height, this::render, simpleKeyboard, simpleMouse);
        this.fps = fps;

        this.keyboard = simpleKeyboard;
        this.mouse = simpleMouse;
    }

    public final void start() {
        if(running) return;
        running = true;

        window.show();
        Thread engineThread = new Thread(this::run);
        engineThread.start();
    }

    public final void stop() {
        if(!running) return;
        running = false;
    }

    protected abstract void init();

    protected void onShutdown(){ }

    private void run(){
        init();

        long lastTime = System.nanoTime();
        double ns = fps / 1000000000;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;

        while(running && !window.isCloseRequested()){
            long now = System.nanoTime();
            delta += (now - lastTime) * ns;
            lastTime = now;

            boolean updated = false;

            while(delta >= 1){
                update();
                if(!running) return;
                if(printFps && logger.hasLogger()) updates++;
                delta--;

                updated = true;
            }

            if(updated){
                window.draw();
            }

            if(printFps && logger.hasLogger()) {
                frames++;

                if(System.currentTimeMillis() - timer > 1000){
                    timer += 1000;
                    logger.debug("UPDATES: " + frames + " - FPS: " + updates);
                    frames = 0;
                    updates = 0;
                }
            }
        }

        window.close();
        onShutdown();
    }

    @Override
    public final void setEngineOut(ILogger logger) {
        this.logger.setLogger(logger);
    }

    @Override
    public final ILogger logger() {
        return logger;
    }

    public final void doPrintFps(){
        this.printFps = true;
    }

}
