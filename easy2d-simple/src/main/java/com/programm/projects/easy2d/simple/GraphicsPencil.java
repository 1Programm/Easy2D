package com.programm.projects.easy2d.simple;

import com.programm.project.easy2d.api.IPencil;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GraphicsPencil implements IPencil {

    private final Canvas canvas;
    private Graphics g;

    public GraphicsPencil(Canvas canvas) {
        this.canvas = canvas;
    }

    public void setGraphics(Graphics g) {
        this.g = g;
    }

    @Override
    public void setColor(Color c) {
        g.setColor(c);
    }

    @Override
    public void drawPixel(float x, float y) {
        g.drawLine((int)x, (int)y, (int)x, (int)y);
    }

    @Override
    public void drawLine(float x1, float y1, float x2, float y2) {
        g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
    }

    @Override
    public void drawRectangle(float x, float y, float width, float height) {
        g.drawRect((int)x, (int)y, (int)width, (int)height);
    }

    @Override
    public void fillRectangle(float x, float y, float width, float height) {
        g.fillRect((int)x, (int)y, (int)width, (int)height);
    }

    @Override
    public void drawImage(BufferedImage img, float x, float y, float width, float height) {
        g.drawImage(img, (int)x, (int)y, (int)width, (int)height, null);
    }

    @Override
    public void fillScreen() {
        g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
}
