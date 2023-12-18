package com.example.entities;


import com.example.EmployeeCondition;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "workers")
public class Employee {
    @Id
    @Column(name="Worker_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Worker_ID;

    private String Imie;
    private String Nazwisko;

    private int RokUr;
    private int Salary;
   // @ManyToOne()
  //  @JoinColumn(name = "Grupa")
    private int Grupa;

    private String Kondycja;


    
    
    public Employee(){}

    public void printing(){
        System.out.println("NAZWA: "+Imie + " " + Nazwisko);
        System.out.println("STAN: " + Kondycja);
        System.out.println("RokUr: " + RokUr);
        System.out.println("Grupa: "+Grupa);
        System.out.println("SALARY: " + Salary + "zl\n\n");
     }


    // Getter/Setter
    public int getWorker_ID() {
        return Worker_ID;
    }
    public void setWorker_ID(int neoID) {
        this.Worker_ID = neoID;
    }
    public String getImie() {
        return this.Imie;
    }
    public void setImie(String newName) {
        this.Imie = newName;
    }
    public String getNazwisko() {
        return this.Nazwisko;
    }
    public void setNazwisko(String newSurname) {
        this.Nazwisko = newSurname;
    }
    public int getRokUr() {
        return this.RokUr;
    }
    public void setRokUr(int newYear) {
        this.RokUr = newYear;
    }
    public int getSalary() {
        return this.Salary;
    }
    public void setSalary(int newMoney) {
        this.Salary = newMoney;
    }
    public int getGrupa() {
        return this.Grupa;
    }
    public void setGrupa(int newGr) {
        this.Grupa = newGr;
    }
    public String getKondycja() {
        return this.Kondycja;
    }
    public void setKondycja(String newCond){
        this.Kondycja=newCond;
    }
/*
 * 
 public void setKondycja(String newCondString){
     switch(newCondString.toLowerCase()){
         case "obecny":
         this.Kondycja=EmployeeCondition.obecny;
         break;
         
         case "chory":
         this.Kondycja=EmployeeCondition.chory;
         break;
         
         case "delegacja":
         this.Kondycja=EmployeeCondition.delegacja;
         break;
         
         case "nieobecny":
         this.Kondycja=EmployeeCondition.nieobecny;
         break;
         
         default:
         throw new IllegalArgumentException("Invalid EmployeeCondition: " + newCondString);
         
        } 
        
    }
    */
    @Override
    public String toString() {
        return "[" +
            " id='" + getWorker_ID() + "'" +
            ", Imie='" + getImie() + "'" +
            ", Nazwisko='" + getNazwisko() + "'" +
            ", RokUr='" + getRokUr() + "'" +
            ", Salary='" + getSalary() + "'" +
            ", Grupa='" + getGrupa() + "'" +
            ", Kondycja='" + getKondycja() + "'" +
            "]";
    }

}