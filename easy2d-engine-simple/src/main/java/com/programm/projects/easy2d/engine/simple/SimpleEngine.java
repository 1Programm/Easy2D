package com.programm.projects.easy2d.engine.simple;

import com.programm.project.easy2d.engine.api.*;

public abstract class SimpleEngine implements IEngine, IContext {

    private final IWindow window;
    private final ProxyLogger logger;
    protected final SimpleKeyboard keyboard;
    protected final SimpleMouse mouse;
    private final float fps;
    private boolean printFps = false;
    private boolean running = false;

    public SimpleEngine(String title, int width, int height, float fps) {
        this.logger = new ProxyLogger();

        this.keyboard = new SimpleKeyboard(logger);
        this.mouse = new SimpleMouse(logger);
        this.window = initWindow(title, width, height);
        this.window.init(this, keyboard, mouse);
        this.fps = fps;
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

    protected IWindow initWindow(String title, int width, int height){
        return new SimpleWindow(title, width, height);
    }

    protected abstract void init();

    protected void onShutdown(){}

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
    public final ILogger log() {
        return logger;
    }

    public final void doPrintFps(){
        this.printFps = true;
    }

    @Override
    public final IWindow window() {
        return window;
    }

    @Override
    public final IMouse mouse() {
        return mouse;
    }

    @Override
    public final IKeyboard keyboard() {
        return keyboard;
    }
}
