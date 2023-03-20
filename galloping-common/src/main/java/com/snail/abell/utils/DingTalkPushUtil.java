package com.snail.abell.utils;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Abell
 * @date 2023/3/12
 */
@Slf4j
public class DingTalkPushUtil {


    /**
     * 日志注入
     */
    private static final Logger logger = LoggerFactory.getLogger(DingTalkPushUtil.class);





    /**
     * 文本消息
     */
    public static void testSendTextMsg(String SECRET,String WEBHOOK) {
        try {
            sendTextMsg("我就是我, 是不一样的烟火", Lists.newArrayList(), Lists.newLinkedList(), false,SECRET,WEBHOOK);
        } catch (Exception e) {
            logger.error("钉钉群消息发送异常，异常原因：{}", e.toString());
        }
    }

    /**
     * link类型
     */
    public static void testSendLinkMsg(String SECRET,String WEBHOOK) {
        try {
            sendLinkMsg("时代的火车向前开",
                    "这个即将发布的新版本，创始人xx称它为红树林。而在此之前，每当面临重大升级，产品经理们都会取一个应景的代号，这一次，为什么是红树林",
                    "https://www.dingtalk.com/s?__biz=MzA4NjMwMTA2Ng==&mid=2650316842&idx=1&sn=60da3ea2b29f1dcc43a7c8e4a7c97a16&scene=2&srcid=09189AnRJEdIiWVaKltFzNTw&from=timeline&isappinstalled=0&key=&ascene=2&uin=&devicetype=android-23&version=26031933&nettype=WIFI",
                    "",SECRET,WEBHOOK);
        } catch (Exception e) {
            logger.error("钉钉群消息发送异常，异常原因：{}", e.toString());
        }
    }

    /**
     * 整体跳转ActionCard类型
     */
    public static void testWholeSendActionCardMsg(String SECRET,String WEBHOOK) {
        try {
            sendActionCardMsg("打造一间咖啡厅", "![screenshot](https://img.alicdn.com/tfs/TB1NwmBEL9TBuNjy1zbXXXpepXa-2400-1218.png) \\n #### 乔布斯 20 年前想打造的苹果咖啡厅 \\n\\n Apple Store 的设计正从原来满满的科技感走向生活化，而其生活化的走向其实可以追溯到 20 年前苹果一个建立咖啡馆的计划",
                    "0", "阅读全文", "https://www.dingtalk.com/",SECRET,WEBHOOK);
        } catch (Exception e) {
            logger.error("钉钉群消息发送异常，异常原因：{}", e.toString());
        }
    }

    /**
     * 独立跳转ActionCard类型
     */
    public static void testIndependentSendActionCardMsg(String SECRET,String WEBHOOK,String PlanName) {
        try {
            List<Map<String, String>> btnsList = new ArrayList<>();
            Map<String, String> map1 = new HashMap<>();
            map1.put("title", "点击查看: GallopingSnail"+PlanName);
            map1.put("messageURL", "http://127.0.0.1:8119/login");
            btnsList.add(map1);
            sendActionCardMsg("GallopingSnail", "![screenshot](http://116.62.219.238:9000/images/1678780668849.jpg) \n GallopingSnail 一站式解决自动化测试平台",
                    "0", btnsList,SECRET,WEBHOOK);
        } catch (Exception e) {
            logger.error("钉钉群消息发送异常，异常原因：{}", e.toString());
        }
    }

    /**
     * FeedCard类型
     */
    public static void testSendFeedCardMsg(String SECRET,String WEBHOOK) {
        try {
            List<Map<String, String>> btnsList = new ArrayList<>();
            Map<String, String> map1 = new HashMap<>();
            Map<String, String> map2 = new HashMap<>();
            map1.put("title", "时代的火车向前开1");
            map1.put("messageURL", "https://www.dingtalk.com/");
            map1.put("picURL", "https://img.alicdn.com/tfs/TB1NwmBEL9TBuNjy1zbXXXpepXa-2400-1218.png");
            map2.put("title", "时代的火车向前开2");
            map2.put("messageURL", "https://www.dingtalk.com/");
            map2.put("picURL", "https://img.alicdn.com/tfs/TB1NwmBEL9TBuNjy1zbXXXpepXa-2400-1218.png");
            btnsList.add(map1);
            btnsList.add(map2);
            sendFeedCardMsg(btnsList,SECRET,WEBHOOK);
        } catch (Exception e) {
            logger.error("钉钉群消息发送异常，异常原因：{}", e.toString());
        }
    }

