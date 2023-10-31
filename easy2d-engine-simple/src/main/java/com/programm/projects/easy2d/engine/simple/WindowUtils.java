package com.programm.projects.easy2d.engine.simple;

import com.programm.project.easy2d.engine.api.IWindow;

import java.awt.*;

class WindowUtils {

    public static void moveWindowToScreen(IWindow window, int screen){
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gd = ge.getScreenDevices();

        if(screen < 0 || screen >= gd.length) throw new RuntimeException("No screen found with index [" + screen + "]!");

        GraphicsDevice selectedScreen = gd[screen];
        GraphicsConfiguration screenConfigs = selectedScreen.getDefaultConfiguration();
        Rectangle screenBounds = screenConfigs.getBounds();

        int midX = (int)(screenBounds.getCenterX() - window.width() / 2.0);
        int midY = (int)(screenBounds.getCenterY() - window.height() / 2.0);

        window.position(midX, midY);
    }

}
