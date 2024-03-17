package org.example;

import java.util.ArrayList;
import java.util.List;

public class School {
    private List<Student> studentsList;
    private List<Teacher> teacherList;
    public School(){
        studentsList = new ArrayList<>();
        teacherList = new ArrayList<>();
    }
    public void addStudent(Student student){
        studentsList.add(student);
    }
    public void addTeacher(Teacher teacher){
        teacherList.add(teacher);
    }
    public void removeStudent(Student student){
        studentsList.remove(student);
    }
    public void removeTeacher(Teacher teacher){
        teacherList.remove(teacher);
    }
    public List<Student> getStudentsList(){
        return studentsList;
    }
    public List<Teacher> getTeacherList(){
        return teacherList;
    }
}
