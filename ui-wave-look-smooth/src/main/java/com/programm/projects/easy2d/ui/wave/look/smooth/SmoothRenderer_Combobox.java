package com.programm.projects.easy2d.ui.wave.look.smooth;

import com.programm.projects.easy2d.engine.api.IPencil;
import com.programm.projects.easy2d.ui.wave.core.GlobalWaveDefaults;
import com.programm.projects.easy2d.ui.wave.core.IWaveComponentRenderer;
import com.programm.projects.easy2d.ui.wave.core.bounds.IBounds;
import com.programm.projects.easy2d.ui.wave.core.bounds.IEditableBounds;
import com.programm.projects.easy2d.ui.wave.core.utils.GFXUtils;
import com.programm.projects.easy2d.ui.wave.elements.AbstractTextComponent;
import com.programm.projects.easy2d.ui.wave.elements.Combobox;
import com.programm.projects.easy2d.ui.wave.elements.ITextComponent;
import com.programm.projects.easy2d.ui.wave.elements.Label;
import com.programm.projects.easy2d.ui.wave.elements.layout.ILayout;

import java.awt.*;
import java.util.Objects;

public class SmoothRenderer_Combobox implements IWaveComponentRenderer<Combobox<?>> {

    private static class SmoothItemRenderer extends AbstractTextComponent {
        static {
            GlobalWaveDefaults.setBaseDefault(SmoothItemRenderer.class, "renderer", new Label.DefaultLabelRenderer());
            GlobalWaveDefaults.setBaseDefault(SmoothItemRenderer.class, "primary", null);
            GlobalWaveDefaults.setBaseDefault(SmoothItemRenderer.class, "secondary", null);
            GlobalWaveDefaults.setBaseDefault(SmoothItemRenderer.class, "disabledColor", Color.LIGHT_GRAY);
            GlobalWaveDefaults.setBaseDefault(SmoothItemRenderer.class, "textColor", Color.BLACK);
        }
    }

    static {
        LookAndFeelSmooth.overrideBaseDefault(Combobox.class, "primary", LookAndFeelSmooth.COLOR_BORDER_BLUE);
        LookAndFeelSmooth.overrideBaseDefault(Combobox.class, "secondary", LookAndFeelSmooth.COLOR_BACKGROUND_WHITE);
        LookAndFeelSmooth.overrideBaseDefault(Combobox.class, "textColor", Color.BLACK);
        LookAndFeelSmooth.overrideBaseDefault(Combobox.class, "itemRenderer", new SmoothItemRenderer());
    }

    private final int edge;

    public SmoothRenderer_Combobox(int edge) {
        this.edge = edge;
    }

    @Override
    public void render(IBounds bounds, IPencil pen, Combobox<?> c) {
        if(c.secondary() != null){
            pen.setColor(c.secondary().get());
            pen.fillRoundRectangle(bounds.x(), bounds.y(), bounds.width(), bounds.height(), edge);
        }

        if(c.primary() != null){
            pen.setColor(GFXUtils.primaryOrDisabled(c));
            pen.drawRoundRectangle(bounds.x(), bounds.y(), bounds.width(), bounds.height(), edge);
        }

        if(c.selectedIndex().get() != -1){
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

        if(c.itemsExpanded().get()){
            ITextComponent itemRenderer = c.itemRenderer().get();
            int selectedIndex = c.selectedIndex().get();
            int hoveredIndex = c.hoveredIndex().get();

            float startY = bounds.y();
            float itemHeight = c.minHeight(pen);
            float itemX = bounds.x();
            float itemWidth = bounds.width();
            float allItemsHeight = itemHeight * (c.items().size() - 1) + bounds.height();

            if(selectedIndex != -1){
                startY -= itemHeight * selectedIndex;
            }
            else {
                startY += bounds.height();
            }

            float curY = startY;

            pen.setColor(Color.WHITE);
            pen.fillRectangle(itemX, startY, itemWidth, allItemsHeight);

            for(int i=0;i<c.items().size();i++){
                Object item = c.items().get(i);
                String _item = Objects.toString(item);
                IEditableBounds renderBounds = c.itemRenderBounds().get(i);

                renderBounds.bounds(itemX, curY, itemWidth, itemHeight);

                if(selectedIndex == i){
                    renderBounds.height(bounds.height());
                }

                if(hoveredIndex == i){
                    pen.setColor(Color.LIGHT_GRAY);
                    pen.fillRectangle(renderBounds.x(), renderBounds.y(), renderBounds.width(), renderBounds.height());
                }

                itemRenderer.text().set(_item);
                itemRenderer.render(renderBounds, pen, true);

                curY += renderBounds.height();
            }

            pen.setColor(Color.BLACK);
            pen.drawRectangle(itemX, startY, itemWidth, allItemsHeight);
        }
    }
}
