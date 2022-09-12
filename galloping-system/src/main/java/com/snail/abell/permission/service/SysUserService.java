package com.snail.abell.permission.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snail.abell.permission.dto.UserDto;
import com.snail.abell.permission.entity.SysMenu;
import com.snail.abell.permission.entity.SysRole;
import com.snail.abell.permission.entity.SysUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 系统用户(SysUser)表服务接口层
 *
 * @author Abell
 * @since 2022-06-05 11:51:28
 */
public interface SysUserService extends IService<SysUser>{
  /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    SysUser queryById(Long userId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SysUser> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    SysUser insert(SysUser sysUser);

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    SysUser update(SysUser sysUser);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    boolean deleteById(Long userId);

  UserDto findByName(String username);

  SysUser selectUserByName(String username);

  SysUser selectUserByEmail(String email);

  List<SysRole> selectSysRoleByUserId(Long userId);

  List<SysMenu> selectSysMenuByUserId(Long userId);

  /**
   * @return 团队成员
   */
  ArrayList<HashMap<String, String>> getMembers();

  void updateTeams(ArrayList<HashMap<String, String>> memberList, String memberCode);

  boolean addTeam(String memberCode, String username);
}
