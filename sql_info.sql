create table t_product (
id varchar(50) not null comment '产品编号',
name varchar(50) not null comment '产品名称',
threshold_amount decimal(15, 3) not null comment '起投金额',
step_amount decimal(15, 3) not null comment '投资步长',
lock_term smallint not null comment '锁定期',
reward_rate decimal(5, 3) not null comment '收益率，0-100 百分比值',
status varchar(20) not null comment '状态，AUDINTING：审核中，IN_SELL：销售中，LOCKED：暂停销售，FINISHED：已结束',
memo varchar(200) not null comment '备注',
create_at datetime not null comment '创建时间',
create_user varchar(20) not null comment '创建者',
update_at datetime not null comment '更新时间',
update_user varchar(20) not null comment '更新着',
primary key(id)
) engine=innodb default charset=utf8 collate=utf8_general_ci;

create table t_product (
order_id varchar(50) not null comment '订单编号',
chan_id varchar(50) not null comment '渠道编号',
product_id varchar(50) not null comment '产品编号',
chan_user_id varchar(50) not null comment '渠道用户编号',
order_type varchar(50) not null comment '订单类型，APPLY：申购，REDEEM：赎回',
order_status varchar(50) not null comment '订单状态，INIT：初始化，PROCESS：处理中，SUCCESS：成功，FAIL：失败',
outer_order_id varchar(50) not null comment '外部订单编号',
amount decimal(15, 3) not null comment '金额',
memo varchar(200) not null comment '备注',
create_at datetime not null comment '创建时间',
update_at datetime not null comment '更新时间'
primary key(order_id)
) engine=innodb default charset=utf8 collate=utf8_general_ci;