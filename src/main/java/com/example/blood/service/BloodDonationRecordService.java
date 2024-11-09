package com.example.blood.service;

import com.example.blood.repository.BloodDonationRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BloodDonationRecordService {
    private final BloodDonationRecordRepository bloodDonationRecordRepository;
    @Autowired
    public BloodDonationRecordService(BloodDonationRecordRepository bloodDonationRecordRepository) {
        this.bloodDonationRecordRepository = bloodDonationRecordRepository;
    }
}
