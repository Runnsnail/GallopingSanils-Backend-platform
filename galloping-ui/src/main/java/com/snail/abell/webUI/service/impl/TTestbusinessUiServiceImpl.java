package com.snail.abell.webUI.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snail.abell.webUI.entity.TTestbusinessUi;
import com.snail.abell.webUI.dao.TTestbusinessUiDao;
import com.snail.abell.webUI.service.TTestbusinessUiService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TTestbusinessUi)表服务实现类
 *
 * @author Abell
 * @since 2022-06-26 16:36:43
 */
@Service
public class TTestbusinessUiServiceImpl extends ServiceImpl<TTestbusinessUiDao,TTestbusinessUi> implements TTestbusinessUiService {
    @Resource
    private TTestbusinessUiDao tTestbusinessUiDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TTestbusinessUi queryById(Long id) {
        return this.tTestbusinessUiDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TTestbusinessUi> queryAllByLimit(int offset, int limit) {
        return this.tTestbusinessUiDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tTestbusinessUi 实例对象
     * @return 实例对象
     */
    @Override
    public TTestbusinessUi insert(TTestbusinessUi tTestbusinessUi) {
        this.tTestbusinessUiDao.insert(tTestbusinessUi);
        return tTestbusinessUi;
    }

    /**
     * 修改数据
     *
     * @param tTestbusinessUi 实例对象
     * @return 实例对象
     */
    @Override
    public TTestbusinessUi update(TTestbusinessUi tTestbusinessUi) {
        this.tTestbusinessUiDao.update(tTestbusinessUi);
        return this.queryById(tTestbusinessUi.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.tTestbusinessUiDao.deleteById(id) > 0;
    }
}