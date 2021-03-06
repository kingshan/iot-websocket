package com.kingshan.iot.push.exception;

/**
 * <p>
 *  群组未找到
 * </p>
 *
 * @author: Kingshan
 * @since: 2019/11/7 15:58
 */
public class GroupNotFindException extends RuntimeException {
    public GroupNotFindException(String s) {
        super(s);
    }

    public GroupNotFindException(String s, Throwable throwable) {
        super(s, throwable);
    }

}
