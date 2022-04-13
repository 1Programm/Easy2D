package com.programm.projects.easy2d.engine.simple.objects;

import com.programm.projects.easy2d.objects.api.*;
import com.programm.projects.plus.maths.Vector2f;

import java.util.ArrayList;
import java.util.List;

public class ArrayObjectCollection implements IObjectCollection {

    protected class PartialObjectCollection implements IObjectCollection {
        private final List<Integer> objectIndices;

        public PartialObjectCollection(List<Integer> objectIndices) {
            this.objectIndices = objectIndices;
        }

        @Override
        public int size() {
            return objectIndices.size();
        }

        @Override
        public IGameObject get(int i) {
            Integer index = objectIndices.get(i);
            if(index == null) return null;
            return objects.get(index);
        }

        @Override
        public void remove(int i) {
            Integer index = objectIndices.remove(i);
            objects.remove(index.intValue());
        }

        @Override
        public IObjectCollection getNearbys(Vector2f pos, float range) {
            return this;
        }

        @Override
        public ICollisionInfo checkCollision(IShape s) {
            for(Integer index : objectIndices){
                IGameObject other = objects.get(index);

                if(other != s){
                    ICollisionInfo info = s.checkCollision(other);

                    if(info != null){
                        return info;
                    }
                }
            }

            return null;
        }
    }

    private final List<IGameObject> objects = new ArrayList<>();

    @Override
    public int size() {
        return objects.size();
    }

    @Override
    public IGameObject get(int i) {
        return objects.get(i);
    }

    @Override
    public void remove(int i) {
        objects.remove(i);
    }

    public void add(IGameObject obj){
        objects.add(obj);
    }

    @Override
    public PartialObjectCollection getNearbys(Vector2f pos, float range) {
        List<Integer> indices = new ArrayList<>();

        for(int i=0;i<objects.size();i++){
            IGameObject obj = objects.get(i);

            float dist = obj.pos().sub(pos).lengthSqrt();
            if(dist < range){
                indices.add(i);
            }
        }

        return new PartialObjectCollection(indices);
    }

    @Override
    public ICollisionInfo checkCollision(IShape s) {
        for(int i=0;i<objects.size();i++){
            IGameObject other = objects.get(i);

            if(other != s){
                ICollisionInfo info = s.checkCollision(other);

                if(info != null){
                    return info;
                }
            }
        }

        return null;
    }
}
