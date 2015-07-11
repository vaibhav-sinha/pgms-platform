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
@TypeAlias("ComplaintDocument")
@Document(collection = "complaint")
@CompoundIndexes(
        {
                @CompoundIndex(def = "{'department.id' : 1, 'complaintStatus' : 1, 'createdOn': 1}"),
                @CompoundIndex(def = "{'department.id' : 1, 'complaintStatus' : 1, 'updatedOn': 1}"),
                @CompoundIndex(def = "{'location.id' : 1, 'complaintStatus' : 1, 'createdOn': 1}"),
                @CompoundIndex(def = "{'location.id' : 1, 'complaintStatus' : 1, 'updatedOn': 1}")
        }
)
@Data
public class ComplaintDocument {
    @Id
    private Long id;
    private EntryStatus entryStatus;
    private Department department;
    private ComplaintStatus complaintStatus;
    private String description;
    private Date createdOn;
    private Date updatedOn;
    private Submitter submitter;
    private Location location;
    private Date targetDate;
    private Date disposalDate;
    private Category category;
    private boolean urgent;
    private boolean reopened;
}
