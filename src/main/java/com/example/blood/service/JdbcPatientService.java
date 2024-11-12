//package com.example.blood.service;
//
//import com.example.blood.dto.PatientDto;
//import com.example.blood.repository.JdbcPatientRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class JdbcPatientService {
//    private final JdbcPatientRepository jdbcPatientRepository;
//
//    @Autowired
//    public JdbcPatientService(JdbcPatientRepository jdbcPatientRepository) {
//        this.jdbcPatientRepository = jdbcPatientRepository;
//    }
//
//    public List<PatientDto> getAllPatients() {
//        return jdbcPatientRepository.findAll();
//    }
//
//    public List<PatientDto> getPatientsByPatientName(String patientName) {
//        return jdbcPatientRepository.findByPatientName(patientName);
//    }
//
//    public List<PatientDto> getPatientsByDiseaseName(String diseaseName) {
//        return jdbcPatientRepository.findByDiseaseName(diseaseName);
//    }
//}
