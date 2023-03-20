package com.snail.abell.config;

import com.snail.abell.entity.MessageSend;
import com.snail.abell.entity.TTestsuiteUi;
import com.snail.abell.service.MessageSendService;
import com.snail.abell.service.TTestsuiteUiService;
import com.snail.abell.utils.DingTalkPushUtil;
import com.snail.abell.utils.WeChatUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Abell
 * @date 2023/3/14
 */
@Slf4j
@Component
public class JobFireListener implements JobListener {

    private final Logger logger = LoggerFactory.getLogger(JobFireListener.class);


    @Resource
    private TTestsuiteUiService testsuiteUiService;

    @Resource
    private MessageSendService messageSendService;



    /**
     * 群机器人复制到的秘钥secret
     */
    String secret = "SEC9152f24ec8e1b46edb34d9ddb1d08e78a0951ffbd251ca99ed89157a1652c1fc";

    /**
     * 配置机器人的webhook
     */
    String webhook = "https://oapi.dingtalk.com/robot/send?access_token=0a4ea724db5502b35060c19c8b5be2a320ace28a759992579512b3459fc106b9";


    private String name = "simpleJobListener";


    @Override
    public String getName() {
        return name;
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {

    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {

    }

    /**
     * job 执行
     *
     * @param context
     * @param e
     */
    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException e) {
        JobDataMap data = context.getMergedJobDataMap();

        String planName = context.getJobDetail().getKey().getName();

        Long suitId = testsuiteUiService.lambdaQuery().eq(TTestsuiteUi::getName,planName).one().getId();

        MessageSend messageSend = messageSendService.lambdaQuery().eq(MessageSend::getId,suitId).one();

        if (e == null) {
            log.info("job执行成功{}",planName);

            //发送钉钉通知
            if(messageSend.getType()==2){
                DingTalkPushUtil.testIndependentSendActionCardMsg(messageSend.getSecret(),messageSend.getTokenId(),planName);
            }

            //发送企业微信通知
            if(messageSend.getType()==1){
                WeChatUtil.SendCardMsg(messageSend.getTokenId(),planName);
            }

        } else {

            logger.error("job fire error: {}",e.getMessage());

            //发送钉钉通知
            if(messageSend.getType()==2){
                DingTalkPushUtil.testIndependentSendActionCardMsg(messageSend.getSecret(),messageSend.getTokenId(),planName);
            }

            //发送企业微信通知
            if(messageSend.getType()==1){
                WeChatUtil.SendCardMsg(messageSend.getTokenId(),planName);
            }

        }


    }


}
