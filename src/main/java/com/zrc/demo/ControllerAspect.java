package com.zrc.demo;

import com.zrc.annotation.Aspect;
import com.zrc.annotation.Controller;
import com.zrc.proxy.AspectProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

@Aspect(Controller.class)
public class ControllerAspect extends AspectProxy {
    private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

    private long begin;
    @Override
    public void before(Class<?> clazz, Method method, Object[] params) throws Throwable {
        LOGGER.debug("------------begin-------------");
        LOGGER.debug("class : " + clazz.getName());
        LOGGER.debug("method : " + method.getName());
        begin = System.currentTimeMillis();
    }

    @Override
    public void after(Class<?> clazz, Method method, Object[] params) throws Throwable {
        LOGGER.debug(String.format("time : %dms", System.currentTimeMillis() - begin));
        LOGGER.debug("------------end-------------");
    }
}
