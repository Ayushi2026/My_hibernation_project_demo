package com.example.hibernation_demo.model;


import jakarta.persistence.*;

@Entity
@Table(name = "student_teacher_mapping")
public class StudentTeacherMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_teacher_mapping_seq")
    @SequenceGenerator(name = "student_teacher_mapping_seq", sequenceName = "student_teacher_mapping_seq", allocationSize = 1)
    private Long id;

    @Column
    private Long studentId;
    @Column
    private Long teacherId;

    @Column
    private String percentage;

    @Column
    private String studentName;

    @Column
    private String teacherName;

    @Column
    private String section;

    @Column
    private String subject;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getGrade() {
        return percentage;
    }

    public String getStudentName(){
        return studentName;
    }

    public String getTeacherName(){
        return teacherName;
    }


    public void setStudentName(String studentName) { this.studentName = studentName;
    }

    public void setTeacherName(String teacherName) { this.teacherName = teacherName;
    }

    public void setSection(String section) { this.section = section;
    }

    public void setPercentage(String percentage) { this.percentage =percentage;
    }


    public void setSubject(String subject) { this.subject = subject;
    }

    public String getPercentage() { return percentage;
    }

    public String getSubject() { return subject;
    }

    public String getSection() { return section;
    }

    public void setName(String studentName) { this.studentName = studentName;
    }

    public String getName() { return studentName;
    }
}
