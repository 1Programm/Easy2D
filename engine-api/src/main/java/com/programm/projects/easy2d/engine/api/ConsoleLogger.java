package com.programm.projects.easy2d.engine.api;

public class ConsoleLogger extends LevelLogger {

    public ConsoleLogger(int level) {
        super(level);
    }

    @Override
    protected void doDebug(String s) {
        System.out.println(s);
    }

    @Override
    protected void doInfo(String s) {
        System.out.println(s);
    }

    @Override
    protected void doError(String s) {
        System.err.println(s);
    }
}
