package ru.hogwarts.school.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;

@RestController
    @RequestMapping("student")
    public class StudentController {
        private final StudentService studentService;

        public StudentController(StudentService studentService) {
            this.studentService = studentService;
        }
        @PostMapping
        public Student createStudent(@RequestBody Student student){
            return studentService.createStudent(student);
        }
        @GetMapping("{id}")
        public ResponseEntity<Student> findStudent(@RequestBody @PathVariable Long id){
            Student student = studentService.findStudent(id);
            if(student == null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(student);
        }
        @PutMapping("{id}")
        public ResponseEntity<Student> editStudent(@RequestBody Student student){
            Student edittingStudent = studentService.editStudent(student);
            if(edittingStudent == null){
                ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(student);
        }
        @DeleteMapping("{id}")
        public Student deleteStudent(@RequestBody @PathVariable Long id){
            return studentService.deleteStudent(id);
        }
        @GetMapping
        public ResponseEntity<Collection<Student>> getAllStudents(){
            return ResponseEntity.ok(studentService.getAllStudents());
        }
        @GetMapping("/filter_by_age/{age}")
        public List<Student> getStudentsAccordingAge(@PathVariable int age){
            return studentService.getStudentsAccordingAge(age);
        }
}
