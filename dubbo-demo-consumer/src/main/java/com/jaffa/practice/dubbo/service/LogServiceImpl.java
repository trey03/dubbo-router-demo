package com.jaffa.practice.dubbo.service;

import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class LogServiceImpl implements LogService{
    @Override
    public void println(String text) {
        System.out.println("log service println");
    }
}
