package com.snail.abell.Vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Abell
 * @date 2022/10/19
 */

@Data
public class TestSuitMetaVo implements Serializable {


    private Long id;


    private String label;


    private Long projectId;


    private Long parentId;


    private String sort;


    private Boolean isLeaf;
}
