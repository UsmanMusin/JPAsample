package com.jpasample.controllers;

import com.jpasample.dao.DAO;
import com.jpasample.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class REST_controller {
    @Autowired
    DAO dao;

    @RequestMapping("/empset")
    public List<Employee> getEmpSet() {
        return dao.getAllEmployees();
    }
}
