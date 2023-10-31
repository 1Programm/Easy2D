package com.programm.projects.easy2d.engine.api;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface IPencil {

    float stringHeight();
    float stringHeight(Font font);

    float stringWidth(String s);
    float stringWidth(Font font, String s);


    float xOffset();
    float yOffset();

    void xOffset(float x);
    void yOffset(float y);

    default void addOffset(float x, float y){
        setOffset(xOffset() + x, yOffset() + y);
    }

    default void setOffset(float x, float y){
        xOffset(x);
        yOffset(y);
    }

    void clip(float x, float y, float width, float height);
    void clearClip();


    void setColor(Color c);
    Color getColor();

    void setFont(Font f);
    Font getFont();


    void drawPixel(float x, float y);

    void drawLine(float x1, float y1, float x2, float y2);

    void drawRectangle(float x, float y, float width, float height);

    void fillRectangle(float x, float y, float width, float height);

    void drawRoundRectangle(float x, float y, float width, float height, float edge);

    void fillRoundRectangle(float x, float y, float width, float height, float edge);

    void drawCircle(float x, float y, float radius);

    void fillCircle(float x, float y, float radius);

    void drawOval(float x, float y, float width, float height);

    void fillOval(float x, float y, float width, float height);

    void drawString(String s, float x, float y);

    void drawStringCentered(String s, float x, float y);

    void drawStringVCentered(String s, float x, float y);

    void drawStringVCenteredRightAligned(String s, float x, float y, float width);

    void drawStringHCentered(String s, float x, float y);

    void drawImage(BufferedImage img, float x, float y, float width, float height);

    void fillScreen();

}
