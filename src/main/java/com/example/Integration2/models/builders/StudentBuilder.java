package com.example.Integration2.models.builders;

import com.example.Integration2.models.Student;
import lombok.Getter;

@Getter
public class StudentBuilder {
    private String studentId;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String address;
    private String phoneNumber;
    private String email;
    private String department;
    private double gpa;
    public StudentBuilder() {}

    public StudentBuilder studentId(String studentId) {
        this.studentId = studentId;
        return this;
    }

    public StudentBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public StudentBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public StudentBuilder age(int age) {
        this.age = age;
        return this;
    }

    public StudentBuilder gender(String gender) {
        this.gender = gender;
        return this;
    }

    public StudentBuilder address(String address) {
        this.address = address;
        return this;
    }

    public StudentBuilder phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public StudentBuilder email(String email) {
        this.email = email;
        return this;
    }

    public StudentBuilder department(String department) {
        this.department = department;
        return this;
    }

    public StudentBuilder gpa(double gpa) {
        this.gpa = gpa;
        return this;
    }
    public Student build(){
        return new Student(this);
    }
}
