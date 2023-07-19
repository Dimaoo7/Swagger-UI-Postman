package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.List;


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
        public ResponseEntity<Collection<Faculty>> getAllFaculties(){
            return ResponseEntity.ok(facultyService.getAllFaculties());
        }

        @GetMapping("/filter_by_color")
        public List<Faculty> getFacultyAccordingColor(@RequestParam String color){
            return facultyService.getFacultyAccordingColor(color);
        }
}
