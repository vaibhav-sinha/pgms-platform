package com.pgms.service.impl;

import com.pgms.service.api.ComplaintService;
import com.pgms.service.api.ComplaintStatusService;
import com.pgms.service.api.ReviewStatusService;
import com.pgms.service.api.VerificationStatusService;
import com.pgms.service.document.ComplaintDocument;
import com.pgms.service.entity.ComplaintEntity;
import com.pgms.service.repository.ComplaintRepository;
import com.pgms.service.repository.MongoComplaintRepository;
import com.pgms.shared.model.Complaint;
import com.pgms.shared.pojo.PgmsComplaintFilter;
import com.pgms.shared.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user-1 on 28/6/15.
 */
@Component
public class ComplaintServiceImpl implements ComplaintService {

    @Autowired
    ComplaintRepository complaintRepository;

    @Autowired
    MongoComplaintRepository mongoComplaintRepository;

    @Autowired
    Mapper mapper;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    ComplaintStatusService complaintStatusService;

    @Autowired
    VerificationStatusService verificationStatusService;

    @Autowired
    ReviewStatusService reviewStatusService;

    @Override
    public Complaint saveComplaint(Complaint complaint) {
        ComplaintEntity complaintEntity = mapper.map(complaint, ComplaintEntity.class);
        Complaint savedComplaint = mapper.map(complaintRepository.save(complaintEntity), Complaint.class);
        mongoComplaintRepository.save(mapper.map(savedComplaint, ComplaintDocument.class));
        return savedComplaint;
    }

    @Override
    public Complaint findComplaintById(Long id) {
        return mapper.map(complaintRepository.findOne(id), Complaint.class);
    }

    @Override
    public List<Complaint> findComplaintsForDepartment(Long id) {
        return mapper.mapAsList(complaintRepository.findComplaintsByDepartmentId(id), Complaint.class);
    }

    @Override
    public List<Complaint> getComplaintsForFilter(PgmsComplaintFilter pgmsComplaintFilter) {
        Query query = buildQuery(pgmsComplaintFilter);
        Pageable pageable = new PageRequest(pgmsComplaintFilter.getPage(), pgmsComplaintFilter.getPageSize());
        query.with(pageable);
        return mapper.mapAsList(mongoTemplate.find(query, ComplaintDocument.class), Complaint.class);
    }

    @Override
    public Long getComplaintsCountForFilter(PgmsComplaintFilter pgmsComplaintFilter) {
        return mongoTemplate.count(buildQuery(pgmsComplaintFilter), ComplaintDocument.class);
    }

    private Query buildQuery(PgmsComplaintFilter pgmsComplaintFilter) {
        Query query = new Query();
        List<Criteria> criteriaList = new ArrayList<>();
        Criteria orCriteria = new Criteria();
        Criteria andCriteria = new Criteria();

        if(pgmsComplaintFilter.getDepartmentId() != null) {
            criteriaList.add(Criteria.where("department._id").is(pgmsComplaintFilter.getDepartmentId()));
        }
        if(pgmsComplaintFilter.getCategoryId() != null) {
            criteriaList.add(Criteria.where("category._id").is(pgmsComplaintFilter.getCategoryId()));
        }
        if(pgmsComplaintFilter.getLocationId() != null) {
            criteriaList.add(Criteria.where("location._id").is(pgmsComplaintFilter.getLocationId()));
        }
        if(pgmsComplaintFilter.getUpdatedAfter() != null) {
            criteriaList.add(Criteria.where("updatedOn").gt(pgmsComplaintFilter.getUpdatedAfter()));
        }
        else if(pgmsComplaintFilter.getCreatedAfter() != null) {
            criteriaList.add(Criteria.where("createdOn").gt(pgmsComplaintFilter.getCreatedAfter()));
        }
        if(pgmsComplaintFilter.getComplaintStatusId() != null) {
            criteriaList.add(Criteria.where("complaintStatus._id").is(pgmsComplaintFilter.getComplaintStatusId()));
        }
        if(pgmsComplaintFilter.getVerificationStatusId() != null) {
            criteriaList.add(Criteria.where("verificationStatus._id").is(pgmsComplaintFilter.getVerificationStatusId()));
        }
        if(pgmsComplaintFilter.getReviewStatusId() != null) {
            criteriaList.add(Criteria.where("reviewStatus._id").is(pgmsComplaintFilter.getReviewStatusId()));
        }
        if(pgmsComplaintFilter.getReopened() != null) {
            criteriaList.add(Criteria.where("reopened").is(pgmsComplaintFilter.getReopened()));
        }
        if(pgmsComplaintFilter.getIsUrgent() != null) {
            criteriaList.add(Criteria.where("urgent").is(pgmsComplaintFilter.getIsUrgent()));
            if(pgmsComplaintFilter.getUserRole().equals("ROLE_OFFICER")) {
                criteriaList.add(Criteria.where("complaintStatus._id").ne(complaintStatusService.getClosedComplaintStatus().getId()));
            }
            if(pgmsComplaintFilter.getUserRole().equals("ROLE_CALL_CENTRE")) {
                criteriaList.add(Criteria.where("verificationStatus._id").ne(verificationStatusService.getClosedVerificationStatus().getId()).and("verificationStatus").ne(null));
            }
            if(pgmsComplaintFilter.getUserRole().equals("ROLE_CMO")) {
                criteriaList.add(Criteria.where("reviewStatus._id").ne(reviewStatusService.getClosedReviewStatus().getId()).and("reviewStatus").ne(null));
            }
        }
        if(pgmsComplaintFilter.getPhase() != null) {
            if(pgmsComplaintFilter.getPhase().equals("complaint")) {
                criteriaList.add(Criteria.where("complaintStatus._id").ne(complaintStatusService.getClosedComplaintStatus().getId()));
            }
            if(pgmsComplaintFilter.getPhase().equals("verification")) {
                criteriaList.add(Criteria.where("verificationStatus._id").ne(verificationStatusService.getClosedVerificationStatus().getId()).and("verificationStatus").ne(null));
            }
            if(pgmsComplaintFilter.getPhase().equals("review")) {
                criteriaList.add(Criteria.where("reviewStatus.id").ne(reviewStatusService.getClosedReviewStatus().getId()).and("reviewStatus").ne(null));
            }
        }
        if(pgmsComplaintFilter.getSearchText() != null) {
            String searchText = pgmsComplaintFilter.getSearchText();
            orCriteria.orOperator(
                    Criteria.where("submitter.name").regex(searchText, "i"),
                    Criteria.where("submitter.email").regex(searchText, "i"),
                    Criteria.where("submitter.mobile").regex(searchText, "i"),
                    Criteria.where("id").regex(searchText, "i"),
                    Criteria.where("description").regex(searchText, "i")
            );
            criteriaList.add(orCriteria);
        }

        if(criteriaList.size() > 0) {
            Criteria[] criteriaArray = new Criteria[criteriaList.size()];
            for(int i=0; i<criteriaList.size(); i++) {
                criteriaArray[i] = criteriaList.get(i);
            }
            andCriteria.andOperator(criteriaArray);
            query.addCriteria(andCriteria);
        }

        return query;
    }
}
