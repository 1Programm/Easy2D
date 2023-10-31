package com.programm.projects.easy2d.objects.api.components.gfx;

import com.programm.projects.easy2d.engine.api.IPencil;
import com.programm.projects.plus.maths.Vector2f;

import java.awt.*;

public class ColoredLine extends GfxComponent {

    private final Color color;
    private final Vector2f start, end;

    public ColoredLine(Color color, Vector2f start, Vector2f end) {
        this.color = color;
        this.start = start;
        this.end = end;
    }

    public ColoredLine(Color color, float startX, float startY, float endX, float endY) {
        this(color, new Vector2f(startX, startY), new Vector2f(endX, endY));
    }

    public ColoredLine(Color color, float endX, float endY) {
        this(color, new Vector2f(0, 0), new Vector2f(endX, endY));
    }

    @Override
    public void render(IPencil pen) {
        float sx = gameObject.position.getX() + start.getX();
        float sy = gameObject.position.getY() + start.getY();
        float ex = gameObject.position.getX() + end.getX();
        float ey = gameObject.position.getY() + end.getY();

        pen.setColor(color);
        pen.drawLine(sx, sy, ex, ey);
    }
}
