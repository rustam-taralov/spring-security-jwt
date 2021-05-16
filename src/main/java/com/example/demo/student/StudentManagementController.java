package com.example.demo.student;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.JstlUtils;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value="/management/api")
public class StudentManagementController {

    List<Student> students= Arrays.asList(
            new Student(1, "Ali"),
            new Student(2,"Omar"),
            new Student(3,"Osman")
    );

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMINTRAINEE')")
    public List<Student> getAllStudent(){
        return students;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public void setStudent(@RequestBody Student student){
        System.out.println(student.toString());
    }

    @PutMapping(path = "{id}")
    @PreAuthorize("hasAuthority('student:write')")
    public void updateStudent(@PathVariable("id") Integer id,@RequestBody Student student){
        System.out.println(String.format("%s %s",id,student));
    }

    @DeleteMapping(value = "{id}")
    @PreAuthorize("hasAuthority('student:write')")
    public void deleteStudent(@PathVariable("id") Integer id){
        System.out.println(id);
    }


}
