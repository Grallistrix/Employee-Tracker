package com.example.entities;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "groups")
public class EmployeeGroup {
    @Id
    @Column(name="Group_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Group_ID;

    private String GrName;
    private int Capacity;

    // Konstruktory
    public EmployeeGroup(){}

    public EmployeeGroup(String name, int cap, int neoId) {
        this.GrName = name;
        this.Capacity = cap;
        this.Group_ID=neoId;
    }

    // Getter/Setter
    public int getGroup_ID() {
        return Group_ID;
    }

    public void setGroup_ID(int id) {
        this.Group_ID = id;
    }

    public String getGroupName() {
        return GrName;
    }

    public void setGroupName(String groupName) {
        this.GrName = groupName;
    }

    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity(int capacity) {
        this.Capacity = capacity;
    }


    @Override
    public String toString() {
        return "--[" +
            " id='" + getGroup_ID() + "'" +
            ", GrName='" + getGroupName() + "'" +
            ", Capacity='" + getCapacity() + "'" +
            "]--";
    }



}