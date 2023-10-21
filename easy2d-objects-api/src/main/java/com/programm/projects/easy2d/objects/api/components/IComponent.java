package com.programm.projects.easy2d.objects.api.components;

import com.programm.projects.easy2d.objects.api.IObjectContext;

public interface IComponent {

    default void init(IObjectContext ctx) {}

    /**
     * @return true if a GameObject can hold multiple of this Component class. If true component can not be queried by the get(...) method in GameObject.
     */
    default boolean canHaveMultiple() { return false; }

}
