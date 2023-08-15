package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FiveLastStudents;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;


    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Optional<Student> findStudent(Long id) {
        return studentRepository.findById(id);
    }

    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> findStudentByAge(Integer age) {
        return studentRepository.findByAge(age);
    }

    public List<Student> findAllByAgeBetween(int min, int max) {
        return studentRepository.findAllByAgeBetween(min, max);
    }

    public List<Student> findAllStudentsByFaculty(Long id) {
        return studentRepository.findAllByFaculty_id(id);
    }


    public Long getLastID() {
        return studentRepository.getLastID();
    }

    public List<Integer> getStudents() {
        return studentRepository.getStudents();
    }

    public List<Double> getAverageAge() {
        return studentRepository.getAverageAge();
    }

    public List<FiveLastStudents> getLastStudents() {
        return studentRepository.getLastStudents();
    }
}
