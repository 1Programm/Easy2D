package com.programm.projects.easy2d.objects.api.components.gfx;

import com.programm.project.easy2d.engine.api.IPencil;
import com.programm.projects.easy2d.objects.api.components.shape.Circle;
import com.programm.projects.easy2d.objects.api.components.shape.Rect;
import com.programm.projects.easy2d.objects.api.components.shape.Shape;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@SuppressWarnings("all")
public class ColoredShape extends GfxComponent {

    private static final Map<Class<? extends Shape>, Supplier<IColoredShapeRenderer>> SHAPE_RENDERER_MAP = new HashMap<>();
    static {
        SHAPE_RENDERER_MAP.put(Circle.class, ColoredCircleRenderer::new);
        SHAPE_RENDERER_MAP.put(Rect.class, ColoredRectRenderer::new);
    }

    private final Color color;
    private final Shape shape;

    private final boolean fill;

    private final IColoredShapeRenderer shapeRenderer;

    public ColoredShape(Color color, Shape shape, boolean fill) {
        this.color = color;
        this.shape = shape;
        this.fill = fill;

        Supplier<IColoredShapeRenderer> shapeRendererSupplier = SHAPE_RENDERER_MAP.get(shape.getClass());
        if(shapeRendererSupplier == null) throw new IllegalStateException("Shape [" + shape.getClass() + "] not supported by " + this.getClass() + "!");

        this.shapeRenderer = shapeRendererSupplier.get();
    }

    public ColoredShape(Color color, Shape shape) {
        this(color, shape, true);
    }

    @Override
    public void render(IPencil pen) {
        shapeRenderer.render(pen, gameObject.position, gameObject.size, objectHandler.unitSize(), color, shape, fill);
    }
}
