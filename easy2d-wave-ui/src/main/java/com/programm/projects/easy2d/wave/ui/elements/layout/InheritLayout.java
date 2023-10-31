package com.programm.projects.easy2d.wave.ui.elements.layout;

import com.programm.project.easy2d.engine.api.IPencil;
import com.programm.projects.easy2d.wave.ui.core.GlobalComponentUtils;
import com.programm.projects.easy2d.wave.ui.core.WaveComponent;
import com.programm.projects.easy2d.wave.ui.core.bounds.IBounds;
import com.programm.projects.easy2d.wave.ui.core.bounds.IEditableBounds;

import java.util.List;

public class InheritLayout implements ILayout {

    @Override
    public void updateBoundsForChildren(IPencil pen, IBounds parent, List<WaveComponent> children, List<Object> childArgs, List<IEditableBounds> bounds) {
        float midX = parent.x() + parent.width() / 2f;
        float midY = parent.y() + parent.height() / 2f;

        for(int i=0;i<children.size();i++){
            WaveComponent child = children.get(i);
            if(!child.visible().get()) continue;
            IEditableBounds childBounds = bounds.get(i);

            float width = GlobalComponentUtils.getWidthConstrainedByMinMax(pen, child);
            float height = GlobalComponentUtils.getHeightConstrainedByMinMax(pen, child);
            float x = GlobalComponentUtils.xUntouched(child) ? (midX - width / 2f) : parent.x() + child.x();
            float y = GlobalComponentUtils.yUntouched(child) ? (midY - height / 2f) : parent.y() + child.y();

            childBounds.bounds(x, y, width, height);
        }
    }

    @Override
    public float minWidth(IPencil pen, List<WaveComponent> children, List<Object> childArgs) {
        Float maxMinWidth = null;

        for(int i=0;i<children.size();i++){
            WaveComponent child = children.get(i);
            if(!child.visible().get()) continue;
            Float minWidth = GlobalComponentUtils.getWidthOrMinWidth(pen, child);//child.minWidth(pen);
            if(maxMinWidth == null || (minWidth != null && maxMinWidth < minWidth)){
                maxMinWidth = minWidth;
            }
        }

        return maxMinWidth == null ? 0 : maxMinWidth;
    }

    @Override
    public float minHeight(IPencil pen, List<WaveComponent> children, List<Object> childArgs) {
        Float maxMinHeight = null;

        for(int i=0;i<children.size();i++){
            WaveComponent child = children.get(i);
            if(!child.visible().get()) continue;
            Float minHeight = GlobalComponentUtils.getHeightOrMinHeight(pen, child);//child.minHeight(pen);
            if(maxMinHeight == null || (minHeight != null && maxMinHeight < minHeight)){
                maxMinHeight = minHeight;
            }
        }

        return maxMinHeight == null ? 0 : maxMinHeight;
    }
}
