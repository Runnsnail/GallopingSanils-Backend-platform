package com.snail.abell.webUI.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.snail.abell.webUI.dto.SuiteUiDto;
import com.snail.abell.webUI.entity.TSuiteCaseUi;
import com.snail.abell.webUI.entity.TTestsuiteUi;

import java.util.List;

/**
 * (TTestsuiteUi)表服务接口层
 *
 * @author Abell
 * @since 2022-06-28 15:34:53
 */
public interface TTestsuiteUiService extends IService<TTestsuiteUi>{
  /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TTestsuiteUi queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TTestsuiteUi> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tTestsuiteUi 实例对象
     * @return 实例对象
     */
    TTestsuiteUi insert(TTestsuiteUi tTestsuiteUi);

    /**
     * 修改数据
     *
     * @param tTestsuiteUi 实例对象
     * @return 实例对象
     */
    TTestsuiteUi update(TTestsuiteUi tTestsuiteUi);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    List<TTestsuiteUi> pageQuery(Page<TTestsuiteUi> page, TTestsuiteUi testcaseUi);

    List<SuiteUiDto> listByProjectId(long id);

  List<TTestsuiteUi> selectByNameAndProjectId(String name, Long projectId);

  List<TTestsuiteUi> selectByNameAndProjectIdAndIdNot(String name, Long projectId, Long id);

  boolean updateCaseSort(List<TSuiteCaseUi> suiteCaseUis);

  boolean addCaseToSuite(List<TSuiteCaseUi> suiteCaseUis);

  boolean deleteByCaseId(Long id);
}