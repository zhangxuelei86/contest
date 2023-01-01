package com.lhl.contest.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("info_para")
public class InfoPara {
    @TableId(value = "id", type = IdType.AUTO)
    private int infoParaId;
    @TableField("info_id")
    private int infoId;
    @TableField("paragraph")
    private String infoPara;
    @TableField("status")
    private String infoParaStatus;

    public int getInfoParaId() {
        return infoParaId;
    }

    public void setInfoParaId(int infoParaId) {
        this.infoParaId = infoParaId;
    }

    public int getInfoId() {
        return infoId;
    }

    public void setInfoId(int infoId) {
        this.infoId = infoId;
    }

    public String getInfoPara() {
        return infoPara;
    }

    public void setInfoPara(String infoPara) {
        this.infoPara = infoPara;
    }

    public String getInfoParaStatus() {
        return infoParaStatus;
    }

    public void setInfoParaStatus(String infoParaStatus) {
        this.infoParaStatus = infoParaStatus;
    }

    @Override
    public String toString() {
        return "InfoPara{" +
                "infoParaId=" + infoParaId +
                ", infoId=" + infoId +
                ", infoPara='" + infoPara + '\'' +
                ", infoParaStatus='" + infoParaStatus + '\'' +
                '}';
    }
}
