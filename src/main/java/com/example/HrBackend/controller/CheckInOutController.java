package com.example.HrBackend.controller;

import com.example.HrBackend.model.CheckInOut;
import com.example.HrBackend.repository.CheckInOutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/RestServiceImpl.svc")
@CrossOrigin
public class CheckInOutController {

    @Autowired
    private CheckInOutRepository repo;

    // ✅ 1. Get today's check-in/out

    // ✅ 2. Get full history
    @GetMapping("/GetCheckHistory")
    public List<CheckInOut> getHistory(
            @RequestParam("UserId") String employeeId
    ) {
        return repo.findAll()
                .stream()
                .filter(e -> e.getEmployeeID().equals(employeeId))
                .toList();
    }

    // ✅ 3. Optional: manual insert/update (for testing)
    @PostMapping("/SaveCheck")
    public CheckInOut saveCheck(@RequestBody CheckInOut check) {
        return repo.save(check);
    }

    @GetMapping("/GetLastAttendanceByEmployeeId")
    public ResponseEntity<?> getLastAttendance(@RequestParam("UserId") String userId) {

        return repo.findLatestByEmployeeID(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}