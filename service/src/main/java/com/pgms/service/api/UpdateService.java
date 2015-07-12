package com.pgms.service.api;

import com.pgms.shared.model.Update;
import com.pgms.shared.pojo.PgmsUpdateFilter;

import java.util.List;

/**
 * Created by user-1 on 28/6/15.
 */
public interface UpdateService {

    Update saveUpdate(Update update);
    Update getUpdate(Long id);
    List<Update> getAllUpdateForComplaint(Long id);
    List<Update> getUpdatesForFilter(PgmsUpdateFilter pgmsUpdateFilter);
    Long getUpdatesCountForFilter(PgmsUpdateFilter pgmsUpdateFilter);
}
