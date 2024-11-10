package com.example.blood.controller;

import com.example.blood.dto.PatientDto;
import com.example.blood.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // 환우 전체 조회
    @GetMapping
    public ResponseEntity<List<PatientDto>> getAllPatients() {
        List<PatientDto> patientDtoList = patientService.getAllPatients();
        if (patientDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found 상태로 응답
        }
        return new ResponseEntity<>(patientDtoList, HttpStatus.OK); // 200 OK 상태로 응답
    }

    // 이름으로 조회
    @GetMapping(params = "patientName")
    public ResponseEntity<List<PatientDto>> getPatientsByPatientName(@RequestParam String patientName) {
        List<PatientDto> patientDtoList = patientService.getPatientsByPatientName(patientName);
        if (patientDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found 상태로 응답
        }
        return new ResponseEntity<>(patientDtoList, HttpStatus.OK); // 200 OK 상태로 응답
    }

    // 병명으로 조회
    @GetMapping(params = "diseaseName")
    public ResponseEntity<List<PatientDto>> getPatientsByDiseaseName(@RequestParam String diseaseName) {
        List<PatientDto> patients = patientService.getPatientsByDiseaseName(diseaseName);
        if (patients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found 상태로 응답
        }
        return new ResponseEntity<>(patients, HttpStatus.OK); // 200 OK 상태로 응답
    }
}
