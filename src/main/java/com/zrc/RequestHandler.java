package com.zrc;

import java.lang.reflect.Method;

public class RequestHandler {
    private Method method;
    private Class<?> controllerClass;

    public RequestHandler(Method method, Class<?> controllerClass) {
        this.method = method;
        this.controllerClass = controllerClass;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public void setControllerClass(Class<?> controllerClass) {
        this.controllerClass = controllerClass;
    }
}
