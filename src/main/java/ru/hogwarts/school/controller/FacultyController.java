package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.Collections;


@RestController
@RequestMapping("/faculty")
public class FacultyController {

        private final FacultyService facultyService;

        public FacultyController(FacultyService facultyService) {
            this.facultyService = facultyService;
        }
        @PostMapping
        public Faculty createStudent(@RequestBody Faculty faculty){
            return facultyService.createFaculty(faculty);
        }
        @GetMapping("{id}")
        public ResponseEntity<Faculty> findFaculty(@RequestBody @PathVariable Long id){
            Faculty faculty = facultyService.findFaculty(id);
            if(faculty == null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(faculty);
        }
        @PutMapping("{id}")
        public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty){
            Faculty editiedFaculty = facultyService.editFaculty(faculty);
            if(editiedFaculty == null){
                ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(faculty);
        }
        @DeleteMapping("{id}")
        public String deleteFaculty(@RequestBody @PathVariable Long id){
            return facultyService.deleteFaculty(id);
        }
        @GetMapping
        public ResponseEntity<Collection<Faculty>> findAllFaculties(@RequestParam(required = false) String color,
                                                                    @RequestParam(required = false) String name){
            if (color != null && !color.isEmpty()){
                return ResponseEntity.ok(facultyService.findFacultyByColor(color));
            }
            if (name != null && !name.isEmpty()){
                return ResponseEntity.ok(Collections.singleton(facultyService.findFacultyByNameContains(name)));
            }
            return ResponseEntity.ok(facultyService.getAllFaculties());
        }


        @GetMapping("{id}/students")
        public ResponseEntity<Faculty> findFacultyByStudentIgnoreCase(@RequestBody Student student){
            return ResponseEntity.ok(facultyService.findFacultyByStudentIgnoreCase(student));
        }
}
