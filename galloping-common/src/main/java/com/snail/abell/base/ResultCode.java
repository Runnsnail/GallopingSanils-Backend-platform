package com.snail.abell.base;

public enum ResultCode {
    /* 成功状态码 */
    SUCCESS(200, "成功"),

    /* 错误状态码 */
    NOT_FOUND(404, "请求的资源不存在"),
    INTERNAL_ERROR(500, "服务器内部错误"),
    PARAMETER_EXCEPTION(501, "请求参数校验异常"),

    /* 业务状态码 */
    USER_NOT_EXIST_ERROR(10001, "用户不存在"),
    PROJECT_NOT_EXIST_ERROR(10002, "项目不存在"),
    PROJECT_EXIST_ERROR(10003,"项目已存在"),
    DICT_NOT_EXIST_ERROR(10004, "字典不存在"),
    PAGEELEMENT_EXIST_ERROR(10005,"页面元素已存在"),
    TESTSUIT_NOT_EXIST_ERROR(10006,"测试用例集不存在"),
    TESTSUIT_EXIST_ERROR(10007,"测试用例集已存在"),
    TESTSTEP_SAVE_ERROR(10008,"测试步骤保存失败"),
    TESTSTEP_EDIT_ERROR(10009,"测试步骤更新失败"),
    SUITCASE_EXIST_ERROR(10010,"测试用例集已存在"),
    DELECT_FAILED(10010,"删除失败"),
    TEAM_EXIST_ERROR(10011,"团队已存在"),
    TEAM_NOT_EXIST_ERROR(10012,"团队不存在"),
    TESTCASE_EXIST_ERROR(10013,"测试用例已存在"),

    /* 用例状态吗 */
    SCEN_EXIST_ERROR(20001,"场景执行失败"),
    DRIVER_EXIST_ERROR(20002,"浏览器驱动初始化失败，请选择正确的驱动"),
    FAIL_STEP(20003,"测试用例执行失败"),
    SUCCESS_STEP(20004,"测试用例执行成功"),
    SKIP_STEP(20005,"忽略步骤执行"),
    CASEVARIABLE_EXIST_ERROR(20006,"用例变量已存在"),
    ASSERTION_FAIL(20007,"断言失败"),
    ;

    private Integer code;
    private String message;

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
