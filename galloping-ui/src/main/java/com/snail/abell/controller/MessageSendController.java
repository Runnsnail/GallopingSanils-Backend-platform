package com.snail.abell.controller;

import com.snail.abell.apiInterface.ResponseResult;
import com.snail.abell.entity.MessageSend;
import com.snail.abell.logInterface.Log;
import com.snail.abell.service.MessageSendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Abell
 * @date 2023/3/12
 */

@Api(tags = "Robort消息通知")
@Validated
@RestController
@ResponseResult
@RequestMapping("/messageSend")
public class MessageSendController {

    @Resource
    private MessageSendService messageService;


    @PostMapping("/saveMessage")
    @ApiOperation(value = "保存WEBHOOK")
    @Log(description = "保存WEBHOOK")
    public boolean saveMessageByMessage(@RequestBody MessageSend message) {



        return messageService.saveOrUpdateByMessage(message);
    }

}
