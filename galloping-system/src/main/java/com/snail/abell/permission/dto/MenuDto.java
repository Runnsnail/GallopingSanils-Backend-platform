package com.snail.abell.permission.dto;

import com.snail.abell.apiInterface.TreeNode;
import com.snail.abell.base.BaseDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @author Abell
 * @date 2020/6/6
 * @description
 */
@Data
public class MenuDto extends BaseDTO implements TreeNode<Long>, Serializable {

    private Long menuId;

    private List<MenuDto> children;

    private Integer type;

    private String permission;

    private String title;

    private Integer menuSort;

    private String path;

    private String component;

    private Long pid;

    private Integer subCount;

    private Boolean iframe;

    private Boolean cache;

    private Boolean hidden;

    private String componentName;

    private String icon;



    public Boolean getHasChildren() {
        return subCount > 0;
    }

    public Boolean getLeaf() {
        return subCount <= 0;
    }

    public String getLabel() {
        return title;
    }

    @Override
    public Long id() {
        return this.menuId;
    }

    @Override
    public Long parentId() {
        return this.pid;
    }

    @Override
    public boolean root() {
        return  Objects.equals(this.pid, null);
    }

    @Override
    public void setChildren(List children) {
        this.children = children;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (o == null || getClass() != o.getClass()) {
//            return false;
//        }
//        MenuDto menuDto = (MenuDto) o;
//        return Objects.equals(menuId, menuDto.menuId);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(menuId);
//    }
}
