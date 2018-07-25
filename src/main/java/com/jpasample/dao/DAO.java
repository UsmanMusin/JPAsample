package com.jpasample.dao;

import com.jpasample.model.Assignment;
import com.jpasample.model.Department;
import com.jpasample.model.Employee;
import com.jpasample.model.Organization;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DAO {
    List<Employee> getAllEmployees();

    List<Department> getAllDepartments();

    List<Organization> getAllOrganizations();

    List<Assignment> getAllAssignments();

    List<Assignment> getMyAssignments(long id);

    List<Assignment> getForMeAssignments(long id);

    List<Assignment> getForMeDoneAssignments(long id);


    @Transactional
    void init();

    long userCheck(String user, String pass);

    void removeEmp(Employee employee);

    Employee getEmpById(long id);

    void updateEmp(Employee employee);

    void addEmp(Employee employee);

    void addAssign(Assignment assignment);

    Assignment getAssignById(long id);

    void executeAssignById(long id);
}
