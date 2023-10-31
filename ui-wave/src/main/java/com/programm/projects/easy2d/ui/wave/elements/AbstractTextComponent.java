package com.programm.projects.easy2d.ui.wave.elements;

import com.programm.libraries.reactiveproperties.core.*;
import com.programm.projects.easy2d.engine.api.IPencil;
import com.programm.projects.easy2d.ui.wave.core.GlobalWaveDefaults;
import com.programm.projects.easy2d.ui.wave.core.WaveComponent;
import com.programm.projects.easy2d.ui.wave.core.reactive.UIColorProperty;
import com.programm.projects.easy2d.ui.wave.core.reactive.UIFloatProperty;
import com.programm.projects.easy2d.ui.wave.core.reactive.UIIntProperty;
import com.programm.projects.easy2d.ui.wave.core.reactive.UIStringProperty;
import com.programm.projects.easy2d.ui.wave.elements.layout.ILayout;

import java.awt.*;

public abstract class AbstractTextComponent extends WaveComponent implements ITextComponent {

    static {
        GlobalWaveDefaults.setBaseDefault(AbstractTextComponent.class, "textColor", Color.BLACK);
        GlobalWaveDefaults.setBaseDefault(AbstractTextComponent.class, "fontSize", 11f);
        GlobalWaveDefaults.setBaseDefault(AbstractTextComponent.class, "textAlign", ILayout.ALIGN_CENTER);
    }

    private final UIStringProperty text = new UIStringProperty(getClass(), "text");
    private final UIColorProperty textColor = new UIColorProperty(getClass(), "textColor");
    private final UIFloatProperty fontSize = new UIFloatProperty(getClass(), "fontSize");
    private final UIIntProperty textAlign = new UIIntProperty(getClass(), "textAlign");

    protected Float minWidth, minHeight;

    public AbstractTextComponent() {
        text.listen(this::requestRecalculate);
        textColor.listen(this::requestRedraw);
        fontSize.listen(this::requestRecalculate);
        textAlign.listen(this::requestRedraw);
    }

    public AbstractTextComponent(String text) {
        this();
        this.text.set(text);
    }

    @Override
    public Float minWidth(IPencil pen) {
        if(minWidth == null){
            minWidth = pen.stringWidth(text.get()) + 4;
        }

        return minWidth;
    }

    @Override
    public Float minHeight(IPencil pen) {
        if(minHeight == null){
            minHeight = pen.stringHeight() + 4;
        }

        return minHeight;
    }

    @Override
    public StringProperty text() {
        return text;
    }

    @Override
    public ObjectProperty<Color> textColor() {
        return textColor;
    }

    @Override
    public FloatProperty fontSize() {
        return fontSize;
    }

    @Override
    public IntProperty textAlign() {
        return textAlign;
    }
}
