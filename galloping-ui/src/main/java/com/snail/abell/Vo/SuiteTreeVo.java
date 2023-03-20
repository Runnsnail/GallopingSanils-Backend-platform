package com.snail.abell.Vo;

import lombok.Data;

/**
 * @author Abell
 * @date 2023/3/16
 */

@Data
public class SuiteTreeVo {


    private Long id;

    private Long parentId;

    private String sort;

    private Boolean isLeaf;

}
