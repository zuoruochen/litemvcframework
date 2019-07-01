package com.zrc.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import java.util.List;

public class ProxyManager {
    @SuppressWarnings("unchecked")
    public static <T> T createProxy(Class<T> cls, List<Proxy> proxyList){
        return (T) Enhancer.create(cls, (MethodInterceptor) (obj, method, args, proxy) -> new ProxyChain(cls, obj, method, proxy, args, proxyList).doProxyChain());
    }
}
