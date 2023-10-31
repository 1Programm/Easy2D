package com.programm.projects.easy2d.engine.api;

public class ProxyLogger implements ILogger {

    private ILogger logger;

    @Override
    public void debug(String s) {
        if(logger == null) return;
        logger.debug(s);
    }

    @Override
    public void info(String s) {
        if(logger == null) return;
        logger.debug(s);
    }

    @Override
    public void error(String s) {
        if(logger == null) return;
        logger.debug(s);
    }

    public void setLogger(ILogger logger) {
        this.logger = logger;
    }

    public boolean hasLogger(){
        return logger != null;
    }
}
