package com.programm.project.easy2d.api;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface IPencil {

    void setColor(Color c);

    void drawPixel(float x, float y);

    void drawLine(float x1, float y1, float x2, float y2);

    void drawRectangle(float x, float y, float width, float height);

    void fillRectangle(float x, float y, float width, float height);

    void drawImage(BufferedImage img, float x, float y, float width, float height);

    void fillScreen();

}
