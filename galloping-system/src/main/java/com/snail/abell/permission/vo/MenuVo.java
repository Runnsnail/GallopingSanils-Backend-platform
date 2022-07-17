package com.snail.abell.permission.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.snail.abell.permission.dto.MenuMetaVo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Abell
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MenuVo implements Serializable {

    private String name;

    private String path;

    private Boolean hidden;

    private String redirect;

    private String component;

    private Boolean alwaysShow;

    private MenuMetaVo meta;

    private List<MenuVo> children;
}
