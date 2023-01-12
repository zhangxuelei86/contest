package com.lhl.contest.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lhl.contest.entity.api.GptApi;
import com.lhl.contest.entity.api.TransApi;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ChatGptController {

    @PostMapping("/chatgpt")
    public String chatGpt(@RequestParam("prompt") String prompt) throws Exception{
        //chatgpt不支持中文，需要进行翻译
        TransApi transApi = new TransApi();
        GptApi gptApi = new GptApi(transApi.getTransResult("auto","en",prompt));
        //获取结果map
        Map responseMap = gptApi.getGptResult();
        //将结果翻译为中文
        String transResult = transApi.getTransResult("auto","zh", (String) responseMap.get("data"));

        responseMap.replace("data",transResult.replace("\\n","\n"));

        JSONObject responseJson = new JSONObject(responseMap);
        return String.valueOf(responseJson);
    }

    //!!!测试
    @PostMapping("/testTrans")
    public String trans(@RequestParam("prompt") String prompt) throws Exception {
        TransApi transApi = new TransApi();
        return transApi.getTransResult("auto","en",prompt);

    }

}
