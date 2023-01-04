package com.lhl.contest.entity.api;

import com.alibaba.fastjson.JSONObject;
import com.lhl.contest.entity.PostRequest;

import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;

public class TransApi {
    private static final String TRANS_API_HOST = "http://api.fanyi.baidu.com/api/trans/vip/translate";
    private static final String appid = "20230103001517593";
    private static final String apiKey = "j4V4T2fI4QM5KPbbDtE5";

    public String getTransResult(String from, String to, String query) throws Exception {
        PostRequest postRequest = new PostRequest(TRANS_API_HOST, appid, apiKey);
        String response = postRequest.sendPostRequest(buildParams(from,to,query));
        //将返回的结果转换为JSON对象
        JSONObject responseJSON = JSONObject.parseObject(response);
        String transResult = responseJSON.getString("trans_result");
        //包含源文本与翻译文本
        JSONObject transJSON = JSONObject.parseObject(transResult.substring(1, transResult.length() - 1));
        //获取翻译后的结果
        return transJSON.getString("dst");
    }

    public String buildParams(String from, String to, String query) throws Exception {
        //随机数
        String salt = String.valueOf(System.currentTimeMillis());

        //签名加密
        String sign0 = appid + query + salt + apiKey;//加密前原文
        //获取MD5算法实例对象
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        //获取需要加密的字符内容对应的的字节信息
        byte[] bytes = md5.digest(sign0.getBytes("UTF-8"));
        StringBuilder sign1 = new StringBuilder();
        for (byte b : bytes) {
            //将加密后的字节转化为 16 进制字符串
            sign1.append(String.format("%02x", b));
        }
        //签名
        String sign = sign1.toString();
        //!!!query发送前需要utf-8编码
        String requestBody = String.format("q=%s&from=%s&to=%s&appid=%s&salt=%s&sign=%s",
                URLEncoder.encode(query), from, to, appid, salt, sign
        );

        return requestBody;
    }
}
