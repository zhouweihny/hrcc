--2018.1.2
--增加供应商备注字段
alter table t_supplier_purchase_detail add column remark varchar(255);
--增加订单备注字段
alter table t_bill_detail add column remark varchar(255);

--2018.1.3
--增加手机号字段
alter table t_user_supplier add column mobileno varchar(255);
--增加供应商普通用户角色
insert into sys_role values ('F717B4EEF241460891F0E1D3AB84F74E', '供应商普通用户', '', null, null, '2018-01-03 10:25:55', '2018-01-03 10:25:55');


---2018.1.10
--促销品种编码表
CREATE TABLE `t_drug_promotion` (
  `id` varchar(32) NOT NULL,
  `drugid` varchar(32) DEFAULT NULL,
  `supplierid` varchar(32) DEFAULT NULL,
  `content` text,
  `startdate` datetime DEFAULT NULL,
  `enddate` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `operatorid` varchar(32) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
 )
 --促销品种对应供应商表
 CREATE TABLE `t_drug_promotion_purchaesr` (
  `id` varchar(32) NOT NULL,
  `promotionid` varchar(32) DEFAULT NULL,
  `purchaserid` varchar(32) DEFAULT NULL,
  `operatorid` varchar(32) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
)
 

---2018.1.22
--微信相关表
CREATE TABLE `sys_wx` (
  `id` varchar(32) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `devname` varchar(255) DEFAULT NULL COMMENT '开发者微信号',
  `name` varchar(255) DEFAULT NULL,
  `appid` varchar(255) DEFAULT NULL,
  `appsecret` varchar(255) DEFAULT NULL,
  `authenticationtime` datetime DEFAULT NULL COMMENT '认证时间',
  `url` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `encodingaesKey` varchar(255) DEFAULT NULL,
  `encrypttype` varchar(255) DEFAULT NULL,
  `jsconfigdebug` bit(1) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `wxmsgid` varchar(255) DEFAULT NULL,
  `domain` varchar(255) DEFAULT NULL,
  `ispromotion` bit(1) DEFAULT NULL,
  `disabled` bit(1) DEFAULT NULL,
  `operatorid` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sys_wx_accesstoken`;
CREATE TABLE `sys_wx_accesstoken` (
  `id` varchar(32) NOT NULL,
  `wxid` varchar(255) DEFAULT NULL,
  `accesstoken` varchar(255) DEFAULT NULL,
  `jsapiticket` varchar(255) DEFAULT NULL,
  `disabled` bit(1) DEFAULT NULL,
  `operatorid` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sys_wx_material_img`;
CREATE TABLE `sys_wx_material_img` (
  `id` varchar(32) NOT NULL,
  `wxid` varchar(255) DEFAULT NULL,
  `igroup` varchar(255) DEFAULT NULL COMMENT '开发者微信号',
  `mediaid` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `disabled` bit(1) DEFAULT NULL,
  `operatorid` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sys_wx_material_tw`;
CREATE TABLE `sys_wx_material_tw` (
  `id` varchar(32) NOT NULL,
  `wxid` varchar(255) DEFAULT NULL,
  `mediaid` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `content` text,
  `remark` varchar(255) DEFAULT NULL,
  `disabled` bit(1) DEFAULT NULL,
  `operatorid` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sys_wx_menu`;
CREATE TABLE `sys_wx_menu` (
  `id` varchar(32) NOT NULL,
  `wxid` varchar(255) DEFAULT NULL,
  `parentid` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL COMMENT '类型',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `url` varchar(255) DEFAULT NULL COMMENT '链接',
  `wxmsgid` varchar(255) DEFAULT NULL COMMENT '消息模板',
  `path` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `remark` varchar(255) DEFAULT NULL,
  `disabled` bit(1) DEFAULT NULL,
  `operatorid` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sys_wx_msg`;
CREATE TABLE `sys_wx_msg` (
  `id` varchar(32) NOT NULL,
  `wxid` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `content` text,
  `type` varchar(255) DEFAULT NULL,
  `remark` text,
  `disabled` bit(1) DEFAULT NULL,
  `operatorid` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sys_wx_qrcode`;
CREATE TABLE `sys_wx_qrcode` (
  `id` varchar(32) NOT NULL,
  `wxid` varchar(255) DEFAULT NULL,
  `scenestr` varchar(255) DEFAULT NULL,
  `expireSeconds` int(11) DEFAULT NULL,
  `ticket` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `departmentid` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `used` bit(1) DEFAULT NULL,
  `disabled` bit(1) DEFAULT NULL,
  `operatorid` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sys_wx_user`;
CREATE TABLE `sys_wx_user` (
  `id` varchar(32) NOT NULL,
  `wxid` varchar(255) DEFAULT NULL,
  `subscribe` bit(1) DEFAULT NULL,
  `openid` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `headimgurl` varchar(255) DEFAULT NULL,
  `subscribe_time` datetime DEFAULT NULL,
  `unionid` varchar(255) DEFAULT NULL,
  `groupid` varchar(255) DEFAULT NULL,
  `tagid_list` varchar(255) DEFAULT NULL,
  `mobileno` varchar(255) DEFAULT NULL,
  `userid` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `disabled` bit(1) DEFAULT NULL,
  `operatorid` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sys_wx_user_qrcode`;
CREATE TABLE `sys_wx_user_qrcode` (
  `id` varchar(32) NOT NULL,
  `wxuserid` varchar(255) DEFAULT NULL,
  `qrscene` varchar(255) DEFAULT NULL,
  `disabled` bit(1) DEFAULT NULL,
  `operatorid` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `t_user_wx` (
  `id` varchar(32) NOT NULL,
  `userid` varchar(255) DEFAULT NULL,
  `wxid` varchar(255) DEFAULT NULL,
  `disabled` bit(1) DEFAULT NULL,
  `operatorid` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `sys_dict` VALUES ('7B8E04FBB72840B0A396E007727B1FD8', 'wx.msg.type', '微信消息类型', '', '\0', null, '2017-03-10 15:14:17', '2017-03-10 15:14:17');
INSERT INTO `sys_dictdata` VALUES ('3FBDBD6E648946898130E70727168147', '7B8E04FBB72840B0A396E007727B1FD8', '1', '文本消息', null, null, null, '2017-03-14 10:12:16', '2017-03-14 10:12:41');
INSERT INTO `sys_dictdata` VALUES ('B9EBAFA9985A4837AF67DD65CCBAF498', '7B8E04FBB72840B0A396E007727B1FD8', '2', '图文消息', null, null, null, '2017-03-14 10:12:30', '2017-03-14 10:12:30');


INSERT INTO `sys_dict` VALUES ('955701FDAEA5459293A4D7A40CD968F5', 'common.sex', '性别', '', '\0', null, '2017-03-16 15:22:27', '2017-03-16 15:22:27');
INSERT INTO `sys_dictdata` VALUES ('8F84388341864B0AB2A036D2C6CF3347', '955701FDAEA5459293A4D7A40CD968F5', '2', '女', null, null, null, '2017-03-16 15:22:43', '2017-03-16 15:22:43');
INSERT INTO `sys_dictdata` VALUES ('984ADD7F85654F00BF9D8FDAE296ED53', '955701FDAEA5459293A4D7A40CD968F5', '1', '男', null, null, null, '2017-03-16 15:22:38', '2017-03-16 15:22:38');
INSERT INTO `sys_dictdata` VALUES ('D52C4AD930B848CFA7F13EB2B6F5D43E', '955701FDAEA5459293A4D7A40CD968F5', '0', '未知', null, null, null, '2017-03-16 15:22:50', '2017-03-16 15:22:50');


INSERT INTO `sys_dict` VALUES ('A0B8ADAB10B34D18AA1DD2F34919300E', 'wx.menu.type', '微信菜单类型', '', '\0', null, '2017-03-03 15:38:37', '2017-03-08 13:46:29');
INSERT INTO `sys_dictdata` VALUES ('87C3BC2B7E5F44909529AA9AD7D61364', 'A0B8ADAB10B34D18AA1DD2F34919300E', 'view', 'view', null, null, null, '2017-03-03 15:38:56', '2017-03-14 14:56:54');
INSERT INTO `sys_dictdata` VALUES ('8F7DB4CB88894CE0AF76128A83542B3F', 'A0B8ADAB10B34D18AA1DD2F34919300E', 'click', 'click', null, null, null, '2017-03-09 09:41:57', '2017-03-09 09:42:05');


INSERT INTO `sys_dict` VALUES ('B24ABEBEBBFC482C81FC5BA41A238EAC', 'wx.encryptType', '微信消息加密方式', '', '\0', null, null, null);
INSERT INTO `sys_dictdata` VALUES ('502CEA3525C040DBBEE389EBCA8556CF', 'B24ABEBEBBFC482C81FC5BA41A238EAC', '1', '明文模式', null, null, null, null, '2017-03-08 10:36:01');
INSERT INTO `sys_dictdata` VALUES ('FDFBA614E5DC413FA387F2E10B838C20', 'B24ABEBEBBFC482C81FC5BA41A238EAC', '2', '兼容模式', null, null, null, null, '2017-03-08 10:36:03');
INSERT INTO `sys_dictdata` VALUES ('854BA03BBF064128AF46CC400A41F4BB', 'B24ABEBEBBFC482C81FC5BA41A238EAC', '3', '安全模式', null, null, null, null, null);

INSERT INTO `sys_menu` VALUES ('413631E865C54D6EBAAA7AB07AAF52D1', '图片素材', '/syswxmaterialimg/list.do', 'E85D59F6E281450F9460C96EA029D497', '695AD27230D9421F89E108C7F8E9E2FE/E85D59F6E281450F9460C96EA029D497/413631E865C54D6EBAAA7AB07AAF52D1', '', NULL, NULL, NULL, '2018-1-15 09:03:40', '2018-1-15 09:03:40');
INSERT INTO `sys_menu` VALUES ('FB243F30A645440DB107D3C1CB992C24', '图文素材', '/syswxmaterialtw/list.do', 'E85D59F6E281450F9460C96EA029D497', '695AD27230D9421F89E108C7F8E9E2FE/E85D59F6E281450F9460C96EA029D497/FB243F30A645440DB107D3C1CB992C24', '', NULL, NULL, NULL, '2018-1-15 09:03:17', '2018-1-15 09:03:17');
INSERT INTO `sys_menu` VALUES ('404A3D5FCEF24FE9A4B38DBC076C6287', '永久二维码', '/syswxqrcode/list.do', '695AD27230D9421F89E108C7F8E9E2FE', '695AD27230D9421F89E108C7F8E9E2FE/404A3D5FCEF24FE9A4B38DBC076C6287', '', NULL, NULL, NULL, '2018-1-15 09:02:53', '2018-1-15 09:02:53');
INSERT INTO `sys_menu` VALUES ('5D4D07AF14AB429A802C2E61AF00E90B', '微信用户', '/syswxuser/list.do', '695AD27230D9421F89E108C7F8E9E2FE', '695AD27230D9421F89E108C7F8E9E2FE/5D4D07AF14AB429A802C2E61AF00E90B', '', NULL, NULL, NULL, '2018-1-15 09:02:38', '2018-1-15 09:02:38');
INSERT INTO `sys_menu` VALUES ('E85D59F6E281450F9460C96EA029D497', '素材管理', '', '695AD27230D9421F89E108C7F8E9E2FE', '695AD27230D9421F89E108C7F8E9E2FE/E85D59F6E281450F9460C96EA029D497', '', NULL, NULL, NULL, '2018-1-15 09:02:17', '2018-1-15 09:02:17');
INSERT INTO `sys_menu` VALUES ('2E889C8F70C04F8787BF667D07832022', '消息管理', '/syswxmsg/list.do', '695AD27230D9421F89E108C7F8E9E2FE', '695AD27230D9421F89E108C7F8E9E2FE/2E889C8F70C04F8787BF667D07832022', '', NULL, NULL, NULL, '2018-1-15 09:02:00', '2018-1-15 09:02:00');
INSERT INTO `sys_menu` VALUES ('870C5D64D76C4F398B2582E238504E7E', '公众号菜单', '/syswxmenu/list.do', '695AD27230D9421F89E108C7F8E9E2FE', '695AD27230D9421F89E108C7F8E9E2FE/870C5D64D76C4F398B2582E238504E7E', '', NULL, NULL, NULL, '2018-1-15 09:01:43', '2018-1-15 09:01:43');
INSERT INTO `sys_menu` VALUES ('0EB8AC89C7E94A128053BEEDDB46F95E', '公众号AccessToken', '/syswxaccesstoken/list.do', '695AD27230D9421F89E108C7F8E9E2FE', '695AD27230D9421F89E108C7F8E9E2FE/0EB8AC89C7E94A128053BEEDDB46F95E', '', NULL, NULL, NULL, '2018-1-15 09:01:23', '2018-1-15 09:01:23');
INSERT INTO `sys_menu` VALUES ('6110F51BFF304D3DA57FF33FFDD01F8C', '公众号管理', '/syswx/list.do', '695AD27230D9421F89E108C7F8E9E2FE', '695AD27230D9421F89E108C7F8E9E2FE/6110F51BFF304D3DA57FF33FFDD01F8C', '', NULL, NULL, NULL, '2018-1-15 09:01:06', '2018-1-15 09:01:06');
INSERT INTO `sys_menu` VALUES ('695AD27230D9421F89E108C7F8E9E2FE', '公共号管理', '', '', '695AD27230D9421F89E108C7F8E9E2FE', '', 0, NULL, NULL, '2018-1-15 08:58:13', '2018-1-15 08:59:42');


--2018.1.24
---增加erpid字段
alter table t_bill add column erpid varchar(32);
alter table t_bill add column erpusername varchar(32);
alter table t_bill add column erpsucode varchar(32);
alter table t_bill add column mobile varchar(32);

alter table t_plan add column erpid varchar(32);
alter table t_plan add column erpusername varchar(32);

alter table t_purchase add column erpid varchar(32);
alter table t_purchase add column erpusername varchar(32);


--2018.1.30
alter table t_user_supplier add column code varchar(32);
alter table t_user_supplier add column name varchar(32);
alter table t_user_supplier add column mobile varchar(32);

--2018.2.1
alter table t_drug add column taxrate int(2);
alter table t_drug add column erpusername varchar(80);
alter table t_drug_supplier add column erpsucode varchar(80);
alter table t_drug_supplier drop column supplierid ;
alter table t_drug_supplier drop column agentid ;
alter table t_drug_supplier drop column code;
alter table t_user add column erpusername varchar(80);
alter table t_drug_supplier add column remark varchar(255);

--增加采购商采购员角色
insert into sys_role values ('9334387B790C42658B7BE0655E155592', '采购商_采购员', '', null, null, '2018-01-03 10:25:55', '2018-01-03 10:25:55');

CREATE TABLE `t_erpuser` (
  `id` varchar(32) NOT NULL,
  `userid` varchar(32) NOT NULL,
  `username` varchar(80) DEFAULT NULL,
  `name` varchar(80) DEFAULT NULL,
  `disabled` bit(1) DEFAULT NULL,
  `operatorid` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) 

CREATE TABLE `t_user_role` (
  `id` varchar(32) NOT NULL,
  `userid` varchar(32) NOT NULL,
  `roleid` varchar(32) DEFAULT NULL,
  `disabled` bit(1) DEFAULT NULL,
  `operatorid` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) 


---增加预计送达时间
alter table t_bill add column arrivaltime datetime  DEFAULT NULL;

alter table t_bill_detail add column contractno varchar(80)  DEFAULT NULL;
alter table t_bill_detail add column contractnum int;
alter table t_bill_detail add column contracttime datetime  DEFAULT NULL;

--自动发送订单?
alter table t_user add column autosendbill bit(1);


--品种分类树表
CREATE TABLE `fx_tree` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parentid` varchar(32) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `disabled` bit(1) DEFAULT NULL,
  `operatorid` varchar(32) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--通用名表
CREATE TABLE `fx_comname` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `treeid` varchar(32) DEFAULT NULL,
  `disabled` bit(1) DEFAULT NULL,
  `operatorid` varchar(32) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--增加药品通用名id
alter table t_drug add column comnameid varchar(32);
alter table t_drug add column comnameuserid varchar(32);
--原数据表
CREATE TABLE `fx_metadata` (
  `id` varchar(32) NOT NULL,
  `treeid` varchar(32) DEFAULT NULL,
  `avg` double DEFAULT NULL,
  `drugnum` int(11) DEFAULT NULL,
  `classname` varchar(255) DEFAULT NULL,
  `methodname` varchar(50) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `operatorid` varchar(32) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `disabled` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--原数据表条件
CREATE TABLE `fx_metadataitem` (
  `id` varchar(32) NOT NULL,
  `createtime` datetime DEFAULT NULL,
  `operatorid` varchar(32) DEFAULT NULL,  
  `name` varchar(32) DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `disabled` bit(1) DEFAULT NULL,
  `metadataid` varchar(32) DEFAULT NULL,
  `condition` varchar(10) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `parameter` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) 

--导入销售数据
CREATE TABLE `fx_impsaledata` (
  `id` varchar(32) NOT NULL,
  `createtime` datetime DEFAULT NULL,
  `operatorid` varchar(255) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `orderno` varchar(32) DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `disabled` bit(1) DEFAULT NULL,
  `storeid` varchar(32) DEFAULT NULL,
  `userid` varchar(32) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `specifications` varchar(255) DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `dosageform` varchar(255) DEFAULT NULL,
  `factory` varchar(255) DEFAULT NULL,
  `zunzi` varchar(255) DEFAULT NULL,
  `qty` decimal(20,4) DEFAULT NULL,
  `costprice` decimal(20,4) DEFAULT NULL,
  `price` decimal(20,4) DEFAULT NULL,
  `bizdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `fx_impstore` (
  `id` varchar(32) NOT NULL,
  `storeid` varchar(32) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `operatorid` varchar(255) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `disabled` bit(1) DEFAULT NULL,
  `userid` varchar(32) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `specifications` varchar(255) DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `dosageform` varchar(255) DEFAULT NULL,
  `factory` varchar(255) DEFAULT NULL,
  `zunzi` varchar(255) DEFAULT NULL,
  `qty` decimal(20,4) DEFAULT NULL,
  `costprice` decimal(20,4) DEFAULT NULL,
  `amt` decimal(20,4) DEFAULT NULL,
  `drugid` varchar(32) DEFAULT NULL,
  `comnameid` varchar(32) DEFAULT NULL,
  `impfilenameid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `drugid` (`drugid`)
)



CREATE TABLE `fx_comname_tree` (
  `id` varchar(32) NOT NULL,
  `comnameid` varchar(32) DEFAULT NULL,
  `treeid` varchar(32) DEFAULT NULL,
  `disabled` bit(1) DEFAULT NULL,
  `operatorid` varchar(32) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) 
) 



CREATE TABLE `fx_tree_meta_datas_msg` (
  `id` varchar(32) NOT NULL,
  `treemetadatasid` varchar(32) DEFAULT NULL,
  `condition` varchar(10) DEFAULT NULL,
  `msg` varchar(120) DEFAULT NULL,
  `remark` varchar(80) DEFAULT NULL,
  `disabled` bit(1) DEFAULT NULL,
  `operatorid` varchar(32) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) 



alter table fx_impfilename add column num int(11);
alter table fx_impfilename add column type varchar(32);
alter table fx_impfilename add column extend varchar(32);
alter table fx_impfilename add column extend varchar(32);


CREATE TABLE `t_user_store` (
  `id` varchar(32) NOT NULL,
  `userid` varchar(32) DEFAULT NULL,
  `storeid` varchar(32) DEFAULT NULL,
  `disabled` bit(1) DEFAULT NULL,
  `operatorid` varchar(32) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
)

alter table t_drug add column ywflag   bit(1);
alter table t_drug add column ybflag   bit(1);
alter table t_drug add column bbflag   bit(1);


CREATE TABLE `t_store_type` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `disabled` bit(1) DEFAULT NULL ,
  `operatorid` varchar(32) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) 

alter table t_store add column typeid varchar(32);
alter table fx_tree_meta add column storetypeid varchar(32);
alter table fx_meta add column code varchar(32);
alter table fx_meta_datas add column code varchar(32);


insert into fx_commname (id,name) value ('00000','其他');
insert into fx_tree (id,parentid,path,name) value ('00000','18346A0044A148D1A818627B25510E59','18346A0044A148D1A818627B25510E59/00000','其他');


