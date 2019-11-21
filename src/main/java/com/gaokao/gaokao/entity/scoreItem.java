package com.gaokao.gaokao.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class scoreItem {
    private String average;
    private int year;
    private String min;
    @JsonProperty("school_id")
    private int schoolId;
    @JsonProperty("min_section")
    private String minSection;
    private String max;
    @JsonProperty("special_id")
    private int specialId;
    @JsonProperty("local_province_name")
    private String localProvinceName;
    @JsonProperty("local_batch_name")
    private String localBatchName;
    private String proscore;
    private String spname;
    private String name;
    @JsonProperty("local_type_name")
    private String localTypeName;
    @JsonProperty("dual_class_name")
    private String dualClassName;
}
