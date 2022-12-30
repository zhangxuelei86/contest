package com.lhl.contest.service;

import com.lhl.contest.entity.Img;
import com.lhl.contest.entity.Info;
import com.lhl.contest.mapper.ImgMapper;
import com.lhl.contest.mapper.InfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class InfoService {
    @Autowired
    private InfoMapper infoMapper;

    @Autowired
    private ImgMapper imgMapper;

    //测试：获取数据库所有图片
    public List listAllImg() {
        List<Img> imgList = imgMapper.selectImgByInfoId(2);
        for (Img img : imgList) {
            System.out.println(img);
        }
        return imgList;
    }

    //获取所有信息
    public List listAllInfo() throws IOException {
        List<Info> infoList = infoMapper.listAllInfo();
        for (Info info : infoList) {
            int infoId = info.getInfoId();
            //获取Img类列表
            List<Img> imgList = info.getImgList();
            //对信息卡片中的图片列表中的图片转化为MultipartFile文件，并保存地址
            List<String> imgPathList = new ArrayList<>();
            for (Img img : imgList) {
                imgPathList.add(getImgPath(img));
            }
            //设置为图片地址列表
            info.setImgList(imgPathList);
        }
        return infoList;
    }


    //保存信息到数据库
    public boolean saveInfo(Info info) throws IOException {
        if (infoMapper.insertInfo(info) > 0) {
            List<MultipartFile> imgList = info.getImgList();
            for (MultipartFile imgFile : imgList) {
                Img img = new Img();
                img.setInfoId(info.getInfoId());
                img.setImgByte(imgFile.getBytes());
                img.setImgName(imgFile.getOriginalFilename());
                imgMapper.insertImg(img);
            }
            return true;
        } else {
            return false;
        }
    }

    //查询关键字相关信息
    public List search(String keyword) throws IOException {
        List<Info> infoList = infoMapper.search("%" + keyword + "%");
        for (Info info : infoList) {
            int infoId = info.getInfoId();
            //获取Img类列表
            List<Img> imgList = info.getImgList();
            //对信息卡片中的图片列表中的图片转化为MultipartFile文件，并保存地址
            List<String> imgPathList = new ArrayList<>();
            for (Img img : imgList) {
                imgPathList.add(getImgPath(img));
            }
            //设置为图片地址列表
            info.setImgList(imgPathList);
        }
        return infoList;
    }

    //将数据库读取的比特格式图片转化为MultipartFile文件，并保存在服务器上，返回地址
    public String getImgPath(Img img) throws IOException {
        byte[] imgByte = img.getImgByte();
        InputStream is = new ByteArrayInputStream(imgByte);
        //转化为MultipartFile文件
        MultipartFile imgFile = new MockMultipartFile(img.getImgName(), is);
        //设置图片保存路径(dir:图片所在路径，path：图片保存路径)
        File dir = new File(getSavePath());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File path = new File(dir.getPath() + "/" + img.getImgName());
        //保存图片到服务器相应路径
        imgFile.transferTo(path);
        //返回浏览器端访问路径
        return "/img/" + img.getImgName();
    }


    //获取服务器上图片保存地址
    public String getSavePath() {
        // 这里需要注意的是ApplicationHome是属于SpringBoot的类
        // 获取项目下resources/static/img路径
        ApplicationHome applicationHome = new ApplicationHome(this.getClass());

        //！！！！本地运行使用！！！！
        // 保存目录位置根据项目需求可随意更改
        return applicationHome.getDir().getParentFile()
                .getParentFile().getAbsolutePath() + "\\src\\main\\resources\\static\\img\\";

        //！！！！部署后运行使用！！！！
        //getDir获取打包后，jar包所在目录
        //return applicationHome.getDir()+"/static/img/";
    }
}
