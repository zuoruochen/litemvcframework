package com.zrc;

import com.zrc.annotation.Aspect;
import com.zrc.proxy.AspectProxy;
import com.zrc.proxy.Proxy;
import com.zrc.proxy.ProxyManager;
import com.zrc.util.ReflectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.*;

public class Aop {
    private static final Logger LOGGER = LoggerFactory.getLogger(Aop.class);
    static {
        Map<Class<?>, Set<Class<?>>> proxyMap = getProxyMap();
        Map<Class<?>, List<Proxy>> targetMap = getTargetMap(proxyMap);
        for(Map.Entry<Class<?>, List<Proxy>> entry : targetMap.entrySet()) {
            Object proxy = ProxyManager.createProxy(entry.getKey(), entry.getValue());
            BeanContainer.setBean(entry.getKey(), proxy);
        }
        LOGGER.info("aop init finished");
    }

    static public Set<Class<?>> getTargetClassSet(Aspect aspect) {
        Set<Class<?>> targetSet = new HashSet<>();
        Class<? extends Annotation> annotation = aspect.value();
        if(annotation!= null && !annotation.equals(Aspect.class)) {
            targetSet.addAll(ClassHelper.getClassSetByAnnotation(annotation));
        }
        return targetSet;
    }

    public static Map<Class<?>, Set<Class<?>>> getProxyMap() {
        Map<Class<?>, Set<Class<?>>> proxyMap = new HashMap<>();
        Set<Class<?>> proxyClassSet = ClassHelper.getClassSetBySuper(AspectProxy.class);
        for(Class<?> clazz : proxyClassSet) {
            if(clazz.isAnnotationPresent(Aspect.class)) {
                proxyMap.put(clazz, getTargetClassSet(clazz.getAnnotation(Aspect.class)));
            }
        }
        return proxyMap;
    }

    public static Map<Class<?>, List<Proxy>> getTargetMap(Map<Class<?>, Set<Class<?>>> proxyMap) {
        Map<Class<?>, List<Proxy>> targetMap = new HashMap<>();
        for(Map.Entry<Class<?>, Set<Class<?>>>  entry : proxyMap.entrySet()) {
            Class<?> proxyClass = entry.getKey();
            Set<Class<?>> targetClassSet = entry.getValue();
            for(Class<?> clazz : targetClassSet) {
                Proxy proxy = (Proxy) ReflectionUtil.newInstance(proxyClass);
                if(targetMap.containsKey(clazz)) {
                    targetMap.get(clazz).add(proxy);
                } else {
                    targetMap.put(clazz, new LinkedList<Proxy>(){{add(proxy);}});
                }
            }
        }
        return targetMap;
    }

}
