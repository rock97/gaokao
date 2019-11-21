package com.gaokao.gaokao.mapper;

import com.gaokao.gaokao.entity.School;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface SchoolDao {
    String INSERT_COLUMNS = "code,name,city,type,subjection,academic_level,`985`,`211`,stream_university,stream_course,other,remark";
    String INSERT_FIELD = "#{item.code},#{item.name},#{item.city},#{item.type},#{item.subjection},#{item.academicLevel},#{item.985},#{item.211},#{item.streamUniversity},#{item.streamCourse},#{item.other},#{item.remark}";

    @Insert("<script>  insert into school ("+INSERT_COLUMNS+") values "+
            "<foreach collection='list'  item='item' index='index' separator=','> ("+
            INSERT_FIELD+
            ")</foreach> </script>"
            )
    @Options(useGeneratedKeys = true, keyProperty = "item.id", keyColumn = "id")
    public void insertList(List<School> list);

}
