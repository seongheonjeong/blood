package com.example.blood.controller;

import com.example.blood.dto.BloodDonationRecordDto;
import com.example.blood.dto.DonationAmountDto;
import com.example.blood.dto.RequestBloodDonationRecordDto;
import com.example.blood.service.BloodDonationRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bloodDonationRecord")
public class BloodDonationRecordController {
    private final BloodDonationRecordService bloodDonationRecordService;

    @Autowired
    public BloodDonationRecordController(BloodDonationRecordService bloodDonationRecordService) {
        this.bloodDonationRecordService = bloodDonationRecordService;
    }

    // 전체 검색 (ID가 아닌 이름으로 보이게) , 헌혈일자 기준 오름차순
    @GetMapping
    public ResponseEntity<List<BloodDonationRecordDto>> getAllBloodDonationRecords() {
        List<BloodDonationRecordDto> bloodDonationRecordDtoList = bloodDonationRecordService.getAllBloodDonationRecords();
        if (bloodDonationRecordDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bloodDonationRecordDtoList, HttpStatus.OK);
    }

    // 회원 이름별 검색 , 헌혈일자 기준 오름차순
    @GetMapping(params = "memberName")
    public ResponseEntity<List<BloodDonationRecordDto>> getBloodDonationRecordsByMemberName(@RequestParam String memberName) {
        List<BloodDonationRecordDto> bloodDonationRecordDtoList = bloodDonationRecordService.getBloodDonationRecordsByMemberName(memberName);
        if (bloodDonationRecordDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bloodDonationRecordDtoList, HttpStatus.OK);
    }
    // 헌혈 릴레이 회차별 검색
    @GetMapping(params = "bloodDonationRelaySession")
    public ResponseEntity<List<BloodDonationRecordDto>> getBloodDonationRecordsByBloodDonationRelaySession(@RequestParam String bloodDonationRelaySession) {
        List<BloodDonationRecordDto> bloodDonationRecordDtoList = bloodDonationRecordService.getBloodDonationRecordsByBloodDonationRelaySession(bloodDonationRelaySession);
        if (bloodDonationRecordDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bloodDonationRecordDtoList, HttpStatus.OK);
    }
    //헌혈종류와 증정품종류별 총헌혈량 검색
    @GetMapping("/bloodAmount")
    public ResponseEntity<List<DonationAmountDto>>findGroupByDonationAmount() {
        List<DonationAmountDto> donationAmountDtoList = bloodDonationRecordService.findGroupByDonationAmount();
        if (donationAmountDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(donationAmountDtoList, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String> addBloodDonationRecord(@RequestBody RequestBloodDonationRecordDto inputBloodDonationRecordDto) {
        try {
            bloodDonationRecordService.addBloodDonationRecord(inputBloodDonationRecordDto);
            return new ResponseEntity<>("successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    //헌혈기록번호를 통한 삭제
    @DeleteMapping (params = "bloodDonationRecordId")
    public ResponseEntity<String> deleteBloodDonationRecord(@RequestParam Long bloodDonationRecordId) {
        try {
            bloodDonationRecordService.deleteBloodDonationRecord(bloodDonationRecordId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>("failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
