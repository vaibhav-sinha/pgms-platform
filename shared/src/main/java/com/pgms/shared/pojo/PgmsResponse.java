package com.pgms.shared.pojo;

import lombok.Data;

/**
 * Created by user-1 on 28/6/15.
 */
@Data
public class PgmsResponse<T> {
    private Boolean success;
    private String message;
    private T data;
}
