package com.programm.projects.easy2d.objects.api.components;

import com.programm.projects.easy2d.engine.api.IContext;
import com.programm.projects.easy2d.objects.api.GameObject;
import com.programm.projects.easy2d.objects.api.IGOH;
import com.programm.projects.easy2d.objects.api.IObjectContext;

public class AbstractComponent implements IComponent {

    protected GameObject gameObject;
    protected IGOH objectHandler;
    protected IContext engine;

    @Override
    public void init(IObjectContext ctx) {
        if(this.gameObject != null) throw new IllegalStateException("Component should only have one parent GameObject!");
        this.gameObject = ctx.gameObject();
        this.objectHandler = ctx.objectHandler();
        this.engine = ctx.engine();
    }
}
