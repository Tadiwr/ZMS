package com.tadiwa.backend.features.member;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tadiwa.backend.features.cell.Cell;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="members")
public class Member {
    
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String fullName;

    @Column
    private Date dateOfBirth;

    @Column
    private Long voterRegNumber;

    @Column
    private String physicalAddress;

    @Column
    private String phoneNumber;

    @Column
    private Long membershipNumber;

    @Column
    private LocalDateTime createdAt;

    @ManyToOne()
    @JoinColumn(name = "cell_id")
    @JsonIgnore()
    private Cell cell;

    public void setCreatedAt(Long millis) {
        LocalDateTime timeAtStamp = LocalDateTime.ofInstant(
            Instant.ofEpochMilli(millis),
            ZoneId.systemDefault()
        );

        this.createdAt = timeAtStamp;
    }

}
