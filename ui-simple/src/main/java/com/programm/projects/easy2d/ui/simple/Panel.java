package com.programm.projects.easy2d.ui.simple;

import com.programm.projects.easy2d.engine.api.IPencil;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Panel extends UIElement {

    protected final List<UIElement> children = new ArrayList<>();

    public Panel(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    @Override
    public void update(float offX, float offY) {
        for(int i=0;i<children.size();i++){
            UIElement child = children.get(i);
            if(!child.visible) continue;
            child.update(offX + x, offY + y);
        }
    }

    @Override
    public void render(IPencil pencil, float offX, float offY) {
        float rx = offX + x;
        float ry = offY + y;

        if(secondary != null) {
            pencil.setColor(secondary);
            pencil.fillRectangle(rx, ry, width, height);
        }

        Color color = primary;

        if(disabled && disabledColor != null){
            color = disabledColor;
        }

        if(color != null){
            pencil.setColor(color);
            pencil.drawRectangle(rx, ry, width, height);
        }

        for(int i=0;i<children.size();i++){
            UIElement child = children.get(i);
            if(!child.visible) continue;
            child.render(pencil, rx, ry);
        }
    }

    @Override
    public void onMousePressed(float mx, float my, int button) {
        for(int i=0;i<children.size();i++){
            UIElement child = children.get(i);
            if(!child.visible) continue;
            child.onMousePressed(mx - x, my - y, button);
        }
    }

    @Override
    public void onMouseReleased(float mx, float my, int button) {
        for(int i=0;i<children.size();i++){
            UIElement child = children.get(i);
            if(!child.visible) continue;
            child.onMouseReleased(mx - x, my - y, button);
        }
    }

    @Override
    public void onMouseDragged(float mx, float my) {
        for(int i=0;i<children.size();i++){
            UIElement child = children.get(i);
            if(!child.visible) continue;
            child.onMouseDragged(mx - x, my - y);
        }
    }

    @Override
    public void onMouseMoved(float mx, float my) {
        for(int i=0;i<children.size();i++){
            UIElement child = children.get(i);
            if(!child.visible) continue;
            child.onMouseMoved(mx - x, my - y);
        }
    }

    @Override
    public void onKeyPressed(int keyCode) {
        for(int i=0;i<children.size();i++){
            UIElement child = children.get(i);
            if(!child.visible) continue;
            child.onKeyPressed(keyCode);
        }
    }

    @Override
    public void onKeyReleased(int keyCode) {
        for(int i=0;i<children.size();i++){
            UIElement child = children.get(i);
            if(!child.visible) continue;
            child.onKeyReleased(keyCode);
        }
    }

    @Override
    public void onMouseScrolled(float scroll) {
        for(int i=0;i<children.size();i++){
            UIElement child = children.get(i);
            if(!child.visible) continue;
            child.onMouseScrolled(scroll);
        }
    }

    public Panel add(UIElement child){
        children.add(child);
        return this;
    }

    public Panel remove(UIElement child){
        children.remove(child);
        return this;
    }

    public Panel clearChildren(){
        children.clear();
        return this;
    }

    @Override
    public Panel x(float x) {
        super.x(x);
        return this;
    }

    @Override
    public Panel y(float y) {
        super.y(y);
        return this;
    }

    @Override
    public Panel width(float width) {
        super.width(width);
        return this;
    }

    @Override
    public Panel height(float height) {
        super.height(height);
        return this;
    }

    @Override
    public Panel primary(Color primary) {
        super.primary(primary);
        return this;
    }

    @Override
    public Panel secondary(Color secondary) {
        super.secondary(secondary);
        return this;
    }

    @Override
    public Panel disabledColor(Color disabledColor) {
        super.disabledColor(disabledColor);
        return this;
    }

    @Override
    public Panel disabled(boolean disabled) {
        super.disabled(disabled);
        for(int i=0;i<children.size();i++){
            children.get(i).disabled(disabled);
        }
        return this;
    }

    @Override
    public Panel visible(boolean visible) {
        super.visible(visible);
        return this;
    }


}
