package org.example;


import java.util.List;

public class Main {
    public static void main(String[] args) {
        School school = new School();
        Student maciek = new Student("maciek", "jurczyga", 4.2);
        Student seba = new Student("seba", "wawrzyniak", 3);
        Teacher lech = new Teacher("Lech", "Adamus", "Mathematics");
        school.addStudent(maciek);
        school.addStudent(seba);
        school.addTeacher(lech);
        List<Student> students = school.getStudentsList();
        List<Teacher> teachers = school.getTeacherList();
        System.out.println("Students in school: ");
        for(Student student:students){
            System.out.println("Name: " + student.getName());
            System.out.println("Surname: " + student.getSurname());
            System.out.println("Final rade: " + student.getFinalGrade());
            System.out.println("----------------------------------");
        }
        System.out.println("teachers in school");
        for(Teacher teacher:teachers){
            System.out.println("Name: " + teacher.getName());
            System.out.println("Surname: " + teacher.getSurname());
            System.out.println("Subject: " + teacher.getSubject());
            System.out.println("-------------------");
        }


        }
    }
