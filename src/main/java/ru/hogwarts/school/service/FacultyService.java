package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;
    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public FacultyService(FacultyRepository facultyRepository) {
        logger.debug("Вызван конструктор FacultyService");
        this.facultyRepository = facultyRepository;
    }


    public Faculty createFaculty(Faculty faculty) {
        logger.debug("Вызван метод createFaculty");
        return facultyRepository.save(faculty);
    }

    public Optional<Faculty> findFaculty(Long id) {
        logger.debug("Вызван метод findFaculty");
        return facultyRepository.findById(id);
    }

    public Faculty editFaculty(Faculty faculty) {
        logger.debug("Вызван метод editFaculty");
        return facultyRepository.save(faculty);

    }

    public void deleteFaculty(Long id) {
        logger.debug("Вызван метод deleteFaculty");
        facultyRepository.deleteById(id);
    }


    public List<Faculty> findAllByColor(String color) {
        logger.debug("Вызван метод findAllByColor");
        return facultyRepository.findAllByColor(color);
    }

    public List<Faculty> findFacultyByNameOrColor(String name, String color) {
        logger.debug("Вызван метод findFacultyByNameOrColor");
        return facultyRepository.findAllByNameOrColorIgnoreCase(name, color);

    }

    public Faculty findFacultyByStudent(Long id) {
        logger.debug("Вызван метод findFacultyByStudent");
        return facultyRepository.findByStudent_id(id);
    }

    public Long findLastID() {
        logger.debug("Вызван метод findLastID");
        return facultyRepository.findLastID();
    }

    public Optional<Faculty> longestFacultyName() {
        logger.debug("Вызван метод longestFaculty");
        return facultyRepository.findAll().stream()
                .max(Comparator.comparingInt(f -> f.getName().length()));
    }
}
