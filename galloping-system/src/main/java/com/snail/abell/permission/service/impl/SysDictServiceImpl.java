package com.snail.abell.permission.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snail.abell.permission.entity.SysDict;
import com.snail.abell.permission.dao.SysDictDao;
import com.snail.abell.permission.service.SysDictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 数据字典(SysDict)表服务实现类
 *
 * @author Abell
 * @since 2022-06-05 11:51:28
 */
@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictDao,SysDict> implements SysDictService {
    @Resource
    private SysDictDao sysDictDao;

    /**
     * 通过ID查询单条数据
     *
     * @param dictId 主键
     * @return 实例对象
     */
    @Override
    public SysDict queryById(Long dictId) {
        return this.sysDictDao.queryById(dictId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SysDict> queryAllByLimit(int offset, int limit) {
        return this.sysDictDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sysDict 实例对象
     * @return 实例对象
     */
    @Override
    public SysDict insert(SysDict sysDict) {
        this.sysDictDao.insert(sysDict);
        return sysDict;
    }

    /**
     * 修改数据
     *
     * @param sysDict 实例对象
     * @return 实例对象
     */
    @Override
    public SysDict update(SysDict sysDict) {
        this.sysDictDao.update(sysDict);
        return this.queryById(sysDict.getDictId());
    }

    /**
     * 通过主键删除数据
     *
     * @param dictId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long dictId) {
        return this.sysDictDao.deleteById(dictId) > 0;
    }
}