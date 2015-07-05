package com.pgms.admin.controller;

import com.pgms.service.api.ActionService;
import com.pgms.shared.model.Action;
import com.pgms.shared.model.EntryStatus;
import com.pgms.shared.pojo.PgmsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by user-1 on 28/6/15.
 */
@RestController
@RequestMapping("/api/action")
public class ActionController {

    @Autowired
    ActionService actionService;

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public PgmsResponse<Action> getAction(@PathVariable Long id) {
        PgmsResponse<Action> pgmsResponse = new PgmsResponse<>();
        if(id == null) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage("Failed to get action. Action id is null");
            return pgmsResponse;
        }
        try {
            pgmsResponse.setData(actionService.getAction(id));
            pgmsResponse.setSuccess(true);
            pgmsResponse.setMessage("Successfully got action with ID " + id);
        }
        catch (Exception e) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage(e.getMessage());
        }
        return pgmsResponse;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public PgmsResponse<Action> saveAction(@RequestBody Action action) {
        PgmsResponse<Action> pgmsResponse = new PgmsResponse<>();
        if(action == null || action.getId() != null) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage("Failed to save action. Either action is null or action id is not null");
            return pgmsResponse;
        }
        try {
            action.setEntryStatus(EntryStatus.ACTIVE);
            action = actionService.saveAction(action);
            pgmsResponse.setSuccess(true);
            pgmsResponse.setMessage("Successfully saved action with ID " + action.getId());
            pgmsResponse.setData(action);
        }
        catch (Exception e) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage(e.getMessage());
        }
        return pgmsResponse;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public PgmsResponse<Action> updateAction(@RequestBody Action action) {
        PgmsResponse<Action> pgmsResponse = new PgmsResponse<>();
        if(action == null || action.getId() == null) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage("Failed to update action. Either action or action id is null");
            return pgmsResponse;
        }
        try {
            if(action.getEntryStatus() == null) {
                action.setEntryStatus(EntryStatus.ACTIVE);
            }
            actionService.saveAction(action);
            pgmsResponse.setSuccess(true);
            pgmsResponse.setMessage("Successfully updated action with ID " + action.getId());
            pgmsResponse.setData(action);
        }
        catch (Exception e) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage(e.getMessage());
        }
        return pgmsResponse;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public PgmsResponse<Action> deleteAction(@RequestBody Action action) {
        PgmsResponse<Action> pgmsResponse = new PgmsResponse<>();
        if(action == null || action.getId() == null) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage("Failed to delete action. Either action or action id is null");
            return pgmsResponse;
        }
        action = actionService.getAction(action.getId());
        action.setEntryStatus(EntryStatus.DELETED);
        return updateAction(action);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public PgmsResponse<List<Action>> getAllAction() {
        PgmsResponse<List<Action>> pgmsResponse = new PgmsResponse<>();
        try {
            pgmsResponse.setSuccess(true);
            pgmsResponse.setMessage("Successfully got all categories");
            pgmsResponse.setData(actionService.getAllActions());
        }
        catch (Exception e) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage(e.getMessage());
        }
        return pgmsResponse;
    }

    @RequestMapping(value = "/getAllActive", method = RequestMethod.GET)
    public PgmsResponse<List<Action>> getAllActiveAction() {
        PgmsResponse<List<Action>> pgmsResponse = new PgmsResponse<>();
        try {
            pgmsResponse.setSuccess(true);
            pgmsResponse.setMessage("Successfully got all categories");
            pgmsResponse.setData(actionService.getAllActiveActions());
        }
        catch (Exception e) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage(e.getMessage());
        }
        return pgmsResponse;
    }
}

