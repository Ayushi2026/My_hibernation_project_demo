package com.example.hibernation_demo.repo;

import com.example.hibernation_demo.model.Student;
import com.example.hibernation_demo.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
