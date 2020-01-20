package com.ynet.poc.util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@Data
@ConfigurationProperties(prefix = "aliyun.sms")
@Component
public class AliyunSmsUtil {
    private String accessKeyId;
    private String accessKeySecret;
    private String signName;
    private String templateCode;

    public void sendSmsCode(String phoneNumber) {
        sendSmsCode(phoneNumber, null);
    }

    public void sendSmsCode(String phoneNumber, String code) {
        DefaultProfile profile = DefaultProfile.getProfile("default", this.getAccessKeyId(), this.getAccessKeySecret());
        IAcsClient client = new DefaultAcsClient(profile);
        if (StringUtils.isEmpty(code)) {
            code = getCode();
        }
        log.info("验证码：" + code);
        log.info(this.getSignName());
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        request.putQueryParameter("SignName", this.getSignName());
        request.putQueryParameter("TemplateCode", this.getTemplateCode());
        request.putQueryParameter("TemplateParam", "{\"code\":" + code + "}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            log.info(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    public String getCode() {
        return Integer.toString(new Random().nextInt(8998) + 1001);
    }

}
