package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;


@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);

    }

    public Student findStudent(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        return studentRepository.getReferenceById(id);
    }

    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> findStudentByAge(int age) {
        return studentRepository.findStudentByAge(age);
    }

    public Student findStudentByNameContains(String name){
        return studentRepository.findStudentByNameContains(name);
    }

    public List<Student> findStudentByAgeBetween(int min, int max){
        return studentRepository.findByAgeBetween(min, max);
    }

    public List<Student> findStudentByFaculty(Faculty faculty){
        return studentRepository.findStudentByFaculty(faculty);
    }
}
