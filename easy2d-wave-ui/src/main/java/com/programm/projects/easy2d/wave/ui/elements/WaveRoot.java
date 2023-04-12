package com.programm.projects.easy2d.wave.ui.elements;

import com.programm.project.easy2d.engine.api.*;
import com.programm.projects.easy2d.wave.ui.core.GlobalComponentUtils;
import com.programm.projects.easy2d.wave.ui.core.bounds.ValueBounds;

public class WaveRoot {

    private final IContext context;
    private final ValueBounds windowBounds = new ValueBounds();
    private View contentView;

    public WaveRoot(IContext context, View contentView) {
        this.context = context;
        this.contentView = contentView;

        context.mouse().onMousePressed(this::onMousePressed);
        context.mouse().onMouseReleased(this::onMouseReleased);
        context.mouse().onMouseMoved(this::onMouseMoved);
        context.mouse().onMouseDragged(this::onMouseDragged);
        context.mouse().onMouseScrolled(this::onMouseScrolled);

        context.keyboard().onKeyPressed(this::onKeyPressed);
        context.keyboard().onKeyReleased(this::onKeyReleased);
    }

    public WaveRoot(IContext context) {
        this(context, null);
    }

    public void render(IPencil pen){
        windowBounds.bounds(0, 0, context.window().width(), context.window().height());
        if(contentView != null){
            contentView.render(windowBounds, pen, GlobalComponentUtils.shouldRecalculate(contentView).get());
        }
    }

    private void onMousePressed(IMouse mouse, int button) {
        if(contentView != null) contentView.onMousePressed(windowBounds, mouse, button);
    }

    private void onMouseReleased(IMouse mouse, int button) {
        if(contentView != null) contentView.onMouseReleased(windowBounds, mouse, button);
    }

    private void onMouseMoved(IMouse mouse) {
        if(contentView != null) contentView.onMouseMoved(windowBounds, mouse);
    }

    private void onMouseDragged(IMouse mouse, int button) {
        if(contentView != null) contentView.onMouseDragged(windowBounds, mouse, button);
    }

    private void onMouseScrolled(IMouse mouse, float scrollX, float scrollY) {
        if(contentView != null) contentView.onMouseScrolled(windowBounds, mouse, scrollX, scrollY);
    }

    private void onKeyPressed(IKeyboard keyboard, int key) {
        if(contentView != null) contentView.onKeyPressed(windowBounds, keyboard, key);
    }

    private void onKeyReleased(IKeyboard keyboard, int key) {
        if(contentView != null) contentView.onKeyReleased(windowBounds, keyboard, key);
    }

    public void setContentView(View contentView) {
        this.contentView = contentView;
    }
}
