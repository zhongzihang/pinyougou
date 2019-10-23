package com.pinyougou.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/user")
public class UserController {

    /*@Autowired*/

    /****
     * 1)给单钱接口创建一个代理对象
     * 2)注入远程调用的信息给代理对象
     */
    @Reference
    private UserService userService;

    /***
     * 获取用户名远程测试
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/name")
    public String getName(){
        return userService.getName();
    }

}
