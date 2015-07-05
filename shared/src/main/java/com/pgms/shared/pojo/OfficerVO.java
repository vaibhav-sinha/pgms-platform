package com.pgms.shared.pojo;

import com.pgms.shared.model.AccountStatus;
import com.pgms.shared.model.Department;
import com.pgms.shared.model.Designation;
import com.pgms.shared.model.EntryStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by user-1 on 5/7/15.
 */
@Data
public class OfficerVO {
    private Long id;
    private EntryStatus entryStatus;
    private String name;
    private Designation designation;
    private Department department;
    private String username;
    private String address;
    private String mobile;
    private String photoUrl;
    private Date lastSignedIn;
    private AccountStatus accountStatus;
    private String role;
}
