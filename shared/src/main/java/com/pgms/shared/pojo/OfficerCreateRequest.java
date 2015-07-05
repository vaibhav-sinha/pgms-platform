package com.pgms.shared.pojo;

import com.pgms.shared.model.Department;
import com.pgms.shared.model.Designation;
import lombok.Data;


/**
 * Created by user-1 on 5/7/15.
 */
@Data
public class OfficerCreateRequest {
    private String name;
    private Designation designation;
    private Department department;
    private String username;
    private String password;
    private String address;
    private String mobile;
    private String photoUrl;
    private String role;
}
