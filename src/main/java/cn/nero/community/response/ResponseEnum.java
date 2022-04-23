package cn.nero.community.response;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/23
 */
public enum ResponseEnum {
    SUCCESS(200, "操作成功"),
    FAILURE(500, "服务器出错,请稍后再试");

    // 自定义状态码
    private final int code;
    // 自定义描述状态
    private final String message;

    ResponseEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
