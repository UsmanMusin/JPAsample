
package com.jpasample.controllers;

import com.jpasample.dao.HiberDAO;
import com.jpasample.model.Cat;
import java.util.Collections;
import java.util.List;

import com.jpasample.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("user")
public class MainController {
    
    Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    HiberDAO dao;
    
    @ModelAttribute("user")
    public String getStatus() {
        return "infinity";
    }
    
    @RequestMapping("list.do")
    public ModelAndView showAll(@ModelAttribute String user) {
        ModelAndView mv = new ModelAndView("listalldata");
        mv.addObject(user);
        List<Cat> allCats = dao.getAllCats();
        mv.addObject("cats", allCats);
        mv.addObject("persons", dao.getAllPersons());
        mv.addObject("employees",dao.getAllEmployees());
        mv.addObject("departments",dao.getAllDepartments());
        mv.addObject("organizations",dao.getAllOrganizations());
        log.info(allCats.toString());
        return mv;              
    }
    
    @RequestMapping("showCat.do")
    public ModelAndView showOne(long id) {
        ModelAndView mv = new ModelAndView("listalldata");
        Cat c = dao.getCatById(id);
        mv.addObject("cats", Collections.singletonList(c));
        return mv;              
    }

    @RequestMapping("deleteCat.do")
    public ModelAndView deleteOne(long id) {
        ModelAndView mv = new ModelAndView("listalldata");
        dao.deleteCat(id);
        //mv.addObject("cats", Collections.singletonList(c));
        return mv;
    }

    @RequestMapping("deletePerson.do")
    public ModelAndView deletePerson(long id) {
        ModelAndView mv = new ModelAndView("listalldata");
        dao.deletePerson(id);
        //mv.addObject("cats", Collections.singletonList(c));
        return mv;
    }

    @RequestMapping("changeOwner.do")
    public ModelAndView changeOwner(long cid, long pid) {
        ModelAndView mv = new ModelAndView("listalldata");
        dao.changeOwner(cid, pid);
        //mv.addObject("cats", Collections.singletonList(c));
        return mv;
    }

    @RequestMapping("init.do")
    public String init() {
        dao.init();
        return "redirect:list.do";
    }
    
    @RequestMapping("addcat.do")
    public ModelAndView addCat() {
        dao.addRandomCat();
        return showAll();             
    }
    
    

    
    
    
    
}
