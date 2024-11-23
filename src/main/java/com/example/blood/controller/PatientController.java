package com.example.blood.controller;

import com.example.blood.dto.DonationDetailsDto;
import com.example.blood.dto.PatientDto;
import com.example.blood.dto.RequestPatientDto;
import com.example.blood.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // 환우 전체 조회 (정상동작)
    @GetMapping
    public ResponseEntity<List<PatientDto>> getAllPatients() {
        List<PatientDto> patientDtoList = patientService.getAllPatients();
        if (patientDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(patientDtoList, HttpStatus.OK);
    }

    // 이름으로 조회 (정상동작)
    @GetMapping(params = "patientName")
    public ResponseEntity<List<PatientDto>> getPatientsByPatientName(@RequestParam String patientName) {
        List<PatientDto> patientDtoList = patientService.getPatientsByPatientName(patientName);
        if (patientDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(patientDtoList, HttpStatus.OK);
    }
    //헌혈증서번호에 대한 회원이름,id , 환우이름,id (동작완료)
    @GetMapping("/details")
    public ResponseEntity<List<DonationDetailsDto>> getDonationDetails() {
        List<DonationDetailsDto> donationDetailsDtoList = patientService.getDonationDetails();
        if (donationDetailsDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(donationDetailsDtoList, HttpStatus.OK);
    }

    //환우 삽입 (정상동작)
    @PostMapping
    public ResponseEntity<String> addPatient(@RequestBody RequestPatientDto requestPatientDto) {
        try {
            patientService.addPatient(requestPatientDto);
            return new ResponseEntity<>("successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 환우(이름, 생년월일, 휴대폰번호, 성별, 병원이름, 병명), 환자ID로 갱신할 튜플 선택
    //환자ID 자동화 대상이므로 갱신 x (정상동작)
    @PutMapping(params = "patientId")
    public ResponseEntity<String> updatePatient(
            @RequestParam String patientId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) LocalDate birth,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String hospitalName,
            @RequestParam(required = false) String diseaseName) {
        try {
            patientService.updatePatient (patientId, name, birth, phoneNumber, gender, hospitalName,diseaseName);
            return new ResponseEntity<>("successfully", HttpStatus.OK); //204
        } catch (Exception e) {
            return new ResponseEntity<>("failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
