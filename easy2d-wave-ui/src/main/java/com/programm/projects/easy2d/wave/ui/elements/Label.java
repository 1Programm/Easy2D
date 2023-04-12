package com.programm.projects.easy2d.wave.ui.elements;

import com.programm.project.easy2d.engine.api.IPencil;
import com.programm.projects.easy2d.wave.ui.core.GlobalWaveDefaults;
import com.programm.projects.easy2d.wave.ui.core.IWaveComponent;
import com.programm.projects.easy2d.wave.ui.core.IWaveComponentRenderer;
import com.programm.projects.easy2d.wave.ui.core.bounds.IBounds;
import com.programm.projects.easy2d.wave.ui.core.utils.GFXUtils;
import com.programm.projects.easy2d.wave.ui.elements.layout.ILayout;

import java.awt.*;

public class Label extends AbstractTextComponent {

    public static class DefaultLabelRenderer implements IWaveComponentRenderer<AbstractTextComponent> {

        @Override
        public void render(IBounds bounds, IPencil pen, AbstractTextComponent c) {
            Color secondary = c.secondary().get();
            if(secondary != null){
                pen.setColor(secondary);
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
        GlobalWaveDefaults.setBaseDefault(Label.class, "renderer", new DefaultLabelRenderer());
        GlobalWaveDefaults.setBaseDefault(Label.class, "primary", null);
        GlobalWaveDefaults.setBaseDefault(Label.class, "secondary", Color.WHITE);
        GlobalWaveDefaults.setBaseDefault(Label.class, "disabledColor", Color.LIGHT_GRAY);
    }

    public Label() {
    }

    public Label(String text) {
        super(text);
    }

    @Override
    public void render(IBounds bounds, IPencil pen, boolean forceRedraw) {
        super.render(bounds, pen, forceRedraw);
    }
}
