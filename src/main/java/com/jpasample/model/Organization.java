package com.jpasample.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "organization")
public class Organization {

    @Id
    @GeneratedValue
    @Column(name = "org_id")
    private long org_id;

    @OneToOne
    private Employee manager;

    @OneToMany
    private Set<Department> departmentSet;

    private String name;
    private String legalAddress;
    private String physicalAddress;

    public Organization() {
    }

    public Organization(Employee manager, Set<Department> departmentSet, String name, String legalAddress, String physicalAddress) {
        this.manager = manager;
        this.departmentSet = departmentSet;
        this.name = name;
        this.legalAddress = legalAddress;
        this.physicalAddress = physicalAddress;
    }

    public long getId() {
        return org_id;
    }

    public void setId(long id) {
        this.org_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public String getLegalAddress() {
        return legalAddress;
    }

    public void setLegalAddress(String legalAddress) {
        this.legalAddress = legalAddress;
    }

    public String getPhysicalAddress() {
        return physicalAddress;
    }

    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }

    public Set<Department> getDepartmentSet() {
        return departmentSet;
    }

    public void setDepartmentSet(Set<Department> departmentSet) {
        this.departmentSet = departmentSet;
    }

    @Override
    public String toString(){
        return "\nOrganization:\n" + "name: " + name + "\nmanager:" + manager +
                "\nlegalAddress: " + legalAddress + "\nphysicalAddress: " + physicalAddress;
    }
}
