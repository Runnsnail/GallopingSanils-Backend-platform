package com.snail.abell.webUI.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snail.abell.webUI.entity.TPlanSuiteUi;
import com.snail.abell.webUI.dao.TPlanSuiteUiDao;
import com.snail.abell.webUI.service.TPlanSuiteUiService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TPlanSuiteUi)表服务实现类
 *
 * @author Abell
 * @since 2022-06-26 16:36:42
 */
@Service
public class TPlanSuiteUiServiceImpl extends ServiceImpl<TPlanSuiteUiDao,TPlanSuiteUi> implements TPlanSuiteUiService {
    @Resource
    private TPlanSuiteUiDao tPlanSuiteUiDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TPlanSuiteUi queryById(Long id) {
        return this.tPlanSuiteUiDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TPlanSuiteUi> queryAllByLimit(int offset, int limit) {
        return this.tPlanSuiteUiDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tPlanSuiteUi 实例对象
     * @return 实例对象
     */
    @Override
    public TPlanSuiteUi insert(TPlanSuiteUi tPlanSuiteUi) {
        this.tPlanSuiteUiDao.insert(tPlanSuiteUi);
        return tPlanSuiteUi;
    }

    /**
     * 修改数据
     *
     * @param tPlanSuiteUi 实例对象
     * @return 实例对象
     */
    @Override
    public TPlanSuiteUi update(TPlanSuiteUi tPlanSuiteUi) {
        this.tPlanSuiteUiDao.update(tPlanSuiteUi);
        return this.queryById(tPlanSuiteUi.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.tPlanSuiteUiDao.deleteById(id) > 0;
    }
}