    /**
     * markdown类型
     */
    public static void testSendMarkdownMsg(String SECRET,String WEBHOOK) {
        try {
            sendMarkdownMsg("GallopingSnail", "#### 杭州天气 @150XXXXXXXX \\n > 9度，西北风1级，空气良89，相对温度73%\\n > ![screenshot](https://img.alicdn.com/tfs/TB1NwmBEL9TBuNjy1zbXXXpepXa-2400-1218.png)\\n > ###### 10点20分发布 [天气](https://www.dingtalk.com) \\n",
                    true, Lists.newArrayList(), Lists.newArrayList(),SECRET,WEBHOOK);
        } catch (Exception e) {
            logger.error("钉钉群消息发送异常，异常原因：{}", e.toString());
        }
    }

    /**
     * 群里面发送消息
     *
     * @param content    消息内容
     * @param isAtAll    是否@所有人
     * @param mobileList 被@人的手机号
     * @param userIdList 被@人的用户userid
     * @throws Exception
     */
    public static void sendTextMsg(String content, List<String> mobileList, List<String> userIdList, boolean isAtAll,String SECRET,String WEBHOOK) throws Exception {
        String dingUrl = getDingUrl(SECRET,WEBHOOK);
        // 组装请求内容
        String reqStr = buildReqTextStr(content, isAtAll, mobileList, userIdList);
        // 推送消息（http请求）
        String result = HttpUtil.post(dingUrl, reqStr);
        handleErrorCode(result);
        logger.info("钉钉请求发送成功，返回结果：" + result);
    }

    /**
     * 组装请求报文-text类型
     *
     * @param content    消息内容
     * @param isAtAll    是否@所有人
     * @param mobileList 被@人的手机号
     * @param atUserIds  被@人的用户userid
     * @return
     */
    private static String buildReqTextStr(String content, boolean isAtAll, List<String> mobileList, List<String> atUserIds) {
        Map<String, String> contentMap = Maps.newHashMap();
        contentMap.put("content", content);

        Map<String, Object> atMap = Maps.newHashMap();
        atMap.put("isAtAll", isAtAll);
        atMap.put("atMobiles", mobileList);
        atMap.put("atUserIds", atUserIds);

        Map<String, Object> reqMap = Maps.newHashMap();
        reqMap.put("msgtype", "text");
        reqMap.put("text", contentMap);
        reqMap.put("at", atMap);

        return JSONObject.toJSONString(reqMap);
    }

    /**
     * 群里面发送消息
     *
     * @param title      消息标题
     * @param messageUrl 点击消息跳转的URL
     * @param picUrl     图片URL
     * @param text       消息内容
     * @throws Exception
     */
    public static void sendLinkMsg(String title, String text, String messageUrl, String picUrl,String SECRET,String WEBHOOK) throws Exception {
        String dingUrl = getDingUrl(SECRET,WEBHOOK);
        // 组装请求内容
        String reqStr = buildReqLinkStr(title, text, messageUrl, picUrl);
        // 推送消息（http请求）
        String result = HttpUtil.post(dingUrl, reqStr);
        handleErrorCode(result);
        logger.info("钉钉请求发送成功，返回结果：" + result);
    }

    /**
     * 组装请求报文-link类型
     *
     * @param title      消息标题
     * @param text       消息内容
     * @param messageUrl 点击消息跳转的URL
     * @param picUrl     图片URL
     * @return
     */
    private static String buildReqLinkStr(String title, String text, String messageUrl, String picUrl) {
        Map<String, String> linkMap = Maps.newHashMap();
        linkMap.put("text", text);
        linkMap.put("title", title);
        linkMap.put("picUrl", picUrl);
        linkMap.put("messageUrl", messageUrl);

        Map<String, Object> reqMap = Maps.newHashMap();
        reqMap.put("msgtype", "link");
        reqMap.put("link", linkMap);

        return JSONObject.toJSONString(reqMap);
    }

