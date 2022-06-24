package com.jaffa.practice.dubbo.filter;

import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

@Activate(group = {"consumer", "provider"})
public class PrintHelloFilter implements Filter {
    public PrintHelloFilter(){
        System.out.println("PrintHelloFilter");
    }
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        StringBuilder info = new StringBuilder();
        if (RpcContext.getServiceContext().isConsumerSide()) {
            info.append("Hello Consumer.");
        }else {
            info.append("Hello Provider");
        }
        System.out.println(info);
        return invoker.invoke(invocation);
    }
}
