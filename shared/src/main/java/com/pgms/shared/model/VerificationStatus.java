package com.pgms.shared.model;

import lombok.Data;

/**
 * Created by user-1 on 12/7/15.
 */
@Data
public class VerificationStatus {
    private Long id;
    private EntryStatus entryStatus;
    private String name;
    private Boolean representsClosed;
    private Boolean representsDefault;
}
