package com.example.blood.controller;

import com.example.blood.dto.PatientDto;
import com.example.blood.service.JdbcPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jdbcPatient")
public class JdbcPatientController {
    private final JdbcPatientService jdbcPatientService;

    @Autowired
    public JdbcPatientController(JdbcPatientService jdbcPatientService) {
        this.jdbcPatientService = jdbcPatientService;
    }

    @GetMapping
    public ResponseEntity<List<PatientDto>> getAllPatients() {
        List<PatientDto> patientDtoList = jdbcPatientService.getAllPatients();
        if (patientDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(patientDtoList, HttpStatus.OK);
    }

    @GetMapping(params = "patientName")
    public ResponseEntity<List<PatientDto>> getPatientsByPatientName(@RequestParam String patientName) {
        List<PatientDto> patientDtoList = jdbcPatientService.getPatientsByPatientName(patientName);
        if (patientDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(patientDtoList, HttpStatus.OK);
    }

    @GetMapping(params = "diseaseName")
    public ResponseEntity<List<PatientDto>> getPatientsByDiseaseName(@RequestParam String diseaseName) {
        List<PatientDto> patientDtoList = jdbcPatientService.getPatientsByDiseaseName(diseaseName);
        if (patientDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(patientDtoList, HttpStatus.OK);
    }
}
