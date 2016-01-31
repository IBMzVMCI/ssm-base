CREATE TABLE `x_passport` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `salt` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `uname` varchar(45) DEFAULT '',
  `create_time` varchar(45) NOT NULL DEFAULT '2015-01-01 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `x_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `user_account` varchar(45) NOT NULL COMMENT '用户帐号',
  `user_name` varchar(45) NOT NULL COMMENT '用户名',
  `func` int(11) NOT NULL COMMENT '员工职能',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态0：正常，1：冻结，2：删除',
  `admin` int(11) NOT NULL DEFAULT '0' COMMENT '是否管理员（0：否 1：是）',
  `create_time` datetime NOT NULL DEFAULT '2015-01-01 00:00:00',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_account_UNIQUE` (`user_account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;