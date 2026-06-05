package com.example.HrBackend.controller;

import com.example.HrBackend.model.Employee;
import com.example.HrBackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class EmployeeController {

    @Autowired
    private EmployeeRepository repo;

    // 🔹 GET Employee by UserId
    @GetMapping("/GetAttendenceDetails")
    public ResponseEntity<?> getEmployeeByUserId(
            @RequestParam("UserId") String userId) {

        return repo.findByEmployeeID(userId)
                .map(emp -> ResponseEntity.ok(emp))
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔹 POST Save Employee (for Postman)
    @PostMapping("/saveEmployee")
    public ResponseEntity<?> saveEmployee(@RequestBody Employee employee) {

        // Optional: prevent duplicate employeeID
        if (repo.findByEmployeeID(employee.getEmployeeID()).isPresent()) {
            return ResponseEntity.badRequest().body("Employee already exists");
        }

        Employee saved = repo.save(employee);
        return ResponseEntity.ok(saved);
    }
}
