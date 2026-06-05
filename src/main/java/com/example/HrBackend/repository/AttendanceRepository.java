package com.example.HrBackend.repository;

import com.example.HrBackend.model.Attendance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends MongoRepository<Attendance, String> {
    List<Attendance> findByEmployeeIDOrderByCDateDescTimeDesc(String employeeID);
    List<Attendance> findByEmployeeID(String employeeID);
    List<Attendance> findByEmployeeIDAndCDate(String employeeID, String cDate);
}
