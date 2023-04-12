package com.programm.projects.easy2d.objects.api;

import com.programm.project.easy2d.engine.api.IKeyboard;
import com.programm.project.easy2d.engine.api.IMouse;

public interface IObjectContext {

    IKeyboard keyboard();
    IMouse mouse();

    IObjectCollection objects();

}
