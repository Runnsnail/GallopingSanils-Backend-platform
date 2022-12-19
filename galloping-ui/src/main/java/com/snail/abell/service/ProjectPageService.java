package com.snail.abell.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snail.abell.entity.ProjectPage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * t_client(TProjectPage)表服务接口层
 *
 * @author Abell
 * @since 2022-06-05 19:11:33
 */
public interface ProjectPageService extends IService<ProjectPage>{
  /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ProjectPage queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ProjectPage> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param projectPage 实例对象
     * @return 实例对象
     */
    ProjectPage insert(ProjectPage projectPage);

    /**
     * 修改数据
     *
     * @param projectPage 实例对象
     * @return 实例对象
     */
    ProjectPage update(ProjectPage projectPage);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);


    ArrayList<HashMap<String, String>> getPageNameList();
}
