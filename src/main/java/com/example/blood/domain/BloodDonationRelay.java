package com.example.blood.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "헌혈릴레이")
public class BloodDonationRelay {
    @Id
    @Column(name="헌혈릴레이ID")
    private String bloodDonationRelayId;
    @Column(name = "헌혈릴레이회차",nullable = true)
    private String bloodDonationRelaySession;
    @Column(name = "시작일")
    private LocalDate start;
    @Column(name = "종료일")
    private LocalDate end;
    @OneToMany(mappedBy = "bloodDonationRelay", cascade = CascadeType.ALL)
    private List<BloodDonationRecord> bloodDonationRecords = new ArrayList<>();
    public BloodDonationRelay() {
    }
    public BloodDonationRelay(String bloodDonationRelayId, String bloodDonationRelaySession, LocalDate start, LocalDate end, List<BloodDonationRecord> bloodDonationRecords) {
        this.bloodDonationRelayId = bloodDonationRelayId;
        this.bloodDonationRelaySession = bloodDonationRelaySession;
        this.start = start;
        this.end = end;
        this.bloodDonationRecords = bloodDonationRecords;
    }


    public String getBloodDonationRelayId() {
        return bloodDonationRelayId;
    }

    public void setBloodDonationRelayId(String bloodDonationRelayId) {
        this.bloodDonationRelayId = bloodDonationRelayId;
    }

    public String getBloodDonationRelaySession() {
        return bloodDonationRelaySession;
    }

    public void setBloodDonationRelaySession(String bloodDonationRelaySession) {
        this.bloodDonationRelaySession = bloodDonationRelaySession;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public List<BloodDonationRecord> getBloodDonationRecords() {
        return bloodDonationRecords;
    }

    public void setBloodDonationRecords(List<BloodDonationRecord> bloodDonationRecords) {
        this.bloodDonationRecords = bloodDonationRecords;
    }
}
