package com.example.blood.service;

import com.example.blood.domain.Patient;
import com.example.blood.dto.DonationDetailsDto;
import com.example.blood.dto.PatientDto;
import com.example.blood.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
    private PatientDto convertToDto(Patient patient) {
        return new PatientDto(
                patient.getPatientId(),
                patient.getName(),
                patient.getBirthDate(),
                patient.getPhoneNumber(),
                patient.getGender(),
                patient.getHospitalName(),
                patient.getDiseaseName()
        );
    }

    public List<DonationDetailsDto> getDonationDetails() {
        return patientRepository.findDonationDetails().stream()
                .map(details -> new DonationDetailsDto(
                        (Long) details[0],
                        (String) details[1],
                        (String) details[2],
                        (String) details[3],
                        (String) details[4]
                ))
                .collect(Collectors.toList());
    }
    public List<PatientDto> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<PatientDto> getPatientsByPatientName(String patientName) {
        return patientRepository.findByPatientName(patientName).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<PatientDto> getPatientsByDiseaseName(String diseaseName) {
        return patientRepository.findByDiseaseName(diseaseName).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}

