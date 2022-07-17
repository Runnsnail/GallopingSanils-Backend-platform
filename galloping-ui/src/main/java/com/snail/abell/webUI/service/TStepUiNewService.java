package com.snail.abell.webUI.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snail.abell.webUI.entity.TStepUiNew;

import java.util.List;

/**
 * (TStepUiNew)表服务接口层
 *
 * @author Abell
 * @since 2022-06-26 12:30:46
 */
public interface TStepUiNewService extends IService<TStepUiNew>{
  /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TStepUiNew queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TStepUiNew> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tStepUiNew 实例对象
     * @return 实例对象
     */
    TStepUiNew insert(TStepUiNew tStepUiNew);

    /**
     * 修改数据
     *
     * @param tStepUiNew 实例对象
     * @return 实例对象
     */
    TStepUiNew update(TStepUiNew tStepUiNew);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

  String savaStep(List<TStepUiNew> testSteps);
}