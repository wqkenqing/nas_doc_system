package com.nas.web.entity;

import lombok.Data;

import java.util.Map;

@Data
public class ElasticEntity {
    /**
     * 主键标识，用户ES持久化
     */
    private String id;

    /**
     * JSON对象，实际存储数据
     */
    private Map data;


}
