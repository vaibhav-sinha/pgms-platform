package com.pgms.shared.pojo;

import lombok.Data;

/**
 * Created by user-1 on 28/6/15.
 */
@Data
public class ComplaintSubmitResponse {
    private Long id;
    private boolean success;
    private String message;
}
