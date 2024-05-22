package com.example.hibernation_demo.controller;

import com.example.hibernation_demo.model.Teacher;
import com.example.hibernation_demo.repo.TeacherRepository;
import com.example.hibernation_demo.requestbody.TeacherReqBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TeacherController {


    private final TeacherRepository teacherRepository;

    public TeacherController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }


    @PostMapping("/add-teacher")
    public ResponseEntity<?> addTeacher(@RequestBody TeacherReqBody teacherReqBody) {
        try {
            Teacher teacher = new Teacher();
            teacher.setName(teacherReqBody.getName());
            teacher.setEmail(teacherReqBody.getEmail());
            teacher.setAddress(teacherReqBody.getAddress());
            teacher.setPrincipal(teacherReqBody.getPrincipal()?1:0);
            teacher.setSubject(teacherReqBody.getSubject());
            teacher.setRole(teacherReqBody.getRole());
            Teacher result =teacherRepository.save(teacher);
            System.out.println(result);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add the Teacher");
        }
    }


    @DeleteMapping("/delete-teacher-by-id/{id}")
    public ResponseEntity<HttpStatus> deleteTeacher(@PathVariable Long id) {
        try {
            teacherRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/delete-all=teachers")
    public ResponseEntity<?> deleteAllTeachers() {
        try {
            teacherRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-all-teachers")
    public ResponseEntity<?> getAllTeachers() {
        try {
            List<Teacher> teacherList =teacherRepository.findAll();
            return new ResponseEntity<>(teacherList,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}