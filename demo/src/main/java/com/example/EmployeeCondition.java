package com.example;

public enum EmployeeCondition{
    obecny{@Override public String toString(){return "obecny";}}, 
    delegacja{@Override public String toString(){return "delegacja";}}, 
    chory{@Override public String toString(){return "chory";}}, 
    nieobecny{@Override public String toString(){return "nieobecny";}}
}