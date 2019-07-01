package com.zrc.util;

import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class ClassLoaderUtilTest {

    @Test
    public void getClassLoader() {
        ClassLoader classLoader = ClassLoaderUtil.getClassLoader();
        System.out.println(classLoader);
        assertNotNull(classLoader);
    }

//    @Test
//    public void loadClass() {
//        ClassLoader classLoader = ClassLoaderUtil.getClassLoader();
//        ClassLoaderUtil.loadClass()
//    }
//
    /**
     * add /target/classes to class path firstly
     */
    @Test
    public void getClassSet() {
        ClassLoader classLoader = ClassLoaderUtil.getClassLoader();
        Set<Class<?>> classSet = ClassLoaderUtil.getClassSet("com.zrc");
        for(Class<?> clazz : classSet) {
            System.out.println(clazz.getName());
        }
        assertNotNull(classSet);
    }
}