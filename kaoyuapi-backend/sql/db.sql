use kaoyuapi;
-- 接口信息
create table if not exists kaoyuapi.`interface_info`
(
`id` bigint not null auto_increment comment '主键' primary key,
`name` varchar(256) not null comment '名称',
`description` varchar(256) null comment '描述',
`method` varchar(256) not null comment '请求类型',
`url` varchar(512) not null comment '接口地址',
`requestHeader` text null comment '请求头',
`responseHeader` text null comment '响应头',
`requestParams` text null comment '请求参数',
`status` int default 0 not null comment '接口状态(0-关闭 1-开启)',
`userId` bigint not null comment '创建人',
`createTime` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
`updateTime` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
`isDeleted` tinyint default 0 not null comment '是否删除(0-未删, 1-已删)'
) comment '接口信息';

-- 用户调用接口关系信息
insert into kaoyuapi.`interface_info` (`name`, `description`, `method`, `url`, `requestHeader`, `responseHeader`, `status`, `userId`) values ('范越彬', '洪煜城', '龙黎昕', 'www.daphne-labadie.co', '孔潇然', '卢明轩', 0, 8);

insert into kaoyuapi.`interface_info` (`name`, `description`, `method`, `url`, `requestHeader`, `responseHeader`, `status`, `userId`) values ('龚绍辉', '吴鹭洋', '白烨磊', 'www.norberto-tremblay.org', '顾熠彤', '赵晋鹏', 0, 4358909288);
insert into kaoyuapi.`interface_info` (`name`, `description`, `method`, `url`, `requestHeader`, `responseHeader`, `status`, `userId`) values ('钟修杰', '邹博涛', '田浩轩', 'www.nohemi-brekke.net', '何展鹏', '邵潇然', 0, 290987300);
insert into kaoyuapi.`interface_info` (`name`, `description`, `method`, `url`, `requestHeader`, `responseHeader`, `status`, `userId`) values ('刘越彬', '龙苑博', '魏煜城', 'www.ming-ortiz.io', '赖烨伟', '薛明辉', 0, 9859);
insert into kaoyuapi.`interface_info` (`name`, `description`, `method`, `url`, `requestHeader`, `responseHeader`, `status`, `userId`) values ('龙钰轩', '严修杰', '崔明杰', 'www.darrin-tillman.com', '邵金鑫', '任语堂', 0, 87492983);
insert into kaoyuapi.`interface_info` (`name`, `description`, `method`, `url`, `requestHeader`, `responseHeader`, `status`, `userId`) values ('蒋烨霖', '袁瑞霖', '冯苑博', 'www.wilbur-turcotte.info', '方俊驰', '毛子轩', 0, 134461256);
insert into kaoyuapi.`interface_info` (`name`, `description`, `method`, `url`, `requestHeader`, `responseHeader`, `status`, `userId`) values ('万子骞', '金立辉', '郑浩轩', 'www.tom-hoppe.com', '万弘文', '郑峻熙', 0, 4505);
insert into kaoyuapi.`interface_info` (`name`, `description`, `method`, `url`, `requestHeader`, `responseHeader`, `status`, `userId`) values ('周金鑫', '莫博文', '戴熠彤', 'www.raymundo-sanford.com', '邹睿渊', '白致远', 0, 244);
insert into kaoyuapi.`interface_info` (`name`, `description`, `method`, `url`, `requestHeader`, `responseHeader`, `status`, `userId`) values ('田远航', '范展鹏', '孟子轩', 'www.lon-luettgen.name', '孟思源', '杨笑愚', 0, 68);
insert into kaoyuapi.`interface_info` (`name`, `description`, `method`, `url`, `requestHeader`, `responseHeader`, `status`, `userId`) values ('吕明辉', '卢修洁', '戴荣轩', 'www.oscar-donnelly.co', '陶煜祺', '贾建辉', 0, 80);
insert into kaoyuapi.`interface_info` (`name`, `description`, `method`, `url`, `requestHeader`, `responseHeader`, `status`, `userId`) values ('姜乐驹', '雷鹤轩', '罗峻熙', 'www.gary-effertz.org', '顾笑愚', '卢弘文', 0, 25868949);
insert into kaoyuapi.`interface_info` (`name`, `description`, `method`, `url`, `requestHeader`, `responseHeader`, `status`, `userId`) values ('曹立辉', '姜耀杰', '熊鹏', 'www.shawnna-kling.io', '丁明辉', '彭思', 0, 4552285853);
insert into kaoyuapi.`interface_info` (`name`, `description`, `method`, `url`, `requestHeader`, `responseHeader`, `status`, `userId`) values ('魏彬', '金立诚', '龙远航', 'www.mimi-oberbrunner.io', '程智宸', '苏耀杰', 0, 60);
insert into kaoyuapi.`interface_info` (`name`, `description`, `method`, `url`, `requestHeader`, `responseHeader`, `status`, `userId`) values ('郑昊焱', '许浩宇', '卢鹭洋', 'www.mignon-mohr.info', '覃博超', '廖苑博', 0, 617667);
insert into kaoyuapi.`interface_info` (`name`, `description`, `method`, `url`, `requestHeader`, `responseHeader`, `status`, `userId`) values ('范雨泽', '龙睿渊', '金钰轩', 'www.thalia-jacobs.io', '石炫明', '黄智辉', 0, 53357);
insert into kaoyuapi.`interface_info` (`name`, `description`, `method`, `url`, `requestHeader`, `responseHeader`, `status`, `userId`) values ('谢博文', '蔡文轩', '严振家', 'www.loraine-beatty.co', '黄哲瀚', '李致远', 0, 63);
insert into kaoyuapi.`interface_info` (`name`, `description`, `method`, `url`, `requestHeader`, `responseHeader`, `status`, `userId`) values ('邓健雄', '郭子骞', '朱鸿煊', 'www.forest-hansen.net', '魏鹏飞', '孔文轩', 0, 36464412);
insert into kaoyuapi.`interface_info` (`name`, `description`, `method`, `url`, `requestHeader`, `responseHeader`, `status`, `userId`) values ('白烨华', '刘苑博', '范睿渊', 'www.mana-torp.info', '冯鸿涛', '赖天磊', 0, 7910743417);
insert into kaoyuapi.`interface_info` (`name`, `description`, `method`, `url`, `requestHeader`, `responseHeader`, `status`, `userId`) values ('唐凯瑞', '姜弘文', '龚胤祥', 'www.kyra-gulgowski.net', '傅立果', '龚烨霖', 0, 4970);
insert into kaoyuapi.`interface_info` (`name`, `description`, `method`, `url`, `requestHeader`, `responseHeader`, `status`, `userId`) values ('贾琪', '田弘文', '姚胤祥', 'www.leonida-hansen.org', '王雨泽', '方懿轩', 0, 78579121);

create table if not exists kaoyuapi.`user_interface_info`
(
    `id` bigint not null auto_increment comment '主键' primary key,
    `userId` bigint not null comment '调用用户Id',
    `interfaceInfoId` bigint not null comment '接口Id',
    `totalNum` int default 0 null comment '总调用次数',
    `leftNum` int default 0 null comment '剩余调用次数',
    `status` int default 0 not null comment '(0-正常 1-禁用)',
    `createTime` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `updateTime` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `isDeleted` tinyint default 0 not null comment '是否删除(0-未删, 1-已删)'
) comment '用户调用接口关系信息表';

