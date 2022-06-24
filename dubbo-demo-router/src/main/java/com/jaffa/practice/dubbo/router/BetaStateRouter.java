package com.jaffa.practice.dubbo.router;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.config.configcenter.ConfigChangedEvent;
import org.apache.dubbo.common.config.configcenter.ConfigurationListener;
import org.apache.dubbo.common.utils.CollectionUtils;
import org.apache.dubbo.common.utils.Holder;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.cluster.router.RouterSnapshotNode;
import org.apache.dubbo.rpc.cluster.router.state.AbstractStateRouter;
import org.apache.dubbo.rpc.cluster.router.state.BitList;

import java.util.Objects;
import java.util.function.Predicate;

public class BetaStateRouter<T> extends AbstractStateRouter<T> implements ConfigurationListener {

    public static final String RUNTIME_ENV = "runtime-env";

    public BetaStateRouter(URL url) {
        super(url);
    }

    @Override
    public void process(ConfigChangedEvent event) {
    }

    private <T> BitList<Invoker<T>> filterInvoker(BitList<Invoker<T>> invokers, Predicate<Invoker<T>> predicate) {
        if (invokers.stream().allMatch(predicate)) {
            return invokers;
        }

        BitList<Invoker<T>> newInvokers = invokers.clone();
        newInvokers.removeIf(invoker -> !predicate.test(invoker));

        return newInvokers;
    }

    @Override
    protected BitList<Invoker<T>> doRoute(BitList<Invoker<T>> invokers, URL url, Invocation invocation, boolean needToPrintMessage, Holder<RouterSnapshotNode<T>> routerSnapshotNodeHolder, Holder<String> messageHolder) throws RpcException {
        System.out.println("url: " + url);
        if (CollectionUtils.isEmpty(invokers)) {
            if (needToPrintMessage) {
                messageHolder.set("Directly Return. Reason: Invokers from previous router is empty.");
            }
            return invokers;
        }

        invokers.stream().forEach(invoker -> System.out.println(invoker.getUrl()));

        String runtimeEnv = StringUtils.isEmpty(invocation.getAttachment(RUNTIME_ENV)) ? url.getParameter(RUNTIME_ENV) :
                invocation.getAttachment(RUNTIME_ENV);

        BitList<Invoker<T>> result = filterInvoker(invokers, invoker -> Objects.equals(runtimeEnv, invoker.getUrl().getParameter(RUNTIME_ENV)));

        if (CollectionUtils.isNotEmpty(result)) {
            return  result;
        }

        return invokers;
    }
}
