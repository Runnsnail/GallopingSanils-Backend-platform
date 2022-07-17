package com.snail.abell.permission.dto;

import com.snail.abell.permission.vo.ProjectPageDto;
import com.snail.abell.projectPage.entity.TProjectPage;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Abell
 * @date 2020/6/6
 */

@Repository
public interface ProjectPageDtoMapper {
    ProjectPageDto selectDtoByPrimaryKey(Long id);

    List<ProjectPageDto> findDtoByAll(TProjectPage projectPage);

    List<ProjectPageDto> findDtoByProjectId(Long id);

    List<ProjectPageDto> findDtoByProjectIdAndPageName(Long projectId, String pageName);

    List<ProjectPageDto> findDtoByProjectIdAndPageNameAndIdNot(Long projectId, String pageName, Long id);
}
