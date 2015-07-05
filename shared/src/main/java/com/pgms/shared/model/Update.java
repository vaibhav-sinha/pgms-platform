package com.pgms.shared.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by user-1 on 27/6/15.
 */
@Data
@Entity
public class Update {

    @GeneratedValue
    @Id
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private EntryStatus entryStatus;
    @ManyToOne(optional = false)
    @JoinColumn
    private Complaint complaint;
    @Enumerated(value = EnumType.STRING)
    private UserAction userAction;
    @ManyToOne(optional = false)
    @JoinColumn
    private Officer officer;
    @ManyToOne(optional = false)
    @JoinColumn
    private ComplaintStatus oldStatus;
    @ManyToOne(optional = false)
    @JoinColumn
    private ComplaintStatus newStatus;
    @ManyToOne(optional = false)
    @JoinColumn
    private Department oldDepartment;
    @ManyToOne(optional = false)
    @JoinColumn
    private Department newDepartment;
    @ManyToOne(optional = false)
    @JoinColumn
    private Category oldCategory;
    @ManyToOne(optional = false)
    @JoinColumn
    private Category newCategory;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date updatedOn;
    @Lob
    private String comment;
}
