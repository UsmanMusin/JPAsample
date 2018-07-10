package com.jpasample.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue
    @Column(name = "dep_id")
    private long dep_id;

    private String name;
    private String contacts;

    @OneToOne
    private Employee manager;

    @OneToMany(cascade={CascadeType.PERSIST}, orphanRemoval=true,fetch = FetchType.EAGER)
    private Set<Employee> employeeSet;

    public Department() {
    }

    public Department(String name, String contacts) {
        this.name = name;
        this.contacts = contacts;
    }

    public long getId() {
        return dep_id;
    }

    public void setId(long id) {
        this.dep_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Set<Employee> getEmployeeSet() {
        return employeeSet;
    }

    public void setEmployeeSet(Set<Employee> employeeSet) {
        this.employeeSet = employeeSet;
    }

    public void addEmployee(Employee employee){
        employeeSet.add(employee);
    }

    @Override
    public String toString(){
        return name;
    }
}
