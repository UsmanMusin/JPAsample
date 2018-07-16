
package com.jpasample.controllers;

import com.jpasample.dao.HiberDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("user")
public class MainController {
    
    Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    HiberDAO dao;

    @RequestMapping("admin.do")
    public ModelAndView adminPage(String user) {
        ModelAndView mv = new ModelAndView("adminpanel");
        mv.addObject("employees",dao.getAllEmployees());
        mv.addObject("departments",dao.getAllDepartments());
        mv.addObject("organizations",dao.getAllOrganizations());
        return mv;              
    }

    @RequestMapping("userpage.do")
    public ModelAndView userPage(String user) {
        ModelAndView mv = new ModelAndView("adminpanel");
        mv.addObject("user",user);
        /*mv.addObject("employees",dao.getAllEmployees());
        mv.addObject("departments",dao.getAllDepartments());
        mv.addObject("organizations",dao.getAllOrganizations());*/
        return mv;
    }

    @RequestMapping(value = "check.do", method = RequestMethod.POST)
    public ModelAndView checkuser(@RequestParam String user) {
        if(dao.userCheck(user)){
            return userPage(user);
        }
        else {
            ModelAndView mv = new ModelAndView("login");
            mv.addObject("error","Такого пользователя не существует");
            return mv;
        }
    }





    @RequestMapping("start.do")
    public ModelAndView start() {
        dao.init();
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

}
