package com.zrc;

import com.zrc.util.ClassLoaderUtil;

public class ServerInit {
    public static void init() {
        Class<?>[] classSet = {
                ClassHelper.class,
                BeanContainer.class,
                Aop.class,
                Ioc.class,
                ControllerMapping.class
        };
        for(Class<?> clazz:classSet) {
            ClassLoaderUtil.loadClass(clazz.getName(), true);
        }
    }
}
