package com.snail.abell.permission.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snail.abell.permission.dao.SysMenuDao;
import com.snail.abell.permission.dto.MenuDto;
import com.snail.abell.permission.dto.MenuMetaVo;
import com.snail.abell.permission.entity.SysMenu;
import com.snail.abell.permission.entity.SysRolesMenus;
import com.snail.abell.permission.entity.SysUsersRoles;
import com.snail.abell.permission.service.SysMenuService;
import com.snail.abell.permission.service.SysRolesMenusService;
import com.snail.abell.permission.service.SysUsersRolesService;
import com.snail.abell.permission.vo.MenuMapper;
import com.snail.abell.permission.vo.MenuVo;
import com.snail.abell.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 系统菜单(SysMenu)表服务实现类
 *
 * @author Abell
 * @since 2022-06-05 11:51:28
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao,SysMenu> implements SysMenuService {

    private  MenuMapper menuMapper;
    @Resource
    private SysMenuDao sysMenuDao;
    @Resource
    private SysMenuService sysMenuService;
    @Resource
    private SysRolesMenusService sysRolesMenusService;
    @Resource
    private SysUsersRolesService  sysUsersRolesService;

    /**
     * 通过ID查询单条数据
     *
     * @param menuId 主键
     * @return 实例对象
     */
    @Override
    public SysMenu queryById(Long menuId) {
        return this.sysMenuDao.queryById(menuId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SysMenu> queryAllByLimit(int offset, int limit) {
        return this.sysMenuDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sysMenu 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(SysMenu sysMenu) {
        int num =  this.sysMenuDao.insert(sysMenu);
        return num;
    }

    /**
     * 修改数据
     *
     * @param sysMenu 实例对象
     * @return 实例对象
     */
    @Override
    public SysMenu update(SysMenu sysMenu) {
        this.sysMenuDao.update(sysMenu);
        return this.queryById(sysMenu.getMenuId());
    }

    /**
     * 通过主键删除数据
     *
     * @param menuId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long menuId) {
        return this.sysMenuDao.deleteById(menuId) > 0;
    }

    @Override
    public List<MenuDto> findByUser(Long currentUserId) {
        //查询当前用户的角色
        SysUsersRoles sysUsersRoles = sysUsersRolesService.getById(currentUserId);
        List<SysRolesMenus> rolesList =sysRolesMenusService.lambdaQuery().eq(SysRolesMenus::getRoleId, sysUsersRoles.getRoleId()).list();
        Set<Long> menuIds = rolesList.stream().map(SysRolesMenus::getMenuId).collect(Collectors.toSet());
        List<SysMenu> menuList = sysMenuService.listByIds(menuIds);
        return menuList.stream().map(menuMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<MenuDto> buildTree(List<MenuDto> menuDtoList) {
        List<MenuDto> trees = new ArrayList<>();
        Set<Long> ids = new HashSet<>();
        for (MenuDto menuDTO : menuDtoList) {
            if (menuDTO.getPid() == null) {
                trees.add(menuDTO);
            }
            for (MenuDto it : menuDtoList) {
                if (menuDTO.getId().equals(it.getPid())) {
                    if (menuDTO.getChildren() == null) {
                        menuDTO.setChildren(new ArrayList<>());
                    }
                    menuDTO.getChildren().add(it);
                    ids.add(it.getId());
                }
            }
        }
        if(trees.size() == 0){
            trees = menuDtoList.stream().filter(s -> !ids.contains(s.getId())).collect(Collectors.toList());
        }
        return trees;
    }

    @Override
    public List<MenuVo> buildMenus(List<MenuDto> menuDtos) {
        List<MenuVo> list = new LinkedList<>();
        menuDtos.forEach(menuDTO -> {
                    if (menuDTO!=null){
                        List<MenuDto> menuDtoList = menuDTO.getChildren();
                        MenuVo menuVo = new MenuVo();
                        menuVo.setName(ObjectUtil.isNotEmpty(menuDTO.getComponentName())  ? menuDTO.getComponentName() : menuDTO.getTitle());
                        // 一级目录需要加斜杠，不然会报警告
                        menuVo.setPath(menuDTO.getPid() == null ? "/" + menuDTO.getPath() :menuDTO.getPath());
                        menuVo.setHidden(menuDTO.getHidden());
                        // 如果不是外链
                        if(!menuDTO.getIFrame()){
                            if(menuDTO.getPid() == null){
                                menuVo.setComponent(StringUtils.isEmpty(menuDTO.getComponent())?"Layout":menuDTO.getComponent());
                                // 如果不是一级菜单，并且菜单类型为目录，则代表是多级菜单
                            }else if(menuDTO.getType() == 0){
                                menuVo.setComponent(StringUtils.isEmpty(menuDTO.getComponent())?"ParentView":menuDTO.getComponent());
                            }else if(StringUtils.isNoneBlank(menuDTO.getComponent())){
                                menuVo.setComponent(menuDTO.getComponent());
                            }
                        }
                        menuVo.setMeta(new MenuMetaVo(menuDTO.getTitle(),menuDTO.getIcon(),!menuDTO.getCache()));
                        if(CollectionUtil.isNotEmpty(menuDtoList)){
                            menuVo.setAlwaysShow(true);
                            menuVo.setRedirect("noredirect");
                            menuVo.setChildren(buildMenus(menuDtoList));
                            // 处理是一级菜单并且没有子菜单的情况
                        } else if(menuDTO.getPid() == null){
                            MenuVo menuVo1 = new MenuVo();
                            menuVo1.setMeta(menuVo.getMeta());
                            // 非外链
                            if(!menuDTO.getIFrame()){
                                menuVo1.setPath("index");
                                menuVo1.setName(menuVo.getName());
                                menuVo1.setComponent(menuVo.getComponent());
                            } else {
                                menuVo1.setPath(menuDTO.getPath());
                            }
                            menuVo.setName(null);
                            menuVo.setMeta(null);
                            menuVo.setComponent("Layout");
                            List<MenuVo> list1 = new ArrayList<>();
                            list1.add(menuVo1);
                            menuVo.setChildren(list1);
                        }
                        list.add(menuVo);
                    }
                }
        );
        return list;
    }

    @Override
    public List<MenuDto> getMenus(Long pid) {
        List<SysMenu> menus;
        if(pid != null && !pid.equals(0L)){
            menus = sysMenuService.lambdaQuery().eq(SysMenu::getPid, pid).list();
        } else {
            menus = sysMenuService.list();
        }
        return menuMapper.toDto(menus);
    }

    @Override
    public Set<SysMenu> getChildMenus(List<SysMenu> menuList, Set<SysMenu> menuSet) {
        for (SysMenu menu : menuList) {
            menuSet.add(menu);
            List<SysMenu> menus = sysMenuService.lambdaQuery().eq(SysMenu::getMenuId, menu.getMenuId()).list();
            if(menus!=null && menus.size()!=0){
                getChildMenus(menus, menuSet);
            }
        }
        return menuSet;
    }

    @Override
    public List<MenuDto> queryAll(MenuDto criteria, boolean b) {
        return null;
    }

    @Override
    public List<MenuDto> getSuperior(MenuDto menuDto, List<SysMenu> menus) {
        if(menuDto.getPid() == null){
            menus.addAll(sysMenuService.list());
            return menuMapper.toDto(menus);
        }
        menus.addAll(menus = sysMenuService.lambdaQuery().eq(SysMenu::getPid, menuDto.getPid()).list());
        LambdaQueryWrapper<SysMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysMenu::getPid, menuDto.getPid());
        return getSuperior(menuMapper.toDto(sysMenuService.getOne(queryWrapper,true)), menus);
    }

    @Override
    public void delete(Set<SysMenu> menuSet) {
        for (SysMenu menu : menuSet) {
            // 清理缓存
            LambdaQueryWrapper<SysRolesMenus> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(SysRolesMenus::getMenuId,menu.getMenuId());
            sysRolesMenusService.remove(queryWrapper);
            sysMenuService.deleteById(menu.getMenuId());
            updateSubCnt(menu.getPid());
            //需要修改
        }
    }

    private void updateSubCnt(Long pid) {
    }

}