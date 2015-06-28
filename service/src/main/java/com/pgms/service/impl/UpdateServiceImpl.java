package com.pgms.service.impl;

import com.pgms.service.api.UpdateService;
import com.pgms.service.repository.UpdateRepository;
import com.pgms.shared.model.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by user-1 on 28/6/15.
 */
@Component
public class UpdateServiceImpl implements UpdateService {

    @Autowired
    UpdateRepository updateRepository;

    @Override
    public Update saveUpdate(Update update) {
        return updateRepository.save(update);
    }

    @Override
    public Update getUpdate(Long id) {
        return updateRepository.findOne(id);
    }

    @Override
    public List<Update> getAllUpdateForComplaint(Long id) {
        return updateRepository.findUpdatesByComplaintId(id);
    }
}
