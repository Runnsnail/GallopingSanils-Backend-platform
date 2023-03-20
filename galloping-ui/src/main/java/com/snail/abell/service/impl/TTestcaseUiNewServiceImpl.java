package com.snail.abell.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snail.abell.Vo.CaseIdVo;
import com.snail.abell.dao.TStepUiNewDao;
import com.snail.abell.dao.TTestcaseUiNewDao;
import com.snail.abell.dao.TTestcaseUiNewDtoMapper;
import com.snail.abell.dao.TestCaseUiMapper;
import com.snail.abell.dto.TestCaseMetoDto;
import com.snail.abell.dto.TestCasesDto;
import com.snail.abell.dto.TestUiDto;
import com.snail.abell.entity.TStepUiNew;
import com.snail.abell.entity.TSuiteCaseUi;
import com.snail.abell.entity.TTestcaseUiNew;
import com.snail.abell.exception.BizException;
import com.snail.abell.service.TStepUiNewService;
import com.snail.abell.service.TSuiteCaseUiService;
import com.snail.abell.service.TTestcaseUiNewService;
import com.snail.abell.utils.SecurityUtils;
import com.snail.abell.utils.SerialUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static com.snail.abell.base.ResultCode.*;

/**
 * (TTestcaseUiNew)表服务实现类
 *
 * @author Abell
 * @since 2022-06-26 11:09:51
 */
@Service
public class TTestcaseUiNewServiceImpl extends ServiceImpl<TTestcaseUiNewDao, TTestcaseUiNew> implements TTestcaseUiNewService {
    @Resource
    private TTestcaseUiNewDao tTestcaseUiNewDao;
    @Resource
    private TTestcaseUiNewService testcaseUiNewService;
    @Resource
    private TestCaseUiMapper testCaseUiMapper;
    @Resource
    private TTestcaseUiNewDtoMapper testUiDtoMapper;
    @Resource
    private TStepUiNewService stepUiNewService;
    @Resource
    private TSuiteCaseUiService suiteCaseUiService;
    @Autowired
    private TStepUiNewDao stepUiNewDao;

