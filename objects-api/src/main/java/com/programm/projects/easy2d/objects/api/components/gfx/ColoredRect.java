package com.programm.projects.easy2d.objects.api.components.gfx;

import com.programm.projects.easy2d.engine.api.IPencil;
import com.programm.projects.plus.maths.Vector2f;

import java.awt.*;

public class ColoredRect extends GfxComponent {

    private final Color color;
    private final Vector2f pos;
    private final Vector2f size;
    private final boolean fill;

    public ColoredRect(Color color, Vector2f pos, Vector2f size, boolean fill) {
        this.color = color;
        this.pos = pos;
        this.size = size;
        this.fill = fill;
    }

    public ColoredRect(Color color, Vector2f pos, Vector2f size) {
        this(color, pos, size, true);
    }

    public ColoredRect(Color color, float posX, float posY, float sizeX, float sizeY) {
        this(color, new Vector2f(posX, posY), new Vector2f(sizeX, sizeY), true);
    }

    public ColoredRect(Color color, Vector2f size) {
        this(color, new Vector2f(0, 0), size, true);
    }

    public ColoredRect(Color color, float sizeX, float sizeY) {
        this(color, new Vector2f(sizeX, sizeY));
    }

    public ColoredRect(Color color) {
        this(color, 1, 1);
    }

    @Override
    public void render(IPencil pen) {
        pen.setColor(color);
        float w = size.getX() * gameObject.size.getX() * objectHandler.unitSize();
        float h = size.getY() * gameObject.size.getY() * objectHandler.unitSize();
        float x = (pos.getX() + gameObject.position.getX()) - w/2f;
        float y = (pos.getY() + gameObject.position.getY()) - h/2f;

        if(fill){
            pen.fillRectangle(x, y, w, h);
        }
        else {
            pen.drawRectangle(x, y, w, h);
        }
    }
}
