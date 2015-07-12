package com.pgms.service.impl;

import com.pgms.service.api.ComplaintService;
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
        Query query = new Query();
        Criteria criteria = new Criteria();
        if(pgmsComplaintFilter.getDepartmentId() != null) {
            criteria.and("complaint.department.id").is(pgmsComplaintFilter.getDepartmentId());
        }
        if(pgmsComplaintFilter.getCategoryId() != null) {
            criteria.and("complaint.category.id").is(pgmsComplaintFilter.getCategoryId());
        }
        if(pgmsComplaintFilter.getLocationId() != null) {
            criteria.and("complaint.location.id").is(pgmsComplaintFilter.getLocationId());
        }
        if(pgmsComplaintFilter.getUpdatedAfter() != null) {
            criteria.and("complaint.updatedOn").gt(pgmsComplaintFilter.getUpdatedAfter());
        }
        else if(pgmsComplaintFilter.getCreatedAfter() != null) {
            criteria.and("complaint.createdOn").gt(pgmsComplaintFilter.getCreatedAfter());
        }
        if(pgmsComplaintFilter.getComplaintStatusId() != null) {
            criteria.and("complaint.complaintStatus.id").is(pgmsComplaintFilter.getComplaintStatusId());
        }
        if(pgmsComplaintFilter.getReopened() != null) {
            criteria.and("complaint.reopened").is(pgmsComplaintFilter.getReopened());
        }

        query.addCriteria(criteria);

        Pageable pageable = new PageRequest(pgmsComplaintFilter.getPage(), pgmsComplaintFilter.getPageSize(), Sort.Direction.DESC);
        query.with(pageable);

        return mapper.mapAsList(mongoTemplate.find(query, ComplaintDocument.class), Complaint.class);
    }

    @Override
    public Long getComplaintsCountForFilter(PgmsComplaintFilter pgmsComplaintFilter) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        if(pgmsComplaintFilter.getDepartmentId() != null) {
            criteria.and("complaint.department.id").is(pgmsComplaintFilter.getDepartmentId());
        }
        if(pgmsComplaintFilter.getCategoryId() != null) {
            criteria.and("complaint.category.id").is(pgmsComplaintFilter.getCategoryId());
        }
        if(pgmsComplaintFilter.getLocationId() != null) {
            criteria.and("complaint.location.id").is(pgmsComplaintFilter.getLocationId());
        }
        if(pgmsComplaintFilter.getUpdatedAfter() != null) {
            criteria.and("complaint.updatedOn").gt(pgmsComplaintFilter.getUpdatedAfter());
        }
        else if(pgmsComplaintFilter.getCreatedAfter() != null) {
            criteria.and("complaint.createdOn").gt(pgmsComplaintFilter.getCreatedAfter());
        }
        if(pgmsComplaintFilter.getComplaintStatusId() != null) {
            criteria.and("complaint.complaintStatus.id").is(pgmsComplaintFilter.getComplaintStatusId());
        }
        if(pgmsComplaintFilter.getReopened() != null) {
            criteria.and("complaint.reopened").is(pgmsComplaintFilter.getReopened());
        }

        query.addCriteria(criteria);
        return mongoTemplate.count(query, ComplaintDocument.class);
    }
