package com.programm.projects.easy2d.wave.ui.core.subscriber;

import com.programm.project.easy2d.engine.api.Subscription;

import java.util.ArrayList;
import java.util.List;

public class RunnableSubscriptionManager implements Subscribable<Runnable> {

    private class SubscriptionImpl implements Subscription {
        final Runnable listener;

        public SubscriptionImpl(Runnable listener) {
            this.listener = listener;
        }

        @Override
        public void unsubscribe() {
            for(int i=0;i<subscriptions.size();i++){
                if(subscriptions.get(i) == this){
                    subscriptions.remove(i);
                    return;
                }
            }
        }
    }

    private final List<SubscriptionImpl> subscriptions = new ArrayList<>();

    @Override
    public Subscription subscribe(Runnable listener) {
        SubscriptionImpl subscription = new SubscriptionImpl(listener);
        subscriptions.add(subscription);
        return subscription;
    }

    public void notifyChange(){
        for(int i=0;i<subscriptions.size();i++){
            subscriptions.get(i).listener.run();
        }
    }
}
