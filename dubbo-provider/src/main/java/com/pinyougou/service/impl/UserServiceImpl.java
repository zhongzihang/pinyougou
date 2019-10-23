package com.pinyougou.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.pinyougou.service.UserService;


/****
 * 1、集成dubbo的功能
 * 2、把该对象创建实例后给SpringIOC容器
 */
@Service
public class UserServiceImpl implements UserService {
    /***
     * 获取用户名
     * @return
     */

    public String getName() {
        System.out.println("my name is xiaohong!");
        return "hello";
    }
}
