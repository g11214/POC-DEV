create table pub_addr_info
(
  pai_id          int auto_increment comment '地址id'
    primary key,
  pai_user_id     int                                not null comment '所属用户id',
  pai_user_mobile varchar(20)                        not null comment '下单手机号',
  pai_name        varchar(10)                        not null comment '收货人姓名',
  pai_addr_info   varchar(200)                       not null comment '收货地址',
  pai_create_time datetime default CURRENT_TIMESTAMP not null comment '添加时间'
)
  comment '收货地址信息表';