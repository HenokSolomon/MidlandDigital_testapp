package com.midlanddigital.test.app.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "staff")
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Staff {

    @Id
    @Column(name = "staff_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int StaffId;

    @Column(name = "uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

}

