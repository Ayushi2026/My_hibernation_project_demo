package com.example.hibernation_demo.repository;

import com.example.hibernation_demo.controller.TeacherController;
import com.example.hibernation_demo.model.Teacher;
import com.example.hibernation_demo.repo.TeacherRepository;
import org.assertj.core.api.AbstractBigDecimalAssert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static com.example.hibernation_demo.model.Teacher.TeacherRole.TEACHER;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@DataJpaTest
@RunWith(SpringRunner.class)
public class TeacherRepositoryTest {

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void should_return_success_when_create_new_teacher() {
        //Given
        Teacher teacher = new Teacher();
        teacher.setName("Sumit");
        teacher.setEmail("sumit@gmail.com");
        teacher.setAddress("12345");
        teacher.setPrincipal(1);
        teacher.setSubject("Chemistry");
        teacher.setRole(TEACHER);

        //When
        Teacher createdTeacher = teacherRepository.save(teacher);

        //Then
        assertThat(entityManager.find(Teacher.class, createdTeacher.getId())).isEqualTo(teacher);
    }

    @Test
    public void should_return_success_when_update_teacher() {
        //Given
        Teacher teacher = new Teacher();
        teacher.setName("Sumit");
        teacher.setEmail("sumit@gmail.com");
        teacher.setAddress("12345");
        teacher.setPrincipal(1);
        teacher.setSubject("Chemistry");
        teacher.setRole(TEACHER);

        //When
        entityManager.persist(teacher);
        String newName = "Kishore";
        teacher.setName(newName);
        teacherRepository.save(teacher);

        //Then
        assertThat(entityManager.find(Teacher.class, teacher.getId()).getName()).isEqualTo(newName);
    }

    @Test
    public void should_return_success_when_find_teacher_by_id() {
        //Given
        Teacher teacher = new Teacher();
        teacher.setName("Sumit");
        teacher.setEmail("sumit@gmail.com");
        teacher.setAddress("12345");
        teacher.setPrincipal(1);
        teacher.setSubject("Chemistry");
        teacher.setRole(TEACHER);

        //When
        entityManager.persist(teacher);
        Optional<Teacher> retrievedTeacher = teacherRepository.findById(teacher.getId());

        //Then
        assertThat(retrievedTeacher).contains(teacher);
    }

    @Test
    public void should_return_success_when_delete_teacher() {
        //Given
        Teacher teacher = new Teacher();
        teacher.setName("Sumit");
        teacher.setEmail("sumit@gmail.com");
        teacher.setAddress("12345");
        teacher.setPrincipal(1);
        teacher.setSubject("Chemistry");
        teacher.setRole(TEACHER);

        //When
        entityManager.persist(teacher);
        teacherRepository.delete(teacher);

        //Then
        assertThat(entityManager.find(Teacher.class, teacher.getId())).isNull();
    }

}

