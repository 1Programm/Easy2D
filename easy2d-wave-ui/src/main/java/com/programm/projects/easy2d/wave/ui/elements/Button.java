package com.programm.projects.easy2d.wave.ui.elements;

import com.programm.libraries.reactiveproperties.core.*;
import com.programm.project.easy2d.engine.api.IMouse;
import com.programm.project.easy2d.engine.api.IPencil;
import com.programm.projects.easy2d.wave.ui.core.GlobalWaveDefaults;
import com.programm.projects.easy2d.wave.ui.core.IWaveComponent;
import com.programm.projects.easy2d.wave.ui.core.IWaveComponentRenderer;
import com.programm.projects.easy2d.wave.ui.core.bounds.IBounds;
import com.programm.projects.easy2d.wave.ui.core.reactive.UIBoolProperty;
import com.programm.projects.easy2d.wave.ui.core.reactive.UIColorProperty;
import com.programm.projects.easy2d.wave.ui.core.utils.GFXUtils;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Button extends Label {

    private static class DefaultButtonRenderer implements IWaveComponentRenderer<Button> {

        @Override
        public void render(IBounds bounds, IPencil pen, Button c) {
            Color secondary = c.secondary().get();
            if(secondary != null){
                pen.setColor(GFXUtils.mixColor(secondary, c.pressedColor().get(), c.pressed().get(), c.hoveredColor().get(), c.hovered().get()));
                IWaveComponent.fillBounds(pen, bounds);
            }

            Color color = GFXUtils.primaryOrDisabled(c);
            if(color != null){
                pen.setColor(color);
                IWaveComponent.drawBounds(pen, bounds);
            }

            ITextComponent.renderText(bounds, pen, c);
        }
    }

    static {
        GlobalWaveDefaults.setBaseDefault(Button.class, "renderer", new DefaultButtonRenderer());
        GlobalWaveDefaults.setBaseDefault(Button.class, "primary", Color.BLACK);
        GlobalWaveDefaults.setBaseDefault(Button.class, "secondary", Color.WHITE);
        GlobalWaveDefaults.setBaseDefault(Button.class, "disabledColor", Color.LIGHT_GRAY);
        GlobalWaveDefaults.setBaseDefault(Button.class, "pressedColor", Color.LIGHT_GRAY);
        GlobalWaveDefaults.setBaseDefault(Button.class, "hoverColor", new Color(220, 220, 220));
        GlobalWaveDefaults.setBaseDefault(Button.class, "pressed", false);
        GlobalWaveDefaults.setBaseDefault(Button.class, "hovered", false);
    }

    private final UIColorProperty pressedColor = new UIColorProperty(getClass(), "pressedColor");
    private final UIColorProperty hoveredColor = new UIColorProperty(getClass(), "hoveredColor");
    private final UIBoolProperty pressed = new UIBoolProperty(getClass(), "pressed");
    private final UIBoolProperty hovered = new UIBoolProperty(getClass(), "hovered");


    public Button(String text) {
        super(text);

        pressedColor.listen(this::requestRedraw);
        hoveredColor.listen(this::requestRedraw);
    }

    public Button() {
        this(null);
    }

    @Override
    public void onMousePressed(IBounds bounds, IMouse mouse, int button) {
        if(button == MouseEvent.BUTTON1) {
            if(bounds.inside(mouse.x(), mouse.y())) {
                pressed.set(true);
                requestRedraw();
            }
        }
    }

    @Override
    public void onMouseReleased(IBounds bounds, IMouse mouse, int button) {
        if(button == MouseEvent.BUTTON1) {
            if(pressed.get()) {
                pressed.set(false);
                requestRedraw();
            }
        }
    }

    @Override
    public void onMouseMoved(IBounds bounds, IMouse mouse) {
        if(bounds.inside(mouse.x(), mouse.y())){
            if(!hovered.get()) {
                hovered.set(true);
                requestRedraw();
            }
        }
        else if(hovered.get()){
            hovered.set(false);
            requestRedraw();
        }
    }

    public ObjectProperty<Color> pressedColor(){
        return pressedColor;
    }

    public ObjectProperty<Color> hoveredColor(){
        return hoveredColor;
    }

    public BoolObservable pressed(){
        return pressed;
    }

    public BoolObservable hovered(){
        return hovered;
    }
}
