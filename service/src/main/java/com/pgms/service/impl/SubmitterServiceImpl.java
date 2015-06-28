package com.pgms.service.impl;

import com.pgms.service.api.SubmitterService;
import com.pgms.service.repository.SubmitterRepository;
import com.pgms.shared.model.Submitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by user-1 on 28/6/15.
 */
@Component
public class SubmitterServiceImpl implements SubmitterService {

    @Autowired
    SubmitterRepository submitterRepository;

    @Override
    public Submitter saveSubmitter(Submitter submitter) {
        return submitterRepository.save(submitter);
    }

    @Override
    public Submitter getSubmitter(Long id) {
        return submitterRepository.findOne(id);
    }

    @Override
    public List<Submitter> getAllSubmitter() {
        return submitterRepository.findAll();
    }

    @Override
    public Submitter getSubmitterByEmailOrMobile(String email, String mobile) {
        Submitter submitter = null;
        if(mobile != null) {
            submitter = submitterRepository.findByMobile(mobile);
            if(submitter != null) {
                return submitter;
            }
        }
        if(email != null) {
            submitter = submitterRepository.findByEmail(email);
        }
        return submitter;
    }
}
