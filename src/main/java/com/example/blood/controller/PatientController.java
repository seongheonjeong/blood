package com.example.blood.controller;



import com.example.blood.dto.PatientDto;
import com.example.blood.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
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
    //환우 전체 조회
    @GetMapping
    public List<PatientDto> getAllPatients() {
        return patientService.getAllPatients();
    }
    //이름으로 조회
    @GetMapping(params = "patientName")
    public List<PatientDto> getPatientsByPaientName(@RequestParam String patientName) {
        return patientService.getPatientsByPatientName(patientName);
    }
    //병명으로 조회
    @GetMapping(params = "diseaseName")
    public List<PatientDto> getPatientsByDiseaseNameName(@RequestParam String diseaseName) {
        return patientService.getPatientsByDiseaseName(diseaseName);
    }
}
