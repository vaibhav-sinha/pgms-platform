package com.pgms.shared.pojo;

import com.pgms.shared.model.Category;
import com.pgms.shared.model.Department;
import com.pgms.shared.model.Location;
import lombok.Data;

/**
 * Created by user-1 on 28/6/15.
 */
@Data
public class ComplaintSubmitRequest {
    private String name;
    private String email;
    private String mobile;
    private String address;
    private String pincode;

    private Department department;
    private String description;
    private Location location;
    private Category category;
    private Boolean urgent;
}
