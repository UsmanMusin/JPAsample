package com.jpasample.service;

import com.jpasample.dao.DAO;
import com.jpasample.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {

    @Autowired
    DAO dao;

    @Override
    public String holturchik(){
        int execute;
        int notExecute;
        float max = 100;
        long idHol = -1;
        for (Employee employee: dao.getAllEmployees()) {
            notExecute = dao.getForMeAssignments(employee.getId()).size();
            execute = dao.getForMeDoneAssignments(employee.getId()).size();
            int average = notExecute + execute;
            float dif = ((execute/average)*100) - ((notExecute/average)*100);
            if(dif < max){
                max = dif;
                idHol = employee.getId();
            }
        }
        if(idHol > -1){
            Employee emp = dao.getEmpById(idHol);
            return emp.getSurname() + emp.getName() + emp.getMiddleName();
        }
        else return "Все пашут как кони";
    }
}
