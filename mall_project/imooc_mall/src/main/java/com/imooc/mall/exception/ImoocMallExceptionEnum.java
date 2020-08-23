package com.imooc.mall.exception;

/**
 * 异常枚举
 */
public enum ImoocMallExceptionEnum {
    NEED_USER_NAME(10001,"用户名不能为空"),
     NEED_PASSWORD(10002,"密码不能为空"),
    PASSWORD_TOO_SHORT(1003,"密码长度不能少于8位"),
    NAME_EXISTED(1004,"不允许重名"),
    INSERT_FAILED(1005,"插入失败，请重试"),
    WRONG_PASSWORD(1006,"密码错误"),
    NEED_LOGIN(1007,"用户未登录"),
    UPDATE_FAILED(1008,"更新失败"),
    NEED_ADMIN(1009,"无管理员权限"),
    PARA_NOT_NULL(1010,"参数不能为空"),
    CREATE_FAILED(1011,"新增失败"),
    REQUEST_PARAM_ERROR(1012,"参数错误"),
    DELECT_FAILED(1013,"删除失败"),
    MKDIR_FAILED(1014,"文件夹创建失败"),
    UPLOAD_FAILED(1015,"图片上传失败"),
    NOT_SALE(1016,"商品状态不可售"),
    NOT_ENOUGH(1017,"商品库存不足"),
    CART_EMPTY(1018,"购物车已勾选的商品为空"),
    NO_ENUM(1019,"未找到对应的枚举类"),
    NO_ORDER(1020,"订单不存在"),
    NOT_YOUR_ORDER(1021,"订单不属于你"),
    WRONG_ORDER_STATUS(1021,"订单状态不符"),
    SYSTEM_ERROR(20000,"系统异常");


    /**
     * 异常码
     */
    Integer code;
    /**
     * 异常信息
     */
    String msg;

    ImoocMallExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
