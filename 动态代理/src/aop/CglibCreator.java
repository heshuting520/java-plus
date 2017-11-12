package aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * Created by 王萍 on 2017/11/12 0012.
 */
public class CglibCreator implements ProxyCreator {
    @Override
    public <T> T getProxy(Class<?> targetClass, List<ProxyInterceptor> interceptors) {
        return (T) Proxy.newProxyInstance(targetClass.getClassLoader(), targetClass.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return new ProxyInterceptorChain(targetClass,targetClass.getConstructor().newInstance(),method,args,interceptors).doChain();
            }
        });
    }
}
