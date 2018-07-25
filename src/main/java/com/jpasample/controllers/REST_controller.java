package com.jpasample.controllers;

import com.jpasample.dao.DAO;
import com.jpasample.model.Employee;
import com.jpasample.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("rest")
public class REST_controller {

    Service service;
    @Autowired
    DAO dao;

    @RequestMapping(value = "/empset.do", method = RequestMethod.GET, produces = "application/json")
    public List<Employee> getEmpSet() {
        return dao.getAllEmployees();
    }

    @RequestMapping(value = "/delemp/{id}.do")
    public void delemp(@PathVariable long id){
        service.deleteEmp(id);
    }
}
