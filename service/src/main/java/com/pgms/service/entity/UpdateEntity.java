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
@Table(name = "complaint_update")
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

    @ManyToOne
    @JoinColumn
    private ComplaintStatusEntity oldComplaintStatus;

    @ManyToOne
    @JoinColumn
    private ComplaintStatusEntity newComplaintStatus;

    @ManyToOne
    @JoinColumn
    private VerificationStatusEntity oldVerificationStatus;

    @ManyToOne
    @JoinColumn
    private VerificationStatusEntity newVerificationStatus;

    @ManyToOne
    @JoinColumn
    private ReviewStatusEntity oldReviewStatus;

    @ManyToOne
    @JoinColumn
    private ReviewStatusEntity newReviewStatus;

    @ManyToOne
    @JoinColumn
    private DepartmentEntity oldDepartment;

    @ManyToOne
    @JoinColumn
    private DepartmentEntity newDepartment;

    @ManyToOne
    @JoinColumn
    private CategoryEntity oldCategory;

    @ManyToOne
    @JoinColumn
    private CategoryEntity newCategory;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date updatedOn;

    @Lob
    private String comment;
}
