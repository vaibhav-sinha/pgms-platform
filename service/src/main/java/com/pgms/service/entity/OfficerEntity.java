package com.pgms.service.entity;

import com.pgms.shared.model.AccountStatus;
import com.pgms.shared.model.EntryStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by user-1 on 27/6/15.
 */
@Data
@Entity
@Table(name = "officer")
public class OfficerEntity {

    @GeneratedValue
    @Id
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private EntryStatus entryStatus;

    private String name;

    @ManyToOne
    @JoinColumn
    private DesignationEntity designation;

    @ManyToOne
    @JoinColumn
    private DepartmentEntity department;

    private String username;
    private String encryptedPassword;
    private String salt;
    private String address;
    private String mobile;
    private String photoUrl;
    private Date lastSignedIn;

    @Enumerated(value = EnumType.STRING)
    private AccountStatus accountStatus;

    private String role;
}
