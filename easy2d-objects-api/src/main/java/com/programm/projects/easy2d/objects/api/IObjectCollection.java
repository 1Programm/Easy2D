package com.programm.projects.easy2d.objects.api;

import com.programm.projects.plus.maths.Vector2f;

import java.util.Iterator;

public interface IObjectCollection extends Iterable<IGameObject> {

    int size();
    IGameObject get(int i);
    void remove(int i);

    IObjectCollection getNearbys(Vector2f pos, float range);

    ICollisionInfo checkCollision(IShape s);

    @Override
    default Iterator<IGameObject> iterator() {
        return new Iterator<>() {
            private final int savedSize = size();
            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < savedSize;
            }

            @Override
            public IGameObject next() {
                return get(i++);
            }
        };
    }
}
