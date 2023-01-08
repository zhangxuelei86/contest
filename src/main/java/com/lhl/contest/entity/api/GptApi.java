package com.lhl.contest.entity.api;

import com.alibaba.fastjson.JSONObject;

public class GptApi {
    private static final String GPT_API_HOST = "https://api.openai.com/v1/completions";
    private static final String API_KEY = "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";
    private static final float temperature = 0.9F;
    //生成文本的长度
    private static final int maxTokens = 2048;
    //模型类型
    private static final String modelEngine = "text-davinci-003";

    private String prompt;

    public GptApi(String prompt) {
        this.prompt = prompt;
    }

    public String getGptResult() throws Exception {
        PostRequest postRequest = new PostRequest(GPT_API_HOST, null, API_KEY);
        JSONObject response = JSONObject.parseObject(postRequest.sendPostRequest(buildParams()));
       //获取包含结果的字段
        String choices = response.getString("choices");
        //去除中括号
        JSONObject result1 = JSONObject.parseObject(choices.substring(1, choices.length() - 1));

        return result1.getString("text");
    }

    public String buildParams() {
        String requestBody = String.format("{\"model\":\"%s\",\"prompt\":\"%s\",\"max_tokens\":%d,\"temperature\":%f}",
                modelEngine, prompt, maxTokens, temperature
        );
        return requestBody;
    }

}
