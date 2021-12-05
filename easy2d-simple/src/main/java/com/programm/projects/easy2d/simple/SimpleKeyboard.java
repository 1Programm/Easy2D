package com.programm.projects.easy2d.simple;

import com.programm.project.easy2d.api.IKeyboard;
import com.programm.project.easy2d.api.ILogger;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SimpleKeyboard extends KeyAdapter implements IKeyboard {

    private static final int NUM_KEYS = KeyEvent.KEY_LAST + 1;
    private final boolean[] keys = new boolean[NUM_KEYS];
    private final ILogger logger;

    public SimpleKeyboard(ILogger logger) {
        this.logger = logger;
    }

    @Override
    public boolean isKeyPressed(int keyCode) {
        if(keyOutRange(keyCode)) throw new NullPointerException("Number [" + keyCode + "] is outside range (0-" + NUM_KEYS + ")!");
        return keys[keyCode];
    }

    @Override
    public void keyPressed(KeyEvent e) {
        setKey(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        setKey(e.getKeyCode(), false);
    }

    private void setKey(int code, boolean state){
        if(keyOutRange(code)){
            logger.error("Unknown keycode: [" + code + "]!");
        }
        else {
            keys[code] = state;
        }
    }

    private boolean keyOutRange(int i){
        return (i < 0 || i >= NUM_KEYS);
    }
}
