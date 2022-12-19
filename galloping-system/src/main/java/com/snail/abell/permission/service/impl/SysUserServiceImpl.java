package com.snail.abell.permission.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snail.abell.permission.dao.SysUserDao;
import com.snail.abell.permission.dto.UserDto;
import com.snail.abell.permission.entity.SysMenu;
import com.snail.abell.permission.entity.SysRole;
import com.snail.abell.permission.entity.SysUser;
import com.snail.abell.permission.service.SysUserService;
import com.snail.abell.permission.vo.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 系统用户(SysUser)表服务实现类
 *
 * @author Abell
 * @since 2022-06-05 11:51:28
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserDao,SysUser> implements SysUserService {
    @Resource
    private SysUserDao sysUserDao;
    private  UserMapper userMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    @Override
    public SysUser queryById(Long userId) {
        return this.sysUserDao.queryById(userId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SysUser> queryAllByLimit(int offset, int limit) {
        return this.sysUserDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @Override
    public SysUser insert(SysUser sysUser) {
        this.sysUserDao.insert(sysUser);
        return sysUser;
    }

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @Override
    public SysUser update(SysUser sysUser) {
        sysUserDao.updateById(sysUser);
        return this.queryById(sysUser.getUserId());
    }

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long userId) {
        return this.sysUserDao.deleteById(userId) > 0;
    }

    @Override
    public UserDto findByName(String username) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", username);
        SysUser sysUser = this.sysUserDao.selectOne(queryWrapper);
        return userMapper.toDto(sysUser);
    }

    /**
     * 查询用户实体
     * @param username
     * @return
     */
    @Override
    public SysUser selectUserByName(String username) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(SysUser ::getUsername, username);
        return this.sysUserDao.selectOne(queryWrapper);
    }

    @Override
    public SysUser selectUserByEmail(String email) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(SysUser ::getEmail, email);
        return this.sysUserDao.selectOne(queryWrapper);
    }

    @Override
    public List<SysRole> selectSysRoleByUserId(Long userId) {
        return this.sysUserDao.selectSysRoleByUserId(userId);
    }

    @Override
    public List<SysMenu> selectSysMenuByUserId(Long userId) {
        return this.sysUserDao.selectSysMenuByUserId(userId);
    }

    @Override
    public ArrayList<HashMap<String, String>> getMembers() {

        ArrayList<HashMap<String, String>> members = new ArrayList<HashMap<String, String>>();
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("member");
        List<SysUser> menuList = this.sysUserDao.selectList(queryWrapper);
        for (SysUser sysUser:menuList) {
            HashMap<String, String> map = new HashMap<>();
            map.put("member",sysUser.getUsername());
            members.add(map);
        }
        return members;
    }

    @Override
    public void updateTeams(ArrayList<HashMap<String, String>> memberList, String memberCode) {
        List<String> memberNames = new ArrayList<>();
        for (Map memberMap: memberList) {
            String value = memberMap.get("member").toString();
            memberNames.add(value);
        }
        this.sysUserDao.updateTeams(memberNames,memberCode);
    }

    @Override
    public boolean addTeam(String memberCode, String username) {
        this.sysUserDao.addTeam(memberCode,username);
        return false;
    }


}
