package com.pgms.admin.controller;

import com.pgms.service.api.ReviewStatusService;
import com.pgms.shared.model.ReviewStatus;
import com.pgms.shared.model.EntryStatus;
import com.pgms.shared.pojo.PgmsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by user-1 on 28/6/15.
 */
@RestController
@RequestMapping("/api/reviewStatus")
public class ReviewStatusController {

    @Autowired
    ReviewStatusService reviewStatusService;

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public PgmsResponse<ReviewStatus> getReviewStatus(@PathVariable Long id) {
        PgmsResponse<ReviewStatus> pgmsResponse = new PgmsResponse<>();
        if(id == null) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage("Failed to get reviewStatus. ReviewStatus id is null");
            return pgmsResponse;
        }
        try {
            ReviewStatus reviewStatus = reviewStatusService.getReviewStatus(id);
            if(reviewStatus == null) {
                pgmsResponse.setSuccess(false);
                pgmsResponse.setMessage("Failed to get reviewStatus. ReviewStatus with id = " + id + " does not exist");
            }
            else {
                pgmsResponse.setData(reviewStatus);
                pgmsResponse.setSuccess(true);
                pgmsResponse.setMessage("Successfully got reviewStatus with ID " + id);
            }
        }
        catch (Exception e) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage(e.getMessage());
        }
        return pgmsResponse;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public PgmsResponse<ReviewStatus> saveReviewStatus(@RequestBody ReviewStatus reviewStatus) {
        PgmsResponse<ReviewStatus> pgmsResponse = new PgmsResponse<>();
        if(reviewStatus == null || reviewStatus.getId() != null) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage("Failed to save reviewStatus. Either reviewStatus is null or reviewStatus id is not null");
            return pgmsResponse;
        }
        try {
            reviewStatus.setEntryStatus(EntryStatus.ACTIVE);
            reviewStatus = reviewStatusService.saveReviewStatus(reviewStatus);
            pgmsResponse.setSuccess(true);
            pgmsResponse.setMessage("Successfully saved reviewStatus with ID " + reviewStatus.getId());
            pgmsResponse.setData(reviewStatus);
        }
        catch (Exception e) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage(e.getMessage());
        }
        return pgmsResponse;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public PgmsResponse<ReviewStatus> updateReviewStatus(@RequestBody ReviewStatus reviewStatus) {
        PgmsResponse<ReviewStatus> pgmsResponse = new PgmsResponse<>();
        if(reviewStatus == null || reviewStatus.getId() == null) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage("Failed to update reviewStatus. Either reviewStatus or reviewStatus id is null");
            return pgmsResponse;
        }
        try {
            if(reviewStatus.getEntryStatus() == null) {
                reviewStatus.setEntryStatus(EntryStatus.ACTIVE);
            }
            reviewStatusService.saveReviewStatus(reviewStatus);
            pgmsResponse.setSuccess(true);
            pgmsResponse.setMessage("Successfully updated reviewStatus with ID " + reviewStatus.getId());
            pgmsResponse.setData(reviewStatus);
        }
        catch (Exception e) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage(e.getMessage());
        }
        return pgmsResponse;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public PgmsResponse<ReviewStatus> deleteReviewStatus(@RequestBody ReviewStatus reviewStatus) {
        PgmsResponse<ReviewStatus> pgmsResponse = new PgmsResponse<>();
        if(reviewStatus == null || reviewStatus.getId() == null) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage("Failed to delete reviewStatus. Either reviewStatus or reviewStatus id is null");
            return pgmsResponse;
        }
        reviewStatus = reviewStatusService.getReviewStatus(reviewStatus.getId());
        reviewStatus.setEntryStatus(EntryStatus.DELETED);
        return updateReviewStatus(reviewStatus);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public PgmsResponse<List<ReviewStatus>> getAllReviewStatus() {
        PgmsResponse<List<ReviewStatus>> pgmsResponse = new PgmsResponse<>();
        try {
            pgmsResponse.setSuccess(true);
            pgmsResponse.setMessage("Successfully got all categories");
            pgmsResponse.setData(reviewStatusService.getAllReviewStatus());
        }
        catch (Exception e) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage(e.getMessage());
        }
        return pgmsResponse;
    }

    @RequestMapping(value = "/getAllActive", method = RequestMethod.GET)
    public PgmsResponse<List<ReviewStatus>> getAllActiveReviewStatus() {
        PgmsResponse<List<ReviewStatus>> pgmsResponse = new PgmsResponse<>();
        try {
            pgmsResponse.setSuccess(true);
            pgmsResponse.setMessage("Successfully got all categories");
            pgmsResponse.setData(reviewStatusService.getAllActiveReviewStatus());
        }
        catch (Exception e) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage(e.getMessage());
        }
        return pgmsResponse;
    }

}
