package com.snail.abell.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snail.abell.entity.CaseStep;

import java.util.List;
    /**
 * @author Abell
 * @date  2022/10/29
 */
public interface CaseStepService extends IService<CaseStep>{


    int updateBatch(List<CaseStep> list);

    int updateBatchSelective(List<CaseStep> list);

    int batchInsert(List<CaseStep> list);

    int insertOrUpdate(CaseStep record);

    int insertOrUpdateSelective(CaseStep record);

}
