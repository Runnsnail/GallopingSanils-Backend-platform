package com.snail.abell.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snail.abell.entity.TSuiteCaseUi;
import com.snail.abell.dao.TSuiteCaseUiDao;
import com.snail.abell.service.TSuiteCaseUiService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TSuiteCaseUi)表服务实现类
 *
 * @author Abell
 * @since 2022-06-26 12:30:48
 */
@Service
public class TSuiteCaseUiServiceImpl extends ServiceImpl<TSuiteCaseUiDao,TSuiteCaseUi> implements TSuiteCaseUiService {
    @Resource
    private TSuiteCaseUiDao tSuiteCaseUiDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TSuiteCaseUi queryById(Long id) {
        return this.tSuiteCaseUiDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TSuiteCaseUi> queryAllByLimit(int offset, int limit) {
        return this.tSuiteCaseUiDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tSuiteCaseUi 实例对象
     * @return 实例对象
     */
    @Override
    public TSuiteCaseUi insert(TSuiteCaseUi tSuiteCaseUi) {
        this.tSuiteCaseUiDao.insert(tSuiteCaseUi);
        return tSuiteCaseUi;
    }

    /**
     * 修改数据
     *
     * @param tSuiteCaseUi 实例对象
     * @return 实例对象
     */
    @Override
    public TSuiteCaseUi update(TSuiteCaseUi tSuiteCaseUi) {
        this.tSuiteCaseUiDao.update(tSuiteCaseUi);
        return this.queryById(tSuiteCaseUi.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.tSuiteCaseUiDao.deleteById(id) > 0;
    }
}
