package com.example.mybatisdemo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class ResultModel<T> implements Serializable {

    private static final long serialVersionUID = 1633103597476168895L;

    private int status = 200;
    private String message;
    private T data;
    private String serverTime;

    {
        serverTime = DateFormatUtils.format(new Date(), "yyyy-MM-dd-HH-mm-ss");
    }

    public ResultModel(int status, String message, T data){
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResultModel(ResultStatus status, T data){
        this.status = status.getCode();
        this.message = status.getMessage();
        this.data = data;
    }

    public static ResultModel error(ResultStatus status){
        return new ResultModel(status, null);
    }

    public static ResultModel success(Object data){
        return new ResultModel(ResultStatus.SUCCESS, data);
    }
}
