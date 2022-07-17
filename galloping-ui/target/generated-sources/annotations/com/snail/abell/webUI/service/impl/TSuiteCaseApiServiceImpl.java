package com.snail.abell.webUI.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snail.abell.webUI.entity.TSuiteCaseApi;
import com.snail.abell.webUI.dao.TSuiteCaseApiDao;
import com.snail.abell.webUI.service.TSuiteCaseApiService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TSuiteCaseApi)表服务实现类
 *
 * @author Abell
 * @since 2022-06-26 19:19:37
 */
@Service("tSuiteCaseApiService")
public class TSuiteCaseApiServiceImpl extends ServiceImpl<TSuiteCaseApiDao,TSuiteCaseApi> implements TSuiteCaseApiService {
    @Resource
    private TSuiteCaseApiDao tSuiteCaseApiDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TSuiteCaseApi queryById(Long id) {
        return this.tSuiteCaseApiDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TSuiteCaseApi> queryAllByLimit(int offset, int limit) {
        return this.tSuiteCaseApiDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tSuiteCaseApi 实例对象
     * @return 实例对象
     */
    @Override
    public TSuiteCaseApi insert(TSuiteCaseApi tSuiteCaseApi) {
        this.tSuiteCaseApiDao.insert(tSuiteCaseApi);
        return tSuiteCaseApi;
    }

    /**
     * 修改数据
     *
     * @param tSuiteCaseApi 实例对象
     * @return 实例对象
     */
    @Override
    public TSuiteCaseApi update(TSuiteCaseApi tSuiteCaseApi) {
        this.tSuiteCaseApiDao.update(tSuiteCaseApi);
        return this.queryById(tSuiteCaseApi.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.tSuiteCaseApiDao.deleteById(id) > 0;
    }
}