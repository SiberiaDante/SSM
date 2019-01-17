package com.dante.ssm.controller;

import com.dante.ssm.bean.User;
import com.dante.ssm.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UserController {
    private static Logger log = LoggerFactory.getLogger(UserController.class);
    @Resource
    private IUserService userService;

    // /user/{id}
    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public String getUser(@PathVariable int id, ModelMap modelMap) {
        User user = userService.getUserById(id);
        modelMap.addAttribute("user", user);
        log.info(user.toString());
        return "user";
    }
}
