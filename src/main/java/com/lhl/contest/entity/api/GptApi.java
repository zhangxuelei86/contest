package com.lhl.contest.entity.api;

import com.alibaba.fastjson.JSONObject;
import com.lhl.contest.entity.PostRequest;

public class GptApi {
    private static final String GPT_API_HOST = "https://api.openai.com/v1/completions";
    private static final String API_KEY = "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";
    private float temperature = 0.8F;
    //生成文本的长度
    private int maxTokens = 2048;
    //模型类型
    private String modelEngine = "text-davinci-003";

    private String prompt;

    public GptApi(String prompt) {
        this.prompt = prompt;
    }

    public String getGptResult() throws Exception {
        PostRequest postRequest = new PostRequest(GPT_API_HOST, null, API_KEY);
        JSONObject response = JSONObject.parseObject(postRequest.sendPostRequest(buildParams()));
        String result0 = response.getString("choices");
        //去除中括号
        JSONObject result1 = JSONObject.parseObject(result0.substring(1, result0.length() - 1));
        return result1.getString("text");
    }

    public String buildParams() {
        String requestBody = String.format("{\"model\":\"%s\",\"prompt\":\"%s\",\"max_tokens\":%d,\"temperature\":%f}",
                modelEngine, prompt, maxTokens, temperature
        );
        return requestBody;
    }

}
