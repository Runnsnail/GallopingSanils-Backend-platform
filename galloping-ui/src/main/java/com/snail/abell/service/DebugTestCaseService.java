package com.snail.abell.service;

import com.snail.abell.entity.TStepUiNew;

import java.util.Map;

/**
 * @author Abell
 * @date 2022/12/11
 */

public interface DebugTestCaseService {

    /**
     *
     * @param context
     * @return boolean
     */
    public boolean runTestCase(Map<String,String> context);

    public boolean isExcBusiness(TStepUiNew stepUiNew,Map<String, String> context);
}
