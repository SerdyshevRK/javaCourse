package com.reflection.dependency;

import com.reflection.annatations.Resource;
import com.reflection.exceptions.ClassBuildException;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class DIContext {
    Map<Class<?>, Object> instances = new HashMap<>();

    public <T> T get(String classType) throws ClassBuildException {
        try {
            Class<?> type = Class.forName(classType);
            return (T) getInstance(type);
        } catch (Exception e) {
            throw new ClassBuildException();
        }
    }

    private Object getInstance(Class<?> type) throws InstantiationException, IllegalAccessException {
        if (instances.containsKey(type))
            return instances.get(type);

        Object retInstance = newInstance(type);
        return retInstance;
    }

    private Object newInstance(Class<?> newInstanceType) throws IllegalAccessException, InstantiationException {
        Object retInstance = newInstanceType.newInstance();
        Field[] fields = newInstanceType.getDeclaredFields();
        Class<?> fieldType;
        Resource annotation;
        boolean isSingleton;

        for (Field field : fields) {
            annotation = field.getAnnotation(Resource.class);

            if (annotation != null) {
                isSingleton = annotation.singleton();
                fieldType = annotation.value();
                if (fieldType == DummyClass.class)
                    fieldType = field.getType();

                field.setAccessible(true);
                Object instance = getInstance(fieldType);
                field.set(retInstance, instance);
                if (isSingleton)
                    instances.put(fieldType, instance);
            }
        }
        return retInstance;
    }
}
