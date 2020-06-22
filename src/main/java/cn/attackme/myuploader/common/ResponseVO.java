package cn.attackme.myuploader.common;

import lombok.Data;

/**
 * @author : +Energy
 * @desc : 公共返回封装
 * @date : 2020/4/16 13:57
 */
@Data
public class ResponseVO<T> {
    private int code;
    private String msg;
    private T data;

    public ResponseVO(T data){
        this(ResponseCode.SUCCESS,data);
    }

    public ResponseVO(int code, String msg, T data){
        this.code=code;
        this.msg = msg;
        this.data=data;
    }

    public ResponseVO(ResponseCode code, T data){
        this.code=code.getCode();
        this.msg =code.getMsg();
        this.data=data;
    }
    public ResponseVO(int code, String msg){
        this.code=code;
        this.msg =msg;
    }
    public static <T> ResponseVO success(T data){
      return new ResponseVO<T> (data);
    }
    public static <T> ResponseVO success(){
        return new ResponseVO<T> (ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg());
    }

}
