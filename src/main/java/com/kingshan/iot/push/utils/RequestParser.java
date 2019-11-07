package com.kingshan.iot.push.utils;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author: Kingshan
 * @since: 2019/11/7 10:39
 */
public class RequestParser {
    private FullHttpRequest fullReq;

    /**
     * 构造一个解析器
     * @param req
     */
    public RequestParser(FullHttpRequest req) {
        this.fullReq = req;
    }

    /**
     * 解析请求参数
     * @return 包含所有请求参数的键值对, 如果没有参数, 则返回空Map
     *
     * @throws Exception
     * @throws IOException
     */
    public Map<String, String> parse() {
        try{
            HttpMethod method = fullReq.method();

            Map<String, String> parmMap = new HashMap<>();

            if (HttpMethod.GET == method) {
                // 是GET请求
                QueryStringDecoder decoder = new QueryStringDecoder(fullReq.uri());
                decoder.parameters().entrySet().forEach(entry -> {
                    // entry.getValue()是一个List, 只取第一个元素
                    parmMap.put(entry.getKey(), entry.getValue().get(0));
                });
            } else if (HttpMethod.POST == method) {
                // 是POST请求
                HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(fullReq);
                decoder.offer(fullReq);

                List<InterfaceHttpData> parmList = decoder.getBodyHttpDatas();

                for (InterfaceHttpData parm : parmList) {

                    Attribute data = (Attribute) parm;
                    parmMap.put(data.getName(), data.getValue());
                }

            } else {
                return null;
            }
            return parmMap;

        }catch (Exception ex){
            return  null;
        }

    }
}
