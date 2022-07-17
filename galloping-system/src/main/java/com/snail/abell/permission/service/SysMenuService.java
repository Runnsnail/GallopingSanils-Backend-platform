package com.snail.abell.permission.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snail.abell.permission.dto.MenuDto;
import com.snail.abell.permission.entity.SysMenu;
import com.snail.abell.permission.vo.MenuVo;

import java.util.List;
import java.util.Set;

/**
 * 系统菜单(SysMenu)表服务接口层
 *
 * @author Abell
 * @since 2022-06-05 11:51:28
 */
public interface SysMenuService extends IService<SysMenu> {
    /**
     * 通过ID查询单条数据
     *
     * @param menuId 主键
     * @return 实例对象
     */
    SysMenu queryById(Long menuId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SysMenu> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param sysMenu 实例对象
     * @return 实例对象
     */
    int insert(SysMenu sysMenu);

    /**
     * 修改数据
     *
     * @param sysMenu 实例对象
     * @return 实例对象
     */
    SysMenu update(SysMenu sysMenu);

    /**
     * 通过主键删除数据
     *
     * @param menuId 主键
     * @return 是否成功
     */
    boolean deleteById(Long menuId);

    List<MenuDto> findByUser(Long currentUserId);

    List<MenuDto> buildTree(List<MenuDto> menuDtoList);

    List<MenuVo> buildMenus(List<MenuDto> menuDtos);

    List<MenuDto> getMenus(Long pid);

    Set<SysMenu> getChildMenus(List<SysMenu> menuList, Set<SysMenu> menuSet);

    List<MenuDto> queryAll(MenuDto criteria, boolean b);

    List<MenuDto> getSuperior(MenuDto menuDto, List<SysMenu> menus);

    void delete(Set<SysMenu> menuSet);
}