package com.pgms.shared.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by user-1 on 27/6/15.
 */
@Data
@Entity
public class Department {

    @GeneratedValue
    @Id
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private EntryStatus entryStatus;
    private String name;
    @OneToOne
    private Officer hod;
    @OneToOne
    private Officer gro;
    @OneToOne
    private Officer agro;
}
