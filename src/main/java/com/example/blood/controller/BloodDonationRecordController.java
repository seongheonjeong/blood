package com.example.blood.controller;

import com.example.blood.dto.BloodDonationRecordDto;
import com.example.blood.service.BloodDonationRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/BloodDonationRecord")
public class BloodDonationRecordController {
    private final BloodDonationRecordService bloodDonationRecordService;

    @Autowired
    public BloodDonationRecordController(BloodDonationRecordService bloodDonationRecordService) {
        this.bloodDonationRecordService = bloodDonationRecordService;
    }

    // 전체 검색 (ID가 아닌 이름으로 보이게)
    @GetMapping
    public ResponseEntity<List<BloodDonationRecordDto>> getAllBloodDonationRecords() {
        List<BloodDonationRecordDto> bloodDonationRecordDtoList = bloodDonationRecordService.getAllBloodDonationRecords();
        if (bloodDonationRecordDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found 상태로 응답
        }
        return new ResponseEntity<>(bloodDonationRecordDtoList, HttpStatus.OK); // 200 OK 상태로 응답
    }

    // 회원 이름별 검색
    @GetMapping(params = "memberName")
    public ResponseEntity<List<BloodDonationRecordDto>> getBloodDonationRecordsByMemberName(@RequestParam String memberName) {
        List<BloodDonationRecordDto> bloodDonationRecordDtoList = bloodDonationRecordService.getBloodDonationRecordsByMemberName(memberName);
        if (bloodDonationRecordDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found 상태로 응답
        }
        return new ResponseEntity<>(bloodDonationRecordDtoList, HttpStatus.OK); // 200 OK 상태로 응답
    }

    // 헌혈 릴레이 회차별 검색
    @GetMapping(params = "bloodDonationRelaySession")
    public ResponseEntity<List<BloodDonationRecordDto>> getBloodDonationRecordsByBloodDonationRelaySession(@RequestParam String bloodDonationRelaySession) {
        List<BloodDonationRecordDto> bloodDonationRecordDtoList = bloodDonationRecordService.getBloodDonationRecordsByBloodDonationRelaySession(bloodDonationRelaySession);
        if (bloodDonationRecordDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found 상태로 응답
        }
        return new ResponseEntity<>(bloodDonationRecordDtoList, HttpStatus.OK); // 200 OK 상태로 응답
    }
}
