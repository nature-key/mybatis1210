package com.jiepi.dao;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Statement;
import java.util.Properties;

@Intercepts({
        @Signature(type = StatementHandler.class, method = "parameterize", args = Statement.class)
})
public class MyFirstPlugin implements Interceptor {

    //拦截对象方法执行
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("dd" + invocation.getMethod());
        Object obj = invocation.getTarget();
        System.out.println(obj);
       MetaObject metaObject= SystemMetaObject.forObject(obj);
       Object value =  metaObject.getValue("parameterHandler.parameterObject");
        System.out.println(value);
        metaObject.setValue("parameterHandler.parameterObject",2);
        Object process = invocation.proceed();
        return process;
    }

    //目标对象创建代理对象
    public Object plugin(Object o) {
        System.out.println("o" + o.getClass());
        Object wrap = Plugin.wrap(o, this);
        return wrap;
    }

    //设置属性
    public void setProperties(Properties properties) {
        System.out.println("p" + properties);
    }
}
