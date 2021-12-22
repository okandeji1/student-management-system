package com.kandesoft.sms.controller;

import com.kandesoft.sms.entity.Student;
import com.kandesoft.sms.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        super();
        this.studentService = studentService;
    }

    // Handler to return list student view
    @GetMapping("/students")
    public String listStudent(Model model){
        model.addAttribute("students", studentService.getStudents());
        return "students";
    }

    @GetMapping("/students/new")
    public String createStudentForm(Model model){
        // Create student object to hold student data
        Student student = new Student();
        model.addAttribute("student", student);
        return "create_student";
    }

    // Add student handler
    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student") Student student){
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/students/edit/{id}")
    public String EditStudent(@PathVariable Long id, Model model){
        model.addAttribute("student", studentService.getStudent(id));
        return "edit_student";
    }

    // Update student handler
    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute("student") Student student){
        //Get student by Id
        Student existingStudent = studentService.getStudent(id);
        // Set student object
        existingStudent.setId(id);
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        // Save updated object
        studentService.updateStudent(existingStudent);
        return "redirect:/students";
    }

    // Delete handler
    @GetMapping("/students/{id}")
    public String deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}
