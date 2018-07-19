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

    @ManyToOne(fetch = FetchType.EAGER)
    private Employee author;

    @ManyToOne(fetch = FetchType.EAGER)
    private Employee executor;

    private int time;
    private String text;
    private boolean execution;

    public Assignment() {
    }

    public Assignment(String theme, Employee author, Employee executor, int time, String text, boolean execution, String status) {
        this.theme = theme;
        this.author = author;
        this.executor = executor;
        this.time = time;
        this.text = text;
        this.execution = execution;
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

    public Employee getExecutor() {
        return executor;
    }

    public void setExecutor(Employee executor) {
        this.executor = executor;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
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

    @Override
    public String toString(){
        return theme;
    }
}
