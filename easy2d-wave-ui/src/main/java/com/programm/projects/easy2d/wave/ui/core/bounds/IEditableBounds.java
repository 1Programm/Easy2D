package com.programm.projects.easy2d.wave.ui.core.bounds;

public interface IEditableBounds extends IBounds {

    IEditableBounds x(float x);

    IEditableBounds y(float y);

    IEditableBounds width(float width);

    IEditableBounds height(float height);


    default IEditableBounds position(float x, float y){
        x(x);
        y(y);
        return this;
    }

    default IEditableBounds size(float width, float height){
        width(width);
        height(height);
        return this;
    }

    default IEditableBounds bounds(float x, float y, float width, float height){
        x(x);
        y(y);
        width(width);
        height(height);
        return this;
    }

    default IEditableBounds bounds(IBounds bounds){
        return bounds(bounds.x(), bounds.y(), bounds.width(), bounds.height());
    }

}
