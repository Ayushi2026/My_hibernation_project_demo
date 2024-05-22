package com.example.hibernation_demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Teacher")

public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teacher_seq")
    @SequenceGenerator(name = "teacher_seq", sequenceName = "teacher_seq", allocationSize = 1)
    private Long id;
    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String address;

    @Column (name = "principal")
    private Integer principal;

    @Column
    private String subject;

    @Enumerated(EnumType.STRING)
    private TeacherRole role;


    public Teacher(long l, String name, String s, String s1, boolean b, String chemistry, TeacherRole teacher) {
        this.name = name;
    }

    public Teacher() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRole(TeacherRole role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public void setPrincipal(Integer principal) { this.principal = principal;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEmail() { return email;}

    public String getAddress() { return address;
    }

    public Integer getPrincipal() { return principal;
    }

    public TeacherRole getRole() { return role;
    }

    public enum TeacherRole {
        TEACHER,
        HOD,
        PRINCIPAL

    }
}
