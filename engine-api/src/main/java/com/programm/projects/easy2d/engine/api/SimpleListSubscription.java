package com.programm.projects.easy2d.engine.api;

import java.util.List;

public class SimpleListSubscription implements Subscription {

    private final List<?> list;
    private final Object toRemove;

    public SimpleListSubscription(List<?> list, Object toRemove) {
        this.list = list;
        this.toRemove = toRemove;
    }

    @Override
    public void unsubscribe() {
        list.remove(toRemove);
    }
}
