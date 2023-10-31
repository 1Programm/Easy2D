package com.programm.projects.easy2d.objects.api.components.gfx;

import com.programm.projects.easy2d.engine.api.IPencil;
import com.programm.projects.easy2d.objects.api.components.shape.Circle;
import com.programm.projects.plus.maths.Vector2f;

import java.awt.*;

class ColoredCircleRenderer implements IColoredShapeRenderer<Circle> {

    @Override
    public void render(IPencil pen, Vector2f pos, Vector2f scale, float unitSize, Color color, Circle shape, boolean fill) {
        pen.setColor(color);

        float x = (pos.getX() + shape.position.getX()) * unitSize;
        float y = (pos.getY() + shape.position.getY()) * unitSize;
        float r = shape.radius * Math.max(scale.getX(), scale.getY()) * unitSize;

        if(fill){
            pen.fillCircle(x, y, r);
        }
        else {
            pen.drawCircle(x, y, r);
        }
    }
}
