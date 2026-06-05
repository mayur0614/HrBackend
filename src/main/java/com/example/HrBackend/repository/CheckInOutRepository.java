package com.example.HrBackend.repository;

import com.example.HrBackend.model.CheckInOut;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface CheckInOutRepository extends MongoRepository<CheckInOut, String> {

    // ✅ FIXED METHOD (guaranteed to work)
    @Query("{ 'employeeID': ?0, 'date': ?1 }")
    Optional<CheckInOut> findByEmployeeIDAndDate(String employeeID, String date);

    @Query(value = "{ 'employeeID': ?0 }", sort = "{ 'date': -1, 'checkOutTime': -1, 'checkInTime': -1 }")
    Optional<CheckInOut> findLatestByEmployeeID(String employeeID);
}