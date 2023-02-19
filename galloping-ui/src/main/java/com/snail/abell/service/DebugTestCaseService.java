package com.snail.abell.service;

import com.snail.abell.base.Result;
import com.snail.abell.entity.TStepUiNew;
import org.openqa.selenium.WebDriver;

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
    public Result runTestCase(Map<String,String> context) throws Exception;

    public Result isExcBusiness(TStepUiNew stepUiNew, Map<String, String> context, WebDriver driver,StringBuilder stepLogMessage) throws Exception;
}
