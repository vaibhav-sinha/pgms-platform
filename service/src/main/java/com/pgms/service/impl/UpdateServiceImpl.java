package com.pgms.service.impl;

import com.pgms.service.api.UpdateService;
import com.pgms.service.document.UpdateDocument;
import com.pgms.service.entity.UpdateEntity;
import com.pgms.service.repository.MongoUpdateRepository;
import com.pgms.service.repository.UpdateRepository;
import com.pgms.shared.model.Update;
import com.pgms.shared.pojo.PgmsUpdateFilter;
import com.pgms.shared.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
    MongoUpdateRepository mongoUpdateRepository;

    @Autowired
    MongoTemplate mongoTemplate;

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

    @Override
    public List<Update> getUpdatesForFilter(PgmsUpdateFilter pgmsUpdateFilter) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        if(pgmsUpdateFilter.getComplaintId() != null) {
            criteria.and("update.complaint.id").is(pgmsUpdateFilter.getComplaintId());
        }
        if(pgmsUpdateFilter.getOfficerId() != null) {
            criteria.and("update.officer.id").is(pgmsUpdateFilter.getOfficerId());
        }
        query.addCriteria(criteria);
        return mapper.mapAsList(mongoTemplate.find(query, UpdateDocument.class), Update.class);
    }

    @Override
    public Long getUpdatesCountForFilter(PgmsUpdateFilter pgmsUpdateFilter) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        if(pgmsUpdateFilter.getComplaintId() != null) {
            criteria.and("update.complaint.id").is(pgmsUpdateFilter.getComplaintId());
        }
        if(pgmsUpdateFilter.getOfficerId() != null) {
            criteria.and("update.officer.id").is(pgmsUpdateFilter.getOfficerId());
        }
        query.addCriteria(criteria);
        return mongoTemplate.count(query, UpdateDocument.class);
    }
}
