package com.snail.abell.webUI.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snail.abell.webUI.entity.TPlanBusinessUi;
import com.snail.abell.webUI.dao.TPlanBusinessUiDao;
import com.snail.abell.webUI.service.TPlanBusinessUiService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TPlanBusinessUi)表服务实现类
 *
 * @author Abell
 * @since 2022-06-26 16:36:43
 */
@Service
public class TPlanBusinessUiServiceImpl extends ServiceImpl<TPlanBusinessUiDao,TPlanBusinessUi> implements TPlanBusinessUiService {
    @Resource
    private TPlanBusinessUiDao tPlanBusinessUiDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TPlanBusinessUi queryById(Long id) {
        return this.tPlanBusinessUiDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TPlanBusinessUi> queryAllByLimit(int offset, int limit) {
        return this.tPlanBusinessUiDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tPlanBusinessUi 实例对象
     * @return 实例对象
     */
    @Override
    public TPlanBusinessUi insert(TPlanBusinessUi tPlanBusinessUi) {
        this.tPlanBusinessUiDao.insert(tPlanBusinessUi);
        return tPlanBusinessUi;
    }

    /**
     * 修改数据
     *
     * @param tPlanBusinessUi 实例对象
     * @return 实例对象
     */
    @Override
    public TPlanBusinessUi update(TPlanBusinessUi tPlanBusinessUi) {
        this.tPlanBusinessUiDao.update(tPlanBusinessUi);
        return this.queryById(tPlanBusinessUi.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.tPlanBusinessUiDao.deleteById(id) > 0;
    }
}