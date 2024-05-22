package com.example.hibernation_demo.Controller;

import com.example.hibernation_demo.controller.TeacherController;
import com.example.hibernation_demo.model.Student;
import com.example.hibernation_demo.model.Teacher;
import com.example.hibernation_demo.repo.TeacherRepository;
import com.example.hibernation_demo.requestbody.TeacherReqBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.ArrayList;
import java.util.List;

import static com.example.hibernation_demo.model.Teacher.TeacherRole.TEACHER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.client.ExpectedCount.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
public class TeacherControllerTest {

    @Mock
    private TeacherRepository teacherRepository;
    private TeacherController teacherController;

    @BeforeEach
    public void setup() {
        teacherController = new TeacherController(teacherRepository);
    }
    private TeacherRepository verify(TeacherRepository teacherRepository) {
        return teacherRepository;
    }

    @Test
    public void should_add_teacher_and_return_success_code () throws Exception {

        //Given
        TeacherReqBody teacherReqBody = new TeacherReqBody();
        teacherReqBody.setName("Ayushi");
        teacherReqBody.setEmail("ayushi@gmail.com");
        teacherReqBody.setAddress("12345");
        teacherReqBody.setPrincipal(true);
        teacherReqBody.setSubject("Chemistry");
        teacherReqBody.setRole(TEACHER);

        Teacher teacherRes = new Teacher();
        teacherRes.setName("Ayushi");
        teacherRes.setEmail("ayushi@gmail.com");
        teacherRes.setAddress("12345");
        teacherRes.setPrincipal(1);
        teacherRes.setSubject("Chemistry");
        teacherRes.setRole(TEACHER);

        //When
        Mockito.when(teacherRepository.save(Mockito.any(Teacher.class))).thenReturn(teacherRes);

        ResponseEntity<?> result = teacherController.addTeacher(teacherReqBody);
        //Then
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        Teacher teacherResultBody;
        teacherResultBody = (Teacher) result.getBody();
        System.out.println(teacherResultBody.toString());
        System.out.println(teacherResultBody.getName());
        System.out.println(teacherResultBody.getAddress());
        assertEquals("Ayushi", teacherResultBody.getName());

        Mockito.verify(teacherRepository).save(Mockito.any(Teacher.class));

    }

    @Test
    public void should_not_add_teacher_and_return_failure_code() throws Exception {
        //Given
        TeacherReqBody teacherReqBody = new TeacherReqBody();
        teacherReqBody.setName("Ayushi");
        teacherReqBody.setEmail("ayushi@gmail.com");
        teacherReqBody.setAddress("12345");
        teacherReqBody.setPrincipal(true);
        teacherReqBody.setSubject("Chemistry");
        teacherReqBody.setRole(TEACHER);

        //When
        Mockito.when(teacherRepository.save(Mockito.any(Teacher.class))).thenThrow(new RuntimeException());

        ResponseEntity<?> result = teacherController.addTeacher(teacherReqBody);
        //Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.toString(), result.getStatusCode().toString());
        assertEquals("Failed to add the Teacher", result.getBody());

        Mockito.verify(teacherRepository).save(Mockito.any(Teacher.class));

    }

    @Test
    public void should_find_all_teachers() throws Exception{

        //Given
        ArrayList<Teacher> listOfTeachers = new ArrayList<Teacher>();
        Teacher teacherRes = new Teacher();
        teacherRes.setName("Ayushi");
        teacherRes.setEmail("ayushi@gmail.com");
        teacherRes.setAddress("12345");
        teacherRes.setPrincipal(1);
        teacherRes.setSubject("Chemistry");
        teacherRes.setRole(TEACHER);
        listOfTeachers.add(teacherRes);

        //When
        Mockito.when(teacherRepository.findAll()).thenReturn(listOfTeachers);

        ResponseEntity<?> result = teacherController.getAllTeachers();
        List<Teacher> teacherResultList;
        System.out.println(result);
        teacherResultList = (List<Teacher>) result.getBody();

        //Then
        assertEquals(1,teacherResultList.size());

    }

    @Test
    public void should_delete_all_teachers_and_return_no_content() {
        //When
        doNothing().when(teacherRepository).deleteAll();
        ResponseEntity<?> response = teacherController.deleteAllTeachers();
        System.out.println(response);

        verify(teacherRepository).deleteAll();
        //Then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }



    @Test
    void should_not_delete_all_teachers_and_return_internal_server_error() {
        //When
        doThrow(RuntimeException.class).when(teacherRepository).deleteAll();

        ResponseEntity<?> response = teacherController.deleteAllTeachers();
        System.out.println(response);

        //Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void should_delete_teacher_by_id_and_return_ok() {
        //Given
        Long id = 1L;

        //When
        doNothing().when(teacherRepository).deleteById(id);
        ResponseEntity<HttpStatus> response = teacherController.deleteTeacher(id);
        verify(teacherRepository).deleteById(id);

        //Then
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    void should_not_delete_teacher_by_id_and_return_internal_server_error_exception() {
        //Given
        Long id = 1L;

        //When
        doThrow(RuntimeException.class).when(teacherRepository).deleteById(id);
        ResponseEntity<HttpStatus> response = teacherController.deleteTeacher(id);
        //verify(teacherRepository).deleteById(id);

        //Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testing_the_teacher_repository_save_method() {
        //Given
        Teacher teacherToSave = new Teacher();
        teacherToSave.setName("Sumit");
        teacherToSave.setEmail("sumit@gmail.com");
        teacherToSave.setAddress("12345");
        teacherToSave.setPrincipal(1);
        teacherToSave.setSubject("Chemistry");
        teacherToSave.setRole(TEACHER);

        //When
        when(teacherRepository.save(any(Teacher.class))).thenAnswer(invocation -> {
            Teacher savedTeacher = invocation.getArgument(0);

            //Then
            assertEquals("Sumit", savedTeacher.getName());
            assertEquals("sumit@gmail.com", savedTeacher.getEmail());
            assertEquals("12345", savedTeacher.getAddress());
            assertEquals(1, savedTeacher.getPrincipal());
            assertEquals("Chemistry", savedTeacher.getSubject());
            assertEquals(TEACHER, savedTeacher.getRole());
           System.out.println(savedTeacher.getName());
           System.out.println(savedTeacher.getEmail());
            return new Teacher();
        });

        teacherRepository.save(teacherToSave);

        verify(teacherRepository).save(any(Teacher.class));
    }

}


