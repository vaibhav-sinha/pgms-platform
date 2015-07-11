package com.pgms.service.repository;

import com.pgms.service.document.UpdateDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by user-1 on 11/7/15.
 */
public interface MongoUpdateRepository extends MongoRepository<UpdateDocument, Long> {
}
