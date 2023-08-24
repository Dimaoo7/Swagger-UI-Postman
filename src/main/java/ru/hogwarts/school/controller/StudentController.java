package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.exceptions.NotFoundException;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FiveLastStudents;
import ru.hogwarts.school.service.StudentService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;


    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping
    public ResponseEntity<Student> createFaculty(@RequestBody Student student) {
        if (student != null) {
            return ResponseEntity.ok(studentService.createStudent(student));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Student>> findFaculty(@PathVariable Long id) {
        try {
            if (id != null) {
                return ResponseEntity.ok(studentService.findStudent(id));
            }
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping
    public ResponseEntity<Student> editFaculty(@RequestBody Student student) {
        try {
            if (student != null) {
                return ResponseEntity.ok(studentService.editStudent(student));
            }
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }


    @DeleteMapping("{id}")
    public void deleteFaculty(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    @GetMapping("/findAllByAge/")
    public ResponseEntity<List<Student>> findStudentByAge(Integer age) {
        if (age != null) {
            return ResponseEntity.ok(studentService.findStudentByAge(age));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping
    public ResponseEntity<List<Student>> findAllByAgeBetween(Integer min, Integer max) {
        if (min != null || max != null) {
            return ResponseEntity.ok(studentService.findAllByAgeBetween(min, max));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/StudentByFaculty/")
    public ResponseEntity<List<Student>> findByFacultyId(Long id) {
        if (id != null) {
            return ResponseEntity.ok(studentService.findAllStudentsByFaculty(id));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/findLastID")
    public Long findLastID() {
        return studentService.getLastID();
    }

    @GetMapping("/getLastStudents")
    public List<FiveLastStudents> getLastStudents() {
        return studentService.getLastStudents();
    }

    @GetMapping("/getStudents")
    public List<Integer> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping("/getAverageAge")
    public Double getAverageAge() {
        return studentService.getAverageAge();
    }

    @GetMapping("/findAllStartsWithA")
    public Stream<Student> findAllStartsWithA() {
        return studentService.findAllStartsWithA();
    }

    @GetMapping("/returnInteger")
    public Integer returnInteger() {
        return studentService.getInteger();
    }

    @GetMapping("/getStudentNamesTreads")
    public void getStudentNamesTreads() {
        studentService.getStudentNamesThreads();
    }
}
