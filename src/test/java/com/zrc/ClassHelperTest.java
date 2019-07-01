package com.zrc;

import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class ClassHelperTest {

    @Test
    public void getControllerSet() {
        Set<Class<?>> classSet = ClassHelper.getControllerSet();
        for(Class<?> clazz : classSet) {
            System.out.println(clazz.getName());
        }
        assertFalse(classSet.isEmpty());
    }

    @Test
    public void getServiceSet() {
        Set<Class<?>> classSet = ClassHelper.getServiceSet();
        for(Class<?> clazz : classSet) {
            System.out.println(clazz.getName());
        }
        assertFalse(classSet.isEmpty());
    }

    @Test
    public void getBeanClass() {
        Set<Class<?>> classSet = ClassHelper.getBeanClass();
        for(Class<?> clazz : classSet) {
            System.out.println(clazz.getName());
        }
        assertFalse(classSet.isEmpty());
    }

    @Test
    public void getClassSet() {
        Set<Class<?>> classSet = ClassHelper.getClassSet();
        for(Class<?> clazz : classSet) {
            System.out.println(clazz.getName());
        }
        assertFalse(classSet.isEmpty());
    }
}