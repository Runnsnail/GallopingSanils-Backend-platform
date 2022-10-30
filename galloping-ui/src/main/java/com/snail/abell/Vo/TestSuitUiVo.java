package com.snail.abell.Vo;

import com.snail.abell.apiInterface.TreeNode;
import com.snail.abell.base.BaseDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @author Abell
 * @date 2022/10/12
 */
@Data
public class TestSuitUiVo extends BaseDTO implements TreeNode<Long>, Serializable {


    private Long id;


    private String label;


    private Long projectId;


    private Long parentId;


    private String sort;


    private Boolean isLeaf;


    private List<TestSuitUiVo> children;


    @Override
    public Long id() {
        return this.id;
    }

    @Override
    public Long parentId() {
        return this.parentId;
    }

    @Override
    public boolean root() {
        return Objects.equals(this.parentId, null);
    }

    @Override
    public void setChildren(List children) {
        this.children = children;
    }

}
