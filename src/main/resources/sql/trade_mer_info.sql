create table trade_mer_info
(
  tri_id            int primary key comment '商品id',
  tri_name          varchar(20) not null comment '商品名称',
  tri_desc          varchar(200) comment '商品描述',
  tri_expense       numeric     not null comment '商品价格',
  tri_class         varchar(20) comment '商品分类',
  tri_cover         varchar(200) comment '商品封面地址',
  tri_comment_count bigint comment '评论数',
  tri_rated         varchar(20) comment '评分',
  tri_create_time   varchar(20) not null comment '创建时间'
) comment '商品信息表'