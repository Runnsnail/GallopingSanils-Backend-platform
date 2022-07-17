package com.snail.abell.permission.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snail.abell.permission.entity.SysUsersRoles;

import java.util.List;

/**
 * 用户角色关联(SysUsersRoles)表服务接口层
 *
 * @author Abell
 * @since 2022-06-05 11:51:28
 */
public interface SysUsersRolesService extends IService<SysUsersRoles>{
  /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    SysUsersRoles queryById(Long userId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SysUsersRoles> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param sysUsersRoles 实例对象
     * @return 实例对象
     */
    SysUsersRoles insert(SysUsersRoles sysUsersRoles);

    /**
     * 修改数据
     *
     * @param sysUsersRoles 实例对象
     * @return 实例对象
     */
    SysUsersRoles update(SysUsersRoles sysUsersRoles);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    boolean deleteById(Long userId);
}