package com.example.hibernation_demo.Controller;


import com.example.hibernation_demo.controller.StudentController;
import com.example.hibernation_demo.model.Student;
import com.example.hibernation_demo.model.StudentTeacherMapping;
import com.example.hibernation_demo.model.Teacher;
import com.example.hibernation_demo.repo.StudentRepository;
import com.example.hibernation_demo.repo.StudentTeacherMappingRepository;
import com.example.hibernation_demo.repo.TeacherRepository;
import com.example.hibernation_demo.requestbody.StudentReqBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class StudentControllerTest {

    @InjectMocks
    private StudentController studentController;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private StudentTeacherMappingRepository studentTeacherMappingRepository;



    @Test
    public void should_add_the_student_and_return_success_code() {
        //Given
        StudentReqBody requestBody = new StudentReqBody();
        requestBody.setName("Tejasvi");
        requestBody.setSection("A");
        requestBody.setRollNumber(12);
        requestBody.setTeacherId(1L);
        requestBody.setPercentage("90%");

        //When
        when(studentRepository.save(any(Student.class))).thenReturn(new Student());
        when(teacherRepository.findById(anyLong())).thenReturn(Optional.of(new Teacher()));
        ResponseEntity<String> response = studentController.addStudent(requestBody);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Student added successfully", response.getBody());
    }

    @Test
    public void should_not_add_student_and_return_internal_server_error() {

        //Given
        StudentReqBody requestBody = new StudentReqBody();
        requestBody.setName("Tejasvi");
        requestBody.setSection("A");
        requestBody.setRollNumber(33);
        requestBody.setTeacherId(1L);
        requestBody.setPercentage("90%");

        //When
        when(studentRepository.save(any(Student.class))).thenThrow(new RuntimeException());
        ResponseEntity<String> response = studentController.addStudent(requestBody);

        //Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Failed to add the student", response.getBody());
    }

    @Test
    public void should_delete_all_students_and_return_success_Code() {
        //When
        doNothing().when(studentRepository).deleteAll();
        ResponseEntity<?> response = studentController.deleteAllStudents();

        //Then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void should_not_delete_all_students_and_return_internal_server_error() {
        //When
        doThrow(new RuntimeException()).when(studentRepository).deleteAll();
        ResponseEntity<?> response = studentController.deleteAllStudents();

        //Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }


    @Test
    public void should_delete_student_by_id_and_return_success_code() {
        //Given
        Long studentId = 1L;

        //When
        doNothing().when(studentRepository).deleteById(studentId);
        ResponseEntity<HttpStatus> response = studentController.deleteStudent(studentId);

        //Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void should_not_delete_student_by_id_and_return_internal_server_error() {
        //Given
        Long studentId = 1L;

        //When
        doThrow(new RuntimeException()).when(studentRepository).deleteById(studentId);
        ResponseEntity<HttpStatus> response = studentController.deleteStudent(studentId);

        //Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void should_find_all_students() throws Exception {

        //Given
        ArrayList<Student> listOfStudents = new ArrayList<Student>();
        Student studentResult = new Student();
        studentResult.setName("Ayushi");
        studentResult.setSection("A");
        studentResult.setRollNumber(30);
        listOfStudents.add(studentResult);
        System.out.println(listOfStudents);

        //When
        Mockito.when(studentRepository.findAll()).thenReturn(listOfStudents);

        ResponseEntity<?> result = studentController.getAllStudents();
        List<Student> studentResultList;
        System.out.println(result);
        studentResultList = (List<Student>) result.getBody();

        //Then
        assertEquals(1, studentResultList.size());

    }

    @Test
    public void testing_the_student_repository_save_method() {
        //Given
        Student studentToSave = new Student();
        studentToSave.setName("Sumit");
        studentToSave.setSection("A");
        studentToSave.setRollNumber(23);

        //When
        when(studentRepository.save(any(Student.class))).thenAnswer(invocation -> {
            Student savedStudent = invocation.getArgument(0);

            //Then
            assertEquals("Sumit", savedStudent.getName());
            assertEquals("A", savedStudent.getSection());
            assertEquals(23, savedStudent.getRollNumber());

            return new Student();
        });

        studentRepository.save(studentToSave);

        verify(studentRepository).save(any(Student.class));
    }

    @Test
    public void should_add_student_with_teacher_present() throws Exception {
        //Given
        StudentReqBody requestBody = new StudentReqBody();
        requestBody.setName("Ayushi");
        requestBody.setSection("A");
        requestBody.setRollNumber(60);
        requestBody.setTeacherId(1L);
        requestBody.setPercentage("95%");

        Student student = new Student();
        student.setId(1L);
        student.setName(requestBody.getName());
        student.setSection(requestBody.getSection());
        student.setRollNumber(requestBody.getRollNumber());

        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setName("Renzo");
        teacher.setSubject("Maths");

        StudentTeacherMapping savedMapping = new StudentTeacherMapping();
        savedMapping.setStudentId(student.getId());
        savedMapping.setTeacherId(teacher.getId());
        savedMapping.setStudentName(requestBody.getName());
        savedMapping.setSection(requestBody.getSection());
        savedMapping.setPercentage(requestBody.getPercentage());
        savedMapping.setSubject(teacher.getSubject());
        savedMapping.setTeacherName(teacher.getName());

        //When
        Mockito.when(studentRepository.save(any(Student.class))).thenReturn(student);
        Mockito.when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));
        Mockito.when(studentTeacherMappingRepository.save(any(StudentTeacherMapping.class))).thenReturn(savedMapping);
        ResponseEntity<String> responseEntity = studentController.addStudent(requestBody);

        //Then
        verify(studentTeacherMappingRepository, times(1)).save(any(StudentTeacherMapping.class));
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Student added successfully", responseEntity.getBody());

        ArgumentCaptor<StudentTeacherMapping> captor = ArgumentCaptor.forClass(StudentTeacherMapping.class);
        verify(studentTeacherMappingRepository).save(captor.capture());
        StudentTeacherMapping mapping = captor.getValue();

        assertEquals(1L,mapping.getStudentId());
        assertEquals(1L,mapping.getTeacherId());
        assertEquals("Ayushi",mapping.getStudentName());
        assertEquals("A",mapping.getSection());
        assertEquals("95%",mapping.getPercentage());
        assertEquals("Maths",mapping.getSubject());
        assertEquals("Renzo",mapping.getTeacherName());

    }

}




