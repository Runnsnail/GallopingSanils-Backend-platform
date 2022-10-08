package com.snail.abell.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snail.abell.Vo.EditElementVo;
import com.snail.abell.Vo.ElementVo;
import com.snail.abell.dao.TPageElementDao;
import com.snail.abell.dto.EditElementMapper;
import com.snail.abell.dto.PageElementMapper;
import com.snail.abell.entity.TPageElement;
import com.snail.abell.service.TPageElementService;
import com.snail.abell.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 * t_client(TPageElement)表服务实现类
 *
 * @author Abell
 * @since 2022-06-06 16:41:23
 */
@Service
public class TPageElementServiceImpl extends ServiceImpl<TPageElementDao,TPageElement> implements TPageElementService {

    @Resource
    private TPageElementDao PageElementDao;
    @Resource
    private TPageElementService pageElementService;
    @Autowired
    private PageElementMapper pageElementMapper;
    @Autowired
    private EditElementMapper editElementMapper;


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TPageElement queryById(Integer id) {
        return this.PageElementDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TPageElement> queryAllByLimit(int offset, int limit) {
        return this.PageElementDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tPageElement 实例对象
     * @return 实例对象
     */
    @Override
    public TPageElement insert(TPageElement tPageElement) {
        this.PageElementDao.insert(tPageElement);
        return tPageElement;
    }

    /**
     * 修改数据
     *
     * @param tPageElement 实例对象
     * @return 实例对象
     */
    @Override
    public TPageElement update(TPageElement tPageElement) {
        this.PageElementDao.update(tPageElement);
        return this.queryById(tPageElement.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.PageElementDao.deleteById(id) > 0;
    }

    @Override
    public Page<TPageElement> pageQuery(Page<TPageElement> page,  ElementVo elementVo) {

        QueryWrapper<TPageElement> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        queryWrapper.eq("page_id",elementVo.getPageId());
        if (StringUtils.isNotEmpty(elementVo.getQ())) {
            queryWrapper.like("element_name", elementVo.getQ());
        }
        return PageElementDao.selectPage(page,queryWrapper);
    }

    @Override
    public boolean copyElemenById(Integer id) {
        TPageElement pageElement = PageElementDao.selectById(id);
        if (pageElement == null) {
            throw new IllegalArgumentException("该页面元素已删除");
        }
        String newElementName = "NEW" + pageElement.getElementName();
        pageElement.setElementName(newElementName);
        pageElement.setId(null);
        pageElement.setCreateTime(DateUtil.date(Calendar.getInstance()));
       return PageElementDao.insert(pageElement) >0;
    }

    @Override
    public boolean batchRemoveById(List<EditElementVo> pageElements) {
        List<Integer> idList = pageElements.stream().map(EditElementVo::getId).collect(Collectors.toList());
        return PageElementDao.deleteBatchIds(idList)>0;
    }

    @Override
    public boolean saveOrUpdateBatchVO(List<EditElementVo> editElementVos) {
        editElementVos.stream().forEach(EditElementVo -> EditElementVo.setCreateTime(DateUtil.date()));
        List<TPageElement> pageElementVoList = EditElementMapper.INSTANCT.toPageElementsList(editElementVos);
        return pageElementService.saveOrUpdateBatch(pageElementVoList);
    }

    @Override
    public boolean updateElement(TPageElement pageElement) {



        return pageElementService.saveOrUpdate(pageElement);
    }


}
