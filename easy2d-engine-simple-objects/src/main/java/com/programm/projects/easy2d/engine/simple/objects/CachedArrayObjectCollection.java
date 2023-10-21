package com.programm.projects.easy2d.engine.simple.objects;

import com.programm.projects.plus.maths.Vector2f;

import java.util.ArrayList;
import java.util.List;

public class CachedArrayObjectCollection extends ArrayObjectCollection {

    private final int cacheRatio;
    private final Integer[][] cacheMap;
    private final List<PartialObjectCollection> cache;

    public CachedArrayObjectCollection(int fieldWidth, int fieldHeight, int cacheRatio) {
        this.cacheRatio = cacheRatio;

        int cacheWidth = fieldWidth / cacheRatio;
        int cacheHeight = fieldHeight / cacheRatio;

        this.cacheMap = new Integer[cacheHeight][cacheWidth];
        this.cache = new ArrayList<>();
    }

    @Override
    public PartialObjectCollection getNearbys(Vector2f pos, float range) {
        int cachePosX = (int)(pos.getX() / cacheRatio);
        int cachePosY = (int)(pos.getY() / cacheRatio);

        Integer index = getCacheIndex(cachePosX, cachePosY);
        if(index == null){
            PartialObjectCollection nearbys = super.getNearbys(pos, range);
            putCache(cachePosX, cachePosY, nearbys);
            return nearbys;
        }
        else {
            return cache.get(index);
        }
    }

    private Integer getCacheIndex(int x, int y){
        return cacheMap[y][x];
    }

    private void putCache(int x, int y, PartialObjectCollection objs){
        int index = cache.size();
        cache.add(objs);
        cacheMap[y][x] = index;
    }

}
