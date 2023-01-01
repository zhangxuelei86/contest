package com.lhl.contest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lhl.contest.entity.InfoPara;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InfoParaMapper extends BaseMapper<InfoPara> {
    //测试！！！
    @Insert("insert into info_para(info_id,paragraph) values(#{infoId},#{paragraph})")
    int insertInfoContent(InfoPara infoPara);

    @Select("select * from info_para where info_id=#{infoId}")
    @Results(
            {
                    @Result(column = "id",property = "infoParaId"),
                    @Result(column = "info_id",property = "infoId"),
                    @Result(column = "paragraph",property = "infoPara"),
                    @Result(column = "status",property = "infoParaStatus")
            }
    )
    List<InfoPara> listParaByInfoId(int infoId);
}
