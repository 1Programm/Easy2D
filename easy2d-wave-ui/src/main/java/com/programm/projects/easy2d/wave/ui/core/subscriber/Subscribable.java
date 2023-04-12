package com.programm.projects.easy2d.wave.ui.core.subscriber;

import com.programm.project.easy2d.engine.api.Subscription;

public interface Subscribable<T> {

    Subscription subscribe(T listener);

}
