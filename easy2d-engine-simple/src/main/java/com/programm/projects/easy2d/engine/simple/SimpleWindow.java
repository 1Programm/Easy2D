package com.programm.projects.easy2d.engine.simple;

import com.programm.project.easy2d.engine.api.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

class SimpleWindow extends WindowAdapter implements IWindow {

    private final JFrame frame;
    private final Canvas canvas;
    private IEngine engine;
    private IKeyboard keyboard;
    private IMouse mouse;
    private boolean closeRequested;

    private final GraphicsPencil pencil;

    public SimpleWindow(String title, int width, int height){
        this.frame = new JFrame(title);
        this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.frame.setResizable(true);
        this.frame.addWindowListener(this);

        Dimension dim = new Dimension(width, height);

        this.canvas = new Canvas();
        this.canvas.setPreferredSize(dim);

        this.pencil = new GraphicsPencil(canvas);

        this.frame.add(canvas);
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
    }

    @Override
    public void init(IEngine engine, IKeyboard keyboard, IMouse mouse) {
        this.engine = engine;
        this.keyboard = keyboard;
        this.mouse = mouse;

        if(!(keyboard instanceof KeyListener)) throw new IllegalStateException("Keyboard must implement the KeyListener interface!");
        if(!(mouse instanceof MouseListener)) throw new IllegalStateException("Mouse must implement the MouseListener interface!");
        if(!(mouse instanceof MouseMotionListener)) throw new IllegalStateException("Mouse must implement the MouseMotionListener interface!");
        if(!(mouse instanceof MouseWheelListener)) throw new IllegalStateException("Mouse must implement the MouseWheelListener interface!");

        this.canvas.addKeyListener((KeyListener) keyboard);
        this.canvas.addMouseListener((MouseListener) mouse);
        this.canvas.addMouseMotionListener((MouseMotionListener) mouse);
        this.canvas.addMouseWheelListener((MouseWheelListener)mouse);
    }

    @Override
    public void draw(){
        BufferStrategy bs = canvas.getBufferStrategy();

        if(bs == null){
            canvas.createBufferStrategy(2);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        pencil.setGraphics(g);

        engine.render(pencil);

        g.dispose();
        bs.show();
    }

    @Override
    public void windowClosing(WindowEvent e) {
        this.closeRequested = true;
    }

    @Override
    public void show(){
        this.frame.setVisible(true);
    }

    @Override
    public void close(){
        this.frame.dispose();
    }

    @Override
    public void setTitle(String title) {
        frame.setTitle(title);
    }

    @Override
    public void setResizable(boolean resizable) {
        frame.setResizable(resizable);
    }

    @Override
    public boolean isCloseRequested() {
        return closeRequested;
    }

    @Override
    public int width() {
        return canvas.getWidth();
    }

    @Override
    public int height() {
        return canvas.getHeight();
    }
}
