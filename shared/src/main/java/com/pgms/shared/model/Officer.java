package com.pgms.shared.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by user-1 on 27/6/15.
 */
@Data
@Entity
public class Officer {

    @GeneratedValue
    @Id
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private EntryStatus entryStatus;
    private String name;
    @ManyToOne
    @JoinColumn
    private Designation designation;
    @ManyToOne
    @JoinColumn
    private Department department;
    private String username;
    private String encryptedPassword;
    private String salt;
    private String address;
    private String mobile;
    private String photoUrl;
    private Date lastSignedIn;
    private AccountStatus accountStatus;
    private String role;
}
