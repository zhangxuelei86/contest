package com.lhl.contest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.lhl.contest.entity.Img;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ImgMapper extends BaseMapper<Img> {
    @Select("select * from img where info_id = #{infoId} and status = '正常'")
    @Results(
            {
                    @Result(column = "id", property = "imgId"),
                    @Result(column = "info_id", property = "infoId"),
                    @Result(column = "name", property = "imgName"),
                    @Result(column = "img", property = "imgByte"),
                    @Result(column = "status", property = "imgStatus")
            }
    )
    List<Img> selectImgByInfoId(int infoId);

    @Insert("insert into img(info_id,name,img) values(#{infoId},#{imgName},#{imgByte})")
    int insertImg(Img img);
}
