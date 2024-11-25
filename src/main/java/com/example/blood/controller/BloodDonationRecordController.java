package com.example.blood.controller;

import com.example.blood.dto.BloodDonationRecordDto;
import com.example.blood.dto.DonationAmountDto;
import com.example.blood.dto.RequestBloodDonationRecordDto;
import com.example.blood.service.BloodDonationRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/bloodDonationRecord")
public class BloodDonationRecordController {
    private final BloodDonationRecordService bloodDonationRecordService;

    @Autowired
    public BloodDonationRecordController(BloodDonationRecordService bloodDonationRecordService) {
        this.bloodDonationRecordService = bloodDonationRecordService;
    }

    //헌혈기록(ID 부분은 이름으로표기,환자이름 암호화,헌혈량 ml 표기) 헌혈일자 기준 오름차순 조회 (완)
    @GetMapping
    public ResponseEntity<List<BloodDonationRecordDto>> getAllBloodDonationRecords() {
        List<BloodDonationRecordDto> bloodDonationRecordDtoList = bloodDonationRecordService.getAllBloodDonationRecords();
        if (bloodDonationRecordDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bloodDonationRecordDtoList, HttpStatus.OK);
    }

    // 회원 이름으로 헌혈일자 기준 오름차순 조회 (완)
    @GetMapping(params = "memberName")
    public ResponseEntity<List<BloodDonationRecordDto>> getBloodDonationRecordsByMemberName(@RequestParam String memberName) {
        List<BloodDonationRecordDto> bloodDonationRecordDtoList = bloodDonationRecordService.getBloodDonationRecordsByMemberName(memberName);
        if (bloodDonationRecordDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bloodDonationRecordDtoList, HttpStatus.OK);
    }
    //헌혈종류와 증정품종류별 총헌혈량 검색 , 총 헌혈량은 L단위(완)
    @GetMapping("/bloodAmount")
    public ResponseEntity<List<DonationAmountDto>>findGroupByDonationAmount() {
        List<DonationAmountDto> donationAmountDtoList = bloodDonationRecordService.findGroupByDonationAmount();
        if (donationAmountDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(donationAmountDtoList, HttpStatus.OK);
    }
    //헌혈기록 삽입 (최초헌혈일,마지막 헌혈일 수정일 테스트 등 나머지 작동) (완)
    @PostMapping
    public ResponseEntity<String> addBloodDonationRecord(@RequestBody RequestBloodDonationRecordDto inputBloodDonationRecordDto) {
        try {
            bloodDonationRecordService.addBloodDonationRecord(inputBloodDonationRecordDto);
            return new ResponseEntity<>("successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    //헌혈기록번호를 통한 삭제 (최초,마지막 헌혈일 수정되는거 확인) (완)
    @DeleteMapping (params = "bloodDonationRecordId")
    public ResponseEntity<String> deleteBloodDonationRecord(@RequestParam Long bloodDonationRecordId) {
        try {
            bloodDonationRecordService.deleteBloodDonationRecord(bloodDonationRecordId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>("failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //헌혈기록 수정(헌혈최초마지막 확인,릴레이기간 확인) (완)
    @PutMapping(params = "bloodDonationRecordId")
    public ResponseEntity<String> updateDonationRecord(
            @RequestParam Long bloodDonationRecordId,
            @RequestParam(required = false) String employeeId,
            @RequestParam(required = false) LocalDate donationDate,
            @RequestParam(required = false) String donationType,
            @RequestParam(required = false) Integer donationAmount,
            @RequestParam(required = false) String giveaway) {
        try {
            bloodDonationRecordService.updateDonationRecord (bloodDonationRecordId, employeeId, donationDate, donationType, donationAmount, giveaway);
            return new ResponseEntity<>("successfully", HttpStatus.OK); //204
        } catch (Exception e) {
            return new ResponseEntity<>("failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
