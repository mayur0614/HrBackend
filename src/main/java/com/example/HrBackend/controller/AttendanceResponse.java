package com.example.HrBackend.controller;

import com.example.HrBackend.model.DispCheck;

import java.util.List;

public class AttendanceResponse {

    private List<DispCheck> GetAttendenceDetailsResult;

    public AttendanceResponse(List<DispCheck> list) {
        this.GetAttendenceDetailsResult = list;
    }

    public List<DispCheck> getGetAttendenceDetailsResult() {
        return GetAttendenceDetailsResult;
    }
}
