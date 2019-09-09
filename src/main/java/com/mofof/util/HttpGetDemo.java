package com.mofof.util;


import static java.lang.System.out;

import java.io.IOException;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.client.methods.HttpHead;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 描述：<br>
 *
 * @author zhangwh<br>
 * @version 1.0<br>
 *          日期：2019年3月18日 下午7:11:08
 */
public class HttpGetDemo {
    // 请登录http://yjapi.com/DataCenter/MyData
    // 查看我的秘钥 我的Key
    private static final String appkey = "1afb8a25d4024eb790417867efb08c20";
    private static final String seckey = "74488C11D5DCB206C860308273FB2224";

    public static void main(String[] args) {
//        String reqInterNme = "http://api.qichacha.com/ChattelMortgage/GetChattelMortgage";
        String reqInterNme = "http://api.qichacha.com/ECIV4/GetDetailsByName";
        String paramStr = "keyword=新疆庆华能源集团有限公司";
        String status = "";
        try {
            // auth header setting
            HttpHead reqHeader = new HttpHead();
            String[] autherHeader = RandomAuthentHeader();
            reqHeader.setHeader("Token", autherHeader[0]);
            reqHeader.setHeader("Timespan", autherHeader[1]);
            final String reqUri = reqInterNme.concat("?key=").concat(appkey).concat("&").concat(paramStr);
            String tokenJson = HttpHelper.httpGet(reqUri, reqHeader.getAllHeaders());
            out.println(String.format("==========================>this is response:{%s}", tokenJson));

            // parse status from json
            status = FormartJson(tokenJson, "Status");
            out.println(String.format("==========================>Status:{%s}", status));
            if (!HttpCodeRegex.isAbnornalRequest(status)) {
                PrettyPrintJson(tokenJson);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    // 获取返回码 Res Code
    static class HttpCodeRegex {
        private static final String ABNORMAL_REGIX = "(101)|(102)";
        private static final Pattern pattern = Pattern.compile(ABNORMAL_REGIX);

        protected static boolean isAbnornalRequest(final String status) {
            return pattern.matcher(status).matches();
        }
    }

    // 获取Auth Code
    protected static final String[] RandomAuthentHeader() {
        String timeSpan = String.valueOf(System.currentTimeMillis() / 1000);
        String[] authentHeaders = new String[] { DigestUtils.md5Hex(appkey.concat(timeSpan).concat(seckey)).toUpperCase(), timeSpan };
        return authentHeaders;
    }

    // 解析JSON
    protected static String FormartJson(String jsonString, String key) throws JSONException {
        JSONObject jObject = JSON.parseObject(jsonString);
        return (String) jObject.get(key);
    }

    // pretty print 返回值
    protected static void PrettyPrintJson(String jsonString) throws JSONException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Object obj = mapper.readValue(jsonString, Object.class);
            String indented = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
            out.println(indented);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
