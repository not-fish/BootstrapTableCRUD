package com.example.bt.controller;

import com.example.bt.dao.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author Peko
 */

@Controller
public class TestController {

    @Resource
    UserMapper userMapper;

    @RequestMapping("test")
    @ResponseBody
    public String userCount(){
        return "count = "+userMapper.selectByName("9");
    }

}
