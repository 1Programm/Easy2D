package com.programm.projects.easy2d.engine.simple;

import com.programm.project.easy2d.engine.api.IPencil;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GraphicsPencil implements IPencil {

    private final Canvas canvas;
    private Graphics g;
    private float xOff, yOff;

    public GraphicsPencil(Canvas canvas) {
        this.canvas = canvas;
    }

    public void setGraphics(Graphics g) {
        this.g = g;
    }

    @Override
    public float stringHeight() {
        return g.getFontMetrics().getHeight();
    }

    @Override
    public float stringHeight(Font font) {
        return g.getFontMetrics(font).getHeight();
    }

    @Override
    public float stringWidth(String s) {
        return g.getFontMetrics().stringWidth(s);
    }

    @Override
    public float stringWidth(Font font, String s) {
        return g.getFontMetrics(font).stringWidth(s);
    }

    @Override
    public float xOffset() {
        return xOff;
    }

    @Override
    public float yOffset() {
        return yOff;
    }

    @Override
    public void xOffset(float x) {
        this.xOff = x;
    }

    @Override
    public void yOffset(float y) {
        this.yOff = y;
    }

    @Override
    public void clip(float x, float y, float width, float height) {
        g.clipRect((int)x, (int)y, (int)width, (int)height);
    }

    @Override
    public void clearClip() {
        g.setClip(null);
    }

    @Override
    public void setColor(Color c) {
        g.setColor(c);
    }

    @Override
    public Color getColor() {
        return g.getColor();
    }

    @Override
    public void setFont(Font f) {
        g.setFont(f);
    }

    @Override
    public Font getFont() {
        return g.getFont();
    }

    @Override
    public void drawPixel(float x, float y) {
        x += xOff;
        y += yOff;
        g.drawLine((int)x, (int)y, (int)x, (int)y);
    }

    @Override
    public void drawLine(float x1, float y1, float x2, float y2) {
        x1 += xOff;
        y1 += yOff;
        x2 += xOff;
        y2 += yOff;
        g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
    }

    @Override
    public void drawRectangle(float x, float y, float width, float height) {
        x += xOff;
        y += yOff;
        g.drawRect((int)x, (int)y, (int)width, (int)height);
    }

    @Override
    public void fillRectangle(float x, float y, float width, float height) {
        x += xOff;
        y += yOff;
        g.fillRect((int)x, (int)y, (int)width, (int)height);
    }

    @Override
    public void drawRoundRectangle(float x, float y, float width, float height, float edge) {
        x += xOff;
        y += yOff;
        g.drawRoundRect((int)x, (int)y, (int)width, (int)height, (int)edge, (int)edge);
    }

    @Override
    public void fillRoundRectangle(float x, float y, float width, float height, float edge) {
        x += xOff;
        y += yOff;
        g.fillRoundRect((int)x, (int)y, (int)width, (int)height, (int)edge, (int)edge);
    }

    @Override
    public void drawCircle(float x, float y, float radius) {
        float dia = radius * 2;
        x += xOff;
        y += yOff;
        g.drawOval((int)(x - radius), (int)(y - radius), (int)dia, (int)dia);
    }

    @Override
    public void fillCircle(float x, float y, float radius) {
        float dia = radius * 2;
        x += xOff;
        y += yOff;
        g.fillOval((int)(x - radius), (int)(y - radius), (int)dia, (int)dia);
    }

    @Override
    public void drawOval(float x, float y, float width, float height) {
        x += xOff;
        y += yOff;
        g.drawOval((int)x, (int)y, (int)width, (int)height);
    }

    @Override
    public void fillOval(float x, float y, float width, float height) {
        x += xOff;
        y += yOff;
        g.fillOval((int)x, (int)y, (int)width, (int)height);
    }

    @Override
    public void drawString(String s, float x, float y) {
        x += xOff;
        y += yOff;
        g.drawString(s, (int)x, (int)y);
    }

    @Override
    public void drawStringCentered(String s, float x, float y) {
        float strWidth = g.getFontMetrics().stringWidth(s);
        float strHeight = g.getFontMetrics().getHeight();
        x += xOff;
        y += yOff;

        g.drawString(s, (int)(x - strWidth / 2), (int)(y + strHeight / 2 - 1));
    }

    @Override
    public void drawStringVCentered(String s, float x, float y) {
        float strHeight = g.getFontMetrics().getHeight();
        x += xOff;
        y += yOff;
        g.drawString(s, (int)(x), (int)(y + strHeight / 2 - 1));
    }

    @Override
    public void drawStringVCenteredRightAligned(String s, float x, float y, float width) {
        float strHeight = g.getFontMetrics().getHeight();
        float strWidth = g.getFontMetrics().stringWidth(s);

        g.drawString(s, (int)(x + width - strWidth), (int)(y + strHeight / 2.5));
    }

    @Override
    public void drawStringHCentered(String s, float x, float y) {
        float strWidth = g.getFontMetrics().stringWidth(s);
        x += xOff;
        y += yOff;
        g.drawString(s, (int)(x - strWidth / 2), (int)(y));
    }

    @Override
    public void drawImage(BufferedImage img, float x, float y, float width, float height) {
        x += xOff;
        y += yOff;
        g.drawImage(img, (int)x, (int)y, (int)width, (int)height, null);
    }

    @Override
    public void fillScreen() {
        g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
}
