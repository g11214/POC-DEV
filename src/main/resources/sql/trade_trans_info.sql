create table trade_trans_info
(
  tti_flow_id     int primary key comment '订单流水号',
  tti_user_id     int         not null comment '用户id',
  tti_user_mobile varchar(11) not null comment '用户手机号',
  tti_stt         varchar(2)  not null comment '订单状态:50 初始化，90 支付成功，91，支付失败，80，订单已关闭',
  tti_expense     numeric     not null comment '付款价格',
  tti_addr_id     int         not null comment '下单地址id',
  tti_create_time varchar(20) not null comment '下单时间',
  constraint tti_user_check foreign key (tti_user_id) references platform_user_reg (pur_user_id),
  constraint tti_addr_check foreign key (tti_addr_id) references pub_addr_info (pai_id)
) comment '订单交易表'