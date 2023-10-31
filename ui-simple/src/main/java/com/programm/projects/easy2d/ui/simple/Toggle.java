package com.programm.projects.easy2d.ui.simple;

import com.programm.projects.easy2d.engine.api.IPencil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Toggle extends UIElement {

    private final List<Consumer<Boolean>> toggleListeners = new ArrayList<>();
    private boolean state;
    private BufferedImage icon;

    public Toggle(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    @Override
    public void update(float offX, float offY) {}

    @Override
    public void render(IPencil pencil, float offX, float offY) {
        float rx = offX + x;
        float ry = offY + y;

        if(icon == null) {
            if(secondary != null) {
                pencil.setColor(secondary);
                pencil.fillRectangle(rx, ry, width, height);
            }

            Color color = disabled ? disabledColor : primary;
            if(color == null) color = primary;

            if(color != null) {
                pencil.setColor(color);
                pencil.drawRectangle(rx, ry, width, height);

                if (state) {
                    pencil.fillRectangle(rx + 3, ry + 3, width - 6, height - 6);
                }
            }
        }
        else {
            pencil.drawImage(icon, rx, ry, width, height);

            if(state && primary != null){
                pencil.setColor(primary);
                pencil.drawRectangle(rx, ry, width, height);
            }

            if(disabled && disabledColor != null){
                pencil.setColor(disabledColor);
                pencil.fillRectangle(rx, ry, width, height);
            }
        }
    }

    @Override
    public void onMousePressed(float mx, float my, int button){
        if(disabled) return;

        if(containsPoint(mx, my)) {
            toggle();
        }
    }

    private void notifyListeners(){
        for(int i=0;i<toggleListeners.size();i++){
            toggleListeners.get(i).accept(state);
        }
    }

    public Toggle onToggle(Consumer<Boolean> listener){
        this.toggleListeners.add(listener);
        return this;
    }

    public Toggle onToggle(Runnable listener){
        this.toggleListeners.add(t -> {
            if(t) listener.run();
        });
        return this;
    }

    public boolean state(){
        return state;
    }

    public Toggle state(boolean state){
        if(state != this.state) {
            this.state = state;
            notifyListeners();
        }

        return this;
    }

    public Toggle toggle(){
        return state(!state);
    }

    @Override
    public Toggle x(float x) {
        super.x(x);
        return this;
    }

    @Override
    public Toggle y(float y) {
        super.y(y);
        return this;
    }

    @Override
    public Toggle width(float width) {
        super.width(width);
        return this;
    }

    @Override
    public Toggle height(float height) {
        super.height(height);
        return this;
    }

    @Override
    public Toggle primary(Color primary) {
        super.primary(primary);
        return this;
    }

    @Override
    public Toggle secondary(Color secondary) {
        super.secondary(secondary);
        return this;
    }

    @Override
    public Toggle disabledColor(Color disabledColor) {
        super.disabledColor(disabledColor);
        return this;
    }

    @Override
    public Toggle disabled(boolean disabled) {
        super.disabled(disabled);
        return this;
    }

    @Override
    public Toggle visible(boolean visible) {
        super.visible(visible);
        return this;
    }

    public Toggle icon(BufferedImage icon){
        this.icon = icon;
        return this;
    }
}
