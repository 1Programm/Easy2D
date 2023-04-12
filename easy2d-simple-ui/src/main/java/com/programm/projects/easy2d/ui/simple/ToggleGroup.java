package com.programm.projects.easy2d.ui.simple;

import com.programm.project.easy2d.engine.api.IPencil;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ToggleGroup extends UIElement {

    private class ToggleStateEntry implements Consumer<Boolean> {

        private final int index;

        public ToggleStateEntry(int index) {
            this.index = index;
        }

        @Override
        public void accept(Boolean state) {
            if(ignoreChange) return;
            updateToggleState(index, state);
        }
    }

    private final List<Toggle> toggleList = new ArrayList<>();
    private int toggledIndex;
    private boolean ignoreChange = false;

    public ToggleGroup() {
        super(0, 0, 0, 0);
        this.primary = null;
        this.secondary = null;
        this.toggledIndex = -1;
    }



    @Override
    public void update(float offX, float offY) {
        //Don't use relative - Toggle group is only a util class and not a container!
        for(int i=0;i<toggleList.size();i++){
            toggleList.get(i).update(x, y);
        }
    }

    @Override
    public void render(IPencil pencil, float offX, float offY) {
        //Don't use relative - Toggle group is only a util class and not a container!
        for(int i=0;i<toggleList.size();i++){
            toggleList.get(i).render(pencil, x, y);
        }
    }

    @Override
    public void onMousePressed(float mx, float my, int button) {
        for(int i=0;i<toggleList.size();i++){
            toggleList.get(i).onMousePressed(mx - x, my - y, button);
        }
    }

    @Override
    public void onMouseReleased(float mx, float my, int button) {
        for(int i=0;i<toggleList.size();i++){
            toggleList.get(i).onMouseReleased(mx - x, my - y, button);
        }
    }

    @Override
    public void onMouseDragged(float mx, float my) {
        for(int i=0;i<toggleList.size();i++){
            toggleList.get(i).onMouseDragged(mx - x, my - y);
        }
    }

    @Override
    public void onMouseMoved(float mx, float my) {
        for(int i=0;i<toggleList.size();i++){
            toggleList.get(i).onMouseMoved(mx - x, my - y);
        }
    }

    @Override
    public void onKeyPressed(int keyCode) {
        for(int i=0;i<toggleList.size();i++){
            toggleList.get(i).onKeyPressed(keyCode);
        }
    }

    @Override
    public void onKeyReleased(int keyCode) {
        for(int i=0;i<toggleList.size();i++){
            toggleList.get(i).onKeyReleased(keyCode);
        }
    }

    @Override
    public void onMouseScrolled(float scroll) {
        for(int i=0;i<toggleList.size();i++){
            toggleList.get(i).onMouseScrolled(scroll);
        }
    }

    private void updateToggleState(int index, boolean state){
        if(state){
            if(toggledIndex == -1){
                toggledIndex = index;
            }
            else if(toggledIndex == index){
                //weird
                throw new IllegalStateException("INVALID STATE!");
            }
            else {
                Toggle last = toggleList.get(toggledIndex);
                last.disabled(false);

                ignoreChange = true;
                last.state(false);
                ignoreChange = false;

                toggledIndex = index;
            }

            toggleList.get(index).disabled(true);
        }
        else {
            if(toggledIndex != -1 && toggledIndex != index){
                return;
            }

            throw new IllegalStateException("INVALID STATE!");
        }
    }

    public <T extends Toggle> T add(T toggle){
        int index = toggleList.size();
        toggleList.add(toggle);
        toggle.onToggle(new ToggleStateEntry(index));

        if(toggle.state()) {
            updateToggleState(index, true);
        }

        return toggle;
    }

    @Override
    public ToggleGroup disabled(boolean disabled) {
        for(int i=0;i<toggleList.size();i++){
            toggleList.get(i).disabled(disabled);
        }

        return this;
    }

    @Override
    public ToggleGroup visible(boolean visible) {
        super.visible(visible);
        return this;
    }

    public int size(){
        return toggleList.size();
    }

    public Toggle get(int i){
        return toggleList.get(i);
    }
}
