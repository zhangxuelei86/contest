package com.lhl.contest.entity.api;

import com.alibaba.fastjson.JSONObject;

import javax.swing.text.html.StyleSheet;
import java.util.Map;

public class GptApi {
    private static final String GPT_API_HOST = "https://api.openai.com/v1/completions";
    private static final String API_KEY = "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";
    //error.html测试
    //private static final String API_KEY = "sk-E8MLru1FWvgqjFrrnMPNT3BlbkFJg8JZpX6auxsdtuKQ88";
    private static final float temperature = 0.9F;
    //生成文本的长度
    private static final int maxTokens = 2048;
    //模型类型
    private static final String modelEngine = "text-davinci-003";

    private String prompt;

    public GptApi(String prompt) {
        this.prompt = prompt;
    }

    public Map getGptResult() throws Exception {
        PostRequest postRequest = new PostRequest(GPT_API_HOST, null, API_KEY);
        Map responseMap = postRequest.sendPostRequest(buildParams());
        JSONObject responseJSON = JSONObject.parseObject((String) responseMap.get("data"));
        //获取包含结果的字段
        String choices = responseJSON.getString("choices");
        //去除中括号
        JSONObject resultProcess = JSONObject.parseObject(choices.substring(1, choices.length() - 1));

        responseMap.replace("data", resultProcess.getString("text"));
        return responseMap;
    }

    public String buildParams() {
        String requestBody = String.format("{\"model\":\"%s\",\"prompt\":\"%s\",\"max_tokens\":%d,\"temperature\":%f}",
                modelEngine, prompt, maxTokens, temperature
        );
        return requestBody;
    }

}
