package com.pgms.service.entity;

import com.pgms.shared.model.EntryStatus;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by user-1 on 27/6/15.
 */
@Data
@Entity
@Table(name = "complaint_status")
public class ComplaintStatusEntity {

    @GeneratedValue
    @Id
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private EntryStatus entryStatus;

    private String name;
    private Boolean officerCanAccess;
    private Boolean callCentreCanAccess;
    private Boolean cmoCanAccess;

}
