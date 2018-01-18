package com.example.springbootdemo.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootdemo.exception.MyException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

//@RestController
@Controller
public class HelloController {
	
	//@ResponseBody
//    @RequestMapping("/hello")
//    public String hello() {
//        return "hello" ;
//    }
//    public String hello() throws Exception {
//        throw new Exception("发生错误");
//    }
    
    @RequestMapping("/json")
    public String json() throws MyException {
        throw new MyException("发生错误2");
    }
    
    
    @RequestMapping("/")
    public String index(ModelMap map) {
        map.addAttribute("host", "http://blog.didispace.com");
        return "index";
    }
    
    @RequestMapping("/login")
    public String login() {
        return "login";
    }
    
    
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello(@RequestParam String name) {
        return "Hello " + name;
    }

}