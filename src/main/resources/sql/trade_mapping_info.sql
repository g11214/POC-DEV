create table trade_mapping_info
(
  tmi_mer_id        int comment '商品id',
  tmi_trans_flow_id int comment '对应的订单id',
  tmi_user_id       int comment '下单的用户id',
  tmi_name          varchar(20) not null comment '商品名称',
  tmi_desc          varchar(200) comment '商品描述',
  tmi_count         int comment '商品个数',
  tmi_expense       numeric     not null comment '商品价格',
  tmi_create_time   varchar(20) not null comment '创建时间',
  constraint primary key (tmi_mer_id, tmi_trans_flow_id),
  constraint tmi_trans_check foreign key (tmi_trans_flow_id) references trade_trans_info (tti_flow_id)
) comment '商品映射订单信息表'