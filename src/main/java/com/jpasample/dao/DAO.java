package com.jpasample.dao;

import com.jpasample.model.Department;
import com.jpasample.model.Employee;
import com.jpasample.model.Organization;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DAO {
    List<Employee> getAllEmployees();

    List<Department> getAllDepartments();

    List<Organization> getAllOrganizations();

    @Transactional
    void init();

    boolean userCheck(String user, String pass);

    void deleteEmp(long id);
}
