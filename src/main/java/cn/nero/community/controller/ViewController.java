package cn.nero.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/6
 */
@Controller
public class ViewController {

    @RequestMapping("/")
    public String index() {
        return "login";
    }

    @GetMapping("/toLogin")
    public String login(){
        return "login";
    }

    @GetMapping("/toRegister")
    public String register(){
        return "register";
    }

    @GetMapping("/toStaff")
    public String toStaff(){
        return "staff";
    }

    @GetMapping("/toEcharts")
    public String toEcharts(){
        return "echarts";
    }



}
