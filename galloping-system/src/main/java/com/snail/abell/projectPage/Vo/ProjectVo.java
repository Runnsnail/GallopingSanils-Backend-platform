package com.snail.abell.projectPage.Vo;

import lombok.Data;

/**
 * @author Abell
 * @date 2022/9/18
 */

@Data
public class ProjectVo {

    private String   q;

    private String   sortBy;

    private Integer   page;

    private Integer   perPage;

    private String   cardTitle;
}
