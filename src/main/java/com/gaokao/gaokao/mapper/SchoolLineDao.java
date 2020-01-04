package com.gaokao.gaokao.mapper;

import com.gaokao.gaokao.entity.SchoolLine;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface SchoolLineDao {
    String INSERT_COLUMNS = "school_code,school_name,student_type,province_line,year,admission_batch,average_score,min_score,other,remark,local_province_name";
    String INSERT_FIELD = "#{item.schoolCode},#{item.schoolName},#{item.studentType},#{item.provinceLine},#{item.year},#{item.admissionBatch},#{item.averageScore},#{item.minScore},#{item.other},#{item.remark},#{item.localProvinceName}";

    @Insert("<script>  insert into school_line ("+INSERT_COLUMNS+") values "+
            "<foreach collection='list'  item='item' index='index' separator=','> ("+
            INSERT_FIELD+
            ")</foreach> </script>"
    )
    @Options(useGeneratedKeys = true, keyProperty = "item.id", keyColumn = "id")
    public void insertList(List<SchoolLine> list);

    @Update("update school_line set school_name = #{name} where school_code=#{code}")
    public void update(String name,int code);

    @Select("select school_code as schoolCode,school_name as schoolName from school_line where school_code = #{code} order by id desc limit 1")
    SchoolLine selectBySchoolCode(int code);

}
