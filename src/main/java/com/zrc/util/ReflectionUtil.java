package com.zrc.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);

    public static Object newInstance(Class<?> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            LOGGER.error("new instance failed", e);
            throw new RuntimeException(e);
        }
    }

    public static Object invokeMethod(Object object, Method method, Object ...params) {
        try {
            method.setAccessible(true);
            return method.invoke(object, params);
        } catch (Exception e) {
            LOGGER.error("invoke method failed", e);
            throw new RuntimeException(e);
        }
    }

    public static void setFiled(Object object, Field field, Object value) {
        try {
            field.setAccessible(true);
            field.set(object, value);
        } catch (Exception e) {
            LOGGER.error("set field failed", e);
            throw new RuntimeException(e);
        }
    }
}
