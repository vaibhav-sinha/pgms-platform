package com.pgms.service.impl;

import com.pgms.service.api.VerificationStatusService;
import com.pgms.service.entity.VerificationStatusEntity;
import com.pgms.service.repository.VerificationStatusRepository;
import com.pgms.shared.model.VerificationStatus;
import com.pgms.shared.model.EntryStatus;
import com.pgms.shared.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by user-1 on 12/7/15.
 */
@Component
public class VerificationStatusServiceImpl implements VerificationStatusService {

    @Autowired
    VerificationStatusRepository verificationStatusRepository;

    @Autowired
    Mapper mapper;

    @Override
    public VerificationStatus saveVerificationStatus(VerificationStatus verificationStatus) {
        VerificationStatusEntity verificationStatusEntity = mapper.map(verificationStatus, VerificationStatusEntity.class);
        return mapper.map(verificationStatusRepository.save(verificationStatusEntity), VerificationStatus.class);
    }

    @Override
    public VerificationStatus getVerificationStatus(Long id) {
        return mapper.map(verificationStatusRepository.findOne(id), VerificationStatus.class);
    }

    @Override
    public VerificationStatus getDefaultVerificationStatus() {
        return mapper.map(verificationStatusRepository.findByRepresentsDefault(true).get(0), VerificationStatus.class);
    }

    @Override
    public VerificationStatus getClosedVerificationStatus() {
        VerificationStatusEntity verificationStatusEntity = verificationStatusRepository.findByRepresentsClosed(true).get(0);
        return mapper.map(verificationStatusEntity, VerificationStatus.class);
    }

    @Override
    public List<VerificationStatus> getAllVerificationStatus() {
        return mapper.mapAsList(verificationStatusRepository.findAll(), VerificationStatus.class);
    }

    @Override
    public List<VerificationStatus> getAllActiveVerificationStatus() {
        return mapper.mapAsList(verificationStatusRepository.findByEntryStatus(EntryStatus.ACTIVE), VerificationStatus.class);
    }

}
