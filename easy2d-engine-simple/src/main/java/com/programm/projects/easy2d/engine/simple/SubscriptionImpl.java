package com.programm.projects.easy2d.engine.simple;

import com.programm.project.easy2d.engine.api.Subscription;

import java.util.List;

class SubscriptionImpl implements Subscription {

    private final List<?> list;
    private final Object toRemove;

    public SubscriptionImpl(List<?> list, Object toRemove) {
        this.list = list;
        this.toRemove = toRemove;
    }

    @Override
    public void unsubscribe() {
        list.remove(toRemove);
    }
}
