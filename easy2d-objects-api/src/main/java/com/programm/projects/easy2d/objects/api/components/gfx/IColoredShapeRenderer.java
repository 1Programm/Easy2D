package com.programm.projects.easy2d.objects.api.components.gfx;

import com.programm.project.easy2d.engine.api.IPencil;
import com.programm.projects.easy2d.objects.api.components.shape.Shape;
import com.programm.projects.plus.maths.Vector2f;

import java.awt.*;

interface IColoredShapeRenderer<T extends Shape> {

    void render(IPencil pen, Vector2f pos, Vector2f scale, float unitSize, Color color, T shape, boolean fill);

}
