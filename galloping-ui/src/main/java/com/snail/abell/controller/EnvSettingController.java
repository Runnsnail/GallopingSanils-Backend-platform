package com.snail.abell.controller;

import com.snail.abell.Vo.EnvVo;
import com.snail.abell.apiInterface.ResponseResult;
import com.snail.abell.entity.TEnv;
import com.snail.abell.logInterface.Log;
import com.snail.abell.service.TEnvService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Abell
 * @date 2023/3/12
 */

@Api(tags = "环境设置")
@Validated
@RestController
@ResponseResult
@RequestMapping("/EnvNameSetting")
public class EnvSettingController {


    @Resource
    private TEnvService envService;

    @GetMapping("/getEnvList")
    @ApiOperation("查询Team所有成员")
    @Log(description = "查询Team所有成员")
    public ArrayList<HashMap<String, String>> getEnvList() {
        ArrayList<HashMap<String, String>> envList = envService.getUiEnv();
        return envList;
    }


    @PostMapping("/updateEnv")
    @ApiOperation(value = "新增")
    @Log(description = "新增")
    public boolean updateEnv(@RequestBody TEnv env) {

        return envService.saveOrUpdateByEnv(env);
    }


}
