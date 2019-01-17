package com.dante.ssm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/test")
public class TestController {
    private static Logger log = LoggerFactory.getLogger(TestController.class);
    @RequestMapping(method = RequestMethod.GET)
    public String test(ModelMap modelMap){
        log.info("----------This is a test log----------");
        modelMap.addAttribute("message","This is s message from test");
        return "success";
    }
}
