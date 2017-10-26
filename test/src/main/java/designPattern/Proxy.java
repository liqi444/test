package designPattern;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created liqi on 2017/9/15.
 */
public class Proxy {
    public static void main(String[] args) {
        new Proxy().test3();
    }

    void test1() {
        IUser user = new User();
        StaticProxy proxy = new StaticProxy(user);
        proxy.save();
    }

    void test2(){
        IUser user = new User();
        System.out.println(user.getClass());
        IUser userProxy = (IUser) new DynamicProxy(user).getProxyInstance();
        System.out.println(userProxy.getClass());
        userProxy.save();
    }
    void test3(){
        User user = new User();
        System.out.println(user.getClass());
        User userProxy = (User) new CglibProxy(user).getInstance();
        System.out.println(userProxy.getClass());
        userProxy.save();
    }

}

interface IUser {
    void save();
}

class User implements IUser {
    @Override
    public void save() {
        System.out.println("save");
    }
}

class StaticProxy implements IUser {
    IUser target;

    public StaticProxy(IUser target) {
        this.target = target;
    }

    @Override
    public void save() {
        System.out.println("start");
        target.save();
        System.out.println("end");
    }
}

class DynamicProxy {
    Object target;

    public DynamicProxy(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        return java.lang.reflect.Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("start");
                Object returnValue = method.invoke(target,args);
                System.out.println("end");
                return returnValue;
            }
        });
    }
}

class User2 {
    public void save() {
        System.out.println("save");
    }
}
class CglibProxy implements MethodInterceptor{
    Object target;
    public CglibProxy(Object target){
        this.target = target;
    }
    public Object getInstance(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("start");
        Object returnValue = method.invoke(target,objects);
        System.out.println("end");
        return returnValue;
    }
}