package com.gaokao.gaokao.mapper;

import com.gaokao.gaokao.entity.School;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface SchoolDao {
    String INSERT_COLUMNS = "code,name,city,type,subjection,academic_level,`985`,`211`,stream_university,stream_course,other,remark";
    String QUERY_COLUMNS = "code,name,city,type,subjection,academic_level as academicLevel,`985`,`211`,stream_university as streamUniversity,stream_course,other,remark";
    String INSERT_FIELD = "#{item.code},#{item.name},#{item.city},#{item.type},#{item.subjection},#{item.academicLevel},#{item.985},#{item.211},#{item.streamUniversity},#{item.streamCourse},#{item.other},#{item.remark}";

    @Insert("<script>  insert into school ("+INSERT_COLUMNS+") values "+
            "<foreach collection='list'  item='item' index='index' separator=','> ("+
            INSERT_FIELD+
            ")</foreach> </script>"
            )
    @Options(useGeneratedKeys = true, keyProperty = "item.id", keyColumn = "id")
    void insertList(List<School> list);

    @Update("update school set `985` = #{is985},`211`= #{is211},code = #{code} where name=#{name}")
    void update(String name,int is985,int is211,int code);

    @Select("select "+QUERY_COLUMNS+" from school where code = #{code} limit 1")
    School selectBySchoolCode(int code);

}
