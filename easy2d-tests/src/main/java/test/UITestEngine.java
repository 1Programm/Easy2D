package test;

import com.programm.project.easy2d.engine.api.IPencil;
import com.programm.projects.easy2d.engine.simple.SimpleEngine;
import com.programm.projects.easy2d.wave.ui.core.WaveComponent;
import com.programm.projects.easy2d.wave.ui.elements.View;
import com.programm.projects.easy2d.wave.ui.elements.WaveRoot;

public class UITestEngine extends SimpleEngine {

    public static final String TITLE = "UI TEST";
    public static final int WIDTH = 600, HEIGHT = 500;
    public static final int FPS = 30;

    private final WaveRoot uiRoot;
    private final View view;

    public UITestEngine() {
        super(TITLE, WIDTH, HEIGHT, FPS);
        this.view = new View();
        this.uiRoot = new WaveRoot(this, view);
    }

    @Override
    protected void init() {}

    @Override
    public void update() {}

    @Override
    public void render(IPencil pen) {
        uiRoot.render(pen);
    }

    public View contentView(){
        return view;
    }

    public void add(WaveComponent component, Object args){
        view.add(component, args);
    }

    public void add(WaveComponent component){
        view.add(component);
    }
}
