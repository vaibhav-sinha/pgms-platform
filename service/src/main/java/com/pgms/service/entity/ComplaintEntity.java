package com.pgms.service.entity;

import com.pgms.shared.model.EntryStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by user-1 on 27/6/15.
 */
@Data
@Entity
@Table(name = "complaint")
public class ComplaintEntity {

    @GeneratedValue
    @Id
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private EntryStatus entryStatus;

    @ManyToOne
    @JoinColumn
    private DepartmentEntity department;

    @ManyToOne
    @JoinColumn
    private ComplaintStatusEntity complaintStatus;

    private String description;
    private Date createdOn;
    private Date updatedOn;

    @ManyToOne
    private SubmitterEntity submitter;

    @ManyToOne
    @JoinColumn
    private LocationEntity location;

    private Date targetDate;
    private Date disposalDate;

    @ManyToOne
    @JoinColumn
    private CategoryEntity category;

    private boolean urgent;
    private boolean reopened = false;
}
