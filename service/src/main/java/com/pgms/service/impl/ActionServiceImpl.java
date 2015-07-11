package com.pgms.service.impl;

import com.pgms.service.api.ActionService;
import com.pgms.service.entity.ActionEntity;
import com.pgms.service.repository.ActionRepository;
import com.pgms.shared.model.Action;
import com.pgms.shared.model.EntryStatus;
import com.pgms.shared.util.Mapper;
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

    @Autowired
    Mapper mapper;

    @Override
    public Action saveAction(Action action) {
        ActionEntity actionEntity = mapper.map(action, ActionEntity.class);
        Action savedAction = mapper.map(actionRepository.save(actionEntity), Action.class);
        return savedAction;
    }

    @Override
    public Action getAction(Long id) {
        return mapper.map(actionRepository.findOne(id), Action.class);
    }

    @Override
    public List<Action> getAllActions() {
        return mapper.mapAsList(actionRepository.findAll(), Action.class);
    }

    @Override
    public List<Action> getAllActiveActions() {
        return mapper.mapAsList(actionRepository.findByEntryStatus(EntryStatus.ACTIVE), Action.class);
    }
}
