package com.zrc.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

public abstract class AspectProxy implements Proxy{
    private static final Logger LOGGER = LoggerFactory.getLogger(AspectProxy.class);

    @Override
    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        Class<?> targetClass = proxyChain.getTargetClass();
        Method targetMethod = proxyChain.getTargetMethod();
        Object[] targetParams = proxyChain.getMethodParams();
        begin();
        try {
            if(intercept(targetClass, targetMethod, targetParams)) {
                before(targetClass, targetMethod, targetParams);
                Object result = proxyChain.doProxyChain();
                after(targetClass, targetMethod, targetParams);
                return result;
            } else {
                return proxyChain.doProxyChain();
            }

        } catch (Exception e) {
            LOGGER.error("proxy failure", e);
            error(targetClass, targetMethod, targetParams, e);
            throw e;
        } finally {
            end();
        }
    }

    /**
     * do something init
     */
    public void begin() {

    }

    /**
     * do something clean
     */
    public void end() {

    }

    /**
     * override this method to define if the clazz.method should be proxy
     * like just proxy the specific method in the class
     * @param clazz
     * @param method
     * @param params
     * @return
     */
    public boolean intercept(Class<?> clazz, Method method, Object[] params) {
        return true;
    }

    /**
     * before adviser
     * @param clazz
     * @param method
     * @param params
     * @throws Throwable
     */
    public void before(Class<?> clazz, Method method, Object[] params) throws Throwable{

    }

    /**
     * after adviser
     * @param clazz
     * @param method
     * @param params
     * @throws Throwable
     */
    public void after(Class<?> clazz, Method method, Object[] params) throws Throwable{

    }
    /**
     * error handling when get exception when invoke method
     * @param clazz
     * @param method
     * @param params
     * @param e
     */
    public void error(Class<?> clazz, Method method, Object[] params, Throwable e) {

    }

}
