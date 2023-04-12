package com.programm.projects.easy2d.ui.simple;

import com.programm.project.easy2d.engine.api.IPencil;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Slider extends UIElement {

    public static int HORIZONTAL = 0;
    public static int VERTICAL = 1;

    private final List<Consumer<Float>> listeners = new ArrayList<>();

    private float mx, my;

    private boolean pressed;
    private int orientation;

    private float min;
    private float max;
    private float value;
    private float[] steps;

    public Slider(float x, float y, float width, float height) {
        super(x, y, width, height);
        this.min = 0;
        this.max = 1;
        this.value = 0;
        this.orientation = HORIZONTAL;
    }

    @Override
    public void update(float offX, float offY) {
        if(pressed){
            if(orientation == HORIZONTAL){
                float x2 = x + width;

                mx = Math.min(Math.max(mx, x), x2);
                value = parseNormalizedValue((mx - x) / width);
                value = toNearestStep(value);
                notifyListeners(value);
            }
            else if(orientation == VERTICAL){
                float y2 = y + height;

                my = Math.min(Math.max(my, y), y2);
                value = parseNormalizedValue((my - y) / height);
                value = toNearestStep(value);
                notifyListeners(value);
            }
        }
    }

    @Override
    public void render(IPencil pencil, float offX, float offY) {
        float rx = offX + x;
        float ry = offY + y;

        if(secondary != null) {
            pencil.setColor(secondary);
            pencil.fillRoundRectangle(rx, ry, width, height, roundOrientationWH());
        }

        Color color = disabled ? disabledColor : primary;
        if(color == null) color = primary;

        if(color != null) {
            pencil.setColor(color);
            pencil.drawRoundRectangle(rx, ry, width, height, roundOrientationWH());

            if(orientation == HORIZONTAL) {
                float handleX = rx + width * normalizeValue(value);
                float handleY = ry + height / 2f;
                pencil.fillCircle(handleX, handleY, (height / 2f) + 4);
            }
            else if(orientation == VERTICAL){
                float handleX = rx + width / 2f;
                float handleY = ry + height * normalizeValue(value);
                pencil.fillCircle(handleX, handleY, (width / 2f) + 4);
            }
        }
    }

    private float parseNormalizedValue(float v){
        return min + v * (max - min);
    }

    private float normalizeValue(float v){
        return (v - min) / (max - min);
    }

    private float toNearestStep(float v){
        if(steps == null) return v;

        float dist = -1;
        for(int i=0;i<steps.length;i++){
            float step = steps[i];
            float nDist = Math.abs(v - step);
            if(v <= step){
                if(dist == -1) return step;
                else if(dist < nDist) return steps[i-1];
                else return step;
            }

            dist = nDist;
        }

        return steps[steps.length - 1];
    }

    private float roundOrientationWH(){
        if(orientation == HORIZONTAL) {
            return height;
        }
        else if(orientation == VERTICAL){
            return width;
        }

        return 0;
    }

    @Override
    public void onMousePressed(float mx, float my, int button){
        if(disabled) return;

        if(containsPoint(mx, my)){
            this.mx = mx;
            this.my = my;
            pressed = true;
        }
    }

    @Override
    public void onMouseReleased(float mx, float my, int button) {
        pressed = false;
    }

    @Override
    public void onMouseDragged(float mx, float my) {
        this.mx = mx;
        this.my = my;
    }

    private void notifyListeners(float value){
        for(int i=0;i<listeners.size();i++){
            listeners.get(i).accept(value);
        }
    }

    public Slider setOrientation(int orientation){
        this.orientation = orientation;
        return this;
    }

    public Slider setValue(float value){
        this.value = Math.min(Math.max(value, min), max);
        notifyListeners(value);
        return this;
    }

    public float getValue(){
        return value;
    }

    public Slider onValueChanged(Consumer<Float> listener){
        listeners.add(listener);
        return this;
    }

    public Slider range(float min, float max){
        this.min = min;
        this.max = max;
        this.value = Math.min(Math.max(value, min), max);
        notifyListeners(value);
        return this;
    }

    public Slider steps(float[] steps){
        this.steps = steps;
        return this;
    }

    @Override
    public Slider x(float x) {
        super.x(x);
        return this;
    }

    @Override
    public Slider y(float y) {
        super.y(y);
        return this;
    }

    @Override
    public Slider width(float width) {
        super.width(width);
        return this;
    }

    @Override
    public Slider height(float height) {
        super.height(height);
        return this;
    }

    @Override
    public Slider primary(Color primary) {
        super.primary(primary);
        return this;
    }

    @Override
    public Slider secondary(Color secondary) {
        super.secondary(secondary);
        return this;
    }

    @Override
    public Slider disabledColor(Color disabledColor) {
        super.disabledColor(disabledColor);
        return this;
    }

    @Override
    public Slider disabled(boolean disabled) {
        super.disabled(disabled);
        return this;
    }

    @Override
    public Slider visible(boolean visible) {
        super.visible(visible);
        return this;
    }
}
