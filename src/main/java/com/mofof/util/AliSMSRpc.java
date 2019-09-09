package com.mofof.util;

import java.util.HashMap;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

public class AliSMSRpc {

  public static void sendSMS(String dest) {
    sendSMS(dest, "MoFoF", "SMS_163847932");
  }

  public static void sendSMS(String dest, String signName, String templateCode) {
    DefaultProfile profile = DefaultProfile.getProfile("default", "LTAIOE9IPmhB5kzm", "9uxXJiaH3mLlOm0R6Uy0TULp3yUypu");
    IAcsClient client = new DefaultAcsClient(profile);

    CommonRequest request = new CommonRequest();
    // request.setProtocol(ProtocolType.HTTPS);
    request.setMethod(MethodType.POST);
    request.setDomain("dysmsapi.aliyuncs.com");
    request.setVersion("2017-05-25");
    request.setAction("SendSms");
    request.putQueryParameter("PhoneNumbers", dest);
    request.putQueryParameter("SignName", signName);
    request.putQueryParameter("TemplateCode", templateCode);
    HashMap map = new HashMap();
    map.put("code", 666666);
    request.putQueryParameter("TemplateParam", JSON.toJSONString(map));
    //request.putQueryParameter("TemplateParam", "{\"code\":\"1\"}");
    try {
      CommonResponse response = client.getCommonResponse(request);
      System.out.println(response.getData());
    } catch (ServerException e) {
      e.printStackTrace();
    } catch (ClientException e) {
      e.printStackTrace();
    }
  }
}