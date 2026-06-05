package com.example.HrBackend.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "employees")
public class Employee {

    @Id
    private String id;

    private String address;
    private String category;
    private String companyName;
    private String contactNo;
    private String dateOfRegistration;
    private String designation;
    private String documents;
    private String education;
    private String email;
    private String employeeID;
    private String gender;
    private String joiningDate;
    private String latitude;
    private String location;
    private String longitude;
    private String name;
    private String panNo;
    private String photo;
    private String radius;

}

