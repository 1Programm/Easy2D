package com.programm.projects.easy2d.wave.ui.elements;

import com.programm.project.easy2d.engine.api.IPencil;
import com.programm.projects.easy2d.wave.ui.core.GlobalComponentUtils;
import com.programm.projects.easy2d.wave.ui.core.GlobalWaveDefaults;
import com.programm.projects.easy2d.wave.ui.core.WaveComponent;
import com.programm.projects.easy2d.wave.ui.core.bounds.IBounds;
import com.programm.projects.easy2d.wave.ui.core.bounds.IEditableBounds;
import com.programm.projects.easy2d.wave.ui.core.bounds.ValueBounds;
import com.programm.projects.easy2d.wave.ui.elements.layout.HorizontalLayout;
import com.programm.projects.easy2d.wave.ui.elements.layout.VerticalLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TabView extends View {

    protected final HorizontalLayout tablLabelViewLayout;
    protected final View tabLabelView;
    protected final List<Button> tabButtons = new ArrayList<>();
    protected final List<WaveComponent> tabViews = new ArrayList<>();

    protected int selectedTab = -1;

    public TabView() {
        super(new VerticalLayout(VerticalLayout.POLICY_STRETCH_FORCE, VerticalLayout.POLICY_INITIAL));

        tablLabelViewLayout = new HorizontalLayout(HorizontalLayout.POLICY_INITIAL, HorizontalLayout.POLICY_STRETCH_FORCE);
        tabLabelView = new View(tablLabelViewLayout);
        tabLabelView.size(0, 30);

        children.add(tabLabelView);

        childArgsList.add(null);
        childArgsList.add(VerticalLayout.POLICY_STRETCH_FORCE);

        childBoundsList.add(new ValueBounds());
        childBoundsList.add(new ValueBounds());
    }

    public void selectTab(int tab){
        if(tab < 0 || tab >= tabViews.size()) return;

        if(selectedTab != -1 && selectedTab != tab) {
            Button btn = tabButtons.get(selectedTab);
            btn.secondary().set(GlobalWaveDefaults.getDefault(Button.class, "secondary"));
        }

        tabButtons.get(tab).secondary().set(GlobalWaveDefaults.getDefault(Button.class, "hoverColor"));
        selectedTab = tab;

        WaveComponent selectedView = tabViews.get(tab);
        if(children.size() == 1){
            children.add(selectedView);
        }
        else {
            children.set(1, selectedView);
        }
        requestRedraw();
    }

    @Override
    public void render(IBounds bounds, IPencil pen, boolean forceRedraw) {
        if(forceRedraw || GlobalComponentUtils.isDirty(this)) {
            GlobalComponentUtils.setDirty(this, false);
            renderSelf(bounds, pen);
            if(layout != null) {
                layout.updateBoundsForChildren(pen, bounds, children, childArgsList, childBoundsList);
            }
            forceRedraw = true;
        }
//        TODO: Needed?
//        else {
//            for(int i=0;i<children.size();i++){
//                if(GlobalComponentUtils.isDirty(children.get(i))){
//                    renderSelf(bounds, pen);
//                    break;
//                }
//            }
//        }

        for(int i=0;i<children.size();i++){
            WaveComponent child = children.get(i);
            IEditableBounds childBounds = childBoundsList.get(i);
            if(childBounds != null) {
                child.render(childBounds, pen, forceRedraw);
            }
        }
    }

    @Override
    public void add(WaveComponent child, Object args) {
        String name = Objects.toString(args);

        Button button = new Button(name);
        final int index = tabButtons.size();
        button.pressed().fTrue().listen(() -> selectTab(index));
        button.size(120, 0);

        tabButtons.add(button);
        tabLabelView.add(button);
        tabViews.add(child);

//        if(selectedTab == -1) selectedTab = 0;
        if(selectedTab == -1) selectTab(0);
    }

    public void horizontalTabNamePolicy(int horizontalPolicy){
        if(horizontalPolicy == HorizontalLayout.POLICY_STRETCH) horizontalPolicy = HorizontalLayout.POLICY_STRETCH_FORCE;
        tablLabelViewLayout.horizontalPolicy(horizontalPolicy);
    }
}