    private static Logger logger = LoggerFactory.getLogger(TTestcaseUiNewServiceImpl.class);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TTestcaseUiNew queryById(Long id) {
        return this.tTestcaseUiNewDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<TTestcaseUiNew> queryAllByLimit(int offset, int limit) {
        return this.tTestcaseUiNewDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tTestcaseUiNew 实例对象
     * @return 实例对象
     */
    @Override
    public TTestcaseUiNew insert(TTestcaseUiNew tTestcaseUiNew) {
        this.tTestcaseUiNewDao.insert(tTestcaseUiNew);
        return tTestcaseUiNew;
    }

    /**
     * 修改数据
     *
     * @param tTestcaseUiNew 实例对象
     * @return 实例对象
     */
    @Override
    public TTestcaseUiNew update(TTestcaseUiNew tTestcaseUiNew) {
        this.tTestcaseUiNewDao.update(tTestcaseUiNew);
        return this.queryById(tTestcaseUiNew.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.tTestcaseUiNewDao.deleteById(id) > 0;
    }

    @Override
    public IPage<TestCasesDto> pageQuery(Page<TestCasesDto> page, CaseIdVo caseIdVo) {
        QueryWrapper<TestCasesDto> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(caseIdVo.getQ())) {
            queryWrapper.like("cu.`name`", caseIdVo.getQ());
        }
        if (StringUtils.isNotBlank(caseIdVo.getStatus())) {
            queryWrapper.lambda().eq(TestCasesDto::getStatus, caseIdVo.getStatus());
        }
        queryWrapper.eq("suite_id", caseIdVo.getSuitId());

        queryWrapper.orderByDesc(caseIdVo.getSortDesc(), caseIdVo.getSortBy());

        return testCaseUiMapper.selectPagesList(page, queryWrapper);
    }

    @Override
    public List<TestUiDto> selectDtoBySuiteId(long id) {
        return testUiDtoMapper.selectDtoBySuiteId(id);
    }

    @Override
    public TTestcaseUiNew add(TestUiDto testcaseUiDto) {
        List<TTestcaseUiNew> byNameAndProjectId = this.lambdaQuery().eq(TTestcaseUiNew::getName, testcaseUiDto.getName()).
                eq(TTestcaseUiNew::getProjectId, testcaseUiDto.getProjectId()).
                list();
        if (byNameAndProjectId.size() > 0) {
            throw new BizException(TESTSUIT_EXIST_ERROR);
        }
        testcaseUiDto.setUpdateBy(SecurityUtils.getCurrentUsername());
        testcaseUiDto.setCreateBy(SecurityUtils.getCurrentUsername());
        tTestcaseUiNewDao.insert(testcaseUiDto);
        List<TStepUiNew> testSteps = testcaseUiDto.getTestSteps();
        for (TStepUiNew tStepUiNew : testSteps) {
            tStepUiNew.setId(null);
            tStepUiNew.setTestcaseId(testcaseUiDto.getId().toString());
        }
        try {
            String s = stepUiNewService.savaStep(testSteps);
            logger.info(s);
        } catch (Exception e) {
            throw new BizException(TESTSTEP_SAVE_ERROR);
        }

        return testcaseUiDto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TTestcaseUiNew copyCaseById(Long id) {
        TTestcaseUiNew tTestcaseUiNew = tTestcaseUiNewDao.selectById(id);
        if (tTestcaseUiNew == null) {
            throw new IllegalArgumentException("该用例已删除");
        }
        String newName = "NEW" + tTestcaseUiNew.getName();
        tTestcaseUiNew.setName(newName);
        tTestcaseUiNew.setCreateBy(SecurityUtils.getCurrentUsername());
        tTestcaseUiNew.setCaseType(1L);
        tTestcaseUiNewDao.insert(tTestcaseUiNew);
        QueryWrapper<TStepUiNew> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("testcase_id", id);
        List<TStepUiNew> stepUiNews = stepUiNewDao.selectList(queryWrapper);
        if (CollectionUtil.isNotEmpty(stepUiNews)) {
            for (TStepUiNew stepUiNew : stepUiNews) {
                stepUiNew.setId(null);
                stepUiNew.setTestcaseId(tTestcaseUiNew.getId().toString());
            }
            stepUiNewService.saveBatch(stepUiNews);
        }
        return tTestcaseUiNew;

    }

    @Override
    public TTestcaseUiNew businesstoCase(Long id) {
        TTestcaseUiNew tTestcaseUiNew = tTestcaseUiNewDao.selectById(id);
        if (tTestcaseUiNew == null) {
            throw new IllegalArgumentException("该用例已删除");
        }
        QueryWrapper<TStepUiNew> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("action_type", "uiAction12");
        queryWrapper.eq("element_id", id);
        List<TStepUiNew> uiAction12 = stepUiNewDao.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(uiAction12)) {
            //没有被引用直接转
            tTestcaseUiNew.setCaseType(1L);
            tTestcaseUiNewDao.updateById(tTestcaseUiNew);
            return tTestcaseUiNew;
        } else {
            //如果被引用复制
            TTestcaseUiNew tTestcaseUiNew1 = copyCaseById(id);
            tTestcaseUiNew.setRemark("好吧，你成功了!");
            return tTestcaseUiNew1;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addOrEditCases(TestCaseMetoDto caseMeto) {
        String caseCode;
        Long caseNum = caseMeto.getCaseId();
        if (caseNum == 0) {
            LambdaQueryWrapper<TTestcaseUiNew> lambdaQueryWrapper = new LambdaQueryWrapper();
            lambdaQueryWrapper.eq(TTestcaseUiNew::getName, caseMeto.getCaseName());
            TTestcaseUiNew testcaseUiNew = testcaseUiNewService.getOne(lambdaQueryWrapper);
            if (ObjectUtil.isNotNull(testcaseUiNew)) {
                throw new BizException(TESTCASE_EXIST_ERROR);
            }
            caseCode = SerialUtil.generateCaseId();
        } else {
            TTestcaseUiNew testcaseUiNew = testcaseUiNewService.lambdaQuery().eq(TTestcaseUiNew::getId, caseMeto.getCaseId()).one();
            caseCode = testcaseUiNew.getCaseId();
        }
        UpdateWrapper<TTestcaseUiNew> updateWrap = new UpdateWrapper<>();
        updateWrap.eq("case_id", caseCode);
        TTestcaseUiNew testcase = TTestcaseUiNew.builder().caseId(caseCode).name(caseMeto.getCaseName()).envId(caseMeto.getEnvName())
                .createBy(SecurityUtils.getCurrentUsername()).projectId(caseMeto.getProjectName()).status(caseMeto.getStatus())
                .suiteId(caseMeto.getSuitId()).teamId(caseMeto.getTeamName()).build();

        boolean flags = testcaseUiNewService.saveOrUpdate(testcase, updateWrap);
        //同步更新映射id表
        //查询加入的最新自增id
        if (caseNum == 0) {
            Long newId = testcaseUiNewService.lambdaQuery().orderByDesc(TTestcaseUiNew::getId).last("limit 1").one().getId();
            TSuiteCaseUi suiteCaseUi = TSuiteCaseUi.builder().suiteId(caseMeto.getSuitId()).caseId(newId).build();
            suiteCaseUiService.save(suiteCaseUi);
        }
        return flags;
    }

    @Override
    @Transactional
    public boolean removeCase(Long caseId) {

        LambdaQueryWrapper<TSuiteCaseUi> lambdaQueryWrap = new LambdaQueryWrapper<>();
        lambdaQueryWrap.eq(TSuiteCaseUi::getCaseId,caseId);
        suiteCaseUiService.remove(lambdaQueryWrap);
        return testcaseUiNewService.deleteById(caseId);
    }


}
