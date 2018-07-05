package com.jpasample.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "assignment")
public class Assignment {

    @Id
    @GeneratedValue
    @Column(name = "as_id")
    private long as_id;

    private String theme;

    @ManyToOne
    private Employee author;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "assigments_employees",
            joinColumns = { @JoinColumn(name = "as_id") },
            inverseJoinColumns = { @JoinColumn(name = "emp_id") })
    private Set<Employee> executors;


    private int time;
    private boolean control;
    private String text;
    private boolean execution;
    private String status;

    public Assignment() {
    }

    public Assignment(String theme, Employee author, Set<Employee> executors, int time, boolean control, String text, boolean execution, String status) {
        this.theme = theme;
        this.author = author;
        this.executors = executors;
        this.time = time;
        this.control = control;
        this.text = text;
        this.execution = execution;
        this.status = status;
    }

    public long getId() {
        return as_id;
    }

    public void setId(long id) {
        this.as_id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Employee getAuthor() {
        return author;
    }

    public void setAuthor(Employee author) {
        this.author = author;
    }

    public Set<Employee> getExecutors() {
        return executors;
    }

    public void setExecutors(Set<Employee> executors) {
        this.executors = executors;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public boolean isControl() {
        return control;
    }

    public void setControl(boolean control) {
        this.control = control;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isExecution() {
        return execution;
    }

    public void setExecution(boolean execution) {
        this.execution = execution;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString(){
        return "\nAssignment:\n" + "theme: " + theme + "\nauthor:" + author +
                "\nexecutors: " + executors + "\ntime: " + time + "\ncontrol: " + control +
                "\ntext: " + text + "\nexecution: " + execution + "\nstatus: " + status;
    }
}
