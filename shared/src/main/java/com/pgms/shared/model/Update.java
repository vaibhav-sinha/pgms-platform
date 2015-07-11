package com.pgms.shared.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by user-1 on 27/6/15.
 */
@Data
public class Update {
    private Long id;
    private EntryStatus entryStatus;
    private Complaint complaint;
    private UserAction userAction;
    private Officer officer;
    private ComplaintStatus oldStatus;
    private ComplaintStatus newStatus;
    private Department oldDepartment;
    private Department newDepartment;
    private Category oldCategory;
    private Category newCategory;
    private Date updatedOn;
    private String comment;
}
