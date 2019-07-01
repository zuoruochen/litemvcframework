package com.zrc;

import com.zrc.annotation.Autowired;
import com.zrc.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

public class Ioc {
    static {
        Map<Class<?>, Object> beanMap = BeanContainer.getBeanMap();
        for(Map.Entry<Class<?>, Object> entry : beanMap.entrySet()) {
            Class<?> clazz = entry.getKey();
            Object bean = entry.getValue();
            Field[] fields = clazz.getDeclaredFields();
            for(Field field : fields) {
                if(field.isAnnotationPresent(Autowired.class)) {
                    Class<?> fieldClass = field.getType();
                    Class<?> specificClass = field.getAnnotation(Autowired.class).value();
                    Object fieldBean = BeanContainer.getIocBean(specificClass, fieldClass, field.getName());
                    if(fieldBean != null) {
                        ReflectionUtil.setFiled(bean, field, fieldBean);
                    }
                }
            }
        }
    }
}
