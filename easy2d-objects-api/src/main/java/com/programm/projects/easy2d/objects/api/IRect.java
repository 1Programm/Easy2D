package com.programm.projects.easy2d.objects.api;

import com.programm.projects.plus.maths.Vector2f;

public interface IRect extends IShape {

    Vector2f size();

    default float width(){
        return size().getX();
    }

    default float height(){
        return size().getY();
    }

    default boolean intersects(IRect rect){
        return x() + width() > rect.x() && x() <= rect.x() + rect.width()
            && y() + height() > rect.y() && y() <= rect.y() + rect.height();
    }

}
