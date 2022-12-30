package com.lhl.contest.controller;

import com.lhl.contest.entity.Info;
import com.lhl.contest.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class InfoController {
    @Autowired
    private InfoService infoService;

    //获取所有信息
    @GetMapping("/listAllInfo")
    public List<Info> listAllInfo() throws IOException {
        return infoService.listAllInfo();
    }

    //上传信息
    @PostMapping("uploadInfo")
    public String uploadInfo(@RequestParam("infoTitle") String infoTitle,
                             @RequestParam("infoContent") String infoContent,
                             @RequestParam("infoType") String infoType,
                             @RequestParam("imgs") MultipartFile[] imgs
    ) throws IOException {
        Info info = new Info();
        info.setInfoTitle(infoTitle);
        info.setInfoContent(infoContent);
        info.setInfoType(infoType);
        //数组转化为列表
        List<MultipartFile> imgList = new ArrayList<MultipartFile>(Arrays.asList(imgs));
        info.setImgList(imgList);
        if (infoService.saveInfo(info)) {
            return "上传成功！";
        } else {
            return "上传失败！";
        }
    }

    //测试：获取数据库所有图片
    @GetMapping("/img")
    public List listAllImg() {
        return infoService.listAllImg();
    }

    //获取关键字进行查询
    @GetMapping("/search")
    public List search(@RequestParam("keyword") String keyword) throws IOException {
        return infoService.search(keyword);
    }
}