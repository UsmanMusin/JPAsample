package com.jpasample.service;

import com.jpasample.dao.DAO;
import com.jpasample.dao.HiberDAO;
import com.jpasample.model.Assignment;
import com.jpasample.model.Department;
import com.jpasample.model.Employee;
import com.jpasample.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {

    @Autowired
    DAO dao;

    @Override
    public String holturchik(){
        int execute;
        int notExecute;
        int max = 0;
        long idHol = 0;
        boolean halhav = false;
        for (Employee employee: dao.getAllEmployees()) {
            notExecute = dao.getForMeAssignments(employee.getId()).size();
            execute = dao.getForMeDoneAssignments(employee.getId()).size();
            if((notExecute!=0) &&(execute != 0)) {
                int average = notExecute + execute;
                int dif = notExecute - execute;
                if (dif > max) {
                    max = dif;
                    halhav = true;
                    idHol = employee.getId();
                }
            }
        }
        if(halhav){
            Employee emp = dao.getEmpById(idHol);
            return emp.getSurname() + emp.getName() + emp.getMiddleName();
        }
        else return "Все пашут как кони";
    }

    @Override
    public String trudyaga(){
        int max = 0;
        boolean have = false;
        Employee maxEmp = new Employee();
        for (Employee employee: dao.getAllEmployees()) {
            int executed = dao.getForMeDoneAssignments(employee.getId()).size();
            if(executed > max){
                maxEmp = employee;
                have = true;
            }
        }
        if(have){
            return maxEmp.getSurname() + maxEmp.getName();
        }
        else return "Все работают одинаково";
    }



    @Override
    @Transactional
    public void deleteEmp(long id) {
        Employee emp;
        emp = dao.getEmpById(id);
        for (Assignment assignment: dao.getAllAssignments()) {
            if(assignment.getAuthor() == emp){
                assignment.setAuthor(null);
            }
            else if(assignment.getExecutor() == emp){
                assignment.setExecutor(null);
            }
        }

        for (Department dep: dao.getAllDepartments()) {
            if(dep.getManager() == emp){
                dep.setManager(null);
            }
            dep.getEmployeeSet().remove(emp);
        }


        for (Organization org: dao.getAllOrganizations()) {
            if(org.getManager() == emp){
                org.setManager(null);
            }
        }

        if(emp != null){
            dao.removeEmp(emp);
        }

    }

}
