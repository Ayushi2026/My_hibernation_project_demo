package com.example.hibernation_demo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentTeacherMappingTest {

    @Test
    public void should_return_success_when_test_student_teacher_mapping_getters_and_setters(){
        //Given
        StudentTeacherMapping studentTeacherMapping = new StudentTeacherMapping();
        studentTeacherMapping.setId(1L);
        studentTeacherMapping.setTeacherId(2L);
        studentTeacherMapping.setStudentId(3L);
        studentTeacherMapping.setTeacherName("Ashish");
        studentTeacherMapping.setStudentName("Hina");
        studentTeacherMapping.setPercentage("%90");
        studentTeacherMapping.setSubject("Chemistry");
        studentTeacherMapping.setSection("B");

        //Then
        assertEquals(1L, studentTeacherMapping.getId());
        assertEquals(2L, studentTeacherMapping.getTeacherId());
        assertEquals(3L, studentTeacherMapping.getStudentId());
        assertEquals("Ashish", studentTeacherMapping.getTeacherName());
        assertEquals("Hina", studentTeacherMapping.getStudentName());
        assertEquals("%90", studentTeacherMapping.getPercentage());
        assertEquals("Chemistry", studentTeacherMapping.getSubject());
        assertEquals("B", studentTeacherMapping.getSection());
    }
}
