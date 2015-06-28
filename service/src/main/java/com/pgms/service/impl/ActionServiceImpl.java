package com.pgms.service.impl;

import com.pgms.service.api.ActionService;
import com.pgms.service.repository.ActionRepository;
import com.pgms.shared.model.Action;
import com.pgms.shared.model.EntryStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by user-1 on 28/6/15.
 */
@Component
public class ActionServiceImpl implements ActionService {

    @Autowired
    ActionRepository actionRepository;

    @Override
    public Action saveAction(Action action) {
        return actionRepository.save(action);
    }

    @Override
    public Action getAction(Long id) {
        return actionRepository.findOne(id);
    }

    @Override
    public List<Action> getAllActions() {
        return actionRepository.findAll();
    }

    @Override
    public List<Action> getAllActiveActions() {
        return actionRepository.findByEntryStatus(EntryStatus.ACTIVE);
    }
}
