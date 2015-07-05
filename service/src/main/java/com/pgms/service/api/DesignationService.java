package com.pgms.service.api;

import com.pgms.shared.model.Designation;
import sun.security.krb5.internal.crypto.Des;

import java.util.List;

/**
 * Created by user-1 on 28/6/15.
 */
public interface DesignationService {

    Designation saveDesignation(Designation designation);
    Designation getDesignation(Long id);
    List<Designation> getAllDesignation();
    List<Designation> getAllActiveDesignation();
}
