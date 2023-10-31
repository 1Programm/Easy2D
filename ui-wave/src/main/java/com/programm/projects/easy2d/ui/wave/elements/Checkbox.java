package com.programm.projects.easy2d.ui.wave.elements;

import com.programm.libraries.reactiveproperties.core.BoolProperty;
import com.programm.projects.easy2d.engine.api.IMouse;
import com.programm.projects.easy2d.engine.api.IPencil;
import com.programm.projects.easy2d.ui.wave.core.GlobalWaveDefaults;
import com.programm.projects.easy2d.ui.wave.core.IWaveComponent;
import com.programm.projects.easy2d.ui.wave.core.IWaveComponentRenderer;
import com.programm.projects.easy2d.ui.wave.core.WaveComponent;
import com.programm.projects.easy2d.ui.wave.core.bounds.IBounds;
import com.programm.projects.easy2d.ui.wave.core.reactive.UIBoolProperty;
import com.programm.projects.easy2d.ui.wave.core.utils.GFXUtils;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Checkbox extends WaveComponent {

    private static class DefaultCheckboxRenderer implements IWaveComponentRenderer<Checkbox> {

        @Override
        public void render(IBounds bounds, IPencil pen, Checkbox c) {
            Color secondary = c.secondary().get();
            if(secondary!= null){
                pen.setColor(secondary);
                IWaveComponent.fillBounds(pen, bounds);
            }

            Color color = GFXUtils.primaryOrDisabled(c);
            if(color != null){
                pen.setColor(color);
                IWaveComponent.drawBounds(pen, bounds);

                if(c.checked.get()) {
                    float x1 = bounds.x();
                    float y1 = bounds.y();
                    float x2 = x1 + bounds.width();
                    float y2 = y1 + bounds.height();

                    pen.drawLine(x1, y1, x2, y2);
                    pen.drawLine(x2, y1, x1, y2);
                }
            }
        }
    }

    static {
        GlobalWaveDefaults.setBaseDefault(Checkbox.class, "renderer", new DefaultCheckboxRenderer());
        GlobalWaveDefaults.setBaseDefault(Checkbox.class, "primary", Color.BLACK);
        GlobalWaveDefaults.setBaseDefault(Checkbox.class, "secondary", Color.WHITE);
        GlobalWaveDefaults.setBaseDefault(Checkbox.class, "disabledColor", Color.LIGHT_GRAY);
        GlobalWaveDefaults.setBaseDefault(Checkbox.class, "checked", false);
    }

    private final UIBoolProperty checked = new UIBoolProperty(getClass(), "checked");

    @Override
    public Float minWidth(IPencil pen) {
        return 16f;
    }

    @Override
    public Float minHeight(IPencil pen) {
        return 16f;
    }

    @Override
    public void onMousePressed(IBounds bounds, IMouse mouse, int button) {
        if(disabled().get()) return;
        if(button == MouseEvent.BUTTON1) {
            if(bounds.inside(mouse.x(), mouse.y())) {
                checked.invert();
                requestRedraw();
            }
        }
    }

    public BoolProperty checked(){
        return checked;
    }

}
