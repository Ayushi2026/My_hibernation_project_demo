package com.example.hibernation_demo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeacherTest {


    @Test
    public void should_return_success_test_teacher_getters_and_setters() {
        //Given
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setName("Tejasvi");
        teacher.setEmail("tejasvi@gmail.com");
        teacher.setAddress("12345");
        teacher.setSubject("Chemistry");
        teacher.setRole(Teacher.TeacherRole.TEACHER);

        //Then
        assertEquals(1L, teacher.getId());
        assertEquals("Tejasvi", teacher.getName());
        assertEquals("tejasvi@gmail.com", teacher.getEmail());
        assertEquals("12345", teacher.getAddress());
        assertEquals("Chemistry", teacher.getSubject());
        assertEquals(Teacher.TeacherRole.TEACHER, teacher.getRole());
    }
}
