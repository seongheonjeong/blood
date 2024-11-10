package com.example.blood.controller;

import com.example.blood.dto.PatientDto;
import com.example.blood.service.JdbcPatientService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<PatientDto> getAllPatients() {
        return jdbcPatientService.getAllPatients();
    }
    @GetMapping(params = "patientName")
    public List<PatientDto> getPatientsByPatientName(@RequestParam String patientName) {
        return jdbcPatientService.getPatientsByPatientName(patientName);
    }
    @GetMapping(params = "diseaseName")
    public List<PatientDto> getPatientsByDiseaseName(@RequestParam String diseaseName) {
        return jdbcPatientService.getPatientsByDiseaseName(diseaseName);
    }
}
