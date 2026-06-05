package com.example.HrBackend.model;

import java.util.List;

public class EmployeeDetailsResponse {
    private List<EmployeeDetails> result;

    public EmployeeDetailsResponse(List<EmployeeDetails> result) {
        this.result = result;
    }

    public List<EmployeeDetails> getResult() {
        return result;
    }
}

