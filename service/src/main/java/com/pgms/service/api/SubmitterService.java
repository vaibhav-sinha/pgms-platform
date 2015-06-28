package com.pgms.service.api;

import com.pgms.shared.model.Submitter;

import java.util.List;

/**
 * Created by user-1 on 28/6/15.
 */
public interface SubmitterService {

    Submitter saveSubmitter(Submitter submitter);
    Submitter getSubmitter(Long id);
    List<Submitter> getAllSubmitter();
    Submitter getSubmitterByEmailOrMobile(String email, String mobile);
}
