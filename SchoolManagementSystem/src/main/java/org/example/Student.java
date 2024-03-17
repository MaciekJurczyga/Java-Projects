package org.example;

public class Student {
    private String name;
    private String surname;
    private double finalGrade;


    public Student(String name, String surname, double finalGrade){
        this.name = name;
        this.surname = surname;
        this.finalGrade = finalGrade;
    }
    public String getName(){
        return this.name;
    }
    public String getSurname(){
        return this.surname;
    }
    public double getFinalGrade(){
        return this.finalGrade;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setSurname(String surname){
        this.surname = surname;
    }
    public void setFinalGrade(double finalGrade){
        this.finalGrade = finalGrade;
    }
}
