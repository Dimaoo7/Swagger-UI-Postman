package ru.hogwarts.school;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class FacultyServiceTest {

    @Mock
    private FacultyRepository facultyRepository;

    @InjectMocks
    private FacultyService facultyService;
    Faculty expectedFaculty;

    @BeforeEach
    public void BeforeEach(){
        facultyService = new FacultyService(facultyRepository);
        expectedFaculty = new Faculty(1L,"Boris", "red");
        facultyService.createFaculty(expectedFaculty);
    }
    @Test
    public void createFacultyTest() {
        Faculty expected = new Faculty(1L, "Name", "Color");
        Mockito.when(facultyRepository.save(expected)).thenReturn(expected);
        Faculty actual = facultyService.createFaculty(expected);
        assertEquals(expected.getName(), actual.getName());
    }
    @Test
    public void findFacultyTest(){
        Mockito.when(facultyRepository.getReferenceById(1L)).thenReturn(expectedFaculty);

        assertEquals(expectedFaculty.getName(), facultyService.findFaculty(1L).getName());
        assertEquals(expectedFaculty.getColor(), facultyService.findFaculty(1L).getColor());
    }

    @Test
    public void editFacultyTest(){
        Faculty expected = new Faculty(1L, "Name", "Color");
        Mockito.when(facultyRepository.save(expected)).thenReturn(expected);
        Faculty actual = facultyService.editFaculty(expected);
        assertEquals(expected.getName(), actual.getName());
    }
    @Test
    public void deleteStudentTest(){
        facultyService.deleteFaculty(1L);
        assertTrue(facultyService.getAllFaculties().isEmpty());
    }

    @Test
    public void getFacultiesAccordingAgeTest(){
        List<Faculty> expected = new ArrayList<>();
        Faculty faculty1 = new Faculty(1L, "Name", "Color");
        Faculty faculty2 = new Faculty(2L, "Name2", "Color2");
        Faculty faculty3 = new Faculty(3L, "Name3", "Color3");
        expected.add(faculty1);
        expected.add(faculty2);
        expected.add(faculty3);
        Mockito.when(facultyRepository.findAll()).thenReturn(expected);
        Collection<Faculty> actual = facultyService.getAllFaculties();
        assertEquals(expected, actual);
    }
}

