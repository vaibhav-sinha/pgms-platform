package com.pgms.service.impl;

import com.pgms.service.api.UpdateService;
import com.pgms.service.entity.UpdateEntity;
import com.pgms.service.repository.UpdateRepository;
import com.pgms.shared.model.Update;
import com.pgms.shared.util.Mapper;
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

    @Autowired
    Mapper mapper;

    @Override
    public Update saveUpdate(Update update) {
        UpdateEntity updateEntity = mapper.map(update, UpdateEntity.class);
        return mapper.map(updateRepository.save(updateEntity), Update.class);
    }

    @Override
    public Update getUpdate(Long id) {
        return mapper.map(updateRepository.findOne(id), Update.class);
    }

    @Override
    public List<Update> getAllUpdateForComplaint(Long id) {
        return mapper.mapAsList(updateRepository.findUpdatesByComplaintId(id), Update.class);
    }
}
