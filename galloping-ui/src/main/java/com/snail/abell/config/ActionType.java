package com.snail.abell.config;

/**
 * @author Abell
 * @date 2022/12/19
 */
public enum ActionType {

    JS("JS操作"),
    BROWER("浏览器操作"),
    FILETYPE("文件操作"),
    KETBOARD("键盘操作"),
    MOUSE("鼠标操作"),
    ELEMENTTYPE("元素操作"),
    COOKERTYPE("Cooker操作"),
    SCENTYPE("场景导入"),
    ALTERTYPE("弹窗操作"),
    API("Api操作"),


    TEXTASSERTION("文本断言"),
    IMAGEASSERTION("图片断言"),
    ELEMENTASSERTION("元素断言"),
    JSASSERTION("JavaScript"),
    REQUESTASSERTION("发送接口请求");


    private String message;

    public String message() {
        return this.message;
    }

    ActionType(String message){
        this.message = message;
    }
}
