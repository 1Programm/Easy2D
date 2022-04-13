package com.programm.projects.easy2d.engine.simple;

import com.programm.project.easy2d.engine.api.IPencil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.function.Consumer;

class SimpleWindow extends WindowAdapter {

    private final JFrame frame;
    private final Canvas canvas;
    private final Consumer<IPencil> renderMethod;
    private boolean closeRequested;

    private final GraphicsPencil pencil;

    public SimpleWindow(String title, int width, int height, Consumer<IPencil> renderMethod, SimpleKeyboard keyboard, SimpleMouse mouse){
        this.renderMethod = renderMethod;

        this.frame = new JFrame(title);
        this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.frame.setResizable(true);
        this.frame.addWindowListener(this);

        Dimension dim = new Dimension(width, height);

        this.canvas = new Canvas();
        this.canvas.setPreferredSize(dim);

        this.canvas.addKeyListener(keyboard);
        this.canvas.addMouseListener(mouse);
        this.canvas.addMouseMotionListener(mouse);

        this.pencil = new GraphicsPencil(canvas);

        this.frame.add(canvas);
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
    }

    public void draw(){
        BufferStrategy bs = canvas.getBufferStrategy();

        if(bs == null){
            canvas.createBufferStrategy(2);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        pencil.setGraphics(g);

        renderMethod.accept(pencil);

        g.dispose();
        bs.show();
    }

    @Override
    public void windowClosing(WindowEvent e) {
        this.closeRequested = true;
    }

    public void show(){
        this.frame.setVisible(true);
    }

    public void close(){
        this.frame.dispose();
    }

    public boolean isCloseRequested() {
        return closeRequested;
    }

}
