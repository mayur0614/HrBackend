package com.example.HrBackend.controller;

import com.example.HrBackend.model.Attendance;
import com.example.HrBackend.model.CheckInOut;
import com.example.HrBackend.repository.AttendanceRepository;
import com.example.HrBackend.repository.CheckInOutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
@RequestMapping("/RestServiceImpl.svc")
@CrossOrigin
public class AttendanceController {

    @Autowired
    private AttendanceRepository repo;

    @Autowired
    private CheckInOutRepository checkRepo;

    // ✅ Get full attendance list
    @GetMapping("/GetAttendanceByEmployeeId")
    public ResponseEntity<?> getAttendanceByEmployeeId(
            @RequestParam("UserId") String userId
    ) {
        return ResponseEntity.ok(
                repo.findByEmployeeIDOrderByCDateDescTimeDesc(userId)
        );
    }

    // ✅ Insert attendance (IN / OUT)
    @PostMapping("/insertAttendence")
    public ResponseEntity<?> insertAttendence(
            @RequestParam("Status") String status,
            @RequestParam("Logitude") String longitude,
            @RequestParam("Latitude") String latitude,
            @RequestParam("Pic") String pic,
            @RequestParam("EmployeeID") String employeeID,
            @RequestParam("Distance") String distance
    ) {

        String currentDate = LocalDate.now().toString();

        String currentTime = LocalTime.now()
                .withNano(0) // keep seconds, just remove nanos
                .format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        String timestamp = LocalDateTime.now().toString(); // ✅ IMPORTANT

        // 🔹 1. Save raw attendance
        Attendance att = new Attendance();
        att.setEmployeeID(employeeID);
        att.setStatus(status);
        att.setLongitude(longitude);
        att.setLatitude(latitude);
        att.setPic(pic);
        att.setDistance(distance);
        att.setCDate(currentDate);
        att.setTime(currentTime);

        repo.save(att);

        // 🔹 2. Fetch today's record
        Optional<CheckInOut> existing =
                checkRepo.findByEmployeeIDAndDate(employeeID, currentDate);

        // 🔹 3. Handle IN
        if (status.equalsIgnoreCase("IN")) {

            if (existing.isEmpty()) {
                CheckInOut check = new CheckInOut();
                check.setEmployeeID(employeeID);
                check.setDate(currentDate);
                check.setCheckInTime(currentTime);
                check.setTimestamp(timestamp); // ✅ FIX

                checkRepo.save(check);
            }

        }

        // 🔹 4. Handle OUT
        else if (status.equalsIgnoreCase("OUT")) {

            if (existing.isPresent()) {
                CheckInOut check = existing.get();

                if (check.getCheckOutTime() == null) {
                    check.setCheckOutTime(currentTime);
                    check.setTimestamp(timestamp); // ✅ FIX

                    try {
                        LocalTime in = LocalTime.parse(check.getCheckInTime());
                        LocalTime out = LocalTime.parse(currentTime);

                        Duration duration = Duration.between(in, out);

                        long hours = duration.toHours();
                        long minutes = duration.toMinutes() % 60;

                        check.setTotalHours(hours + "h " + minutes + "m");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    checkRepo.save(check);
                }
            }
        }

        return ResponseEntity.ok("Saved successfully");
    }
}