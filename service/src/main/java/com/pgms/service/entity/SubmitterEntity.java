package com.pgms.service.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by user-1 on 27/6/15.
 */
@Data
@Entity
@Table(name = "submitter")
public class SubmitterEntity {

    @GeneratedValue
    @Id
    private Long id;

    private String name;
    private String email;
    private String mobile;
    private String address;
    private String pincode;
}
