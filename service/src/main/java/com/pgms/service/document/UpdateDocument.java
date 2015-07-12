package com.pgms.service.document;

import com.pgms.shared.model.*;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by user-1 on 11/7/15.
 */
@TypeAlias("UpdateDocument")
@Document(collection = "update")
@CompoundIndexes(
        {
                @CompoundIndex(def = "{'complaint.id' : 1}"),
        }
)
@Data
public class UpdateDocument {
    @Id
    private Long id;
    private EntryStatus entryStatus;
    private Complaint complaint;
    private UserAction userAction;
    private Officer officer;
    private ComplaintStatus oldComplaintStatus;
    private ComplaintStatus newComplaintStatus;
    private VerificationStatus oldVerificationStatus;
    private VerificationStatus newVerificationStatus;
    private ReviewStatus oldReviewStatus;
    private ReviewStatus newReviewStatus;
    private Department oldDepartment;
    private Department newDepartment;
    private Category oldCategory;
    private Category newCategory;
    private Date updatedOn;
    private String comment;
}
