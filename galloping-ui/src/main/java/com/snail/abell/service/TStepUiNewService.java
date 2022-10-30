package com.snail.abell.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snail.abell.Vo.CaseStepVo;
import com.snail.abell.entity.TStepUiNew;

import java.util.List;
/**
 * @author Abell
 * @date  2022/10/29
 */
public interface TStepUiNewService extends IService<TStepUiNew>{


    int updateBatch(List<TStepUiNew> list);

    int updateBatchSelective(List<TStepUiNew> list);

    int batchInsert(List<TStepUiNew> list);


    int insertOrUpdateSelective(TStepUiNew record);

    int insertOrUpdateWithBLOBs(TStepUiNew record);

    String savaStep(List<TStepUiNew> uiTestStepList);

    List<TStepUiNew> getStepsByCaseId(String caseId);

    Boolean addCaseStep(CaseStepVo caseStepVo);
}
