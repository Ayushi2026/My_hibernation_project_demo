package com.example.hibernation_demo.model;


import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
    @SequenceGenerator(name = "student_seq", sequenceName = "student_seq", allocationSize = 1)
    private Long id;
    @Column
    private String name;
    @Column
    private String section;

    @Column
    private int rollNumber;

    public Student() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setSection(String section) { this.section = section;    }

    public void setRollNumber (int rollNumber) { this.rollNumber = rollNumber;}

    public String getName() { return name;
    }

    public String getSection() { return section;
    }

    public int getRollNumber() { return rollNumber;
    }

}


