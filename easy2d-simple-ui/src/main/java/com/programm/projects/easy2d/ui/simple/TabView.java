package com.programm.projects.easy2d.ui.simple;

import com.programm.project.easy2d.engine.api.IContext;
import com.programm.project.easy2d.engine.api.IPencil;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class TabView extends UIElement {

    private final List<Consumer<Integer>> tabChangeListeners = new ArrayList<>();
    private final List<String> tabLabels = new ArrayList<>();
    private final List<Panel> tabPanels = new ArrayList<>();

    private final float tabLabelHeight;

    private int selectedIndex = -1;
    private Color selectedColor = Color.LIGHT_GRAY;

    public TabView(float x, float y, float width, float height, float tabHeight) {
        super(x, y, width, height + tabHeight);
        this.tabLabelHeight = tabHeight;
    }

    public TabView(float x, float y, float width, float height) {
        this(x, y, width, height, 20);
    }

    @Override
    public void update(float offX, float offY) {
        if(selectedIndex != -1){
            tabPanels.get(selectedIndex).update(offX + x, offY + y + tabLabelHeight);
        }
    }

    @Override
    public void render(IPencil pencil, float offX, float offY) {
        float rx = offX + x;
        float ry = offY + y;

        if(secondary != null) {
            pencil.setColor(secondary);
            pencil.fillRectangle(rx, ry, width, tabLabelHeight);
        }

        float tabLabelWidth = width / tabLabels.size();

        if(selectedColor != null){
            if(selectedIndex != -1){
                pencil.setColor(selectedColor);
                pencil.fillRectangle(rx + selectedIndex * tabLabelWidth, ry, tabLabelWidth, tabLabelHeight);
            }
        }

        Color color = primary;

        if(disabled && disabledColor != null){
            color = disabledColor;
        }

        if(color != null){
            pencil.setColor(color);
            pencil.drawRectangle(rx, ry, width, tabLabelHeight);

            for(int i=0;i<tabLabels.size();i++){
                float xStart = rx + i * tabLabelWidth;
                if(i > 0) pencil.drawLine(xStart, ry, xStart, ry + tabLabelHeight);

                float xMid = xStart + tabLabelWidth / 2;
                float yMid = ry + tabLabelHeight / 2;

                pencil.drawStringCentered(tabLabels.get(i), xMid, yMid);
            }
        }

        if(selectedIndex != -1){
            tabPanels.get(selectedIndex).render(pencil, rx, ry + tabLabelHeight);
        }
    }

    @Override
    public void onMousePressed(float mx, float my, int button) {
        mx -= x;
        my -= y;
        if(mx < 0 || my < 0 || mx >= width || my >= height) return;

        if(my <= tabLabelHeight) {
            selectedIndex((int)(mx / (width / tabLabels.size())));
        }
        else {
            if (selectedIndex != -1) {
                tabPanels.get(selectedIndex).onMousePressed(mx, my - tabLabelHeight, button);
            }
        }
    }

    @Override
    public void onMouseReleased(float mx, float my, int button) {
        if (selectedIndex != -1) {
            mx -= x;
            my -= (y + tabLabelHeight);
            if(mx < 0 || my < 0 || mx >= width || my >= height) return;

            tabPanels.get(selectedIndex).onMouseReleased(mx, my, button);
        }
    }

    @Override
    public void onMouseDragged(float mx, float my) {
        if (selectedIndex != -1) {
            mx -= x;
            my -= (y + tabLabelHeight);
            if(mx < 0 || my < 0 || mx >= width || my >= height) return;

            tabPanels.get(selectedIndex).onMouseDragged(mx, my);
        }
    }

    @Override
    public void onMouseMoved(float mx, float my) {
        if (selectedIndex != -1) {
            mx -= x;
            my -= (y + tabLabelHeight);
            if(mx < 0 || my < 0 || mx >= width || my >= height) return;

            tabPanels.get(selectedIndex).onMouseMoved(mx, my);
        }
    }

    @Override
    public void onKeyPressed(int keyCode) {
        if (selectedIndex != -1) {
            tabPanels.get(selectedIndex).onKeyPressed(keyCode);
        }
    }

    @Override
    public void onKeyReleased(int keyCode) {
        if (selectedIndex != -1) {
            tabPanels.get(selectedIndex).onKeyReleased(keyCode);
        }
    }

    @Override
    public void onMouseScrolled(float scroll) {
        if(selectedIndex != -1){
            tabPanels.get(selectedIndex).onMouseScrolled(scroll);
        }
    }

    private void notifyListeners(){
        for(int i=0;i<tabChangeListeners.size();i++){
            tabChangeListeners.get(i).accept(selectedIndex);
        }
    }

    public TabView addTab(String label, Panel panel, int index){
        tabLabels.add(index, label);
        tabPanels.add(index, panel);
        if(selectedIndex == -1) selectedIndex(0);
        return this;
    }

    public TabView addTab(String label, Panel panel){
        return addTab(label, panel, tabLabels.size());
    }

    public Panel newTab(String label){
        Panel panel = new Panel(0, 0, width, height - tabLabelHeight);
        addTab(label, panel);
        return panel;
    }

    public TabView selectedIndex(int index){
        this.selectedIndex = index;
        notifyListeners();
        return this;
    }

    public int selectedIndex(){
        return this.selectedIndex;
    }

    public int getTabCount(){
        return tabPanels.size();
    }

    public Panel getTabPanel(int index){
        if(index < 0 || index >= tabPanels.size()) return null;
        return tabPanels.get(index);
    }

    public Panel removeTab(int index){
        if(index < 0 || index >= tabPanels.size()) return null;
        if(selectedIndex >= index) selectedIndex--;
        tabLabels.remove(index);
        return tabPanels.remove(index);
    }

    public TabView clearTabs(){
        tabPanels.clear();
        tabLabels.clear();
        selectedIndex = -1;
        return this;
    }

    public TabView selectedColor(Color color){
        this.selectedColor = color;
        return this;
    }

    @Override
    public TabView x(float x) {
        super.x(x);
        return this;
    }

    @Override
    public TabView y(float y) {
        super.y(y);
        return this;
    }

    @Override
    public TabView width(float width) {
        super.width(width);
        return this;
    }

    @Override
    public TabView height(float height) {
        super.height(height);
        return this;
    }

    @Override
    public TabView primary(Color primary) {
        super.primary(primary);
        return this;
    }

    @Override
    public TabView secondary(Color secondary) {
        super.secondary(secondary);
        return this;
    }

    @Override
    public TabView disabledColor(Color disabledColor) {
        super.disabledColor(disabledColor);
        return this;
    }

    @Override
    public TabView disabled(boolean disabled) {
        super.disabled(disabled);
        for(int i=0;i<tabPanels.size();i++){
            tabPanels.get(i).disabled(disabled);
        }
        return this;
    }

    @Override
    public TabView visible(boolean visible) {
        super.visible(visible);
        return this;
    }

    public TabView onTabChange(Consumer<Integer> listener){
        this.tabChangeListeners.add(listener);
        return this;
    }
}
