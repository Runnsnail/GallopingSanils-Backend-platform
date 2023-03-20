package com.snail.abell.Vo;

import lombok.Data;

/**
 * @author Abell
 * @date 2023/3/11
 */
@Data
public class UiExectionVo {


    private String q;

    private Integer perPage;

    private Integer page;

    private String sortBy;

    private Boolean sortDesc;

    private String status;


}
