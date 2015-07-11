package com.pgms.service.entity;

import com.pgms.shared.model.EntryStatus;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by user-1 on 27/6/15.
 */
@Data
@Entity
@Table(name = "action")
public class ActionEntity {

    @GeneratedValue
    @Id
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private EntryStatus entryStatus;
    private String name;
}
