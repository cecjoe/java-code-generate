package com.joe.code.common.utils;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericSuperclassUtil {

    /*
     * 获取泛型类Class对象，不是泛型类则返回null
     */
    public static Class<?> getActualTypeArgument(Class<?> clazz) {
        Class<?> entitiClass = null;
        Type genericSuperclass = clazz.getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass)
                    .getActualTypeArguments();
            if (actualTypeArguments != null && actualTypeArguments.length > 0) {
                entitiClass = (Class<?>) actualTypeArguments[0];
            }
        }

        return entitiClass;
    }

    /**
     * 获取泛型方法Class对象
     * @param clazz
     * @return
     */
    public static Class<?> getActualTypeArgumentMethod(Class<?> clazz) {
        Class<?> entitiClass = null;
        Type genericSuperclass = clazz.getGenericSuperclass();

//        if (genericSuperclass instanceof ParameterizedType) {
//            Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass)
//                    .getActualTypeArguments();
//            if (actualTypeArguments != null && actualTypeArguments.length > 0) {
//                entitiClass = (Class<?>) actualTypeArguments[0];
//            }
//        }

        return entitiClass;
    }

    public static void main(String[] args){
        DynamicQueryExecutor dynamicQueryExecutor = new DynamicQueryExecutor();
//        dynamicQueryExecutor.execSQLQuery("", "");
//        Method method = DynamicQueryExecutor.class.getDeclaredMethod("execSQLQuery");
//        Method sayHiI = DynamicQueryExecutor.class.ge
//        getActualTypeArgument(dynamicQueryExecutor.);
    }
}
