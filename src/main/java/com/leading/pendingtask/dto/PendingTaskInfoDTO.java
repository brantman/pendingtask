package com.leading.pendingtask.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author junwang
 * @date 2019/12/10
 */
@Data
@Accessors(chain = true)
public class PendingTaskInfoDTO {

    private Integer count;

    private String name;

    private String url;

    private Integer status;
}
