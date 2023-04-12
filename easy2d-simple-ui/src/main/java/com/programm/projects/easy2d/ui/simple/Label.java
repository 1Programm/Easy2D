package com.programm.projects.easy2d.ui.simple;

import com.programm.project.easy2d.engine.api.IPencil;

import java.awt.*;

public class Label extends UIElement {

    public static final int START = 0;
    public static final int CENTER = 1;

    private String text;
    private int orientation;
    private boolean bordered;

    public Label(float x, float y, float width, float height, String text) {
        super(x, y, width, height);
        this.text = text;
        this.secondary = null;
        this.orientation = CENTER;
    }

    @Override
    public void update(float offX, float offY) {}

    @Override
    public void render(IPencil pencil, float offX, float offY) {
        float rx = offX + x;
        float ry = offY + y;

        if(secondary != null){
            pencil.setColor(secondary);
            pencil.fillRectangle(rx, ry, width, height);
        }

        Color color = disabled ? disabledColor : primary;
        if(color == null) color = primary;

        if(color != null){
            pencil.setColor(color);
            if(orientation == START){
                pencil.drawStringVCentered(text, rx, ry + height / 2);
            }
            else if(orientation == CENTER) {
                pencil.drawStringCentered(text, rx + width / 2, ry + height / 2);
            }

            if(bordered){
                pencil.drawRectangle(rx, ry, width, height);
            }
        }
    }

    public String text(){
        return text;
    }

    public Label text(String text){
        this.text = text;
        return this;
    }

    public int orientation(){
        return orientation;
    }

    public Label orientation(int orientation){
        this.orientation = orientation;
        return this;
    }

    public Label bordered(boolean flag){
        this.bordered = flag;
        return this;
    }

    @Override
    public Label x(float x) {
        super.x(x);
        return this;
    }

    @Override
    public Label y(float y) {
        super.y(y);
        return this;
    }

    @Override
    public Label width(float width) {
        super.width(width);
        return this;
    }

    @Override
    public Label height(float height) {
        super.height(height);
        return this;
    }

    @Override
    public Label primary(Color primary) {
        super.primary(primary);
        return this;
    }

    @Override
    public Label secondary(Color secondary) {
        super.secondary(secondary);
        return this;
    }

    @Override
    public Label disabledColor(Color disabledColor) {
        super.disabledColor(disabledColor);
        return this;
    }

    @Override
    public Label disabled(boolean disabled) {
        super.disabled(disabled);
        return this;
    }

    @Override
    public Label visible(boolean visible) {
        super.visible(visible);
        return this;
    }

}