/*@Override
    public List<Complaint> getComplaintsForFilter(PgmsComplaintFilter pgmsComplaintFilter) {
        //For createdAfter
        if(pgmsComplaintFilter.getUpdatedAfter() == null) {
            if (pgmsComplaintFilter.getDepartmentId() != null &&
                    pgmsComplaintFilter.getCreatedAfter() != null &&
                    pgmsComplaintFilter.getComplaintStatus() != null &&
                    pgmsComplaintFilter.getReopened() != null) {

            } else if (pgmsComplaintFilter.getDepartmentId() != null &&
                    pgmsComplaintFilter.getCreatedAfter() != null &&
                    pgmsComplaintFilter.getComplaintStatus() != null &&
                    pgmsComplaintFilter.getReopened() == null) {

            } else if (pgmsComplaintFilter.getDepartmentId() != null &&
                    pgmsComplaintFilter.getCreatedAfter() != null &&
                    pgmsComplaintFilter.getComplaintStatus() == null &&
                    pgmsComplaintFilter.getReopened() != null) {

            } else if (pgmsComplaintFilter.getDepartmentId() != null &&
                    pgmsComplaintFilter.getCreatedAfter() == null &&
                    pgmsComplaintFilter.getComplaintStatus() != null &&
                    pgmsComplaintFilter.getReopened() != null) {

            } else if (pgmsComplaintFilter.getDepartmentId() == null &&
                    pgmsComplaintFilter.getCreatedAfter() != null &&
                    pgmsComplaintFilter.getComplaintStatus() != null &&
                    pgmsComplaintFilter.getReopened() != null) {

            } else if (pgmsComplaintFilter.getDepartmentId() != null &&
                    pgmsComplaintFilter.getCreatedAfter() != null &&
                    pgmsComplaintFilter.getComplaintStatus() == null &&
                    pgmsComplaintFilter.getReopened() == null) {

            } else if (pgmsComplaintFilter.getDepartmentId() != null &&
                    pgmsComplaintFilter.getCreatedAfter() == null &&
                    pgmsComplaintFilter.getComplaintStatus() != null &&
                    pgmsComplaintFilter.getReopened() == null) {

            } else if (pgmsComplaintFilter.getDepartmentId() == null &&
                    pgmsComplaintFilter.getCreatedAfter() != null &&
                    pgmsComplaintFilter.getComplaintStatus() != null &&
                    pgmsComplaintFilter.getReopened() == null) {

            } else if (pgmsComplaintFilter.getDepartmentId() != null &&
                    pgmsComplaintFilter.getCreatedAfter() == null &&
                    pgmsComplaintFilter.getComplaintStatus() == null &&
                    pgmsComplaintFilter.getReopened() != null) {

            } else if (pgmsComplaintFilter.getDepartmentId() == null &&
                    pgmsComplaintFilter.getCreatedAfter() != null &&
                    pgmsComplaintFilter.getComplaintStatus() == null &&
                    pgmsComplaintFilter.getReopened() != null) {

            } else if (pgmsComplaintFilter.getDepartmentId() == null &&
                    pgmsComplaintFilter.getCreatedAfter() == null &&
                    pgmsComplaintFilter.getComplaintStatus() != null &&
                    pgmsComplaintFilter.getReopened() != null) {

            } else if (pgmsComplaintFilter.getDepartmentId() != null &&
                    pgmsComplaintFilter.getCreatedAfter() == null &&
                    pgmsComplaintFilter.getComplaintStatus() == null &&
                    pgmsComplaintFilter.getReopened() == null) {

            } else if (pgmsComplaintFilter.getDepartmentId() == null &&
                    pgmsComplaintFilter.getCreatedAfter() != null &&
                    pgmsComplaintFilter.getComplaintStatus() == null &&
                    pgmsComplaintFilter.getReopened() == null) {

            } else if (pgmsComplaintFilter.getDepartmentId() == null &&
                    pgmsComplaintFilter.getCreatedAfter() == null &&
                    pgmsComplaintFilter.getComplaintStatus() != null &&
                    pgmsComplaintFilter.getReopened() == null) {

            } else if (pgmsComplaintFilter.getDepartmentId() == null &&
                    pgmsComplaintFilter.getCreatedAfter() == null &&
                    pgmsComplaintFilter.getComplaintStatus() == null &&
                    pgmsComplaintFilter.getReopened() != null) {

            } else if (pgmsComplaintFilter.getDepartmentId() == null &&
                    pgmsComplaintFilter.getCreatedAfter() == null &&
                    pgmsComplaintFilter.getComplaintStatus() == null &&
                    pgmsComplaintFilter.getReopened() == null) {

            }
        }
        
        //For updatedAfter
        if(pgmsComplaintFilter.getCreatedAfter() == null) {
            if (pgmsComplaintFilter.getDepartmentId() != null &&
                    pgmsComplaintFilter.getUpdatedAfter() != null &&
                    pgmsComplaintFilter.getComplaintStatus() != null &&
                    pgmsComplaintFilter.getReopened() != null) {

            } else if (pgmsComplaintFilter.getDepartmentId() != null &&
                    pgmsComplaintFilter.getUpdatedAfter() != null &&
                    pgmsComplaintFilter.getComplaintStatus() != null &&
                    pgmsComplaintFilter.getReopened() == null) {

            } else if (pgmsComplaintFilter.getDepartmentId() != null &&
                    pgmsComplaintFilter.getUpdatedAfter() != null &&
                    pgmsComplaintFilter.getComplaintStatus() == null &&
                    pgmsComplaintFilter.getReopened() != null) {

            } else if (pgmsComplaintFilter.getDepartmentId() != null &&
                    pgmsComplaintFilter.getUpdatedAfter() == null &&
                    pgmsComplaintFilter.getComplaintStatus() != null &&
                    pgmsComplaintFilter.getReopened() != null) {

            } else if (pgmsComplaintFilter.getDepartmentId() == null &&
                    pgmsComplaintFilter.getUpdatedAfter() != null &&
                    pgmsComplaintFilter.getComplaintStatus() != null &&
                    pgmsComplaintFilter.getReopened() != null) {

            } else if (pgmsComplaintFilter.getDepartmentId() != null &&
                    pgmsComplaintFilter.getUpdatedAfter() != null &&
                    pgmsComplaintFilter.getComplaintStatus() == null &&
                    pgmsComplaintFilter.getReopened() == null) {

            } else if (pgmsComplaintFilter.getDepartmentId() != null &&
                    pgmsComplaintFilter.getUpdatedAfter() == null &&
                    pgmsComplaintFilter.getComplaintStatus() != null &&
                    pgmsComplaintFilter.getReopened() == null) {

            } else if (pgmsComplaintFilter.getDepartmentId() == null &&
                    pgmsComplaintFilter.getUpdatedAfter() != null &&
                    pgmsComplaintFilter.getComplaintStatus() != null &&
                    pgmsComplaintFilter.getReopened() == null) {

            } else if (pgmsComplaintFilter.getDepartmentId() != null &&
                    pgmsComplaintFilter.getUpdatedAfter() == null &&
                    pgmsComplaintFilter.getComplaintStatus() == null &&
                    pgmsComplaintFilter.getReopened() != null) {

            } else if (pgmsComplaintFilter.getDepartmentId() == null &&
                    pgmsComplaintFilter.getUpdatedAfter() != null &&
                    pgmsComplaintFilter.getComplaintStatus() == null &&
                    pgmsComplaintFilter.getReopened() != null) {

            } else if (pgmsComplaintFilter.getDepartmentId() == null &&
                    pgmsComplaintFilter.getUpdatedAfter() == null &&
                    pgmsComplaintFilter.getComplaintStatus() != null &&
                    pgmsComplaintFilter.getReopened() != null) {

            } else if (pgmsComplaintFilter.getDepartmentId() != null &&
                    pgmsComplaintFilter.getUpdatedAfter() == null &&
                    pgmsComplaintFilter.getComplaintStatus() == null &&
                    pgmsComplaintFilter.getReopened() == null) {

            } else if (pgmsComplaintFilter.getDepartmentId() == null &&
                    pgmsComplaintFilter.getUpdatedAfter() != null &&
                    pgmsComplaintFilter.getComplaintStatus() == null &&
                    pgmsComplaintFilter.getReopened() == null) {

            } else if (pgmsComplaintFilter.getDepartmentId() == null &&
                    pgmsComplaintFilter.getUpdatedAfter() == null &&
                    pgmsComplaintFilter.getComplaintStatus() != null &&
                    pgmsComplaintFilter.getReopened() == null) {

            } else if (pgmsComplaintFilter.getDepartmentId() == null &&
                    pgmsComplaintFilter.getUpdatedAfter() == null &&
                    pgmsComplaintFilter.getComplaintStatus() == null &&
                    pgmsComplaintFilter.getReopened() != null) {

            } else if (pgmsComplaintFilter.getDepartmentId() == null &&
                    pgmsComplaintFilter.getUpdatedAfter() == null &&
                    pgmsComplaintFilter.getComplaintStatus() == null &&
                    pgmsComplaintFilter.getReopened() == null) {

            }
        }

        return null;
    }*/
}
