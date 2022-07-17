package com.snail.abell.exception;

import com.snail.abell.base.ResultCode;

public class BizException extends BaseException {
  public BizException(ResultCode resultCode) {
    super(resultCode);
   }
}

