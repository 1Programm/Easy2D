package com.programm.projects.easy2d.ui.simple;

import com.programm.project.easy2d.engine.api.IPencil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Button extends UIElement {

    private final List<Runnable> pressedListeners = new ArrayList<>();
    private String text;
    private BufferedImage icon;

    private Color pressedColor;
    private boolean pressed;

    public Button(float x, float y, float width, float height, String text) {
        super(x, y, width, height);
        this.text = text;
        this.pressedColor = Color.LIGHT_GRAY;
    }

    @Override
    public void update(float offX, float offY) {}

    @Override
    public void render(IPencil pencil, float offX, float offY) {
        float rx = offX + x;
        float ry = offY + y;

        if(icon == null){
            if(secondary != null) {
                if(pressed && pressedColor != null){
                    pencil.setColor(pressedColor);
                }
                else {
                    pencil.setColor(secondary);
                }

                pencil.fillRectangle(rx, ry, width, height);
            }

            Color color = disabled ? disabledColor : primary;
            if(color == null) color = primary;

            if(color != null) {
                pencil.setColor(color);
                pencil.drawRectangle(rx, ry, width, height);

                if (text != null) {
                    pencil.drawStringCentered(text, rx + width / 2, ry + height / 2);
                }
            }
        }
        else {
            pencil.drawImage(icon, rx, ry, width, height);

            if(pressed && pressedColor != null){
                pencil.setColor(pressedColor);
                pencil.fillRectangle(rx, ry, width, height);
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

        if(containsPoint(mx, my)){
            this.pressed = true;
            notifyListeners();
        }
    }

    @Override
    public void onMouseReleased(float mx, float my, int button) {
        this.pressed = false;
    }

    private void notifyListeners(){
        for(int i=0;i<pressedListeners.size();i++){
            pressedListeners.get(i).run();
        }
    }

    public Button onPressed(Runnable runnable){
        this.pressedListeners.add(runnable);
        return this;
    }

    public String text(){
        return text;
    }

    public Button text(String text){
        this.text = text;
        return this;
    }

    public Button pressedColor(Color pressedColor){
        this.pressedColor = pressedColor;
        return this;
    }

    @Override
    public Button x(float x) {
        super.x(x);
        return this;
    }

    @Override
    public Button y(float y) {
        super.y(y);
        return this;
    }

    @Override
    public Button width(float width) {
        super.width(width);
        return this;
    }

    @Override
    public Button height(float height) {
        super.height(height);
        return this;
    }

    @Override
    public Button primary(Color primary) {
        super.primary(primary);
        return this;
    }

    @Override
    public Button secondary(Color secondary) {
        super.secondary(secondary);
        return this;
    }

    @Override
    public Button disabledColor(Color disabledColor) {
        super.disabledColor(disabledColor);
        return this;
    }

    @Override
    public Button disabled(boolean disabled) {
        super.disabled(disabled);
        return this;
    }

    public Button icon(BufferedImage icon) {
        this.icon = icon;
        return this;
    }

    public BufferedImage icon(){
        return this.icon;
    }

    @Override
    public Button visible(boolean visible) {
        super.visible(visible);
        return this;
    }
}
