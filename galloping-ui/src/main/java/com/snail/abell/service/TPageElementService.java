package com.snail.abell.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.snail.abell.Vo.EditElementVo;
import com.snail.abell.Vo.ElementVo;
import com.snail.abell.entity.TPageElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * t_client(TPageElement)表服务接口层
 *
 * @author Abell
 * @since 2022-06-06 16:41:22
 */
public interface TPageElementService extends IService<TPageElement>{
  /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TPageElement queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TPageElement> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tPageElement 实例对象
     * @return 实例对象
     */
    TPageElement insert(TPageElement tPageElement);

    /**
     * 修改数据
     *
     * @param tPageElement 实例对象
     * @return 实例对象
     */
    TPageElement update(TPageElement tPageElement);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

  Page<TPageElement> pageQuery(Page<TPageElement> page,  ElementVo elementVo);

    boolean copyElemenById(Integer id);


  boolean batchRemoveById(List<EditElementVo> pageElements);

  boolean saveOrUpdateBatchVO(List<EditElementVo> pageElementVos);

  boolean updateElement(TPageElement pageElement);

    ArrayList<HashMap<String, String>> getPageElementList(Long id);
}
