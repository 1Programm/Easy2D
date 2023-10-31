package com.programm.projects.easy2d.engine.api;

public abstract class LevelLogger implements ILogger {

    public static final int DEBUG = 1;
    public static final int INFO = 2;
    public static final int ERROR = 3;

    private int level;

    public LevelLogger(int level){
        this.level = level;
    }

    protected abstract void doDebug(String s);
    protected abstract void doInfo(String s);
    protected abstract void doError(String s);

    @Override
    public final void debug(String s) {
        if(level <= DEBUG){
            doDebug(s);
        }
    }

    @Override
    public final void info(String s) {
        if(level <= INFO){
            doInfo(s);
        }
    }

    @Override
    public final void error(String s) {
        if(level <= ERROR){
            doError(s);
        }
    }

    public final void setLevel(int level) {
        this.level = level;
    }
}
