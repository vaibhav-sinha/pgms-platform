package com.pgms.shared.pojo;

import com.pgms.shared.model.ComplaintStatus;
import lombok.Data;

import java.util.Date;

/**
 * Created by user-1 on 8/7/15.
 */
@Data
public class PgmsComplaintFilter {
    private Long departmentId;
    private Long categoryId;
    private Long locationId;
    private Integer page = 0;
    private Integer pageSize = 20;
    private Date createdAfter;
    private Date updatedAfter;
    private Long complaintStatusId;
    private Boolean reopened;
}
