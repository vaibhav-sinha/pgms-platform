package com.pgms.shared.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by user-1 on 27/6/15.
 */
@Data
public class ComplaintStatus {
    private Long id;
    private EntryStatus entryStatus;
    private String name;
    private Boolean officerCanAccess;
    private Boolean callCentreCanAccess;
    private Boolean cmoCanAccess;
    private Boolean representsClosed;
}
