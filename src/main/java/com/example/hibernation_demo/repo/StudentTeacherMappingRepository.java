package com.example.hibernation_demo.repo;

import com.example.hibernation_demo.model.StudentTeacherMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentTeacherMappingRepository extends JpaRepository<StudentTeacherMapping, Long> {
}
