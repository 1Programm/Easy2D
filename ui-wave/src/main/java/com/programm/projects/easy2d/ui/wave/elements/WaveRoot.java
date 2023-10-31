package com.programm.projects.easy2d.ui.wave.elements;

import com.programm.libraries.reactiveproperties.ObservableNotifier;
import com.programm.libraries.reactiveproperties.core.BoolProperty;
import com.programm.projects.easy2d.engine.api.*;
import com.programm.projects.easy2d.ui.wave.core.GlobalComponentUtils;
import com.programm.projects.easy2d.ui.wave.core.bounds.ValueBounds;

public class WaveRoot {

    private final IContext context;
    private final ValueBounds windowBounds = new ValueBounds();
    private final ObservableNotifier windowBoundsChangeNotifier = new ObservableNotifier();
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

        context.window().onWindowResized(this::onWindowResized);


        this.windowBoundsChangeNotifier
                .fDebounce(50)
                .listen(this::contentViewRecalculate);
    }

    public WaveRoot(IContext context) {
        this(context, null);
    }

    public void render(IPencil pen){
        if(contentView != null){
            BoolProperty shouldRecalculate = GlobalComponentUtils.shouldRecalculate(contentView);

            if(shouldRecalculate.get()){
                contentView.render(windowBounds, pen, true);
                shouldRecalculate.set(false);
            }
            else {
                contentView.render(windowBounds, pen, false);
            }
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

    private void onWindowResized(IWindow window, int width, int height){
        windowBounds.bounds(0, 0, width, height);
        windowBoundsChangeNotifier.call();
    }

    private void contentViewRecalculate() {
        GlobalComponentUtils.shouldRecalculate(contentView).set(true);
    }

    public void setContentView(View contentView) {
        this.contentView = contentView;
    }
}
