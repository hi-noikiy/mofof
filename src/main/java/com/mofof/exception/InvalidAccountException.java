package com.mofof.exception;

import org.apache.shiro.authc.AuthenticationException;

public class InvalidAccountException extends AuthenticationException {

  private static final long serialVersionUID = 5462780466374739974L;

  public InvalidAccountException(String message) {
    super(message);
  }
  
}
