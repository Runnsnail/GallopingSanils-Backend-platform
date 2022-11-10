package com.snail.abell.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Abell
 */
@Data
public class Result<T> implements Serializable {
    private Integer code;
  private String message;
  private boolean success = true;
  private T data;
  @JsonIgnore
  private ResultCode resultCode;

  private Result() {
   }

  public void setResultCode(ResultCode resultCode) {
    this.resultCode = resultCode;
    this.code = resultCode.code();
    this.message = resultCode.message();
   }

  public Result(ResultCode resultCode, T data) {
    this.code = resultCode.code();
    this.message = resultCode.message();
    this.data = data;
   }

  public static <T> Result<T> success() {
    Result<T> result = new Result<>();
    result.setResultCode(ResultCode.SUCCESS);
    return result;
   }

  public static <T> Result<T> success(T data) {
    Result<T> result = new Result<>();
    result.setResultCode(ResultCode.SUCCESS);
    result.setData(data);
      return result;
   }
}

