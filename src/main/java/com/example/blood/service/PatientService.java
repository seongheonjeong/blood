package com.example.blood.service;

import com.example.blood.dto.PatientDto;
import com.example.blood.repository.JdbcTemplatePatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private final JdbcTemplatePatientRepository jdbcTemplatePatientRepository;

    @Autowired
    public PatientService(JdbcTemplatePatientRepository jdbcTemplatePatientRepository) {
        this.jdbcTemplatePatientRepository = jdbcTemplatePatientRepository;
    }

    public List<PatientDto> getAllPatients() {
        return jdbcTemplatePatientRepository.findAll();
    }

    public List<PatientDto> getPatientsByPatientName(String patientName) {
        return jdbcTemplatePatientRepository.findByPatientName(patientName);
    }

    public List<PatientDto> getPatientsByDiseaseName(String diseaseName) {
        return jdbcTemplatePatientRepository.findByDiseaseName(diseaseName);
    }
}
