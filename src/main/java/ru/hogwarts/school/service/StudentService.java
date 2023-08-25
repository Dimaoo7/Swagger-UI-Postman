package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FiveLastStudents;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    Logger logger = LoggerFactory.getLogger(StudentService.class);


    public StudentService(StudentRepository studentRepository) {
        logger.debug("Вызван конструктор StudentService");
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        logger.debug("Вызван метод createStudent");
        return studentRepository.save(student);
    }

    public Optional<Student> findStudent(Long id) {
        logger.debug("Вызван метод findStudent");
        return studentRepository.findById(id);
    }

    public Student editStudent(Student student) {
        logger.debug("Вызван метод editStudent");
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        logger.debug("Вызван метод deleteStudent");
        studentRepository.deleteById(id);
    }

    public List<Student> findStudentByAge(Integer age) {
        logger.debug("Вызван метод findStudentByAge");
        return studentRepository.findByAge(age);
    }

    public List<Student> findAllByAgeBetween(int min, int max) {
        logger.debug("Вызван метод findAllByAgeBetween");
        return studentRepository.findAllByAgeBetween(min, max);
    }

    public List<Student> findAllStudentsByFaculty(Long id) {
        logger.debug("Вызван метод findAllStudentsByFaculty");
        return studentRepository.findAllByFaculty_id(id);
    }


    public Long getLastID() {
        logger.debug("Вызван метод getLastID");
        return studentRepository.getLastID();
    }

    public List<Integer> getStudents() {
        logger.debug("Вызван метод getStudents");
        return studentRepository.getStudents();
    }

    public Double getAverageAge() {
        logger.debug("Вызван метод getAverageAge");
        return studentRepository.findAll().stream()
                .map(Student::getAge)
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0);
    }

    public List<FiveLastStudents> getLastStudents() {
        logger.debug("Вызван метод getLastStudents");
        return studentRepository.getLastStudents();
    }

    public Stream<Student> findAllStartsWithA() {
        logger.debug("Вызван метод findAllStartsWithA");
        return studentRepository.findAll().stream()
                .filter(student -> student.getName().startsWith("A"));
    }

    public int getInteger() {
        logger.debug("Вызван метод returnInteger");
      // return  Stream.iterate(1, a -> a +1) .limit(1_000_000) .reduce(0, (a, b) -> a + b ); //1784293664

        return Stream.iterate(1, a -> a +1)
                .limit(1_000_000)
                .parallel()
                .reduce(0, Integer::sum); //1784293664
    }

    //Многопоточный вывод имен
    public String  getStudentNamesThreads() {
        logger.debug("Вызван метод getNames");
        var findAll = studentRepository.findAll();
        System.out.println("Основной поток: ");
        final int[] num = {0};
        for (; num[0] < 2; num[0]++) {
            System.out.println( findAll.get(num[0]).getName() + " ");
        }
        new Thread(() -> {
            System.out.println("Параллельный поток");
            for (; num[0] < 4; num[0]++) {
                System.out.println( findAll.get(num[0]).getName() + " ");
            }
        })
                .start();
        new Thread(() -> {
            System.out.println("Второй параллельный поток");
            for (; num[0] < 6; num[0]++) {
                System.out.println( findAll.get(num[0]).getName() + " ");
            }
        })
                .start();
        return "Многопоточный вывод имен";
        }

    //Синхронизация потока
    public String getStudentSynchronizedTreads() {
        logger.debug("Вызван метод getStudentSynchronizedTreads");

        System.out.println("Имя 0 студента: " + studentRepository.findAll().get(0).getName());
        System.out.println("Имя 1 студента: " + studentRepository.findAll().get(1).getName());

        Thread thread1 = new Thread(() -> {
            synchronized (StudentService.class){
                System.out.println("Имя 2 студента: " + studentRepository.findAll().get(2).getName());}
            synchronized (StudentService.class){
                System.out.println("Имя 3 студента: " + studentRepository.findAll().get(3).getName());}
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            synchronized (StudentService.class){
                System.out.println("Имя 4 студента: " + studentRepository.findAll().get(4).getName());}
            synchronized (StudentService.class){
                System.out.println("Имя 5 студента: " + studentRepository.findAll().get(5).getName());}
        });
        thread2.start();
        return "Синхронизация потока";

    }
}

