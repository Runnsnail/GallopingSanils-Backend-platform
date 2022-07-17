package com.snail.abell.permission.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.snail.abell.permission.entity.SysMenu;
import com.snail.abell.permission.entity.SysRole;
import com.snail.abell.permission.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统用户(SysUser)表数据库访问层
 *
 * @author Abell
 * @since 2022-06-05 11:51:28
 */
@Repository
public interface SysUserDao extends BaseMapper<SysUser>{

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    SysUser queryById(Long userId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SysUser> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysUser 实例对象
     * @return 对象列表
     */
    List<SysUser> queryAll(SysUser sysUser);

    /**
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 影响行数
     */
    @Override
    int insert(SysUser sysUser);

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 影响行数
     */
    int update(SysUser sysUser);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 影响行数
     */
    int deleteById(Long userId);

    List<SysRole> selectSysRoleByUserId(Long userId);

    List<SysMenu> selectSysMenuByUserId(Long userId);
}
