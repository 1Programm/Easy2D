package com.programm.projects.easy2d.ui.simple;

import com.programm.project.easy2d.engine.api.IContext;
import com.programm.project.easy2d.engine.api.IKeyboard;
import com.programm.project.easy2d.engine.api.IMouse;
import com.programm.project.easy2d.engine.api.IPencil;

import java.awt.*;

public abstract class UIElement {
    protected float x, y, width, height;
    protected Color primary, secondary, disabledColor;
    protected boolean disabled;
    protected boolean visible = true;

    public UIElement(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.primary = Color.BLACK;
        this.secondary = Color.WHITE;
        this.disabledColor = Color.GRAY;
    }

    public final void initBindings(IContext ctx){
        IMouse mouse = ctx.mouse();
        IKeyboard keyboard = ctx.keyboard();

        mouse.onMousePressed((m, btn) -> onMousePressed(m.x(), m.y(), btn));
        mouse.onMouseReleased((m, btn) -> onMouseReleased(m.x(), m.y(), btn));
        mouse.onMouseMoved((m, btn) -> onMouseMoved(m.x(), m.y()));
        mouse.onMouseDragged((m, btn) -> onMouseDragged(m.x(), m.y()));
        mouse.onMouseScrolled((m, scroll) -> onMouseScrolled(scroll));

        keyboard.onKeyPressed((k, key) -> onKeyPressed(key));
        keyboard.onKeyReleased((k, key) -> onKeyReleased(key));
    }

    public abstract void update(float offX, float offY);
    public abstract void render(IPencil pencil, float offX, float offY);

    public void onMousePressed(float mx, float my, int button){}
    public void onMouseReleased(float mx, float my, int button){}
    public void onMouseDragged(float mx, float my){}
    public void onMouseMoved(float mx, float my){}
    public void onMouseScrolled(float scroll){}

    public void onKeyPressed(int keyCode){}
    public void onKeyReleased(int keyCode){}

    public boolean containsPoint(float px, float py){
        return px >= x && px < x + width && py >= y && py < y + height;
    }

    public float x() {
        return x;
    }

    public UIElement x(float x) {
        this.x = x;
        return this;
    }

    public float y() {
        return y;
    }

    public UIElement y(float y) {
        this.y = y;
        return this;
    }

    public float width() {
        return width;
    }

    public UIElement width(float width) {
        this.width = width;
        return this;
    }

    public float height() {
        return height;
    }

    public UIElement height(float height) {
        this.height = height;
        return this;
    }

    public UIElement primary(Color primary){
        this.primary = primary;
        return this;
    }

    public UIElement secondary(Color secondary){
        this.secondary = secondary;
        return this;
    }

    public UIElement disabledColor(Color disabledColor){
        this.disabledColor = disabledColor;
        return this;
    }

    public boolean disabled(){
        return disabled;
    }

    public UIElement disabled(boolean disabled){
        this.disabled = disabled;
        return this;
    }

    public boolean  visible(){
        return visible;
    }

    public UIElement visible(boolean visible){
        this.visible = visible;
        return this;
    }
}
