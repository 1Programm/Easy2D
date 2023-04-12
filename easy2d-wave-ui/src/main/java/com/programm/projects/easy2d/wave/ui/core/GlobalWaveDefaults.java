package com.programm.projects.easy2d.wave.ui.core;

import com.programm.libraries.reactiveproperties.ObservableValue;
import com.programm.libraries.reactiveproperties.SettableValue;
import com.programm.libraries.reactiveproperties.core.ObjectProperty;
import com.programm.libraries.reactiveproperties.core.ObjectPropertyValue;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class GlobalWaveDefaults {

    private static final Map<Class<?>, GlobalWaveDefaults> DEFAULTS = new HashMap<>();

    public static void setDefault(Class<?> cls, String name, Object defaultValue, int level){
        GlobalWaveDefaults defaults = DEFAULTS.computeIfAbsent(cls, c -> new GlobalWaveDefaults(cls));

        Integer curLevel = defaults.setLevel.get(name);

        if(curLevel == null || level >= curLevel){
            defaults.setLevel.put(name, level);
            defaults.values.computeIfAbsent(name, n -> new ObjectPropertyValue<>()).set(defaultValue);
        }
    }

    public static void setDefault(Class<?> cls, String name, Object defaultValue){
        setDefault(cls, name, defaultValue, Integer.MAX_VALUE);
    }

    public static void setBaseDefault(Class<?> cls, String name, Object defaultValue){
        setDefault(cls, name, defaultValue, 0);
    }



    @SuppressWarnings("unchecked")
    public static <T> T getDefault(Class<?> cls, String name){
        GlobalWaveDefaults defaults = DEFAULTS.get(cls);
        if(defaults != null){
            ObjectProperty<?> prop = defaults.values.get(name);
            if(prop != null){
                Object value = prop.get();
                if(value != null) return (T) value;
            }
        }

        Class<?> superCls = cls.getSuperclass();
        if(superCls != null && WaveComponent.class.isAssignableFrom(superCls)){
            return getDefault(superCls, name);
        }

        return null;
    }



    @SuppressWarnings("unchecked")
    public static <T extends WaveComponent> void initDefaults(Class<? extends WaveComponent> cls, T component){
        Class<?> superCls = cls.getSuperclass();
        if(superCls != null && WaveComponent.class.isAssignableFrom(superCls)){
            initDefaults((Class<? extends WaveComponent>) superCls, component);
        }

        GlobalWaveDefaults defaults = DEFAULTS.get(cls);
        if(defaults != null){
            for(Map.Entry<String, ObjectProperty<Object>> entry : defaults.values.entrySet()){
                String fieldName = entry.getKey();

                Field field = defaults.allFields.get(fieldName);
                if(field == null) continue;

                Class<?> type = field.getType();
                if(SettableValue.class.isAssignableFrom(type)) continue;

                ObjectProperty<?> prop = entry.getValue();
                if(prop == null) continue;

                Object defaultValue = prop.get();
                if(defaultValue == null) continue;

                setFieldToDefault(field, component, defaultValue);
            }
        }
    }

    private static Map<String, Field> collectFields(Class<?> cls){
        Map<String, Field> fieldMap = new HashMap<>();

        while(cls != null && cls != Object.class){
            Field[] declaredFields = cls.getDeclaredFields();

            for(Field field : declaredFields){
                int mods = field.getModifiers();
                if(Modifier.isStatic(mods) || (Modifier.isFinal(mods) && !SettableValue.class.isAssignableFrom(field.getType()))) continue;
                fieldMap.put(field.getName(), field);
            }

            cls = cls.getSuperclass();
        }

        return fieldMap;
    }

    private static void setFieldToDefault(Field field, WaveComponent component, Object defaultValue){
        try {
            field.setAccessible(true);
            field.set(component, defaultValue);
        }
        catch (IllegalAccessException e){
            System.err.println(e.getMessage());
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T> ObservableValue<T> getOptionalExpression(Class<?> componentCls, String name){
        if(componentCls == null || !WaveComponent.class.isAssignableFrom(componentCls)) throw new IllegalStateException("[" + componentCls + "] Is not a valid component class!");

        GlobalWaveDefaults cacheDefaults = DEFAULTS.get(componentCls);
        if(cacheDefaults != null){
            ObservableValue<?> cacheExpression = cacheDefaults.optionalExpressions.get(name);
            if(cacheExpression != null){
                return (ObservableValue<T>) cacheExpression;
            }
        }

        Stack<Class<?>> uiClasses = new Stack<>();
        Class<?> curCls = componentCls;
        while(curCls != null && WaveComponent.class.isAssignableFrom(curCls)){
            uiClasses.push(curCls);
            curCls = curCls.getSuperclass();
        }

        ObservableValue<T> curValue = null;
        while(!uiClasses.isEmpty()){
            Class<?> cls = uiClasses.pop();
            GlobalWaveDefaults defaults = DEFAULTS.computeIfAbsent(cls, GlobalWaveDefaults::new);
            ObservableValue optionalValue;

            ObservableValue<?> cachedExpression = defaults.optionalExpressions.get(name);
            if(cachedExpression != null){
                optionalValue = cachedExpression;
            }
            else {
                optionalValue = defaults.values.computeIfAbsent(name, n -> new ObjectPropertyValue<>());
            }

            if(curValue == null){
                curValue = (ObservableValue<T>) optionalValue;
            }
            else {
                curValue = optionalValue.orIfNull(curValue);
            }

            defaults.optionalExpressions.put(name, curValue);
        }

        return curValue;
    }

    private final Map<String, ObjectProperty<Object>> values;
    private final Map<String, Integer> setLevel;
    private final Map<String, Field> allFields;

    private final Map<String, ObservableValue<?>> optionalExpressions;

    private GlobalWaveDefaults(Class<?> cls){
        this.values = new HashMap<>();
        this.setLevel = new HashMap<>();
        this.allFields = collectFields(cls);

        this.optionalExpressions = new HashMap<>();
    }

}
