package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }
    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(Long id) {
        return facultyRepository.getReferenceById(id);
    }

    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public String deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
        return id + " was deleted";
    }

    public Collection<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    public List<Faculty> getFacultyAccordingColor(String color) {
        for (Faculty faculty : getAllFaculties()) {
            if (faculty.getColor().equals(color)) {
                return Collections.singletonList(faculty);
            }
        }
        return null;

    }
}
