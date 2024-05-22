package com.example.hibernation_demo.requestbody;

import com.example.hibernation_demo.model.Teacher;

public class
StudentReqBody {

    private String name;
    private String percentage;

    private Long teacher_id;

    private String section;

    private int rollNumber;

    public String getName() {
        return name;
    }

    public String getPercentage() {
        return percentage;
    }

    public String getSection(){ return section;}

    public void setName(String name) {
        this.name = name;
    }


    public void setTeacher_id(Long teacher_id) {
        this.teacher_id = teacher_id;
    }

    public Long getTeacherId() {
        return teacher_id;
    }

    public int getRollNumber() { return rollNumber;
    }

    public void setSection(String section) { this.section = section;
    }

    public void setRollNumber(Integer rollNumber) { this.rollNumber = rollNumber;
    }

    public void setTeacherId(Long teacher_id) { this.teacher_id=teacher_id;
    }

    public void setPercentage(String percentage) { this.percentage = percentage;
    }
}
