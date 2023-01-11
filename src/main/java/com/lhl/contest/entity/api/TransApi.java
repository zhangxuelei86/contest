package com.lhl.contest.entity.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Map;

public class TransApi {
    private static final String TRANS_API_HOST = "http://api.fanyi.baidu.com/api/trans/vip/translate";
    private static final String appid = "20230103001517593";
    private static final String apiKey = "j4V4T2fI4QM5KPbbDtE5";

    public String getTransResult(String from, String to, String query) throws Exception {
        PostRequest postRequest = new PostRequest(TRANS_API_HOST, appid, apiKey);
        Map responseMap = postRequest.sendPostRequest(buildParams(from,to,query));
        //包含源文本与翻译文本
        String transResult = JSONObject.parseObject((String) responseMap.get("data")).getString("trans_result");
        //转化为JSON数组
        JSONArray transJSONArray = JSONArray.parseArray(transResult);
        //对结果进行拼接
        String result ="";
        for (int i=0;i<transJSONArray.size();i++){
            JSONObject jsonObject = transJSONArray.getJSONObject(i);
            result+=jsonObject.getString("dst")+"\\n";
        }
        //获取翻译后的结果
        return result;
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
