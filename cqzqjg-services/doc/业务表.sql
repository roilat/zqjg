/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/1/1 23:31:07                            */
/*==============================================================*/


drop table if exists BIZ_MEMBER_COMPANY;

drop table if exists BIZ_MEMBER_USER;

drop table if exists BIZ_PORTAL_INFO;

drop table if exists BIZ_PORTAL_INFO_CATEGORY;

drop table if exists BIZ_PROPERTIES;

drop table if exists BIZ_PROPERTIES_HISTORY;

/*==============================================================*/
/* Table: BIZ_MEMBER_COMPANY                                    */
/*==============================================================*/
create table BIZ_MEMBER_COMPANY
(
   id                   bigint not null auto_increment comment '主键',
   type                 varchar(100) comment '会员类型',
   join_date            date comment '加入时间',
   quit_date            date comment '退出时间',
   primary_contact_person varchar(20) comment '主要联系人',
   primary_contact_info varchar(50) comment '主要联系人联系方式',
   company_name         varchar(50) comment '单位名称',
   company_code         varchar(20) comment '企业统一信用代码',
   legal_person         varchar(20) comment '企业法人',
   ownership_pattern    varchar(20) comment '所有制类型',
   company_place        varchar(100) comment '场所',
   registration_date    date comment '注册时间',
   registration_assets  varchar(30) comment '注册资本',
   company_phone        varchar(20) comment '联系电话',
   company_address      varchar(100) comment '联系地址',
   company_fax          varchar(20) comment '传真',
   company_email        varchar(100) comment '邮箱',
   company_url          varchar(100) comment '网址',
   record_status        char(1) comment '记录状态(0草稿,1已发布)',
   create_by            national varchar(50) comment '创建人',
   create_time          datetime comment '创建时间',
   last_update_by       national varchar(50) comment '更新人',
   last_update_time     datetime comment '更新时间',
   del_flag             tinyint(4) default 0 comment '是否删除  -1：已删除  0：正常',
   primary key (id),
   key AK_Key_1 (id)
);

alter table BIZ_MEMBER_COMPANY comment '会员单位信息表';

/*==============================================================*/
/* Table: BIZ_MEMBER_USER                                       */
/*==============================================================*/
create table BIZ_MEMBER_USER
(
   id                   bigint not null auto_increment comment '主键',
   company_name         varchar(50) comment '会员单位名称',
   company_id           int comment '会员单位ID',
   login_name           varchar(20) comment '登录名',
   nick_name            varchar(20) comment '昵称',
   password             varchar(32) comment '密码',
   salt             varchar(40) comment '盐',
   avatar               varchar(100) comment '头像',
   wechat               varchar(32) comment '微信号',
   if_wechat_login      char(1) comment '是否绑定微信',
   approve_status       char(1) comment '审批状态(0待审核,1审核不通过2审核通过)',
   if_locked            char(1) comment '是否已锁定',
   create_by            national varchar(50) comment '创建人',
   create_time          datetime comment '创建时间',
   last_update_by       national varchar(50) comment '更新人',
   last_update_time     datetime comment '更新时间',
   del_flag             tinyint(4) default 0 comment '是否删除  -1：已删除  0：正常',
   primary key (id),
   key AK_Key_1 (id)
);

alter table BIZ_MEMBER_USER comment '会员单位e用户信息表';

/*==============================================================*/
/* Table: BIZ_PORTAL_INFO                                       */
/*==============================================================*/
create table BIZ_PORTAL_INFO
(
   id                   bigint not null auto_increment comment '主键',
   type_code            varchar(20) comment '信息类型code',
   title                varchar(50) comment '标题(category=1)',
   main_desc            varchar(100) comment '主要描述(category=1)',
   sub_desc             varchar(50) comment '子描述(category=1)',
   picture_path         varchar(200) comment '图片路径',
   content              varchar(500) comment '描述内容',
   create_by            national varchar(50) comment '创建人',
   create_time          datetime comment '创建时间',
   last_update_by       national varchar(50) comment '更新人',
   last_update_time     datetime comment '更新时间',
   del_flag             tinyint(4) default 0 comment '是否删除  -1：已删除  0：正常',
   primary key (id)
);

