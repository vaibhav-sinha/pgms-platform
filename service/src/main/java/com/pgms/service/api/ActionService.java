package com.pgms.service.api;

import com.pgms.shared.model.Action;

import java.util.List;

/**
 * Created by user-1 on 27/6/15.
 */
public interface ActionService {

    Action saveAction(Action action);
    Action getAction(Long id);
    List<Action> getAllActions();
    List<Action> getAllActiveActions();
}
