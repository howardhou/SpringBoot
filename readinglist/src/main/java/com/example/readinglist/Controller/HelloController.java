package com.example.readinglist.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// 第一个页面
@Controller
@RequestMapping("/hello")
public class HelloController {
    @RequestMapping(value = "/index")
    public String hello(Model model) {
        model.addAttribute("name", "Dear");
        return "hello";
    }
}
