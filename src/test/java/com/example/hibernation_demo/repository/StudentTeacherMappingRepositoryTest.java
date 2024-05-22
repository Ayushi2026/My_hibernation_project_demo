package com.example.hibernation_demo.repository;

import com.example.hibernation_demo.model.Student;
import com.example.hibernation_demo.model.StudentTeacherMapping;
import com.example.hibernation_demo.model.Teacher;
import com.example.hibernation_demo.repo.StudentTeacherMappingRepository;
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
public class StudentTeacherMappingRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    private StudentTeacherMappingRepository studentTeacherMappingRepository;

    @Test
    public void should_return_success_when_create_new_student_teacher_mapped_table() {
        //Given
        StudentTeacherMapping studentTeacherMapping = new StudentTeacherMapping();
        studentTeacherMapping.setTeacherId(2L);
        studentTeacherMapping.setStudentId(3L);
        studentTeacherMapping.setTeacherName("Ashish");
        studentTeacherMapping.setStudentName("Hina");
        studentTeacherMapping.setPercentage("%90");
        studentTeacherMapping.setSubject("Chemistry");
        studentTeacherMapping.setSection("B");

        //When
        StudentTeacherMapping createdStudentTeacherMapped = studentTeacherMappingRepository.save(studentTeacherMapping);

        //Then
        assertThat(entityManager.find(StudentTeacherMapping.class, createdStudentTeacherMapped.getId())).isEqualTo(studentTeacherMapping);
    }

    @Test
    public void should_return_success_when_update_new_student_teacher_mapped_table() {
        //Given
        StudentTeacherMapping studentTeacherMapping = new StudentTeacherMapping();
        studentTeacherMapping.setTeacherId(2L);
        studentTeacherMapping.setStudentId(3L);
        studentTeacherMapping.setTeacherName("Ashish");
        studentTeacherMapping.setStudentName("Hina");
        studentTeacherMapping.setPercentage("%90");
        studentTeacherMapping.setSubject("Chemistry");
        studentTeacherMapping.setSection("B");

        //When
        entityManager.persist(studentTeacherMapping);
        String newName = "Kishore";
        studentTeacherMapping.setName(newName);
        studentTeacherMappingRepository.save(studentTeacherMapping);

        //Then
        assertThat(entityManager.find(StudentTeacherMapping.class, studentTeacherMapping.getId()).getName()).isEqualTo(newName);
    }

    @Test
    public void should_return_success_when_delete_student_teacher_mapped_table() {
        //Given
        StudentTeacherMapping studentTeacherMapping = new StudentTeacherMapping();
        studentTeacherMapping.setTeacherId(2L);
        studentTeacherMapping.setStudentId(3L);
        studentTeacherMapping.setTeacherName("Ashish");
        studentTeacherMapping.setStudentName("Hina");
        studentTeacherMapping.setPercentage("%90");
        studentTeacherMapping.setSubject("Chemistry");
        studentTeacherMapping.setSection("B");

        //When
        entityManager.persist(studentTeacherMapping);
        studentTeacherMappingRepository.delete(studentTeacherMapping);

        //Then
        assertThat(entityManager.find(StudentTeacherMapping.class, studentTeacherMapping.getId())).isNull();
    }
}
