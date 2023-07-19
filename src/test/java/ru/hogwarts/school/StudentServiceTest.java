package ru.hogwarts.school;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    private StudentService studentService;
    @Mock
    private StudentRepository studentRepository;
    Student expectedStudent;

    @BeforeEach
    public void BeforeEach(){
        studentService = new StudentService(studentRepository);
        expectedStudent = new Student(1L,"Boris", 35);
        studentService.createStudent(expectedStudent);
    }

    @Test
    public void createStudentTest() {
        Mockito.when(studentRepository.save(expectedStudent)).thenReturn(expectedStudent);

        assertEquals(expectedStudent.getName(), studentService.createStudent(expectedStudent).getName());
        assertEquals(expectedStudent.getAge(), studentService.createStudent(expectedStudent).getAge());
    }

    @Test
    public void findStudentTest(){
            Mockito.when(studentRepository.getReferenceById(1L)).thenReturn(expectedStudent);

            assertEquals(expectedStudent.getName(), studentService.findStudent(1L).getName());
            assertEquals(expectedStudent.getAge(), studentService.findStudent(1L).getAge());
        }

    @Test
    public void editStudentTest(){
        Mockito.when(studentRepository.getReferenceById(1L)).thenReturn(expectedStudent);

        assertEquals(expectedStudent.getName(), studentService.findStudent(1L).getName());
        assertEquals(expectedStudent.getAge(), studentService.findStudent(1L).getAge());

        Student newStudent = new Student(1L,"Caesar", 33);
        studentService.editStudent(newStudent);

        Mockito.when(studentRepository.getReferenceById(1L)).thenReturn(newStudent);

        assertEquals(newStudent.getName(), studentService.findStudent(1L).getName());
        assertEquals(newStudent.getAge(), studentService.findStudent(1L).getAge());
    }

    @Test
    public void deleteStudentTest(){
        studentService.deleteStudent(1L);
        assertTrue(studentService.getAllStudents().isEmpty());
        verify(studentRepository, times(1)).deleteById(1L);
    }

    @Test
    public void getAllStudentsTest(){
        List<Student> expectedList = new ArrayList<>(List.of(
                new Student(1L, "Boris", 35)
        ));
        Mockito.when(studentRepository.findAll()).thenReturn(expectedList);
        assertEquals(expectedList, studentService.getAllStudents());
    }

    @Test
    public void getStudentsAccordingAgeTest(){

        studentService.createStudent(new Student(2L, "Rik", 25));
        studentService.createStudent(new Student(3L, "Bik", 15));
        studentService.createStudent(new Student(4L, "Mik", 25));

        List<Student>expextedList1 = new ArrayList<>(List.of(
                new Student(3L, "Bik", 15)
        ));

        Mockito.when(studentRepository.findAll()).thenReturn(expextedList1);
        assertEquals(expextedList1, studentService.getStudentsAccordingAge(15));

        List<Student>expextedList2 = new ArrayList<>(List.of(
                new Student(2L, "Rik", 25),
                new Student(4L, "Mik", 25)
        ));

        Mockito.when(studentRepository.findAll()).thenReturn(expextedList2);
        assertEquals(expextedList2, studentService.getStudentsAccordingAge(25));
    }
}