package com.snail.abell.Vo;

import lombok.Data;

/**
 * @author Abell
 * @date 2022/10/22
 */
@Data
public class CaseIdVo {

    private String q;

    private Integer perPage;

    private Integer page;

    private String sortBy;

    private Boolean sortDesc;

    private String status;

    private Integer suitId;
}
