package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

//Resources for API (Responsible for APIs)
@RestController  //For HTTP requests
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired //StudentService should be autowired for us (Injected into the constructor)
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents (){
        return studentService.getStudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student){ //Take request and map it into student
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}") //path = http://localhost:8080/api/v1/student > path+= {studentId}
    public void deleteStudent(@PathVariable("studentId") Long id){
        studentService.deleteStudent(id);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false)  String email){
        studentService.updateStudent(id, name, email);
    }
}
