package com.example.blood.repository;

import com.example.blood.domain.BloodDonationRelay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaBloodDonationRelay extends JpaRepository<BloodDonationRelay,Long> {
    BloodDonationRelay findFirstByBloodDonationRelayId(String relayId);
}
