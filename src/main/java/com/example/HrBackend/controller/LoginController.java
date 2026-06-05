package com.example.HrBackend.controller;

import com.example.HrBackend.model.Employee;
import com.example.HrBackend.model.EmployeeDetails;
import com.example.HrBackend.model.EmployeeDetailsResponse;
import com.example.HrBackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;




@RestController
@RequestMapping("/RestServiceImpl.svc")
@CrossOrigin
public class LoginController {

    @Autowired
    private EmployeeRepository repo;

    @PostMapping("/Login")
    public EmployeeDetailsResponse login(
            @RequestParam String UserId,
            @RequestParam String Password) {

        return repo.findByEmployeeID(UserId)
                .filter(emp -> emp.getContactNo().equals(Password))
                .map(emp -> {
                    EmployeeDetails dto = mapToDto(emp);
                    return new EmployeeDetailsResponse(List.of(dto));
                })
                .orElse(new EmployeeDetailsResponse(List.of()));
    }
    @GetMapping("/GetEmployeeDetails")
    public Map<String, List<EmployeeDetails>> getEmployeeDetails(
            @RequestParam("UserID") String userId
    ) {

        List<EmployeeDetails> result = repo.findByEmployeeID(userId)
                .map(emp -> List.of(mapToDto(emp)))
                .orElse(List.of());

        return Map.of("GetEmployeeDetailsResult", result);
    }
    private EmployeeDetails mapToDto(Employee emp) {
        EmployeeDetails d = new EmployeeDetails();
        d.setAddress(emp.getAddress());
        d.setCategory(emp.getCategory());
        d.setCompanyName(emp.getCompanyName());
        d.setContactNo(emp.getContactNo());
        d.setDateOfRegistration(emp.getDateOfRegistration());
        d.setDesignation(emp.getDesignation());
        d.setDocuments(emp.getDocuments());
        d.setEducation(emp.getEducation());
        d.setEmail(emp.getEmail());
        d.setEmployeeID(emp.getEmployeeID());
        d.setGender(emp.getGender());
        d.setJoiningDate(emp.getJoiningDate());
        d.setLatitude(emp.getLatitude());
        d.setLocation(emp.getLocation());
        d.setLongitude(emp.getLongitude());
        d.setName(emp.getName());
        d.setPanNo(emp.getPanNo());
        d.setPhoto(emp.getPhoto());
        d.setRadius(emp.getRadius());
        return d;
    }
}
