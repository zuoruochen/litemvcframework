package com.zrc.util;

import com.zrc.ClassHelper;
import com.zrc.demo.ControllerDemo;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Set;

import static org.junit.Assert.*;

public class ReflectionUtilTest {

    @Test
    public void newInstance() {
        Set<Class<?>> classSet = ClassHelper.getControllerSet();
        for(Class<?> clazz : classSet) {
            ControllerDemo cd = (ControllerDemo) ReflectionUtil.newInstance(clazz);
            System.out.println(cd.getName());
            assertNotNull(cd);
        }
    }

    @Test
    public void invokeMethod() {
        Set<Class<?>> classSet = ClassHelper.getControllerSet();
        for(Class<?> clazz : classSet) {
            try {
                Method method = clazz.getMethod("setName", String.class);
                Parameter[] parameters = method.getParameters();
                for(Parameter parameter : parameters) {
                    System.out.println(parameter.getType());
                    System.out.println(parameter.getType().getClass());
                    System.out.println(parameter.getParameterizedType());
                    System.out.println(parameter.getClass());
                }
                Object cd = ReflectionUtil.newInstance(clazz);
                ReflectionUtil.invokeMethod(cd, method, "zuoruochen");
                Method method1 = clazz.getMethod("getName");
                System.out.println(ReflectionUtil.invokeMethod(cd, method1));
                assertNotNull(cd);
            } catch (Exception e) {
                System.out.println("get Method error");
            }
        }
    }

    @Test
    public void setFiled() {
        Set<Class<?>> classSet = ClassHelper.getControllerSet();
        for(Class<?> clazz : classSet) {
            try {
                Object cd = ReflectionUtil.newInstance(clazz);
                Field field = clazz.getDeclaredField("name");
                ReflectionUtil.setFiled(cd, field, "wangqiqi");
                Method method1 = clazz.getMethod("getName");
                System.out.println(ReflectionUtil.invokeMethod(cd, method1));
                assertNotNull(cd);
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("get Method error");
            }
        }
    }
}