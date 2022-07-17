package com.snail.abell.projectPage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.snail.abell.permission.vo.ProjectPageDto;
import com.snail.abell.projectPage.entity.TProjectPage;

import java.util.List;

/**
 * t_client(TProjectPage)表服务接口层
 *
 * @author Abell
 * @since 2022-06-05 19:11:33
 */
public interface  TProjectPageService extends IService<TProjectPage>{
  /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TProjectPage queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TProjectPage> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tProjectPage 实例对象
     * @return 实例对象
     */
    TProjectPage insert(TProjectPage tProjectPage);

    /**
     * 修改数据
     *
     * @param tProjectPage 实例对象
     * @return 实例对象
     */
    TProjectPage update(TProjectPage tProjectPage);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

  void insertSelective(TProjectPage projectPage);

  void updateByPrimaryKey(TProjectPage projectPage);

  void deleteByPrimaryKey(Long id);

  void copyPageById(Long id);

  List<TProjectPage> pageQuery(Page<TProjectPage> page, TProjectPage projectPage);

  List<ProjectPageDto> findDtoByProjectId(Long id);

  ProjectPageDto selectDtoByPrimaryKey(Long id);

  List<ProjectPageDto> findDtoByProjectIdAndPageName(Long projectId, String pageName);


  List<ProjectPageDto> findDtoByProjectIdAndPageNameAndIdNot(Long projectId, String pageName, Long id);
}