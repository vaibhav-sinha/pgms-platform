package com.pgms.service.entity;

import com.pgms.shared.model.EntryStatus;
import com.pgms.shared.model.UserAction;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by user-1 on 27/6/15.
 */
@Data
@Entity
@Table(name = "update")
public class UpdateEntity {

    @GeneratedValue
    @Id
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private EntryStatus entryStatus;

    @ManyToOne(optional = false)
    @JoinColumn
    private ComplaintEntity complaint;

    @Enumerated(value = EnumType.STRING)
    private UserAction userAction;

    @ManyToOne(optional = false)
    @JoinColumn
    private OfficerEntity officer;

    @ManyToOne(optional = false)
    @JoinColumn
    private ComplaintStatusEntity oldComplaintStatus;

    @ManyToOne(optional = false)
    @JoinColumn
    private ComplaintStatusEntity newComplaintStatus;

    @ManyToOne(optional = false)
    @JoinColumn
    private VerificationStatusEntity oldVerificationStatus;

    @ManyToOne(optional = false)
    @JoinColumn
    private VerificationStatusEntity newVerificationStatus;

    @ManyToOne(optional = false)
    @JoinColumn
    private ReviewStatusEntity oldReviewStatus;

    @ManyToOne(optional = false)
    @JoinColumn
    private ReviewStatusEntity newReviewStatus;

    @ManyToOne(optional = false)
    @JoinColumn
    private DepartmentEntity oldDepartment;

    @ManyToOne(optional = false)
    @JoinColumn
    private DepartmentEntity newDepartment;

    @ManyToOne(optional = false)
    @JoinColumn
    private CategoryEntity oldCategory;

    @ManyToOne(optional = false)
    @JoinColumn
    private CategoryEntity newCategory;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date updatedOn;

    @Lob
    private String comment;
}
