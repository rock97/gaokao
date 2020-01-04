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

create table score_line
(
  id                  bigint unsigned auto_increment comment '主键ID'
    primary key,
  school_code         int                                     null comment '院校编码',
  school_name         varchar(300)  default ''                null comment '院校名称',
  student_type        varchar(100)  default ''                null comment '考生类型',
  subject_name        varchar(1000) default ''                null comment '专业名称',
  local_province_name varchar(100)                            null comment '考生所在地',
  year                int           default 0                 null comment '年份',
  admission_batch     varchar(50)   default ''                null comment '录取批次',
  max_score           int           default 0                 null comment '最高分',
  average_score       int           default 0                 null comment '平均分',
  min_score           int           default 0                 null comment '最低分',
  other               varchar(255)  default ''                null comment '其它',
  remark              varchar(255)  default ''                null comment '备注',
  create_time         timestamp     default CURRENT_TIMESTAMP null comment '记录创建时间，默认当前时间',
  update_time         timestamp     default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '记录更新时间，默认当前时间'
)ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COMMENT='高校专业年份考生地方录取分数线';

create index idx_school_code
  on score_line (school_code);

create table school_line
(
  id                  bigint unsigned auto_increment comment '主键ID'
    primary key,
  school_code         int                                     null comment '院校编码',
  school_name         varchar(300)  default ''                null comment '院校名称',
  student_type        varchar(100)  default ''                null comment '考生类型',
  year                int           default 0                 null comment '年份',
  admission_batch     varchar(50)   default ''                null comment '录取批次',
  average_score       int           default 0                 null comment '平均分',
  min_score           int           default 0                 null comment '最低分',
  province_line       int default 0 null comment '省控线',
  other               varchar(255)  default ''                null comment '其它',
  remark              varchar(255)  default ''                null comment '备注',
  create_time         timestamp     default CURRENT_TIMESTAMP null comment '记录创建时间，默认当前时间',
  update_time         timestamp     default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '记录更新时间，默认当前时间',
  local_province_name varchar(100)                            null comment '考生所在地'
)ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COMMENT='高校投档分数线';

create INDEX idx_school_code  on school_line(school_code);
