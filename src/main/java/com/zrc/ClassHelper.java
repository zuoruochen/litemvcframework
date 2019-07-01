package com.zrc;

import com.zrc.annotation.Component;
import com.zrc.annotation.Controller;
import com.zrc.annotation.Service;
import com.zrc.configuration.ConfigureParser;
import com.zrc.util.ClassLoaderUtil;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

public class ClassHelper {
    private static final Set<Class<?>> CLASS_SET;
    static {
        String basePackage = ConfigureParser.getAppBasePackage();
        CLASS_SET = ClassLoaderUtil.getClassSet(basePackage);
    }

    public static Set<Class<?>> getClassSet() {
        return CLASS_SET;
    }

    public static Set<Class<?>> getControllerSet() {
        return getClassSetByAnnotation(Controller.class);
    }

    public static Set<Class<?>> getServiceSet() {
        return getClassSetByAnnotation(Service.class);
    }

    public static Set<Class<?>> getComponentSet() {
        return getClassSetByAnnotation(Component.class);
    }

    public static Set<Class<?>> getBeanClass() {
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        classSet.addAll(getControllerSet());
        classSet.addAll(getServiceSet());
        classSet.addAll(getComponentSet());
        return classSet;
    }

    public static Set<Class<?>> getClassSetBySuper(Class<?> superClass) {
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for(Class<?> clazz : CLASS_SET) {
            if(superClass.isAssignableFrom(clazz) && !clazz.equals(superClass)) {
                classSet.add(clazz);
            }
        }
        return classSet;
    }

    public static Set<Class<?>> getClassSetByAnnotation(Class<? extends Annotation> annotation) {
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for(Class<?> clazz : CLASS_SET) {
            if(clazz.isAnnotationPresent(annotation)) {
                classSet.add(clazz);
            }
        }
        return classSet;
    }

}