alter table BIZ_PORTAL_INFO comment '首页的显示信息表';

/*==============================================================*/
/* Table: BIZ_PORTAL_INFO_CATEGORY                              */
/*==============================================================*/
create table BIZ_PORTAL_INFO_CATEGORY
(
   type_code            varchar(20) not null comment 'main顶部信息,aboutUs关于我们,news消息资讯,dealScene成交现场,chooseUs选择我们,culture企业文化',
   content_type         char(1) comment '内容类型：1多个封面及相应标题,2多个图片配一段文字,3多个封面配相应描述,4一个图片配一段文字,5多个封面配图文内容',
   del_flag             tinyint(4) default 0 comment '是否删除  -1：已删除  0：正常',
   primary key (type_code)
);

alter table BIZ_PORTAL_INFO_CATEGORY comment '首页信息分类表';

INSERT INTO `biz_portal_info_category` VALUES ('aboutUs', '4', '0');
INSERT INTO `biz_portal_info_category` VALUES ('chooseUs', '4', '0');
INSERT INTO `biz_portal_info_category` VALUES ('culture', '4', '0');
INSERT INTO `biz_portal_info_category` VALUES ('dealScene', '3', '0');
INSERT INTO `biz_portal_info_category` VALUES ('main', '1', '0');
INSERT INTO `biz_portal_info_category` VALUES ('news', '2', '0');

/*==============================================================*/
/* Table: BIZ_PROPERTIES                                        */
/*==============================================================*/
create table BIZ_PROPERTIES
(
   id                   bigint not null auto_increment comment '主键',
   name                 varchar(50) comment '资产名称',
   type                 varchar(20) comment '资产类型',
   cover                varchar(200) comment '封面图片',
   unit                 varchar(10) comment '单位',
   quantity             int comment '资产数量',
   address              varchar(50) comment '所在地区',
   content              varchar(500) comment '内容描述',
   start_own_date       date comment '开始持有时间',
   last_own_date        date comment '最后持有时间',
   create_by            national varchar(50) comment '创建人',
   create_time          datetime comment '创建时间',
   last_update_by       national varchar(50) comment '更新人',
   last_update_time     datetime comment '更新时间',
   del_flag             tinyint(4) default 0 comment '是否删除  -1：已删除  0：正常',
   primary key (id)
);

alter table BIZ_PROPERTIES comment '资产信息表';

/*==============================================================*/
/* Table: BIZ_PROPERTIES_HISTORY                                */
/*==============================================================*/
create table BIZ_PROPERTIES_HISTORY
(
   id                   bigint not null auto_increment comment '主键',
   propery_id           bigint comment '所属资产id',
   new_name             varchar(50) comment '新资产名称',
   old_name             varchar(50) comment '旧资产名称',
   new_cover            varchar(200) comment '新封面图片',
   old_cover            varchar(200) comment '旧封面图片',
   new_type             varchar(20) comment '新资产类型',
   old_type             varchar(20) comment '旧资产类型',
   new_unit             varchar(10) comment '新单位',
   old_unit             varchar(10) comment '旧单位',
   new_quantity         int comment '新资产数量',
   old_quantity         int comment '旧资产数量',
   new_address          varchar(50) comment '新所在地区',
   old_address          varchar(50) comment '旧所在地区',
   new_content          varchar(500) comment '新内容描述',
   old_content          varchar(500) comment '旧内容描述',
   create_by            national varchar(50) comment '创建人',
   create_time          datetime comment '创建时间',
   del_flag             tinyint(4) default 0 comment '是否删除  -1：已删除  0：正常',
   primary key (id)
);

alter table BIZ_PROPERTIES_HISTORY comment '资产信息历史信息表';