    /**
     * 群里面发送消息
     *
     * @param title      首屏会话透出的展示内容
     * @param text       markdown格式的消息
     * @param isAtAll    是否@所有人
     * @param mobileList 被@人的手机号
     * @param atUserIds  被@人的用户userid
     * @throws Exception
     */
    public static void sendMarkdownMsg(String title, String text, boolean isAtAll, List<String> mobileList, List<String> atUserIds,String SECRET,String WEBHOOK) throws Exception {
        String dingUrl = getDingUrl(SECRET,WEBHOOK);
        // 组装请求内容
        String reqStr = buildReqMarkdownStr(title, text, isAtAll, mobileList, atUserIds);
        // 推送消息（http请求）
        String result = HttpUtil.post(dingUrl, reqStr);
        handleErrorCode(result);
        logger.info("钉钉请求发送成功，返回结果：" + result);
    }

    /**
     * 组装请求报文-markdown类型
     *
     * @param title      首屏会话透出的展示内容
     * @param text       markdown格式的消息
     * @param isAtAll    是否@所有人
     * @param mobileList 被@人的手机号
     * @param atUserIds  被@人的用户userid
     * @return
     */
    private static String buildReqMarkdownStr(String title, String text, boolean isAtAll, List<String> mobileList, List<String> atUserIds) {
        Map<String, String> contentMap = Maps.newHashMap();
        contentMap.put("title", title);
        contentMap.put("text", text);

        Map<String, Object> atMap = Maps.newHashMap();
        atMap.put("isAtAll", isAtAll);
        atMap.put("atMobiles", mobileList);
        atMap.put("atUserIds", atUserIds);

        Map<String, Object> reqMap = Maps.newHashMap();
        reqMap.put("msgtype", "markdown");
        reqMap.put("markdown", contentMap);
        reqMap.put("at", atMap);

        return JSONObject.toJSONString(reqMap);
    }

    /**
     * 群里面发送消息
     *
     * @param title          首屏会话透出的展示内容
     * @param text           markdown格式的消息
     * @param btnOrientation 0：按钮竖直排列 1：按钮横向排列
     * @param singleTitle    单个按钮的标题
     * @param singleURL      点击消息跳转的URL
     * @throws Exception
     */
    public static void sendActionCardMsg(String title, String text, String btnOrientation, String singleTitle, String singleURL,String SECRET,String WEBHOOK) throws Exception {
        String dingUrl = getDingUrl(SECRET,WEBHOOK);
        // 组装请求内容
        String reqStr = buildReqActionCard(title, text, btnOrientation, singleTitle, singleURL,SECRET,WEBHOOK);
        // 推送消息（http请求）
        String result = HttpUtil.post(dingUrl, reqStr);
        handleErrorCode(result);
        logger.info("钉钉请求发送成功，返回结果：" + result);
    }

    /**
     * 组装请求报文-整体跳转ActionCard类型
     *
     * @param title          首屏会话透出的展示内容
     * @param text           markdown格式的消息
     * @param btnOrientation 0：按钮竖直排列 1：按钮横向排列
     * @param singleTitle    单个按钮的标题
     * @param singleURL      点击消息跳转的URL
     * @return
     */
    private static String buildReqActionCard(String title, String text, String btnOrientation, String singleTitle, String singleURL,String SECRET,String WEBHOOK) {
        Map<String, String> actionCardMap = Maps.newHashMap();
        actionCardMap.put("title", title);
        actionCardMap.put("text", text);
        actionCardMap.put("btnOrientation", btnOrientation);
        actionCardMap.put("singleTitle", singleTitle);
        actionCardMap.put("singleURL", singleURL);

        Map<String, Object> reqMap = Maps.newHashMap();
        reqMap.put("msgtype", "actionCard");
        reqMap.put("actionCard", actionCardMap);

        return JSONObject.toJSONString(reqMap);
    }

