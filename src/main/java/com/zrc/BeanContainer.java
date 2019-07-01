package com.zrc;

import com.zrc.util.ReflectionUtil;

import java.util.*;

public class BeanContainer {
    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<Class<?>, Object>();
    private static final Set<Class<?>> BEAN_CLASS;
    static {
        BEAN_CLASS = ClassHelper.getBeanClass();
        for(Class<?> bean : BEAN_CLASS) {
            Object object = ReflectionUtil.newInstance(bean);
            if(!BEAN_MAP.containsKey(bean)) {
                BEAN_MAP.put(bean , object);
            }
        }
    }

    public static Map<Class<?>, Object> getBeanMap() {
        return BEAN_MAP;
    }

    public static <T> T getBean(Class<T> clazz) {
        if(!BEAN_MAP.containsKey(clazz)) {
            throw new RuntimeException("can find the bean by class :" + clazz);
        }
        return (T)BEAN_MAP.get(clazz);
    }

    public static void setBean(Class<?> clazz, Object object) {
        BEAN_MAP.put(clazz, object);
    }

    public static Object getIocBean(Class<?> specificClass, Class<?> fieldClass, String fieldName) {
        if(specificClass != null) {
            if (fieldClass.isAssignableFrom(specificClass)) {
                return BEAN_MAP.get(specificClass);
            } else {
                throw new RuntimeException(specificClass + " can't assign to " + fieldClass);
            }
        } else {
            if(fieldClass.isInterface()) {
                List<Class<?>> assignableClass = new LinkedList<>();
                for(Class<?> clazz : BEAN_CLASS) {
                    if(fieldClass.isAssignableFrom(clazz)) {
                        assignableClass.add(clazz);
                    }
                }
                if(assignableClass.size() == 0) {
                    throw new RuntimeException("no implement class for the interface : " + fieldClass.getName());
                }
                for(Class<?> clazz : assignableClass) {
                    if(clazz.getSimpleName().equalsIgnoreCase(fieldName)) {
                        return BEAN_MAP.get(clazz);
                    }
                }
                return BEAN_MAP.get(assignableClass.get(0));

            } else {
                return BEAN_MAP.get(fieldClass);
            }
        }
    }
}
