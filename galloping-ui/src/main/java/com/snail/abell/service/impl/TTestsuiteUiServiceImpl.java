package com.snail.abell.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snail.abell.Vo.*;
import com.snail.abell.dao.TSuiteCaseUiDao;
import com.snail.abell.dao.TTestsuiteUiDao;
import com.snail.abell.dao.TestUiExectionMapper;
import com.snail.abell.dto.TestSuitUiMapper;
import com.snail.abell.dto.UiExectionDto;
import com.snail.abell.entity.JobForm;
import com.snail.abell.entity.TSuiteCaseUi;
import com.snail.abell.entity.TTestsuiteUi;
import com.snail.abell.exception.BizException;
import com.snail.abell.service.JobService;
import com.snail.abell.service.TEnvService;
import com.snail.abell.service.TTestsuiteUiService;
import com.snail.abell.utils.SecurityUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Resource
    private TestUiExectionMapper testUiExectionMapper;
    @Resource
    private TEnvService envService;
    @Resource
    private JobService jobService;

    @Qualifier("testSuitUiMapperImpl")
    @Autowired
    private TestSuitUiMapper suiteMapper;

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
    public List<TestSuitUiVo> pageQuery(PageSuitsVo testcaseUi) {
        QueryWrapper<TTestsuiteUi> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");
        if (null==testcaseUi.getIsLeaf()) {
            queryWrapper.eq("is_leaf",false);
        }
        if (StringUtils.isNotEmpty(testcaseUi.getQ())) {
            queryWrapper.like("name", testcaseUi.getQ());
        }
        List<TTestsuiteUi> testSuitesList = testsuiteUiService.list(queryWrapper);

        return testSuitesList.stream().map(suiteMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<TestSuitUiVo> listByProjectId(long id) {
        List<TTestsuiteUi> testsuiteUiList = testsuiteUiService.lambdaQuery().eq(TTestsuiteUi::getId,id).list();
        List<TestSuitUiVo> suiteUiDtoList = testsuiteUiList.stream().map(suiteMapper::toDto).collect(Collectors.toList());
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
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSuiteTree(List<SuiteTreeVo> suiteTreeVoList) {

        Boolean result = true;
        try{
        for (SuiteTreeVo suiteTreeVo: suiteTreeVoList) {
            UpdateWrapper<TTestsuiteUi> update = new UpdateWrapper<>();
            update.eq("id",suiteTreeVo.getId()).set("sort",suiteTreeVo.getSort()).set("parentId",suiteTreeVo.getParentId());
            tTestsuiteUiDao.update(null,update);
        }}catch (Exception e) {
            result = false;
        }

        return result;
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

    @Override
    public boolean updateSuit(TestSuitMetaVo testsuiteUi) {
        LambdaUpdateWrapper<TTestsuiteUi> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(TTestsuiteUi::getId,testsuiteUi.getId()).set(TTestsuiteUi::getName,testsuiteUi.getName()).set(TTestsuiteUi::getUpdateBy, SecurityUtils.getCurrentUsername());
        return tTestsuiteUiDao.update(null,lambdaUpdateWrapper)>0;
    }

    @Override
    public IPage<UiExectionDto> pageQueryList(Page<UiExectionDto> page, UiExectionVo uiExectionVo) {
        QueryWrapper<UiExectionDto> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(uiExectionVo.getQ())) {
            queryWrapper.like("eu.`name`", uiExectionVo.getQ());
        }
        if (StringUtils.isNotBlank(uiExectionVo.getStatus())) {
            queryWrapper.lambda().eq(UiExectionDto::getStatus, uiExectionVo.getStatus());
        }

        queryWrapper.orderByDesc(uiExectionVo.getSortDesc(), uiExectionVo.getSortBy());

        return testUiExectionMapper.selectPagesList(page, queryWrapper);

    }

    @Override
    public boolean updateByUiExection(EnvVo envVo) {

        LambdaUpdateWrapper<TTestsuiteUi> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(TTestsuiteUi::getId,envVo.getId()).set(TTestsuiteUi::getNotificationType,envVo.getEnvId());

        return tTestsuiteUiDao.update(null,lambdaUpdateWrapper)>0;
    }

    @Override
    public boolean saveAndCreatJob(TTestsuiteUi newTestsuiteUi) throws Exception {

        String jobGroupName = newTestsuiteUi.getName() + IdUtil.simpleUUID();
        String cronExpression = "* * * * * ? 2800-3000";
//        String cronExpression = "* * * * * ? *";
        JobForm form = JobForm.builder().jobClassName("com.snail.abell.service.impl.UiExectionJob")
                .jobName(newTestsuiteUi.getName()).cronExpression(cronExpression).jobGroupName(jobGroupName).build();

        jobService.addJob(form);

        return testsuiteUiService.save(newTestsuiteUi);
    }

}
