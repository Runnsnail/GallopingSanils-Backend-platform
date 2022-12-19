package com.snail.abell.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snail.abell.Vo.CaseStepVo;
import com.snail.abell.dao.TStepUiNewMapper;
import com.snail.abell.dto.ElementObject;
import com.snail.abell.dto.PageObject;
import com.snail.abell.dto.StepMapper;
import com.snail.abell.dto.StepMessageDto;
import com.snail.abell.entity.CaseStep;
import com.snail.abell.entity.ProjectPage;
import com.snail.abell.entity.TPageElement;
import com.snail.abell.entity.TStepUiNew;
import com.snail.abell.service.CaseStepService;
import com.snail.abell.service.TPageElementService;
import com.snail.abell.service.TStepUiNewService;
import com.snail.abell.utils.SecurityUtils;
import com.snail.abell.utils.SerialUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Abell
 * @date 2022/10/29
 */
@Service
public class TStepUiNewServiceImpl extends ServiceImpl<TStepUiNewMapper, TStepUiNew> implements TStepUiNewService {

    @Resource
    private TStepUiNewMapper stepUiNewMapper;
    @Resource
    private TStepUiNewService stepUiNewService;
    @Resource
    private CaseStepService caseStepService;
    @Resource
    private TPageElementService pageElementService;
    @Resource
    private com.snail.abell.service.ProjectPageService ProjectPageService;

    @Override
    public int updateBatch(List<TStepUiNew> list) {
        return baseMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<TStepUiNew> list) {
        return baseMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<TStepUiNew> list) {
        return baseMapper.batchInsert(list);
    }

    @Override
    public int insertOrUpdateSelective(TStepUiNew record) {
        return baseMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertOrUpdateWithBLOBs(TStepUiNew record) {
        return baseMapper.insertOrUpdateWithBLOBs(record);
    }

    @Override
    public String savaStep(List<TStepUiNew> uiTestStepList) {
        if (uiTestStepList.size() > 0) {
            List<TStepUiNew> byTestcaseId = stepUiNewMapper.findByTestcaseId(uiTestStepList.get(0).getTestcaseId());
            for (TStepUiNew stepUi : byTestcaseId) {
                boolean flag = false;
                for (TStepUiNew uiTestStep : uiTestStepList) {
                    if (uiTestStep.getId() != null && stepUi.getId().equals(uiTestStep.getId())) {
                        //如果存在步骤id不为空且在数据库存在，就不需要删除
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    stepUiNewMapper.deleteById(stepUi.getId());
                }
            }
            Long sort = 0L;
            for (TStepUiNew uiTestStep : uiTestStepList) {
                uiTestStep.setSort(sort);
                sort = sort + 1;
                if (uiTestStep.getId() == null) {
                    uiTestStep.setUpdateBy(SecurityUtils.getCurrentUsername());
                    uiTestStep.setCreateBy(SecurityUtils.getCurrentUsername());
                    stepUiNewMapper.insert(uiTestStep);
                } else {
                    uiTestStep.setUpdateBy(SecurityUtils.getCurrentUsername());
                    stepUiNewMapper.updateById(uiTestStep);
                }
            }
        }
        return "保存用例步骤成功";
    }

    @Override
    public List<TStepUiNew> getStepsByCaseId(String caseId) {
        List<CaseStep> caseStepList = caseStepService.lambdaQuery().eq(CaseStep::getCaseId, caseId).list();
        List<Integer> stepIds = caseStepList.stream().map(CaseStep::getStepId).collect(Collectors.toList());
        List<TStepUiNew> stepList = stepUiNewMapper.selectBatchIds(stepIds);
        List<TStepUiNew> sortsList = stepList.stream().sorted(Comparator.comparing(TStepUiNew::getSort)).collect(Collectors.toList());
        return sortsList;
    }

    @Override
    public String addCaseStep(CaseStepVo caseStepVo) {
        TStepUiNew stepUiNew = new TStepUiNew();
        String caseCode;
        if (caseStepVo.getTestcaseId().equals("1")) {
            caseCode = SerialUtil.generateCaseId();
            caseStepVo.setTestcaseId(caseCode);
        }
        BeanUtils.copyProperties(caseStepVo, stepUiNew);

        stepUiNewService.saveOrUpdate(stepUiNew);
        TStepUiNew newCaseStep = stepUiNewService.lambdaQuery().orderByDesc(TStepUiNew::getId).last("limit 1").one();
        //同步更新用例与步骤映射
        CaseStep caseStep = CaseStep.builder().id(null).stepId(newCaseStep.getId()).caseId(stepUiNew.getTestcaseId()).build();
        caseCode = caseStep.getCaseId();
        caseStepService.saveOrUpdate(caseStep);
        return caseCode;
    }

    @Override
    public StepMessageDto getStepById(Integer stepId) {
        TStepUiNew stepInfo = stepUiNewService.getById(stepId);
        StepMessageDto stepMessageDto = StepMapper.INSTANCE.toStepMessageDto(stepInfo);
        ProjectPage projectPage = ProjectPageService.getById(stepInfo.getPageId());
        if(ObjectUtil.isNotNull(projectPage)){
        PageObject page = PageObject.builder().value(projectPage.getId().toString()).text(projectPage.getPageName()).build();
        stepMessageDto.setPage(page);
        }else {
            stepMessageDto.setPage(PageObject.builder().build());
        }
        TPageElement pageElement = pageElementService.getById(stepInfo.getElementId());
        if (ObjectUtil.isNotNull(pageElement)) {
        ElementObject element = ElementObject.builder().value(pageElement.getId().toString()).text(pageElement.getElementName()).build();
        stepMessageDto.setElement(element);
        }else {
            stepMessageDto.setElement(ElementObject.builder().build());
        }
        return stepMessageDto;
    }

    @Override
    public Boolean updateSortBatch(List<TStepUiNew> stepsList) {
        int count = 0;
        for (TStepUiNew stepUiNew: stepsList) {
            count++;
            LambdaUpdateWrapper<TStepUiNew> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.set(TStepUiNew::getSort,count);
            updateWrapper.eq(TStepUiNew::getId,stepUiNew.getId());
            try {
                stepUiNewService.update(null,updateWrapper);
            }catch (Exception e) {
              throw new RuntimeException();
            }
        }
        return true;
    }
}
