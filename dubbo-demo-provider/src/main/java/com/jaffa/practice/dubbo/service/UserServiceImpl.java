package com.jaffa.practice.dubbo.service;

import com.jaffa.practice.dubbo.dto.UserInfo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@DubboService
public class UserServiceImpl implements UserService{
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @DubboReference
    private LogService logService;

    @Override
    public Boolean create(UserInfo userInfo) {
        logService.println("abc");
        logger.debug("userInfo = {}", userInfo);
        //System.out.println(userInfo);
        return Boolean.TRUE;
    }
}
