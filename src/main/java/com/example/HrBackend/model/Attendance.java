package com.example.HrBackend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "attendance")
@Data
public class Attendance {

    @Id
    private String id;

    private String employeeID;
    private String status;
    private String latitude;
    private String longitude;
    private String distance;
    private String pic;
    private String cDate;
    private String time;
}

