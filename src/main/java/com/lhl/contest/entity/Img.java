package com.lhl.contest.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"imgByte","imgStatus","imgName"})//生成JSON忽略字段
@TableName("img")
public class Img {
    @TableId(value = "img_id", type = IdType.AUTO)
    private int imgId;
    @TableField("info_id")
    private int infoId;
    @TableField("name")
    private String imgName;
    @TableField("img")
    private byte[] imgByte;
    @TableField(exist = false)
    private String imgPath;
    @TableField("status")
    private String imgStatus;

    @TableField("serial_num")
    private int serialNum;

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public int getInfoId() {
        return infoId;
    }

    public void setInfoId(int infoId) {
        this.infoId = infoId;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public byte[] getImgByte() {
        return imgByte;
    }

    public void setImgByte(byte[] imgByte) {
        this.imgByte = imgByte;
    }

    public String getImgStatus() {
        return imgStatus;
    }

    public void setImgStatus(String imgStatus) {
        this.imgStatus = imgStatus;
    }

    public int getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(int serialNum) {
        this.serialNum = serialNum;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return "Img{" +
                "imgId=" + imgId +
                ", infoId=" + infoId +
                ", imgName='" + imgName + '\'' +
                ", imgPath=" + imgPath +
                ", imgStatus='" + imgStatus + '\'' +
                ", serialNum=" + serialNum +
                '}';
    }
}

