package cn.attackme.myuploader.common;

import lombok.Getter;

@Getter
public enum ResponseCode {

    SUCCESS(0, "success"),
    ERROR(504, "系统好像出了故障，您可以向管理员反馈，谢谢！"),
    FAILED(1, "响应失败"),
    VALIDATE_FAILED(2, "参数校验失败"),
    NOT_EXIST(3,"数据不存在或已删除"),


    HAS_EXIST(4,"数据已存在,换一个试试！"),
    FILE_HAS_EXIST(5,"下载的文件不存在,换一个试试！"),


    DESIGN_COMPANY_NOT_EXIST(1001, "design company not exist-->"),


    BRAND_NOT_EXIST(1101, "brand not exist-->"),


    PROJECT_NOT_EXIST(1201, "project not exist-->");






    private int code;  
    private String msg;  

    ResponseCode(int code, String msg) {
        this.code = code;  
        this.msg = msg;  
    }  

}  