package com.example.HrBackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EmployeeDetails {

    @JsonProperty("Address") private String address;
    @JsonProperty("Category") private String category;
    @JsonProperty("CompanyName") private String companyName;
    @JsonProperty("ContactNo") private String contactNo;
    @JsonProperty("Date_Of_Registration") private String dateOfRegistration;
    @JsonProperty("Designatiion") private String designation; // typo kept
    @JsonProperty("Documents") private String documents;
    @JsonProperty("Education") private String education;
    @JsonProperty("Email") private String email;
    @JsonProperty("EmployeeID") private String employeeID;
    @JsonProperty("Gender") private String gender;
    @JsonProperty("JoiningDate") private String joiningDate;
    @JsonProperty("Latitude") private String latitude;
    @JsonProperty("Location") private String location;
    @JsonProperty("Logitude") private String longitude; // typo kept
    @JsonProperty("Name") private String name;
    @JsonProperty("PanNo") private String panNo;
    @JsonProperty("Photo") private String photo;
    @JsonProperty("Radius") private String radius;
}

