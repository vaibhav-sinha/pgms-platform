package com.pgms.service.impl;

import com.pgms.service.api.SubmitterService;
import com.pgms.service.entity.SubmitterEntity;
import com.pgms.service.repository.SubmitterRepository;
import com.pgms.shared.model.Submitter;
import com.pgms.shared.util.Mapper;
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

    @Autowired
    Mapper mapper;

    @Override
    public Submitter saveSubmitter(Submitter submitter) {
        SubmitterEntity submitterEntity = mapper.map(submitter, SubmitterEntity.class);
        return mapper.map(submitterRepository.save(submitterEntity), Submitter.class);
    }

    @Override
    public Submitter getSubmitter(Long id) {
        return mapper.map(submitterRepository.findOne(id), Submitter.class);
    }

    @Override
    public List<Submitter> getAllSubmitter() {
        return mapper.mapAsList(submitterRepository.findAll(), Submitter.class);
    }

    @Override
    public Submitter getSubmitterByEmailOrMobile(String email, String mobile) {
        Submitter submitter = null;
        if(mobile != null) {
            submitter = mapper.map(submitterRepository.findByMobile(mobile), Submitter.class);
            if(submitter != null) {
                return submitter;
            }
        }
        if(email != null) {
            submitter = mapper.map(submitterRepository.findByEmail(email), Submitter.class);
        }
        return submitter;
    }
}
