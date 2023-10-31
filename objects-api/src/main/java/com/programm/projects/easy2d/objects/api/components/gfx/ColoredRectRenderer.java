package com.programm.projects.easy2d.objects.api.components.gfx;

import com.programm.projects.easy2d.engine.api.IPencil;
import com.programm.projects.easy2d.objects.api.components.shape.Rect;
import com.programm.projects.plus.maths.Vector2f;

import java.awt.*;

class ColoredRectRenderer implements IColoredShapeRenderer<Rect> {

    @Override
    public void render(IPencil pen, Vector2f pos, Vector2f scale, float unitSize, Color color, Rect shape, boolean fill) {
        pen.setColor(color);

        float w = shape.size.getX() * scale.getX() * unitSize;
        float h = shape.size.getY() * scale.getY() * unitSize;

        float x = (pos.getX() + shape.position.getX()) * unitSize - w * 0.5f;
        float y = (pos.getY() + shape.position.getY()) * unitSize - h * 0.5f;

        if(fill){
            pen.fillRectangle(x, y, w, h);
        }
        else {
            pen.drawRectangle(x, y, w, h);
        }
    }
}
