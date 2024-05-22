package com.example.hibernation_demo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class StudentTest {

    @Test
    public void should_return_success_test_student_getters_and_setters(){
        //Given
        Student student = new Student();
        student.setId(1L);
        student.setName("Kishore");
        student.setSection("A");
        student.setRollNumber(20);

        //Then
        assertEquals(1L, student.getId());
        assertEquals("Kishore", student.getName());
        assertEquals("A", student.getSection());
        assertEquals(20, student.getRollNumber());
    }
}
