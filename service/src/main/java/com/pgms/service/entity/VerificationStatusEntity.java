package com.pgms.service.entity;

import com.pgms.shared.model.EntryStatus;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by user-1 on 12/7/15.
 */
@Data
@Entity
@Table(name = "verification_status")
public class VerificationStatusEntity {

    @GeneratedValue
    @Id
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private EntryStatus entryStatus;

    private String name;
    private Boolean representsClosed;
    private Boolean representsDefault;
}
