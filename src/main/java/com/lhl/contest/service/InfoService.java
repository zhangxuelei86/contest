package com.lhl.contest.service;

import com.lhl.contest.entity.Img;
import com.lhl.contest.entity.Info;
import com.lhl.contest.entity.InfoPara;
import com.lhl.contest.mapper.ImgMapper;
import com.lhl.contest.mapper.InfoParaMapper;
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

    @Autowired
    private InfoParaMapper infoParaMapper;


    //获取信息
    public List listInfo(String keyword) throws IOException {
        List<Info> infoList = null;
        if (keyword == null) {
            //获取所有信息
            infoList = infoMapper.listAllInfo();
        } else {
            //获取相关信息
            infoList = infoMapper.search("%" + keyword + "%");
        }
        for (Info info : infoList) {
            //获取Img类列表
            List<Img> imgList = info.getImgList();
            //对信息卡片中的图片列表中的图片转化为MultipartFile文件，并保存地址
            for (Img img : imgList) {
                img.setImgPath(getImgPath(img));
            }
        }
        return infoList;
    }

    //根据Id获取信息
    public Info getInfoById(int id) throws IOException {
        Info info = infoMapper.selectInfoById(id);
        //获取Img类列表
        List<Img> imgList = info.getImgList();
        //对信息卡片中的图片列表中的图片转化为MultipartFile文件，并保存地址
        for (Img img : imgList) {
            img.setImgPath(getImgPath(img));
        }
        return info;
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
        return "/infoImg/" + img.getImgName();
    }


    //获取服务器上图片保存地址
    public String getSavePath() {
        // 这里需要注意的是ApplicationHome是属于SpringBoot的类
        // 获取项目下resources/static/img路径
        ApplicationHome applicationHome = new ApplicationHome(this.getClass());

        //！！！！本地运行使用！！！！
        // 保存目录位置根据项目需求可随意更改
        return applicationHome.getDir().getParentFile()
                .getParentFile().getAbsolutePath() + "\\src\\main\\resources\\static\\infoImg";

        //！！！！部署后运行使用！！！！
        //getDir获取打包后，jar包所在目录
        //return applicationHome.getDir()+"/static/img/";
    }

    //测试！！！保存信息到数据库
    public boolean saveInfo(Info info) throws IOException {
        if (infoMapper.insertInfo(info) > 0) {
            //保存图片到数据库中
            List<MultipartFile> imgList = info.getImgList();
            for (MultipartFile imgFile : imgList) {
                Img img = new Img();
                //转换为对象
                img.setInfoId(info.getInfoId());
                img.setImgByte(imgFile.getBytes());
                img.setImgName(imgFile.getOriginalFilename());
                if (imgMapper.insertImg(img) <= 0) {
                    return false;
                }
            }
            //保存信息内容段落到数据库中
            List<String> infoParaList = info.getInfoParaList();
            for (String infoParaStr : infoParaList) {
                InfoPara infoPara = new InfoPara();
                //转换为对象
                infoPara.setInfoId(info.getInfoId());
                infoPara.setInfoPara(infoParaStr);
                if (infoParaMapper.insertInfoContent(infoPara) <= 0) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    //测试：获取数据库所有图片
    public List listAllImg() {
        List<Img> imgList = imgMapper.listImgByInfoId(2);
        for (Img img : imgList) {
            System.out.println(img);
        }
        return imgList;
    }

    //测试！！！查询关键字相关信息
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
}
