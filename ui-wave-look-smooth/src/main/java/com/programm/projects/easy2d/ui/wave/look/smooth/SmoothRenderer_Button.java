package com.programm.projects.easy2d.ui.wave.look.smooth;

import com.programm.projects.easy2d.engine.api.IPencil;
import com.programm.projects.easy2d.ui.wave.core.IWaveComponentRenderer;
import com.programm.projects.easy2d.ui.wave.core.bounds.IBounds;
import com.programm.projects.easy2d.ui.wave.core.utils.GFXUtils;
import com.programm.projects.easy2d.ui.wave.elements.Button;
import com.programm.projects.easy2d.ui.wave.elements.layout.ILayout;

class SmoothRenderer_Button implements IWaveComponentRenderer<Button> {

    static {
        LookAndFeelSmooth.overrideBaseDefault(Button.class, "primary", LookAndFeelSmooth.COLOR_BORDER_BLUE);
        LookAndFeelSmooth.overrideBaseDefault(Button.class, "secondary", LookAndFeelSmooth.COLOR_BACKGROUND_WHITE);
        LookAndFeelSmooth.overrideBaseDefault(Button.class, "hoverColor", LookAndFeelSmooth.COLOR_BACKGROUND_HOVER);
        LookAndFeelSmooth.overrideBaseDefault(Button.class, "pressedColor", LookAndFeelSmooth.COLOR_BACKGROUND_PRESSED);
    }

    private final int edge;

    public SmoothRenderer_Button(int edge) {
        this.edge = edge;
    }

    @Override
    public void render(IBounds bounds, IPencil pen, Button c) {
        if(c.secondary() != null){
            pen.setColor(GFXUtils.mixColor(c.secondary().get(), c.pressedColor().get(), c.pressed().get(), c.hoveredColor().get(), c.hovered().get()));
            pen.fillRoundRectangle(bounds.x(), bounds.y(), bounds.width(), bounds.height(), edge);
        }

        if(c.primary() != null){
            pen.setColor(GFXUtils.primaryOrDisabled(c));
            pen.drawRoundRectangle(bounds.x(), bounds.y(), bounds.width(), bounds.height(), edge);
        }

        if(c.textColor() != null && c.text() != null){
            pen.setColor(GFXUtils.mixColor(c.textColor().get(), c.disabledColor().get(), c.disabled().get()));

            int align = c.textAlign().get();
            if(align == ILayout.ALIGN_CENTER) {
                pen.drawStringCentered(c.text().get(), bounds.x() + bounds.width() / 2, bounds.y() + bounds.height() / 2);
            }
            else if(align == ILayout.ALIGN_LEFT){
                pen.drawStringVCentered(c.text().get(), bounds.x() + 2, bounds.y() + bounds.height() / 2);
            }
            else if(align == ILayout.ALIGN_RIGHT){
                pen.drawStringVCenteredRightAligned(c.text().get(), bounds.x() - 2, bounds.y() + bounds.height() / 2, bounds.width());
            }
        }
    }
}
