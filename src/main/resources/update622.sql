DROP TABLE IF EXISTS `fx_sale_file`;
CREATE TABLE `fx_sale_file` (
  `id` varchar(32) NOT NULL,
  `name` varchar(32) DEFAULT NULL,
  `storeid` varchar(32) DEFAULT NULL,
  `userid` varchar(32) DEFAULT NULL,
  `startdate` datetime DEFAULT NULL,
  `enddate` datetime DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `processed` int(11) DEFAULT NULL,
  `error` int(11) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `filepath` varchar(255) DEFAULT NULL,
  `errorpath` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `disabled` bit(1) DEFAULT NULL COMMENT '销售 1，库存为2',
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `operatorid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `storeid` (`storeid`,`userid`) USING BTREE,
  KEY `userid` (`userid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `fx_sale_data`;
CREATE TABLE `fx_sale_data` (
  `id` varchar(32) NOT NULL,
  `fileid` varchar(32) DEFAULT NULL,
  `storeid` varchar(32) DEFAULT NULL,
  `orderno` varchar(80) DEFAULT NULL,
  `drugid` varchar(32) DEFAULT NULL,
  `code` varchar(32) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `specifications` varchar(120) DEFAULT NULL,
  `factory` varchar(80) DEFAULT NULL,
  `unit` varchar(20) DEFAULT NULL,
  `zunzi` varchar(80) DEFAULT NULL,
  `qty` decimal(10,4) DEFAULT NULL,
  `userid` varchar(32) DEFAULT NULL,
  `costprice` decimal(10,4) DEFAULT NULL,
  `price` decimal(10,4) DEFAULT NULL,
  `saledate` datetime DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `operatorid` varchar(32) DEFAULT NULL,
  `disabled` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `drugid` (`drugid`),
  KEY `impfilenameid` (`fileid`),
  KEY `storeid` (`storeid`,`qty`,`price`,`fileid`,`costprice`) USING BTREE,
  KEY `orderno` (`orderno`),
  KEY `storeid_2` (`storeid`),
  KEY `comnameid` (`fileid`) USING BTREE,
  KEY `orderno_2` (`orderno`),
  KEY `orderno_3` (`orderno`,`fileid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `fx_sale_month`;
CREATE TABLE `fx_sale_month` (
  `id` varchar(32) NOT NULL,
  `drugid` varchar(32) DEFAULT NULL,
  `storeid` varchar(32) DEFAULT NULL,
  `xse` decimal(10,2) DEFAULT NULL,
  `cb` decimal(10,2) DEFAULT NULL,
  `ml` decimal(10,2) DEFAULT NULL,
  `mlv` decimal(10,2) DEFAULT NULL,
  `userid` varchar(32) DEFAULT NULL,
  `month` datetime DEFAULT NULL,
  `pc` int(11) DEFAULT NULL,
  `xssl` decimal(10,2) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `drugid` (`drugid`,`storeid`,`userid`,`month`),
  KEY `drugid_2` (`drugid`),
  KEY `storeid` (`storeid`,`userid`,`month`),
  KEY `userid` (`userid`,`month`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


alter table fx_tree add column analysis   bit(1);
alter table fx_tree add column userid   varchar(32);
alter table fx_comname_tree add column fwflag   bit(1);
update  fx_tree  set analysis=1   where id='18346A0044A148D1A818627B25510E59';
update  fx_tree  set userid='F8DD2C67A39047789B75F6574F766A14' ;   
--更新确定经营范围通用名
CREATE TEMPORARY TABLE temp_fwid select a.id from 
(select ct.*,t.name  from fx_comname_tree ct, fx_tree t where t.path like '18346A0044A148D1A818627B25510E59%' and ct.treeid=t.id) a,
(select ct.*,t.name from fx_comname_tree ct, fx_tree t where t.path like '0646772636684747996D030BE556BB73%' and ct.treeid=t.id) b
where a.comnameid=b.comnameid and a.name=b.name;
update  fx_comname_tree  set  fwflag=1 where id in(select id from temp_fwid);