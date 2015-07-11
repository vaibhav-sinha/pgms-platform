package com.pgms.service.entity;

import com.pgms.shared.model.EntryStatus;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by user-1 on 27/6/15.
 */
@Data
@Entity
@Table(name = "department")
public class DepartmentEntity {

    @GeneratedValue
    @Id
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private EntryStatus entryStatus;

    private String name;

    @OneToOne
    private OfficerEntity hod;

    @OneToOne
    private OfficerEntity gro;

    @OneToOne
    private OfficerEntity agro;
}
