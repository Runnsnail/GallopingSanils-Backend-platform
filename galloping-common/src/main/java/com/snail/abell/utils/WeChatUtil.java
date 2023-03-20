package com.snail.abell.utils;

import cn.hutool.http.HttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Abell
 * @date 2023/3/14
 */
@Slf4j
public class WeChatUtil {

    private static final Logger logger = LoggerFactory.getLogger(WeChatUtil.class);


    /**
     * 文本消息
     */
    public static void testSendTextMsg(String WEBHOOK,String PlanName) {

        String textMsg = "{\n" +
                "    \"msgtype\": \"markdown\",\n" +
                "    \"markdown\": {\n" +
                "        \"content\": \"定时任务已完成<font color=\\\"warning\\\">"+PlanName+"</font>，请相关同事注意。\\n\n" +
                "         >登录官网查看:<font color=\\\"comment\\\">GallopingSnail</font>\n" +
                "    }\n" +
                "}";

        try {

            HttpRequest.post(WEBHOOK)
                    .body(textMsg)
                    .execute().body();

        } catch (Exception e) {
            logger.error("钉钉群消息发送异常，异常原因：{}", e.toString());
        }
    }


    /**
     * 卡片消息
     */
    public static void SendCardMsg(String WEBHOOK,String PlanName) {


        String sendCardMessage = "{\n" +
                "    \"msgtype\":\"template_card\",\n" +
                "    \"template_card\":{\n" +
                "        \"card_type\":\"news_notice\",\n" +
                "        \"source\":{\n" +
                "            \"icon_url\":\"http://116.62.219.238:9000/images/snails.jpg\",\n" +
                "            \"desc\":\"GallopingSnail\",\n" +
                "            \"desc_color\":0\n" +
                "        },\n" +
                "        \"main_title\":{\n" +
                "            \"title\":\"定时任务: \""+PlanName+",\n" +
                "            \"desc\":\"请登录GS官网进行查看\"\n" +
                "        },\n" +
                "        \"card_image\":{\n" +
                "            \"url\":\"http://116.62.219.238:9000/images/1678780668849.jpg\",\n" +
                "            \"aspect_ratio\":2.25\n" +
                "        },\n" +
                "        \"horizontal_content_list\":[\n" +
                "            {\n" +
                "                \"keyname\":\"GallopingSnail官网\",\n" +
                "                \"value\":\"点击访问\",\n" +
                "                \"type\":1,\n" +
                "                \"url\":\"http://127.0.0.1:8119/login\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"keyname\":\"执行定时任务\",\n" +
                "                \"value\":\"GS机器人\",\n" +
                "                \"type\":2,\n" +
                "                \"media_id\":\"MEDIAID\"\n" +
                "            }\n" +
                "        ],\n" +
                "}";
        try {
            HttpRequest.post(WEBHOOK)
                    .body(sendCardMessage)
                    .execute().body();

        } catch (Exception e) {
            logger.error("企业微信群消息发送异常，异常原因：{}", e.toString());
        }
    }

}
