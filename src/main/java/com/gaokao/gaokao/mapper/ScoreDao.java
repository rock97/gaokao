package com.gaokao.gaokao.mapper;

import com.gaokao.gaokao.entity.School;
import com.gaokao.gaokao.entity.Score;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ScoreDao {
    String INSERT_COLUMNS = "local_province_name,school_code,school_name,student_type,subject_name,year,admission_batch,max_score,average_score,min_score,other,remark";
    String INSERT_FIELD = "#{item.localProvinceName},#{item.schoolCode},#{item.schoolName},#{item.studentType},#{item.subjectName},#{item.year},#{item.admissionBatch},#{item.maxScore},#{item.averageScore},#{item.minScore},#{item.other},#{item.remark}";

    @Insert("<script>  insert into score_line ("+INSERT_COLUMNS+") values "+
            "<foreach collection='list'  item='item' index='index' separator=','> ("+
            INSERT_FIELD+
            ")</foreach> </script>"
    )
    @Options(useGeneratedKeys = true, keyProperty = "item.id", keyColumn = "id")
    public void insertList(List<Score> list);

    @Update("update score_line set school_name = #{name} where school_code=#{code}")
    public void update(String name,int code);

    @Select("select school_code as schoolCode,school_name as schoolName from score_line where school_code = #{code} order by id desc limit 1")
    Score selectBySchoolCode(int code);

}
