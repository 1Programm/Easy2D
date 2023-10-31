package com.programm.projects.easy2d.objects.api.components.gfx;

import com.programm.projects.easy2d.engine.api.IPencil;

import java.awt.*;

public class ColoredCircle extends GfxComponent {

    private final Color color;
    private final boolean fill;

    public ColoredCircle(Color color) {
        this.color = color;
        this.fill = true;
    }

    public ColoredCircle(Color color, boolean fill) {
        this.color = color;
        this.fill = fill;
    }

    @Override
    public void render(IPencil pen) {
        pen.setColor(color);
        float radius = Math.max(gameObject.size.getX(), gameObject.size.getY()) / 2f;
        if(fill){
            pen.fillCircle(gameObject.position.getX(), gameObject.position.getY(), radius);
        }
        else {
            pen.drawCircle(gameObject.position.getX(), gameObject.position.getY(), radius);
        }
    }
}
