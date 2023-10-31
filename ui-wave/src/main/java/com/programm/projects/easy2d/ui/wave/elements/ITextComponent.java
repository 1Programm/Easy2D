package com.programm.projects.easy2d.ui.wave.elements;

import com.programm.libraries.reactiveproperties.core.FloatProperty;
import com.programm.libraries.reactiveproperties.core.IntProperty;
import com.programm.libraries.reactiveproperties.core.ObjectProperty;
import com.programm.libraries.reactiveproperties.core.StringProperty;
import com.programm.projects.easy2d.engine.api.IPencil;
import com.programm.projects.easy2d.ui.wave.core.IWaveComponent;
import com.programm.projects.easy2d.ui.wave.core.bounds.IBounds;
import com.programm.projects.easy2d.ui.wave.core.utils.GFXUtils;
import com.programm.projects.easy2d.ui.wave.elements.layout.ILayout;

import java.awt.*;

public interface ITextComponent extends IWaveComponent {

    static void renderText(IBounds bounds, IPencil pen, ITextComponent c){
        String text = c.text().get();
        Color textColor = c.textColor().get();
        float fontSize = c.fontSize().get();//TODO: font handling
        int textAlign = c.textAlign().get();
        if(textColor != null && text != null){
            pen.setColor(GFXUtils.mixColor(textColor, c.disabledColor().get(), c.disabled().get()));

            if(textAlign == ILayout.ALIGN_CENTER) {
                pen.drawStringCentered(text, bounds.x() + bounds.width() / 2, bounds.y() + bounds.height() / 2);
            }
            else if(textAlign == ILayout.ALIGN_LEFT){
                pen.drawStringVCentered(text, bounds.x() + 2, bounds.y() + bounds.height() / 2);
            }
            else if(textAlign == ILayout.ALIGN_RIGHT){
                pen.drawStringVCenteredRightAligned(text, bounds.x() - 2, bounds.y() + bounds.height() / 2, bounds.width());
            }
        }
    }

    StringProperty text();
    ObjectProperty<Color> textColor();
    FloatProperty fontSize();
    IntProperty textAlign();

}
