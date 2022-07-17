package com.snail.abell.webUI.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.snail.abell.webUI.dto.TestUiDto;
import com.snail.abell.webUI.entity.TTestcaseUiNew;

import java.util.List;

/**
 * (TTestcaseUiNew)表服务接口层
 *
 * @author Abell
 * @since 2022-06-26 11:09:51
 */
public interface TTestcaseUiNewService extends IService<TTestcaseUiNew>{
  /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TTestcaseUiNew queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TTestcaseUiNew> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tTestcaseUiNew 实例对象
     * @return 实例对象
     */
    TTestcaseUiNew insert(TTestcaseUiNew tTestcaseUiNew);

    /**
     * 修改数据
     *
     * @param tTestcaseUiNew 实例对象
     * @return 实例对象
     */
    TTestcaseUiNew update(TTestcaseUiNew tTestcaseUiNew);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

  List<TTestcaseUiNew> pageQuery(Page<TTestcaseUiNew> page, TTestcaseUiNew testcaseUiNew);

    List<TestUiDto> selectDtoBySuiteId(long id);


  TTestcaseUiNew add(TestUiDto testcaseUi);

  TTestcaseUiNew copyCaseById(Long id);

  TTestcaseUiNew businesstoCase(Long id);

}