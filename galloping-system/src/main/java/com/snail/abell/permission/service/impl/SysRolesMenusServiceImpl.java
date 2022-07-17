package com.snail.abell.permission.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snail.abell.permission.entity.SysRolesMenus;
import com.snail.abell.permission.dao.SysRolesMenusDao;
import com.snail.abell.permission.service.SysRolesMenusService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色菜单关联(SysRolesMenus)表服务实现类
 *
 * @author Abell
 * @since 2022-06-05 11:51:28
 */
@Service
public class SysRolesMenusServiceImpl extends ServiceImpl<SysRolesMenusDao,SysRolesMenus> implements SysRolesMenusService {
    @Resource
    private SysRolesMenusDao sysRolesMenusDao;

    /**
     * 通过ID查询单条数据
     *
     * @param menuId 主键
     * @return 实例对象
     */
    @Override
    public SysRolesMenus queryById(Long menuId) {
        return this.sysRolesMenusDao.queryById(menuId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SysRolesMenus> queryAllByLimit(int offset, int limit) {
        return this.sysRolesMenusDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sysRolesMenus 实例对象
     * @return 实例对象
     */
    @Override
    public SysRolesMenus insert(SysRolesMenus sysRolesMenus) {
        this.sysRolesMenusDao.insert(sysRolesMenus);
        return sysRolesMenus;
    }

    /**
     * 修改数据
     *
     * @param sysRolesMenus 实例对象
     * @return 实例对象
     */
    @Override
    public SysRolesMenus update(SysRolesMenus sysRolesMenus) {
        this.sysRolesMenusDao.update(sysRolesMenus);
        return this.queryById(sysRolesMenus.getMenuId());
    }

    /**
     * 通过主键删除数据
     *
     * @param menuId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long menuId) {
        return this.sysRolesMenusDao.deleteById(menuId) > 0;
    }
}