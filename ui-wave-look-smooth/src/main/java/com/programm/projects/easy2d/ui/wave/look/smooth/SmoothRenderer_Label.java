package com.programm.projects.easy2d.ui.wave.look.smooth;

import com.programm.projects.easy2d.engine.api.IPencil;
import com.programm.projects.easy2d.ui.wave.core.IWaveComponentRenderer;
import com.programm.projects.easy2d.ui.wave.core.bounds.IBounds;
import com.programm.projects.easy2d.ui.wave.core.utils.GFXUtils;
import com.programm.projects.easy2d.ui.wave.elements.Label;
import com.programm.projects.easy2d.ui.wave.elements.layout.ILayout;

class SmoothRenderer_Label implements IWaveComponentRenderer<Label> {

    private final int edge;

    public SmoothRenderer_Label(int edge) {
        this.edge = edge;
    }

    @Override
    public void render(IBounds bounds, IPencil pen, Label c) {
        if(c.secondary() != null){
            pen.setColor(c.secondary().get());
            pen.fillRoundRectangle(bounds.x(), bounds.y(), bounds.width(), bounds.height(), edge);
        }

        if(c.primary() != null){
            pen.setColor(GFXUtils.primaryOrDisabled(c));
            pen.drawRoundRectangle(bounds.x(), bounds.y(), bounds.width(), bounds.height(), edge);
        }

        if(c.textColor() != null && c.text() != null){
            pen.setColor(GFXUtils.mixColor(c.textColor().get(), c.disabledColor().get(), c.disabled().get()));

            int textAlign = c.textAlign().get();
            if(textAlign == ILayout.ALIGN_CENTER) {
                pen.drawStringCentered(c.text().get(), bounds.x() + bounds.width() / 2, bounds.y() + bounds.height() / 2);
            }
            else if(textAlign == ILayout.ALIGN_LEFT){
                pen.drawStringVCentered(c.text().get(), bounds.x() + 2, bounds.y() + bounds.height() / 2);
            }
            else if(textAlign == ILayout.ALIGN_RIGHT){
                pen.drawStringVCenteredRightAligned(c.text().get(), bounds.x() - 2, bounds.y() + bounds.height() / 2, bounds.width());
            }
        }
    }
}
