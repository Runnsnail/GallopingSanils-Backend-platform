package com.snail.abell.permission.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snail.abell.permission.entity.SysRolesMenus;

import java.util.List;

/**
 * 角色菜单关联(SysRolesMenus)表服务接口层
 *
 * @author Abell
 * @since 2022-06-05 11:51:28
 */
public interface SysRolesMenusService extends IService<SysRolesMenus>{
  /**
     * 通过ID查询单条数据
     *
     * @param menuId 主键
     * @return 实例对象
     */
    SysRolesMenus queryById(Long menuId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SysRolesMenus> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param sysRolesMenus 实例对象
     * @return 实例对象
     */
    SysRolesMenus insert(SysRolesMenus sysRolesMenus);

    /**
     * 修改数据
     *
     * @param sysRolesMenus 实例对象
     * @return 实例对象
     */
    SysRolesMenus update(SysRolesMenus sysRolesMenus);

    /**
     * 通过主键删除数据
     *
     * @param menuId 主键
     * @return 是否成功
     */
    boolean deleteById(Long menuId);
}