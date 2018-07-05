package com.jpasample.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue
    @Column(name = "emp_id")
    private long emp_id;

    private String name;
    private String surname;
    private String middleName;
    private String position;

    @ManyToOne
    private Department department;



    @ManyToMany(mappedBy = "executors")
    private Set<Assignment> assignmentSet;

    public Employee() {
    }

    public Employee(String name, String surname, String middleName, String position) {
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.position = position;
        //this.department = department;
        //this.assignmentSet = assignmentSet;
    }

    public long getId() {
        return emp_id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getPosition() {
        return position;
    }

    public Department getDepartment() {
       return department;
    }

    public void setId(long id) {
        this.emp_id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Assignment> getAssignmentSet() {
        return assignmentSet;
    }

    public void setAssignmentSet(Set<Assignment> assignmentSet) {
        this.assignmentSet = assignmentSet;
    }

    @Override
    public String toString(){
        return "\nEmployee:\n" + "name: " + name + "\nsurname:" + surname +
                "\nmiddlename: " + middleName + "\nposition: " + position;
//                "\ndepartment: " + department;
    }
}
