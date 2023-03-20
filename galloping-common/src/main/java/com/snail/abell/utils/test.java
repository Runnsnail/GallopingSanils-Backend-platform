package com.snail.abell.utils;

/**
 * @author Abell
 * @date 2023/3/12
 */
public class test {

    public static void main(String[] args) {

        /**
         * 群机器人复制到的秘钥secret
         */
        String SECRET = "SEC9152f24ec8e1b46edb34d9ddb1d08e78a0951ffbd251ca99ed89157a1652c1fc";

        /**
         * 配置机器人的webhook
         */
        String WEBHOOK = "https://oapi.dingtalk.com/robot/send?access_token=0a4ea724db5502b35060c19c8b5be2a320ace28a759992579512b3459fc106b9";

        String PlanName = "测试计划";

        DingTalkPushUtil.testIndependentSendActionCardMsg(SECRET,WEBHOOK,PlanName);

//        DingTalkPushUtil.testSendTextMsg(SECRET,WEBHOOK);
//
//        DingTalkPushUtil.testSendFeedCardMsg(SECRET,WEBHOOK);
//
//        DingTalkPushUtil.testSendMarkdownMsg(SECRET,WEBHOOK);
    }
}
