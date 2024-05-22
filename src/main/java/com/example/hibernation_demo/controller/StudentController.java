package com.example.hibernation_demo.controller;

import com.example.hibernation_demo.model.Student;
import com.example.hibernation_demo.model.StudentTeacherMapping;
import com.example.hibernation_demo.model.Teacher;
import com.example.hibernation_demo.repo.StudentTeacherMappingRepository;
import com.example.hibernation_demo.repo.TeacherRepository;
import com.example.hibernation_demo.requestbody.StudentReqBody;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.hibernation_demo.repo.StudentRepository;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1")
public class StudentController {

    public StudentController(StudentRepository studentRepository, TeacherRepository teacherRepository, StudentTeacherMappingRepository studentTeacherMappingRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.studentTeacherMappingRepository = studentTeacherMappingRepository;
    }

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    StudentTeacherMappingRepository studentTeacherMappingRepository;


    @PostMapping("/add-student")
    public ResponseEntity<String> addStudent(@RequestBody StudentReqBody studentReqBody) {
        try {
            Student student = new Student();
            student.setName(studentReqBody.getName());
            student.setSection(studentReqBody.getSection());
            student.setRollNumber(studentReqBody.getRollNumber());
            Student persistedStudent = studentRepository.save(student);

            Optional<Teacher> teacher = teacherRepository.findById(studentReqBody.getTeacherId());

            if (teacher.isPresent()) {
                Teacher teacher1 = teacher.get();
                String teacherName = teacher1.getName();
                String subject = teacher1.getSubject();
                StudentTeacherMapping studentTeacherMapping = new StudentTeacherMapping();
                studentTeacherMapping.setStudentId(persistedStudent.getId());
                studentTeacherMapping.setTeacherId(studentReqBody.getTeacherId());
                studentTeacherMapping.setStudentName(studentReqBody.getName());
                studentTeacherMapping.setPercentage(studentReqBody.getPercentage());
                studentTeacherMapping.setSection(studentReqBody.getSection());
                studentTeacherMapping.setSubject(subject);
                studentTeacherMapping.setTeacherName(teacherName);
                studentTeacherMappingRepository.save(studentTeacherMapping);
            }
            return ResponseEntity.status(HttpStatus.CREATED).body("Student added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add the student");
        }


    }

    @DeleteMapping("/delete-student-by-id/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable Long id) {
        try {
            studentRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-all-students")
    public ResponseEntity<?> deleteAllStudents() {
        try {
            studentRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-all-students")
    public ResponseEntity<?> getAllStudents() {
        try {
            List<Student> studentList =studentRepository.findAll();
            return new ResponseEntity<>(studentList,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}



