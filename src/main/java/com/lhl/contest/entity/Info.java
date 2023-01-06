package com.lhl.contest.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@TableName("info")
public class Info {
    @TableId(value = "info_id", type = IdType.AUTO)
    private int infoId;
    @TableField("title")
    private String infoTitle;
    @TableField(exist = false)
    private List infoParaList;
    @TableField("type")
    private String infoType;
    @JsonIgnore//生成json忽略
    @TableField("status")
    private String infoStatus;
    @TableField(exist = false)
    private List imgList;

    public int getInfoId() {
        return infoId;
    }

    public void setInfoId(int infoId) {
        this.infoId = infoId;
    }

    public String getInfoTitle() {
        return infoTitle;
    }

    public void setInfoTitle(String infoTitle) {
        this.infoTitle = infoTitle;
    }

    public List getInfoParaList() {
        return infoParaList;
    }

    public void setInfoParaList(List infoParaList) {
        this.infoParaList = infoParaList;
    }

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    public String getInfoStatus() {
        return infoStatus;
    }

    public void setInfoStatus(String infoStatus) {
        this.infoStatus = infoStatus;
    }

    public List getImgList() {
        return imgList;
    }

    public void setImgList(List imgList) {
        this.imgList = imgList;
    }

    @Override
    public String toString() {
        return "Info{" +
                "infoId=" + infoId +
                ", infoTitle='" + infoTitle + '\'' +
                ", infoParaList=" + infoParaList +
                ", infoType='" + infoType + '\'' +
                ", infoStatus='" + infoStatus + '\'' +
                ", imgList=" + imgList +
                '}';
    }
}

