package com.programm.projects.easy2d.ui.advanced;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class UIDefaults {

    private static final Map<String, Object> DEFAULTS = new HashMap<>();

    public static void LOAD(String resource) throws IOException {
        LOAD(UIDefaults.class.getResourceAsStream(resource));
    }

    public static void LOAD(InputStream is) throws IOException {
        if(is == null) throw new NullPointerException("InputStream is null!");

        Properties properties = new Properties();
        properties.load(is);
        LOAD(properties);
    }

    public static void LOAD(Properties properties){
        for(String name : properties.stringPropertyNames()){
            DEFAULTS.put(name, properties.getProperty(name));
        }
    }


    public static <T> T get(String name, Class<T> cls){
        Object o = DEFAULTS.get(name);
        if(o == null) return null;

        return cls.cast(o);
    }




    private UIDefaults(){}

}
