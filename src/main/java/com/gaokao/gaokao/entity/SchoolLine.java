package com.gaokao.gaokao.entity;

import java.util.Date;
import lombok.Data;

@Data
public class SchoolLine {
    private Integer id;
    private Integer year;
    private Integer schoolCode;
    private String schoolName;
    private String studentType;
    private String localProvinceName;
    private int provinceLine;
    private String admissionBatch;
    private int maxScore;
    private int averageScore;
    private int minScore;
    private String other;
    private String remark;
    private Date createTime;
    private Date updateTime;
}
