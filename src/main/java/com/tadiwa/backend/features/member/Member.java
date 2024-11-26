package com.tadiwa.backend.features.member;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tadiwa.backend.features.cell.Cell;
import com.tadiwa.backend.features.executive_members.ExecutiveRole;
import com.tadiwa.backend.features.member.dto.MemberDTO;
import com.tadiwa.backend.shared.tranferable.Transferable;

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
public class Member implements Transferable<MemberDTO> {
    
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String fullName;

    @Column
    private Date dateOfBirth;

    @Column
    private Long voterRegNumber;

    @Column(unique = true)
    private String idNumber;

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


    @Override
    public MemberDTO toDTO() {
        return new MemberDTO(
            this.id,
            this.fullName,
            this.dateOfBirth,
            this.voterRegNumber,
            this.idNumber,
            this.physicalAddress,
            this.phoneNumber,
            this.membershipNumber,
            this.createdAt,
            this.getCell().getId()
        );
    }


}
