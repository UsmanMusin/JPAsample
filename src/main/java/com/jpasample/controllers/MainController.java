
package com.jpasample.controllers;

import com.jpasample.dao.DAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@SessionAttributes("user")
public class MainController {
    
    Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    DAO dao;


    @RequestMapping("admin.do")
    public ModelAndView adminPage() {
        ModelAndView mv = new ModelAndView("adminpanel");
        mv.addObject("employees",dao.getAllEmployees());
        mv.addObject("departments",dao.getAllDepartments());
        mv.addObject("organizations",dao.getAllOrganizations());
        return mv;              
    }

    @RequestMapping("userpage.do")
    public String userPage(Model m) {
       // ModelAndView mv = new ModelAndView("adminpanel");
       // mv.addObject("user",user);
        m.addAttribute("message","Hello");
        //m.asMap().put("user", "Petrov");
        /*mv.addObject("employees",dao.getAllEmployees());
        mv.addObject("departments",dao.getAllDepartments());
        mv.addObject("organizations",dao.getAllOrganizations());*/
        return "userpage";
    }

    @RequestMapping(value = "check.do", method = RequestMethod.POST)
    public ModelAndView checkuser(@RequestParam String user, String pass) {
        if(dao.userCheck(user,pass)){
            ModelAndView mv = new ModelAndView("redirect:userpage.do");
            mv.addObject("user",user);
            return mv;
        }
        else {
            ModelAndView mv = new ModelAndView("login");
            mv.addObject("error","Неверный логин или пароль");
            return mv;
        }
    }

    @RequestMapping("init.do")
    public ModelAndView init(){
        dao.init();
        ModelAndView mv = new ModelAndView("login");
        mv.addObject("initmsg","Данные внесены в базу");
        return mv;
    }



    @RequestMapping("start.do")
    public ModelAndView start() {
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

    @RequestMapping("deleteEmp{id}.do")
    public ModelAndView deleteEmp(@PathVariable("id") long id) {
        dao.deleteEmp(id);
        ModelAndView mv = new ModelAndView("redirect:admin.do");
        /*mv.addObject("employees",dao.getAllEmployees());
        mv.addObject("departments",dao.getAllDepartments());
        mv.addObject("organizations",dao.getAllOrganizations());*/
        return mv;
    }
 
}
