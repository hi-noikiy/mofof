package com.mofof.util;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * DataServer API访问包装类
 */
public class DataServerHttpUtils {
    private static final String appUrl = "http://localhost:8082";

    public static JSONObject getRegInfo(String name) {
        String encoderName = "";
        try {
            encoderName = URLEncoder.encode(name, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String reqInterNme = appUrl + "/saic/business-info";
        String paramStr = "name=" + encoderName;
        String status = "";
        try {
            // auth header setting
            final String reqUri = reqInterNme.concat("?").concat(paramStr);
            String tokenJson = HttpHelper.httpGet(reqUri, null);
            System.out.println(String.format("==========================>this is response:{%s}", tokenJson));

            JSONObject jObject = JSON.parseObject(tokenJson);
            return jObject;
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new DataServerHttpUtils().appUrl);
//        String reqInterNme = "http://localhost:8082/saic/business-info";
//        String paramStr = "name=新疆庆华能源集团有限公司";
//        String status = "";
//        try {
//            // auth header setting
//            final String reqUri = reqInterNme.concat("?").concat(paramStr);
//            String tokenJson = HttpHelper.httpGet(reqUri, null);
//            System.out.println(String.format("==========================>this is response:{%s}", tokenJson));
//
//            if (!HttpCodeRegex.isAbnornalRequest(status)) {
//                PrettyPrintJson(tokenJson);
//            }
//        } catch (Exception e1) {
//            e1.printStackTrace();
//        }
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
//        String timeSpan = String.valueOf(System.currentTimeMillis() / 1000);
//        String[] authentHeaders = new String[] { DigestUtils.md5Hex(appkey.concat(timeSpan).concat(seckey)).toUpperCase(), timeSpan };
//        return authentHeaders;
        return null;
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
            System.out.println(indented);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
