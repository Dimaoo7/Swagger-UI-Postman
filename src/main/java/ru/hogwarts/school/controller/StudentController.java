package ru.hogwarts.school.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping //CREATE  http://localhost:8080/student
    public Student createStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }

    @GetMapping("{id}") //READ  http://localhost:8080/student/1
    public ResponseEntity<Student> findStudent(@RequestBody @PathVariable Long id){
        Student student = studentService.findStudent(id);
        if(student == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping("{id}") //UPDATE  http://localhost:8080/student/1
    public ResponseEntity<Student> editStudent(@RequestBody Student student){
        Student edittingStudent = studentService.editStudent(student);
        if(edittingStudent == null){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("{id}") //DELETE  http://localhost:8080/student/1
    public ResponseEntity<Student> deleteStudent(@RequestBody @PathVariable Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping //READ  http://localhost:8080/student
    public ResponseEntity<Collection<Student>> findAllStudents(@RequestParam int age, @RequestParam String name){
        if (age != 0){
            return ResponseEntity.ok(studentService.findStudentByAge(age));
        }
        if (name != null && !name.isEmpty()){
            return ResponseEntity.ok(Collections.singleton(studentService.findStudentByNameContains(name)));
        }
        return ResponseEntity.ok(studentService.getAllStudents());

    }
}
