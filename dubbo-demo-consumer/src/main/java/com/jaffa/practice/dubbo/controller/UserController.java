package com.jaffa.practice.dubbo.controller;

import com.jaffa.practice.dubbo.dto.UserInfo;
import com.jaffa.practice.dubbo.router.BetaStateRouter;
import com.jaffa.practice.dubbo.service.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/userinfo/")
public class UserController {
    @DubboReference
    private UserService userService;

    @RequestMapping("get")
    public Mono<UserInfo> get(){
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1000L);
        userInfo.setName("Test 01");
        return Mono.just(userInfo);
    }
    @RequestMapping("create")
    public Mono<Boolean> create(UserInfo userInfo) {
        RpcContext.getContext().setAttachment(BetaStateRouter.RUNTIME_ENV, "beta");
        Boolean res = userService.create(userInfo);
        return Mono.just(res);
    }
}
