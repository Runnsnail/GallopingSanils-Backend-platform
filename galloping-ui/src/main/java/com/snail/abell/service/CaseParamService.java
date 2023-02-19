package com.snail.abell.service;

import java.util.List;
import com.snail.abell.entity.CaseParam;
import com.baomidou.mybatisplus.extension.service.IService;
    /**
 * @author Abell
 * @date  2023/2/5
 */
public interface CaseParamService extends IService<CaseParam>{


    int updateBatchSelective(List<CaseParam> list);

}
