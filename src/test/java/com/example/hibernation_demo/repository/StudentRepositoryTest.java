package com.example.hibernation_demo.repository;


import com.example.hibernation_demo.model.Student;
import com.example.hibernation_demo.model.Teacher;
import com.example.hibernation_demo.repo.StudentRepository;
import com.example.hibernation_demo.requestbody.StudentReqBody;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static com.example.hibernation_demo.model.Teacher.TeacherRole.TEACHER;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
public class StudentRepositoryTest {

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void should_return_success_when_create_new_student() {
        //Given
        Student student = new Student();
        student.setName("Tejasvi");
        student.setSection("A");
        student.setRollNumber(12);

        //When
        Student createdStudent = studentRepository.save(student);

        //Then
        assertThat(entityManager.find(Student.class, createdStudent.getId())).isEqualTo(student);
    }

    @Test
    public void should_return_success_when_update_student() {
        //Given
        Student student = new Student();
        student.setName("Tejasvi");
        student.setSection("A");
        student.setRollNumber(12);

        //When
        entityManager.persist(student);
        String newName = "Kishore";
        student.setName(newName);
        studentRepository.save(student);

        //Then
        assertThat(entityManager.find(Student.class, student.getId()).getName()).isEqualTo(newName);
    }
    @Test
    public void should_return_success_when_find_student_by_id() {
        //Given
        Student student = new Student();
        student.setName("Tejasvi");
        student.setSection("A");
        student.setRollNumber(12);

        //When
        entityManager.persist(student);
        Optional<Student> retrievedStudent = studentRepository.findById(student.getId());

        //Then
        assertThat(retrievedStudent).contains(student);
    }

    @Test
    public void should_return_success_when_delete_student() {
        //Given
        Student student = new Student();
        student.setName("Tejasvi");
        student.setSection("A");
        student.setRollNumber(12);

        //When
        entityManager.persist(student);
        studentRepository.delete(student);

        //Then
        assertThat(entityManager.find(Student.class, student.getId())).isNull();
    }


}
