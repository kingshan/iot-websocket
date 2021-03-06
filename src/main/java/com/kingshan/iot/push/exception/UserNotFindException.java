package com.kingshan.iot.push.exception;

/**
 * <p>
 * 用户未找到
 * </p>
 *
 * @author: Kingshan
 * @since: 2019/11/7 15:58
 */
public class UserNotFindException extends RuntimeException {
    public UserNotFindException(String s) {
        super(s);
    }

    public UserNotFindException(String s, Throwable throwable) {
        super(s, throwable);
    }

}
