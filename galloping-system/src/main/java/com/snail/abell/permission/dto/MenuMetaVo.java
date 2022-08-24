package com.snail.abell.permission.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Abell
 * @date 2022/6/19
 */
@Data
@AllArgsConstructor
public class MenuMetaVo implements Serializable {

    private String pageTitle;

    private List breadcrumb;

    private String name;


}
