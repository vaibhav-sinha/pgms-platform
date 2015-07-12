package com.pgms.service.api;

import com.pgms.shared.model.ReviewStatus;

import java.util.List;

/**
 * Created by user-1 on 12/7/15.
 */
public interface ReviewStatusService {

    ReviewStatus saveReviewStatus(ReviewStatus reviewStatus);
    ReviewStatus getReviewStatus(Long id);
    ReviewStatus getDefaultReviewStatus();
    ReviewStatus getClosedReviewStatus();
    ReviewStatus getReopenReviewStatus();
    List<ReviewStatus> getAllReviewStatus();
    List<ReviewStatus> getAllActiveReviewStatus();
}
