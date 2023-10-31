package com.programm.projects.easy2d.ui.wave.look.smooth;

import com.programm.projects.easy2d.engine.api.IPencil;
import com.programm.projects.easy2d.ui.wave.core.IWaveComponentRenderer;
import com.programm.projects.easy2d.ui.wave.core.bounds.IBounds;
import com.programm.projects.easy2d.ui.wave.core.utils.GFXUtils;
import com.programm.projects.easy2d.ui.wave.elements.Checkbox;

public class SmoothRenderer_Checkbox implements IWaveComponentRenderer<Checkbox> {

//    private static final MipMap MIPMAP_CHECKED = new MipMap(true);

    static {
//        try {
//            MIPMAP_CHECKED.loadSizedResource("/icons8-check-32.png", 16);
//            MIPMAP_CHECKED.loadSizedResource("/icons8-check-96.png", 24);
//            MIPMAP_CHECKED.loadResources("/icons8-check-32.png", "/icons8-check-48.png", "/icons8-check-96.png", "/icons8-check-128.png");
//        }
//        catch (IOException e){
//            throw new IllegalStateException("INVALID STATE!", e);
//        }

        LookAndFeelSmooth.overrideBaseDefault(Checkbox.class, "primary", LookAndFeelSmooth.COLOR_BORDER_BLUE);
        LookAndFeelSmooth.overrideBaseDefault(Checkbox.class, "secondary", LookAndFeelSmooth.COLOR_BACKGROUND_WHITE);
    }

    private final int edge;

    public SmoothRenderer_Checkbox(int edge) {
        this.edge = edge;
    }

    @Override
    public void render(IBounds bounds, IPencil pen, Checkbox c) {
        if(c.secondary() != null){
            pen.setColor(c.secondary().get());
            pen.fillRoundRectangle(bounds.x(), bounds.y(), bounds.width(), bounds.height(), edge);
        }

        if(c.primary() != null){
            pen.setColor(GFXUtils.primaryOrDisabled(c));
            pen.drawRoundRectangle(bounds.x(), bounds.y(), bounds.width(), bounds.height(), edge);

            if(c.checked().get()) {
//                MIPMAP_CHECKED.render(bounds, pen);
                pen.drawRoundRectangle(bounds.x() + 3, bounds.y() + 3, bounds.width() - 6, bounds.height() - 6, edge);
            }
        }
    }
}
