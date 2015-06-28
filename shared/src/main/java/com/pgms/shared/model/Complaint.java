package com.pgms.shared.model;

import lombok.Data;
import org.activiti.engine.task.Task;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by user-1 on 27/6/15.
 */
@Data
public class Complaint {

    @GeneratedValue
    @Id
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private EntryStatus entryStatus;
    @ManyToOne
    @JoinColumn
    private Department department;
    @ManyToOne
    @JoinColumn
    private ComplaintStatus complaintStatus;
    private String description;
    private Date createdOn;
    private Date updatedOn;
    private Submitter submitter;
    private Location location;
    private Date targetDate;
    private Date disposalDate;
    @ManyToOne
    @JoinColumn
    private Category category;
    private boolean urgent;
}
