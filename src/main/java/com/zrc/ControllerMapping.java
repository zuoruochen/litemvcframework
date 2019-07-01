package com.zrc;

import com.zrc.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ControllerMapping {
    private static final Map<RequestAction, RequestHandler> ACTION_MAP = new HashMap<RequestAction, RequestHandler>();
    static {
        Set<Class<?>> classSet = ClassHelper.getControllerSet();
        for(Class<?> clazz : classSet) {
            Method[] methods = clazz.getDeclaredMethods();
            for(Method method : methods) {
                if(method.isAnnotationPresent(RequestMapping.class)) {
                    RequestHandler requestHandler = new RequestHandler(method, clazz);
                    Annotation requestMapping = method.getAnnotation(RequestMapping.class);
                    // TODO: validate method and path value
                    RequestAction requestAction = new RequestAction(((RequestMapping) requestMapping).method(), ((RequestMapping) requestMapping).path());
                    ACTION_MAP.put(requestAction, requestHandler);
                }

            }
        }
    }

    public static RequestHandler getRequestHandler(String requestMethod, String requestPath) {
        RequestAction requestAction = new RequestAction(requestMethod, requestPath);
        return ACTION_MAP.get(requestAction);
    }
}
