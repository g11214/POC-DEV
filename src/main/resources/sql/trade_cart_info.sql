create table trade_cart_info
(
  tci_id          int primary key comment '购物车流水号',
  tci_user_id     int         not null comment '用户id',
  tci_user_mobile varchar(11) not null comment '手机号',
  tci_mer_id      int         not null comment '商品id',
  tci_count       int         not null comment '商品个数',
  tci_create_time datetime default current_timestamp comment '添加时间',
  constraint tci_user_check foreign key (tci_user_id) references platform_user_reg (pur_user_id),
  constraint tci_mer_check foreign key (tci_mer_id) references trade_mer_info (tri_id)
) comment '购物车表';
