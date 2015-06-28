package com.pgms.shared.model;

import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by user-1 on 27/6/15.
 */
@Data
public class Location {

    @GeneratedValue
    @Id
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private EntryStatus entryStatus;
    private String name;
}
