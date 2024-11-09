package com.example.blood.controller;

import com.example.blood.service.BloodDonationRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/BloodDonationRecord")
public class BloodDonationRecordController {
    private final BloodDonationRecordService bloodDonationRecordService;

    @Autowired
    public BloodDonationRecordController(BloodDonationRecordService bloodDonationRecordService) {
        this.bloodDonationRecordService = bloodDonationRecordService;
    }
    //전체검색(id들이아니라 이름으로 보이게),회원이름,헌혈릴레이회차로
}
