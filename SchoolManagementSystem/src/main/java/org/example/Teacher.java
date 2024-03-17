package org.example;

public class Teacher {
    private String name;
    private String surname;
    private String subject;
    public Teacher(String name, String surname, String subject){
        this.name = name;
        this.surname = surname;
        this.subject = subject;
    }
    public String getName(){
        return this.name;
    }
    public String getSurname(){
        return this.surname;
    }
    public String getSubject(){
        return this.subject;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setSurname(String surname){
        this.surname = surname;
    }
    public void setSubject(String subject){
        this.subject = subject;
    }
}
