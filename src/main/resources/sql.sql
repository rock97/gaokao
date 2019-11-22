CREATE TABLE `school` (
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `code` int NOT NULL COMMENT '院校编码',
    `name` varchar(300) NOT NULL DEFAULT '' COMMENT '院校名称',
    `city` varchar(300) NOT NULL DEFAULT '' COMMENT '院校所在城市',
    `type` varchar(100) NOT NULL DEFAULT '' COMMENT '院校类型',
    `subjection` varchar(100) NOT NULL DEFAULT '' COMMENT '院校隶属',
    `academic_level` varchar(50) NOT NULL DEFAULT '' COMMENT '学历层次',
    `985` bool NOT NULL DEFAULT false COMMENT '是否985',
    `211` bool NOT NULL DEFAULT false COMMENT '是否211',
    `stream_university` bool NOT NULL DEFAULT false COMMENT '是否一流大学',
    `stream_course` bool NOT NULL DEFAULT false COMMENT '是否一流学科',
    `other` varchar(255) NOT NULL DEFAULT '' COMMENT '院校隶属',
    `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '院校隶属',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间，默认当前时间',
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间，默认当前时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COMMENT='学校表';


CREATE TABLE `score_line` (
     `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
     `school_code` int  NULL COMMENT '院校编码',
     `school_name` varchar(300)  NULL DEFAULT '' COMMENT '院校名称',
     `student_type` varchar(100)  NULL DEFAULT '' COMMENT '考生类型',
     `subject_name` varchar(1000)  NULL DEFAULT '' COMMENT '专业名称',
     `year` int  NULL DEFAULT 0 COMMENT '年份',
     `admission_batch` varchar(50)  NULL DEFAULT '' COMMENT '录取批次',
     `max_score` int  NULL DEFAULT 0 COMMENT '最高分',
     `average_score` int  NULL DEFAULT 0 COMMENT '平均分',
     `min_score` int  NULL DEFAULT 0 COMMENT '最低分',
     `other` varchar(255)  NULL DEFAULT '' COMMENT '其它',
     `remark` varchar(255)  NULL DEFAULT '' COMMENT '备注',
     `create_time` timestamp  NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间，默认当前时间',
     `update_time` timestamp  NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间，默认当前时间',
     PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COMMENT='专业录取分数表';
create  index idx_school_code on score_line (school_code);