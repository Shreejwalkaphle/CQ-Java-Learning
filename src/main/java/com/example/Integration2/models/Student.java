package com.example.Integration2.models;

import com.example.Integration2.models.builders.StudentBuilder;

public class Student {
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

    public Student(StudentBuilder builder){
        this.studentId = builder.getStudentId();
        this.firstName = builder.getFirstName();
        this.lastName = builder.getLastName();
        this.age = builder.getAge();
        this.gender = builder.getGender();
        this.address = builder.getAddress();
        this.phoneNumber = builder.getPhoneNumber();
        this.email = builder.getEmail();
        this.department = builder.getDepartment();
        this.gpa = builder.getGpa();
    }
    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", department='" + department + '\'' +
                ", gpa=" + gpa +
                '}';
    }

}
