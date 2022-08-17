package com.snail.abell.permission.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.snail.abell.apiInterface.ResponseResult;
import com.snail.abell.exception.BadRequestException;
import com.snail.abell.logInterface.Log;
import com.snail.abell.permission.dao.SysMenuDao;
import com.snail.abell.permission.dto.MenuDto;
import com.snail.abell.permission.entity.SysMenu;
import com.snail.abell.permission.service.SysMenuService;
import com.snail.abell.permission.vo.MenuMapper;
import com.snail.abell.utils.SecurityUtils;
import com.snail.abell.utils.TreeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 系统菜单(SysMenu)表控制层
 *
 * @author Abell
 * @since 2022-06-05 11:51:28
 */
@Api(tags = "系统菜单")
@Validated
@ResponseResult
@RestController
@RequestMapping("/spi/menus")
public class SysMenuController {
    /**
     * 服务对象
     */
    @Resource
    private SysMenuService menuService;

    @Autowired
    private SysMenuDao menuDao;
    @Autowired
    private MenuMapper menuMapper;
    private static final String ENTITY_NAME = "menu";

    @Log(description = "获取前端所需菜单")
    @GetMapping(value = "/build")
    @ApiOperation("获取前端所需菜单")
    public Object buildMenus() {
        List<MenuDto> menuDtoList = menuService.findByUser(SecurityUtils.getCurrentUserId());
        // List<MenuDto> menuDtos = menuService.buildTree(menuDtoList);
        List<MenuDto> menuDtos = TreeUtils.generateTrees(menuDtoList);
        return menuService.buildMenus(menuDtos);
    }

    @ApiOperation("返回全部的菜单")
    @GetMapping(value = "/lazy")
    @PreAuthorize("@el.check('menu:list','roles:list')")
    public Object queryAllMenu(@RequestParam Long pid) {
        return menuService.getMenus(pid);
    }

    @ApiOperation("根据菜单ID返回所有子节点ID，包含自身ID")
    @GetMapping(value = "/child")
    @PreAuthorize("@el.check('menu:list','roles:list')")
    public Set<Long> childMenu(@RequestParam Long id) {
        Set<SysMenu> menuSet = new HashSet<>();
        List<MenuDto> menuList = menuService.getMenus(id);
        menuSet.add(menuService.getById(id));
        menuSet = menuService.getChildMenus(menuMapper.toEntity(menuList), menuSet);
        return menuSet.stream().map(SysMenu::getMenuId).collect(Collectors.toSet());

    }

    @GetMapping
    @ApiOperation("查询菜单")
    @PreAuthorize("@el.check('menu:list')")
    public List<SysMenu> queryMenu(@RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize) throws Exception {
        Page<SysMenu> page = new Page<>(pageNum, pageSize);
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid",null);
        queryWrapper.orderByDesc("menu_id");
        return this.menuDao.selectPage(page, queryWrapper).getRecords();
    }

    @ApiOperation("查询菜单:根据ID获取同级与上级数据")
    @PostMapping("/superior")
    @PreAuthorize("@el.check('menu:list')")
    public ResponseEntity<Object> getMenuSuperior(@RequestBody List<Long> ids) {
        Set<MenuDto> menuDtos = new LinkedHashSet<>();
        if (CollectionUtil.isNotEmpty(ids)) {
            for (Long id : ids) {
                MenuDto menuDto = menuMapper.toDto(menuService.getById(id));
                menuDtos.addAll(menuService.getSuperior(menuDto, new ArrayList<>()));
            }
            return new ResponseEntity<>(menuService.buildTree(new ArrayList<>(menuDtos)), HttpStatus.OK);
        }
        return new ResponseEntity<>(menuService.getMenus(null), HttpStatus.OK);
    }

    @Log(description = "新增菜单")
    @ApiOperation("新增菜单")
    @PostMapping
    @PreAuthorize("@el.check('menu:add')")
    public boolean createMenu(@Validated @RequestBody SysMenu resources) {
        if (resources.getMenuId() != null) {
            throw new BadRequestException("A new " + ENTITY_NAME + " cannot already have an ID");
        }
        int num = menuService.insert(resources);
        return  num > 0;

    }

    @Log(description = "修改菜单")
    @ApiOperation("修改菜单")
    @PutMapping
    @PreAuthorize("@el.check('menu:edit')")
    public SysMenu updateMenu( @RequestBody SysMenu resources) {

        return  menuService.update(resources);
    }

    @Log(description = "删除菜单")
    @ApiOperation("删除菜单")
    @DeleteMapping
    @PreAuthorize("@el.check('menu:del')")
    public boolean deleteMenu(@RequestBody Set<Long> ids) {
        Set<SysMenu> menuSet = new HashSet<>();
        for (Long id : ids) {
            List<MenuDto> menuList = menuService.getMenus(id);
            menuSet.add(menuService.getBaseMapper().selectById(id));
            menuSet = menuService.getChildMenus(menuMapper.toEntity(menuList), menuSet);
        }
        menuService.delete(menuSet);
        return true;
    }
}

