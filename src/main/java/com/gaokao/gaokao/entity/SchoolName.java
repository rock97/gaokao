package com.gaokao.gaokao.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SchoolName {
    @JsonProperty("school_id")
    private int schoolId;
    private String name;
}
