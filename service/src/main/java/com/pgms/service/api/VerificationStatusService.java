package com.pgms.service.api;

import com.pgms.shared.model.VerificationStatus;

import java.util.List;

/**
 * Created by user-1 on 12/7/15.
 */
public interface VerificationStatusService {

    VerificationStatus saveVerificationStatus(VerificationStatus verificationStatus);
    VerificationStatus getVerificationStatus(Long id);
    VerificationStatus getDefaultVerificationStatus();
    VerificationStatus getClosedVerificationStatus();
    List<VerificationStatus> getAllVerificationStatus();
    List<VerificationStatus> getAllActiveVerificationStatus();
}
