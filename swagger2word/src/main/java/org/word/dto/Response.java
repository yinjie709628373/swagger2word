package org.word.dto;

import lombok.Data;

/**
 * Created by yinjie
 */
@Data
public class Response {

    /**
     * 返回参数
     */
    private String description;

    /**
     * 参数名
     */
    private String name;

    /**
     * 备注
     */
    private String remark;
}
