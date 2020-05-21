-- auto-generated definition
create table blog_account
(
    id        bigint auto_increment
        primary key,
    account   varchar(32) not null comment '账号',
    password  varchar(32) not null comment '密码',
    salt      varchar(32) not null comment '咸盐',
    user_id   varchar(32) not null comment '用户id',
    user_name varchar(32) not null comment '用户名',
    constraint account
        unique (account)
)
    comment '用户账号' charset = utf8mb4;

-- auto-generated definition
create table blog_user
(
    id       bigint auto_increment
        primary key,
    name     varchar(32) not null comment '用户名',
    sex      varchar(8)  not null comment '性别',
    birthday datetime    null comment '生日',
    position varchar(64) null comment '职位'
)
    comment '用户信息' charset = utf8mb4;

