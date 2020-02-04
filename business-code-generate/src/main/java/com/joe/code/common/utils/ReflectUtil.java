package com.joe.code.common.utils;

import java.lang.reflect.Field;

public class ReflectUtil {

    public static Field getFieldByClass(Class clazz, String fileLabel){

        Field[] fields=clazz.getDeclaredFields();
        boolean b=false;
        for (int i = 0; i < fields.length; i++) {
            if(fields[i].getName().equals(fileLabel))
            {
                return fields[i];
            }
        }

        return null;
    }
}
