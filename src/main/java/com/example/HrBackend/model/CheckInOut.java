package com.example.HrBackend.model;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "checkinout")
@Data
public class CheckInOut {

    @Id
    private String id;

    private String employeeID;
    private String date;

    private String checkInTime;
    private String checkOutTime;

    private String totalHours;

    // ✅ ADD THIS FIELD
    private String timestamp;
}