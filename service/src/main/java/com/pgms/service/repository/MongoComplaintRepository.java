package com.pgms.service.repository;

import com.pgms.service.document.ComplaintDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by user-1 on 11/7/15.
 */
public interface MongoComplaintRepository extends MongoRepository<ComplaintDocument, Long> {
}
