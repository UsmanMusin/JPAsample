
package com.jpasample.controllers;

import com.jpasample.dao.DAO;

import com.jpasample.model.Assignment;
import com.jpasample.model.Employee;
import com.jpasample.service.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashSet;
import java.util.Set;

@Controller
@SessionAttributes({"user","userId"})
public class MainController {
    
    Logger log = LoggerFactory.getLogger(this.getClass());
    ServiceImpl service;

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
    public ModelAndView userPage(@ModelAttribute("userId") long userId) {
        ModelAndView mv = new ModelAndView("userpage");
        mv.addObject("myAssignments", dao.getMyAssignments(userId));
        mv.addObject("forMeAssignments", dao.getForMeAssignments(userId));
        mv.addObject("forMeDoneAssignments", dao.getForMeDoneAssignments(userId));
        return mv;
    }

    @RequestMapping(value = "check.do", method = RequestMethod.POST)
    public ModelAndView checkuser(@RequestParam String user, String pass) {
        if(dao.userCheck(user,pass) > 0){
            ModelAndView mv = new ModelAndView("redirect:userpage.do");
            mv.addObject("user",user);
            mv.addObject("userId",dao.userCheck(user,pass));
            return mv;
        }
        else {
            ModelAndView mv = new ModelAndView("login");
            mv.addObject("error","Неверный логин или пароль");
            return mv;
        }
    }

    @RequestMapping("exit.do")
    public ModelAndView exit(SessionStatus status) {
        status.setComplete();
        ModelAndView mv = new ModelAndView("login");
        return mv;
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
        return mv;
    }

    @RequestMapping("editEmp{id}.do")
    public ModelAndView editEmp(@PathVariable("id") long id) {
        Employee employee = dao.getEmpById(id);
        ModelAndView mv = new ModelAndView("editemp");
        mv.addObject("employee",employee);
        return mv;
    }

    @RequestMapping("newEmp.do")
    public ModelAndView newEmp() {
        ModelAndView mv = new ModelAndView("addemp");
        return mv;
    }

    @RequestMapping("newAssign.do")
    public ModelAndView newAssign() {
        ModelAndView mv = new ModelAndView("addassign");
        mv.addObject("employees",dao.getAllEmployees());
        return mv;
    }

    @RequestMapping("executeAssign{id}.do")
    public String executeAssign(@PathVariable("id") long id) {
        dao.executeAssignById(id);
        return "redirect:userpage.do";
    }



    @RequestMapping(value = "updateemp{id}.do", method = RequestMethod.POST)
    public ModelAndView updateEmp(@PathVariable("id") long id,
            @RequestParam("name") String name,@RequestParam("surname") String surname,
                               @RequestParam("middlename") String middlename, @RequestParam("position") String position,
                               @RequestParam("password") String password) {
        Employee employee = dao.getEmpById(id);
        employee.setName(name);
        employee.setSurname(surname);
        employee.setMiddleName(middlename);
        employee.setPosition(position);
        employee.setPassword(password);
        dao.updateEmp(employee);
        ModelAndView mv = new ModelAndView("redirect:admin.do");
        return mv;
    }

    @RequestMapping(value = "addemp.do", method = RequestMethod.POST)
    public ModelAndView addEmp(@RequestParam("name") String name,@RequestParam("surname") String surname,
                               @RequestParam("middlename") String middlename, @RequestParam("position") String position,
                               @RequestParam("password") String password) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setSurname(surname);
        employee.setMiddleName(middlename);
        employee.setPosition(position);
        employee.setPassword(password);
        dao.addEmp(employee);
        ModelAndView mv = new ModelAndView("redirect:admin.do");
        return mv;
    }

    @RequestMapping(value = "addassign.do", method = RequestMethod.POST)
    public String addAssign(@ModelAttribute("userId") long userId,
            @RequestParam("theme") String theme,@RequestParam("executor") long id,
                                  @RequestParam("time") int time, @RequestParam("text") String text) {
        Assignment assignment = new Assignment();
        assignment.setTheme(theme);
        assignment.setTime(time);
        assignment.setText(text);
        assignment.setExecution(false);
        assignment.setExecutor(dao.getEmpById(id));
        Set<Assignment> assignmentSet = new HashSet<>();
        assignmentSet.add(assignment);
        assignment.setAuthor(dao.getEmpById(userId));
        dao.getEmpById(id).setAssignmentSet(assignmentSet);
        dao.addAssign(assignment);
        return "redirect:userpage.do";
    }

    @RequestMapping("holturchik.do")
    public ModelAndView holturchik() {
        String holturchik = service.holturchik();
        ModelAndView mv = new ModelAndView("redirect:admin.do");
        mv.addObject("holturchik", holturchik);
        return mv;
    }
 
}
