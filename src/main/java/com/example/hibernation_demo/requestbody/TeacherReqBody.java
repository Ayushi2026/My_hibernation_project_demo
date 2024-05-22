package com.example.hibernation_demo.requestbody;

import com.example.hibernation_demo.model.Teacher;

public class TeacherReqBody {

    private String name;

    private String email;

    private String address;

    private Boolean principal;

    private String subject;

    private Teacher.TeacherRole role;


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }


    public boolean getPrincipal() { return principal;
    }

    public String getSubject() { return subject;
    }

    public Teacher.TeacherRole getRole(){ return role;}

    public void setName(String name) { this.name= name;
    }

    public void setEmail(String email) { this.email= email;
    }

    public void setAddress(String address) { this.address = address;
    }

    public void setPrincipal(boolean principal) {this.principal = principal;
    }

    public void setSubject(String subject) { this.subject = subject;
    }

    public void setRole(Teacher.TeacherRole role) {
        this.role = role;
    }


}
