package com.pgms.shared.pojo;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Created by user-1 on 11/7/15.
 */
@Data
public class Info<T> {

    private String name;
    private List<String> keys;
    private List<InfoEntry> data;

    @Data
    public class InfoEntry {
        private String name;
        private Map<String, T> values;
    }
}
