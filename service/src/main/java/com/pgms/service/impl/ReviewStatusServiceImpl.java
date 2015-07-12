package com.pgms.service.impl;

import com.pgms.service.api.ReviewStatusService;
import com.pgms.service.entity.ReviewStatusEntity;
import com.pgms.service.repository.ReviewStatusRepository;
import com.pgms.shared.model.ReviewStatus;
import com.pgms.shared.model.EntryStatus;
import com.pgms.shared.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by user-1 on 12/7/15.
 */
@Component
public class ReviewStatusServiceImpl implements ReviewStatusService {

    @Autowired
    ReviewStatusRepository reviewStatusRepository;

    @Autowired
    Mapper mapper;

    @Override
    public ReviewStatus saveReviewStatus(ReviewStatus reviewStatus) {
        ReviewStatusEntity reviewStatusEntity = mapper.map(reviewStatus, ReviewStatusEntity.class);
        return mapper.map(reviewStatusRepository.save(reviewStatusEntity), ReviewStatus.class);
    }

    @Override
    public ReviewStatus getReviewStatus(Long id) {
        return mapper.map(reviewStatusRepository.findOne(id), ReviewStatus.class);
    }

    @Override
    public ReviewStatus getDefaultReviewStatus() {
        return mapper.map(reviewStatusRepository.findByRepresentsDefault(true).get(0), ReviewStatus.class);
    }

    @Override
    public ReviewStatus getClosedReviewStatus() {
        ReviewStatusEntity reviewStatusEntity = reviewStatusRepository.findByRepresentsClosed(true).get(0);
        return mapper.map(reviewStatusEntity, ReviewStatus.class);
    }

    @Override
    public ReviewStatus getReopenReviewStatus() {
        return mapper.map(reviewStatusRepository.findByRepresentsReopen(true).get(0), ReviewStatus.class);
    }

    @Override
    public List<ReviewStatus> getAllReviewStatus() {
        return mapper.mapAsList(reviewStatusRepository.findAll(), ReviewStatus.class);
    }

    @Override
    public List<ReviewStatus> getAllActiveReviewStatus() {
        return mapper.mapAsList(reviewStatusRepository.findByEntryStatus(EntryStatus.ACTIVE), ReviewStatus.class);
    }

}
