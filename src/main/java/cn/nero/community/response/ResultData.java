package cn.nero.community.response;

import lombok.Data;

import java.util.Date;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/23
 */
@Data
public class ResultData<T> {

    private int status;
    private String message;
    private T data;
    private String timeStamp;

    public ResultData(){
        this.timeStamp = new Date().toString();
    }

    public static <T> ResultData<T> success(T data){
        ResultData<T> resultData = new ResultData<>();
        resultData.setStatus(ResponseEnum.SUCCESS.getCode());
        resultData.setMessage(ResponseEnum.SUCCESS.getMessage());
        resultData.setData(data);
        return resultData;
    }

    public static <T> ResultData<T> failure(int code, String message){
        ResultData<T> resultData = new ResultData<>();
        resultData.setStatus(code);
        resultData.setMessage(message);
        return resultData;
    }

}
