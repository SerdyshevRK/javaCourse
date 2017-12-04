package com.reflection;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

public class ReflectionUtil {

    public static String toString(Object object) throws IllegalAccessException {
        if (object == null) return "null";
        StringBuilder sb = new StringBuilder();
        Class<?> refClass = object.getClass();
        if (refClass == String.class)
            return (printString((String) object));
        if (refClass == Integer.class || refClass == Byte.class || refClass == Short.class || refClass == Long.class
                || refClass == Float.class || refClass == Double.class || refClass == Boolean.class || refClass == Character.class)
            return printPrimitive(object);
        if (refClass.isArray())
            return printArray(object);
        sb.append(refClass.getSimpleName()).append(" = {");
        Field[] fields = refClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getName().contains("this") || field.getName().contains("parent"))
                continue;
            sb.append(field.getName()).append(" = ").append(toString(field.get(object))).append(" ");
        }
        sb.append("}");
        return sb.toString();
    }

    private static String printString(String string) {
        return string;
    }
    private static String printPrimitive(Object object) {
        return String.valueOf(object);
    }
    private static String printArray(Object object) throws IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        sb.append(object.getClass().getSimpleName()).append(" = {");
        int length = Array.getLength(object);
        String comma = ", ";
        for (int i = 0; i < length; i++) {
            if (i == length - 1)
                comma = "";
            sb.append(toString(Array.get(object, i))).append(comma);
        }
        sb.append("}");
        return sb.toString();
    }
}
