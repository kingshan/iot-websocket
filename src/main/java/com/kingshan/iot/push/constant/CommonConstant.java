package com.kingshan.iot.push.constant;

/**
 * <p>
 *
 * </p>
 *
 * @author: Kingshan
 * @since: 2019/11/7 14:44
 */
public interface CommonConstant {
    /** {@code 500 Server Error} (HTTP/1.0 - RFC 1945) */
    public static final Integer SC_INTERNAL_SERVER_ERROR_500 = 500;
    public static final Integer SC_ERROR_USER_NOT_FOUND_301 = 301;
    public static final Integer SC_ERROR_GROUP_NOT_FOUND_302 = 302;
    /** {@code 200 OK} (HTTP/1.0 - RFC 1945) */
    public static final Integer SC_OK_200 = 200;

    /**访问权限认证未通过 510*/
    public static final Integer SC_JEECG_NO_AUTHZ=510;
}
