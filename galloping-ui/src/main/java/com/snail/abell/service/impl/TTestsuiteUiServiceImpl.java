package com.snail.abell.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snail.abell.exception.BizException;
import com.snail.abell.dao.TSuiteCaseUiDao;
import com.snail.abell.dao.TTestsuiteUiDao;
import com.snail.abell.dto.SuiteUiDto;
import com.snail.abell.dto.SuiteUiMapper;
import com.snail.abell.entity.TSuiteCaseUi;
import com.snail.abell.entity.TTestsuiteUi;
import com.snail.abell.service.TTestsuiteUiService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import static com.snail.abell.base.ResultCode.DELECT_FAILED;

/**
 * (TTestsuiteUi)表服务实现类
 *
 * @author Abell
 * @since 2022-06-28 15:34:53
 */
@Service
public class TTestsuiteUiServiceImpl extends ServiceImpl<TTestsuiteUiDao,TTestsuiteUi> implements TTestsuiteUiService {
    @Resource
    private TTestsuiteUiDao tTestsuiteUiDao;
    @Resource
    private  TSuiteCaseUiDao suiteUiDao;
    @Resource
    private TTestsuiteUiService testsuiteUiService;

    private  SuiteUiMapper suiteUiMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TTestsuiteUi queryById(Long id) {
        return this.tTestsuiteUiDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TTestsuiteUi> queryAllByLimit(int offset, int limit) {
        return this.tTestsuiteUiDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tTestsuiteUi 实例对象
     * @return 实例对象
     */
    @Override
    public TTestsuiteUi insert(TTestsuiteUi tTestsuiteUi) {
        this.tTestsuiteUiDao.insert(tTestsuiteUi);
        return tTestsuiteUi;
    }

    /**
     * 修改数据
     *
     * @param tTestsuiteUi 实例对象
     * @return 实例对象
     */
    @Override
    public TTestsuiteUi update(TTestsuiteUi tTestsuiteUi) {
        this.tTestsuiteUiDao.update(tTestsuiteUi);
        return this.queryById(tTestsuiteUi.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.tTestsuiteUiDao.deleteById(id) > 0;
    }

    @Override
    public List<TTestsuiteUi> pageQuery(Page<TTestsuiteUi> page, TTestsuiteUi testcaseUi) {
        QueryWrapper<TTestsuiteUi> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        List<TTestsuiteUi> testsuitesList = tTestsuiteUiDao.selectPage(page,queryWrapper).getRecords();
        return testsuitesList;
    }

    @Override
    public List<SuiteUiDto> listByProjectId(long id) {
        List<TTestsuiteUi> testsuiteUiList = testsuiteUiService.lambdaQuery().eq(TTestsuiteUi::getId,id).list();
        List<SuiteUiDto> suiteUiDtoList = testsuiteUiList.stream().map(suiteUiMapper::toDto).collect(Collectors.toList());
        return suiteUiDtoList;
    }

    @Override
    public List<TTestsuiteUi> selectByNameAndProjectId(String name, Long projectId) {
        QueryWrapper<TTestsuiteUi> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);
        queryWrapper.eq("project_id",projectId);
        List<TTestsuiteUi> testsuitesList = tTestsuiteUiDao.selectList(queryWrapper);
        return testsuitesList;
    }

    @Override
    public List<TTestsuiteUi> selectByNameAndProjectIdAndIdNot(String name, Long projectId, Long id) {
        QueryWrapper<TTestsuiteUi> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);
        queryWrapper.eq("project_id",projectId);
        queryWrapper.eq("id",id);
        List<TTestsuiteUi> testsuitesList = tTestsuiteUiDao.selectList(queryWrapper);
        return testsuitesList;
    }

    @Override
    public boolean updateCaseSort(List<TSuiteCaseUi> suiteCaseUis) {
        int sort = 0;
        for (TSuiteCaseUi testsuiteCaseUi : suiteCaseUis) {
            testsuiteCaseUi.setSort(sort);
            suiteUiDao.updateById(testsuiteCaseUi);
            sort++;
        }
        return true;
    }

    @Override
    public boolean addCaseToSuite(List<TSuiteCaseUi> suiteCaseUis) {
        int sort = 0;
        Integer maxSort = suiteUiDao.findMaxSortBySuiteId(suiteCaseUis.get(0).getSuiteId());
        for (TSuiteCaseUi testsuiteCaseUi : suiteCaseUis) {
            testsuiteCaseUi.setSort(sort);
            suiteUiDao.insert(testsuiteCaseUi);
            sort++;
        }
        return true;
    }

    @Override
    public boolean deleteByCaseId(Long id) {
        boolean flag = suiteUiDao.deleteById(id)>0;
        if (!flag) {
            throw new BizException(DELECT_FAILED);
        }
        return flag;
    }
}