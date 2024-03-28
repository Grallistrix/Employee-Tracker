package com.example;


import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.text.html.parser.Entity;

import com.example.entities.Employee;
import com.example.entities.EmployeeGroup;
import com.example.entities.Rate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class Main {
    public static void main(String[] args) {
        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show.sql","true");
        props.put("hibernate.hbm2ddl.auto","update");
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();
        System.out.println("\tCREATED EMF - EM\n\tROZPOCZYNAM transakcję");
        try{
           
            controll(em);
           
        }catch(Exception e){e.printStackTrace();}
        finally{
            System.out.println("\n\n\tCLOSING THIS STUFF\n");
            em.close();
        }
    }

    static Date getDate(int year, int month, int day){
        return new Date(year-1900,month-1, day);
    }

    public static void controll(EntityManager em) throws IOException, ParseException{
        System.out.println("Wybierz który moduł chcesz uruchomić\n 1 - Groups\n 2 - Rating\n 3 - Workers\n 4 -");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        while(userInput!="x"){
             em.getTransaction().begin();
            switch (userInput) {
            case "1":
            module_Groups(em);
            break;

            case "2":
            module_Ratings(em);
            break;

            case "3":
            module_Employees(em);
            break;
            
            case "x":
            em.getTransaction().commit();
            break;

            default:
            System.out.println("ERR, try again");
            break;
            }
            em.getTransaction().commit();
             System.out.println("Wybierz który moduł chcesz uruchomić\n 1 - Groups\n 2 - Rating\n 3 - Workers\n 4 -");
             userInput = scanner.nextLine();
    }
    scanner.close();

    }
    public static void module_Groups(EntityManager em) throws IOException{
        System.out.println("Co chcesz zrobić?\n 1 - Wyświetl\n 2 - Dodaj\n 3 - Usuń\n 4 -Modyfikuj\n 5 - Export CSV");
        Scanner scanner = new Scanner(System.in);
        int userInput = Integer.parseInt(scanner.nextLine());

        switch (userInput) {
            case 1:
            showGroups(em);
            break;
            
            case 2:
            saveGroup(em);
            break;

            case 3:
            removeGroup(em);
            break;

            case 4:
            changeGroup(em);
            break;

            case 5:
            CSV_Group(em);
            break;

            default:
               throw new IllegalArgumentException("Invalid Answear: " + userInput);
        }
    }

    public static void module_Employees(EntityManager em) throws IOException{
        System.out.println("Co chcesz zrobić?\n 1 - Wyświetl\n 2 - Dodaj\n 3 - Usuń\n 4 -Modyfikuj\n 5 - Export CSV");
        Scanner scanner = new Scanner(System.in);
        int userInput = Integer.parseInt(scanner.nextLine());

        switch (userInput) {
            case 1:
            showEmployees(em);
            break;
            
            case 2:
            saveEmployee(em);
            break;

            case 3:
            removeEmployee(em);
            break;

            case 4:
            changeEmployee(em);
            break;

            case 5:
            CSV_Employee(em);
            break;

            default:
               throw new IllegalArgumentException("Invalid Answear: " + userInput);       
        }
    }

    public static void module_Ratings(EntityManager em) throws ParseException, IOException{
        System.out.println("Co chcesz zrobić?\n 1 - Wyświetl\n 2 - Dodaj\n 3 - Usuń\n 4 -Modyfikuj\n 5 - Export CSV");
        Scanner scanner = new Scanner(System.in);
        int userInput = Integer.parseInt(scanner.nextLine());

        switch (userInput) {
            case 1:
            showRates(em);
            break;
            
            case 2:
            saveRating(em);
            break;

            case 3:
            reomveRate(em);
            break;

            case 4:
            changeRating(em);
            break;

            case 5:
            CSV_Rate(em);

            default:
               throw new IllegalArgumentException("Invalid Answear: " + userInput);
        }
    }

    public static Rate createRate() throws ParseException{
        Scanner scanner = new Scanner(System.in);

        String comment=null;

        System.out.println("Podaj ocenę [0-6]:");
        int value = Integer.parseInt(scanner.nextLine());

        System.out.println("Podaj grupę którą oceniasz [ID]");
        int which_group = Integer.parseInt(scanner.nextLine());

        System.out.println("Podaj Datę [YYYY-MM-DD]:");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        java.util.Date utilDate = dateFormat.parse(scanner.nextLine());
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        System.out.println("Komentarz? Y/N");
        String temp = scanner.nextLine();
        switch (temp.toUpperCase()) {
            case "Y":
            System.out.println("Podaj Komentarz");
            comment=scanner.nextLine();
                break;

            case"N":
            System.out.println("Nie, to nie");
            break;
        
            default:
                break;
        }

        Rate newRate= new Rate();
        newRate.setComment(comment);
        newRate.setDate(sqlDate);
        newRate.setValue(value);
        newRate.setWhich_group(which_group);
        System.out.println("\nOto twoja grupa, przejść dalej? Y/N\n"+newRate);

        temp = scanner.nextLine();
        switch (temp.toUpperCase()) {
            case "Y":
                 return newRate;
            case "N":
                System.out.println("Stwórz ponownie");
                return createRate();
            default:
            throw new IllegalArgumentException("Invalid Answear: " + temp);
        }

       
    }
    public static Employee createEmployee(){
        Employee pracownik = new Employee();
        Scanner scanner = new Scanner(System.in);

        String comment=null;

        System.out.println("Podaj Imie:");
        String imie = scanner.nextLine();

        System.out.println("Podaj nazwisko");
        String nazwisko = scanner.nextLine();

        System.out.println("Podaj Grupę Pracownika [ID]");
        int grupa = Integer.parseInt(scanner.nextLine());

        System.out.println("Podaj Rok Urodzenia");
        int rokUr = Integer.parseInt(scanner.nextLine());

        System.out.println("Pensja [L.Całk]\n");
        int salary = Integer.parseInt(scanner.nextLine());    

        System.out.println("Podaj kondycję\n");
        String condition =scanner.nextLine();   

        pracownik.setImie(imie);
        pracownik.setNazwisko(nazwisko);
        pracownik.setRokUr(rokUr);
        pracownik.setSalary(salary);
        pracownik.setKondycja(condition);
        pracownik.setGrupa(grupa);

        System.out.println("Oto twój pracownik, czy chcesz iśc dalej Y/N\n?"+pracownik);
        String temp = scanner.nextLine();
        switch (temp.toUpperCase()) {
            case "Y":
                 return pracownik;
            case "N":
                System.out.println("Stwórz ponownie");
                return createEmployee();
            default:
            throw new IllegalArgumentException("Invalid Answear: " + temp);
        }
    }
    public static EmployeeGroup createGroup(){
        EmployeeGroup grupa = new EmployeeGroup();
        Scanner scanner = new Scanner(System.in);

        String comment=null;

        System.out.println("Podaj Nazwę:");
        String nazwa = scanner.nextLine();

        System.out.println("Podaj Capacity");
         int capacity = Integer.parseInt(scanner.nextLine());



       grupa.setCapacity(capacity);
       grupa.setGroupName(nazwa);
        
        System.out.println("Oto twój pracownik, czy chcesz iśc dalej Y/N\n?"+grupa);
        String temp = scanner.nextLine();
        switch (temp.toUpperCase()) {
            case "Y":
                 return grupa;
            case "N":
                System.out.println("Stwórz ponownie");
                return createGroup();
            default:
            throw new IllegalArgumentException("Invalid Answear: " + temp);
        }
    }

    public static void showRates(EntityManager em){
            TypedQuery<Rate> queries = em.createQuery("SELECT r FROM Rate r", Rate.class);
            List<Rate> rating = queries.getResultList();
            for(Rate x : rating)
                System.out.println(x);
    }
    public static void showGroups(EntityManager em){
            TypedQuery<EmployeeGroup> queries = em.createQuery("SELECT g FROM EmployeeGroup g", EmployeeGroup.class);
            List<EmployeeGroup> groups = queries.getResultList();
            for(EmployeeGroup x : groups)
                System.out.println(x);
    }
    public static void showEmployees(EntityManager em){
            TypedQuery<Employee> queries = em.createQuery("SELECT g FROM Employee g", Employee.class);
            List<Employee> groups = queries.getResultList();
            for(Employee x : groups)
                System.out.println(x);
    }

    public static void changeEmployee(EntityManager em){
        showEmployees(em);
        System.out.println("Którego chcesz zmienić?");
        Scanner scanner = new Scanner(System.in);
        int whichToChange = Integer.parseInt(scanner.nextLine());
        Employee changedEmployee = em.find(Employee.class, whichToChange);
        if(changedEmployee == null){
            System.out.println("ERR - nie znaleziono");
            return;
        }
        Employee tempEmployee = createEmployee();
        changedEmployee.setImie(tempEmployee.getImie());
        changedEmployee.setNazwisko(tempEmployee.getNazwisko());
        changedEmployee.setGrupa(tempEmployee.getGrupa());
        changedEmployee.setKondycja(tempEmployee.getKondycja());
        changedEmployee.setRokUr(tempEmployee.getRokUr());
        changedEmployee.setSalary(tempEmployee.getSalary());

        System.out.println("Czy jesteś pewien zmiany? Y/N");
        String temp = scanner.nextLine();
        switch (temp.toUpperCase()) {
            case "Y":
                break;
            case "N":
                System.out.println("Przywracanie poprzednich ustawień:");
                em.refresh(changedEmployee);
                System.out.println(changedEmployee);
                break;
            default:
            throw new IllegalArgumentException("Invalid Answear: " + temp);
        }
    }
    public static void changeGroup(EntityManager em){
        showGroups(em);
        System.out.println("Którą chcesz zmienić?");
        Scanner scanner = new Scanner(System.in);
        int whichToChange = Integer.parseInt(scanner.nextLine());
        EmployeeGroup changedGroup = em.find(EmployeeGroup.class, whichToChange);
        if(changedGroup == null){
            System.out.println("ERR - nie znaleziono");
            return;
        }
        EmployeeGroup tempGroup = createGroup();

        changedGroup.setCapacity(tempGroup.getCapacity());
        changedGroup.setGroupName(tempGroup.getGroupName());

        System.out.println("Czy jesteś pewien zmiany? Y/N");
        String temp = scanner.nextLine();
        switch (temp.toUpperCase()) {
            case "Y":
                break;
            case "N":
                System.out.println("Przywracanie poprzednich ustawień:");
                em.refresh(changedGroup);
                System.out.println(changedGroup);
                break;
            default:
            throw new IllegalArgumentException("Invalid Answear: " + temp);
        }
    }
    public static void changeRating(EntityManager em) throws ParseException{
        showRates(em);
        System.out.println("Którą chcesz zmienić?");
        Scanner scanner = new Scanner(System.in);
        int whichToChange = Integer.parseInt(scanner.nextLine());
        Rate changedRate = em.find(Rate.class, whichToChange);
        if(changedRate == null){
            System.out.println("ERR - nie znaleziono");
            return;
        }
        Rate tempGroup = createRate();
        changedRate.setComment(tempGroup.getComment());
        changedRate.setDate(tempGroup.getDate());
        changedRate.setValue(tempGroup.getValue());
        changedRate.setWhich_group(tempGroup.getWhich_group());
      

        System.out.println("Czy jesteś pewien zmiany? Y/N");
        String temp = scanner.nextLine();
        switch (temp.toUpperCase()) {
            case "Y":
                break;
            case "N":
                System.out.println("Przywracanie poprzednich ustawień:");
                em.refresh(changedRate);
                System.out.println(changedRate);
                break;
            default:
            throw new IllegalArgumentException("Invalid Answear: " + temp);
        }
    }

    public static void saveRating(EntityManager em)throws ParseException{    
        Rate rating = createRate();
        em.persist(rating);
    }
    public static void saveGroup(EntityManager em){
        EmployeeGroup grupa = createGroup();
        em.persist(grupa);
    }
    public static void saveEmployee(EntityManager em){
        Employee pracownik = createEmployee();
        em.persist(pracownik);
    }

    public static void reomveRate(EntityManager em){
        showRates(em);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ktore chcesz usunac?");
        int whichToRemove = Integer.parseInt(scanner.nextLine());
        em.remove(em.find(Rate.class,whichToRemove));
    }
    public static void removeGroup(EntityManager em){
        showGroups(em);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ktore chcesz usunac?");
        int whichToRemove = Integer.parseInt(scanner.nextLine());
        em.remove(em.find(EmployeeGroup.class,whichToRemove));
    }
    public static void removeEmployee(EntityManager em){
        showEmployees(em);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ktore chcesz usunac?");
        int whichToRemove = Integer.parseInt(scanner.nextLine());
        em.remove(em.find(Employee.class,whichToRemove));
    }

    public static void getGroupRating(EntityManager em, int groupID){
            TypedQuery<Employee> queries = em.createQuery("SELECT g FROM Employee g", Employee.class);
            List<Employee> groups = queries.getResultList();
            for(Employee x : groups)
                System.out.println(x);
    }

    public static void CSV_Group(EntityManager em) throws IOException{
            String queryString = "SELECT e.Group_ID, e.GrName, e.Capacity FROM EmployeeGroup e";
            Query query = em.createQuery(queryString);

            List<Object[]> results = query.getResultList();

            try (FileWriter writer = new FileWriter("output_group.csv")) {
                for (Object[] row : results) {
                    for (int i = 0; i < row.length; i++) {
                        writer.append(String.valueOf(row[i]));
                        if (i < row.length - 1) {
                            writer.append(",");
                        }
                    }
                    writer.append("\n");
                }
            }
    }   
    public static void CSV_Employee(EntityManager em) throws IOException{
            String queryString = "SELECT e.Worker_ID, e.Imie, e.Nazwisko, e.RokUr, e.Salary, e.Grupa, e.Kondycja FROM Employee e";
            Query query = em.createQuery(queryString);

            
            List<Object[]> results = query.getResultList();

            try (FileWriter writer = new FileWriter("output_employee.csv")) {
                for (Object[] row : results) {
                    for (int i = 0; i < row.length; i++) {
                        writer.append(String.valueOf(row[i]));
                        if (i < row.length - 1) {
                            writer.append(",");
                        }
                    }
                    writer.append("\n");
                }
            }
    }   
    public static void CSV_Rate(EntityManager em) throws IOException{
            String queryString = "SELECT e.Rate_ID, e.value, e.which_group, e.date, e.comment FROM Rate e";
            Query query = em.createQuery(queryString);

         
            List<Object[]> results = query.getResultList();

            try (FileWriter writer = new FileWriter("output_rating.csv")) {
                for (Object[] row : results) {
                    for (int i = 0; i < row.length; i++) {
                        writer.append(String.valueOf(row[i]));
                        if (i < row.length - 1) {
                            writer.append(",");
                        }
                    }
                    writer.append("\n");
                }
            }
    }   

}



