package com.gaokao.gaokao.entity;

import java.util.Date;
import lombok.Data;

@Data
public class School {
    private Integer id;
    private Integer code;
    private String name;
    private String city;
    private String type;
    private String subjection;
    private String academicLevel;
    private boolean is985;
    private boolean is211;
    private boolean isStreamUniversity;
    private boolean isStreamCourse;
    private String other;
    private String remark;
    private Date createTime;
    private Date updateTime;
}
