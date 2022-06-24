package com.jaffa.practice.dubbo.router;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.cluster.router.state.CacheableStateRouterFactory;
import org.apache.dubbo.rpc.cluster.router.state.StateRouter;

@Activate(order = 1000)
public class BetaStateRouterFactory extends CacheableStateRouterFactory {

    @Override
    protected <T> StateRouter<T> createRouter(Class<T> interfaceClass, URL url) {
        return new BetaStateRouter(url);
    }
}
