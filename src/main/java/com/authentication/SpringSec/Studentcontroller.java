package com.authentication.SpringSec;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;
@RestController
public class Studentcontroller {
    private List<Student> students = new ArrayList<>(List.of(new Student(1,"Vignesh",90),new Student(2,"srujan",85)));
    @GetMapping("/students")
    public List<Student> getstudents(){
        return students;
    }
    @PostMapping("/students")
    public Student addstudent(@RequestBody Student student){
        students.add(student);
        return student;
    }
    @GetMapping("/csrf-token")
    public CsrfToken getcsrftoken(HttpServletRequest request){ 
        return (CsrfToken) request.getAttribute("_csrf");
    }
}
