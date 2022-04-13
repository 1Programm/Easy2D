package com.programm.projects.easy2d.engine.simple;

import com.programm.project.easy2d.engine.api.IKeyboard;
import com.programm.project.easy2d.engine.api.ILogger;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SimpleKeyboard extends KeyAdapter implements IKeyboard {

    private static final int NUM_KEYS = KeyEvent.KEY_LAST + 1;
    private final boolean[] keys = new boolean[NUM_KEYS];
    private final ILogger logger;

    private final List<Consumer<Integer>> keyPressedListeners = new ArrayList<>();
    private final List<Consumer<Integer>> keyReleasedListeners = new ArrayList<>();

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
        int keyCode = e.getKeyCode();
        setKey(keyCode, true);

        for(int i=0;i<keyPressedListeners.size();i++){
            keyPressedListeners.get(i).accept(keyCode);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        setKey(keyCode, false);

        for(int i=0;i<keyReleasedListeners.size();i++){
            keyReleasedListeners.get(i).accept(keyCode);
        }
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

    @Override
    public void onKeyPressed(Consumer<Integer> consumer) {
        keyPressedListeners.add(consumer);
    }

    @Override
    public void onKeyReleased(Consumer<Integer> consumer) {
        keyReleasedListeners.add(consumer);
    }
}
