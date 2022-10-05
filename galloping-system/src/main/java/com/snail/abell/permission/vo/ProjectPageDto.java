package com.snail.abell.permission.vo;

import com.snail.abell.projectPage.entity.TProjectPage;
import com.snail.abell.entity.TPageElement;
import lombok.Data;

import java.util.List;

@Data
public class ProjectPageDto extends TProjectPage {
    private String projectName;
    private List<TPageElement> pageElementList;
}