    /**
     * 群里面发送消息
     *
     * @param title          首屏会话透出的展示内容
     * @param text           markdown格式的消息
     * @param btnOrientation 0：按钮竖直排列 1：按钮横向排列
     * @param btnsList       按钮
     * @throws Exception
     */
    public static void sendActionCardMsg(String title, String text, String btnOrientation, List<Map<String, String>> btnsList,String SECRET,String WEBHOOK) throws Exception {
        String dingUrl = getDingUrl(SECRET,WEBHOOK);
        // 组装请求内容
        String reqStr = buildReqActionCard(title, text, btnOrientation, btnsList,SECRET,WEBHOOK);
        // 推送消息（http请求）
        String result = HttpUtil.post(dingUrl, reqStr);
        handleErrorCode(result);
        logger.info("钉钉请求发送成功，返回结果：" + result);
    }

    /**
     * 组装请求报文-独立跳转ActionCard类型
     *
     * @param title          首屏会话透出的展示内容
     * @param text           markdown格式的消息
     * @param btnOrientation 0：按钮竖直排列 1：按钮横向排列
     * @param btnsList       按钮
     * @return
     */
    private static String buildReqActionCard(String title, String text, String btnOrientation, List<Map<String, String>> btnsList,String SECRET,String WEBHOOK) {
        Map<String, Object> actionCardMap = Maps.newHashMap();
        actionCardMap.put("title", title);
        actionCardMap.put("text", text);
        actionCardMap.put("btnOrientation", btnOrientation);
        actionCardMap.put("btns", btnsList);

        Map<String, Object> reqMap = Maps.newHashMap();
        reqMap.put("msgtype", "actionCard");
        reqMap.put("actionCard", actionCardMap);

        return JSONObject.toJSONString(reqMap);
    }

    /**
     * 群里面发送消息
     *
     * @param linksList 列表集合
     * @throws Exception
     */
    public static void sendFeedCardMsg(List<Map<String, String>> linksList,String SECRET,String WEBHOOK) throws Exception {
        String dingUrl = getDingUrl(SECRET,WEBHOOK);
        // 组装请求内容
        String reqStr = buildReqFeedCard(linksList);
        // 推送消息（http请求）
        String result = HttpUtil.post(dingUrl, reqStr);
        handleErrorCode(result);
        logger.info("钉钉请求发送成功，返回结果：" + result);
    }

    /**
     * 组装请求报文-FeedCard类型
     *
     * @param linksList 列表集合
     * @return
     */
    private static String buildReqFeedCard(List<Map<String, String>> linksList) {
        Map<String, Object> feedCardMap = Maps.newHashMap();
        feedCardMap.put("links", linksList);

        Map<String, Object> reqMap = Maps.newHashMap();
        reqMap.put("msgtype", "feedCard");
        reqMap.put("feedCard", feedCardMap);

        return JSONObject.toJSONString(reqMap);
    }

    /**
     * 获取请求url
     *
     * @return
     */
    private static String getDingUrl(String SECRET,String WEBHOOK) throws Exception {
        // 获取系统时间戳
        Long timestamp = System.currentTimeMillis();
        // 拼接
        String stringToSign = timestamp + "\n" + SECRET;
        // 使用HmacSHA256算法计算签名
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(SECRET.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
        // 进行Base64 encode 得到最后的sign，可以拼接进url里
        String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)), "UTF-8");
        // 钉钉机器人地址（配置机器人的webhook），为了让每次请求不同，避免钉钉拦截，加上时间戳
        String dingUrl = WEBHOOK + "&timestamp=" + timestamp + "&sign=" + sign;
        return dingUrl;
    }

    /**
     * errcode处理
     *
     * @param resultStr
     */
    private static void handleErrorCode(String resultStr) {
        if (StringUtils.isEmpty(resultStr)) {
            throw new RuntimeException("返回结果为空");
        }
        JSONObject jsonObject = JSONObject.parseObject(resultStr);
        if (310000 == jsonObject.getLong("errcode")) {
            throw new RuntimeException("keywords not in content");
        }
    }
}
