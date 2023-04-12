package com.programm.projects.easy2d.ui.advanced;

import com.programm.project.easy2d.engine.api.IPencil;
import com.programm.projects.easy2d.engine.simple.SimpleEngine;
import com.programm.projects.easy2d.ui.simple.Label;
import com.programm.projects.easy2d.ui.simple.Panel;
import com.programm.projects.easy2d.ui.simple.Slider;
import com.programm.projects.easy2d.ui.simple.TabView;

import java.awt.*;

public class Test extends SimpleEngine {

    public static void main(String[] args) {
        new Test("A", 600, 500, 30).start();
    }

    public Test(String title, int width, int height, float fps) {
        super(title, width, height, fps);
    }

    private final TabView tabView = new TabView(100, 100, 250, 350);

    @Override
    protected void init() {
        Panel p1 = tabView.newTab("A");
        Panel p2 = tabView.newTab("B");
        Panel p3 = tabView.newTab("TEST");

        p1.add(new Slider(100, 100, 100, 11).range(1, 8).steps(new float[]{1, 2, 5, 8}));
        p2.add(new Label(150, 0, 100, 20, "Test: B"));
        p3.add(new Label(10, 120, 100, 20, "Test: TEST"));

        tabView.initBindings(this);
    }

    @Override
    public void update() {
        tabView.update(0, 0);
    }

    @Override
    public void render(IPencil pen) {
        pen.setColor(Color.LIGHT_GRAY);
        pen.fillScreen();
        tabView.render(pen, 0, 0);
    }
}
