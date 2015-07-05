package com.pgms.shared.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by user-1 on 27/6/15.
 */
@Data
@Entity
public class Submitter {

    @GeneratedValue
    @Id
    private Long id;
    private String name;
    private String email;
    private String mobile;
    private String address;
    private String pincode;
}
