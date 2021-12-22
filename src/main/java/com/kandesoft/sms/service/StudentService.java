package com.kandesoft.sms.service;

import com.kandesoft.sms.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> getStudents();
    Student getStudent(Long id);
    Student saveStudent(Student student);
    Student updateStudent(Student student);
    void deleteStudent(Long id);
}
