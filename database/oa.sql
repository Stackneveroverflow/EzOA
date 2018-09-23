CREATE TABLE user_info
(
  id          INT PRIMARY KEY AUTO_INCREMENT,
  uid         VARCHAR(32) NOT NULL
  COMMENT '员工编号',
  name        VARCHAR(32) NOT NULL
  COMMENT '员工姓名',
  sex         INT,
  age         INT COMMENT '年龄',
  tel         VARCHAR(32) COMMENT '电话号码',
  email       VARCHAR(64) COMMENT '邮箱',
  status      INT             DEFAULT 0
  COMMENT '员工状态，0在职，1待入职，2已离职，3长期休假',
  department  VARCHAR(32) COMMENT '部门',
  station     VARCHAR(32) COMMENT '岗位',
  entry_time  DATE            DEFAULT current_date,
  quit_time   DATE,
  level       VARCHAR(16)     DEFAULT 'L1-1'
  COMMENT '员工职级',
  work_age    INT             DEFAULT 0
  COMMENT '工龄',
  create_time TIMESTAMP       DEFAULT current_timestamp,
  update_time TIMESTAMP       DEFAULT current_timestamp
  ON UPDATE current_timestamp
);
CREATE UNIQUE INDEX user_info_uid_uindex
  ON user_info (uid);
ALTER TABLE user_info
  COMMENT = '员工信息';

CREATE TABLE user_account
(
  id          INT PRIMARY KEY AUTO_INCREMENT,
  account     VARCHAR(32) COMMENT '账号',
  password    VARCHAR(64) NOT NULL
  COMMENT '密码',
  uid         VARCHAR(32) NOT NULL
  COMMENT '员工编号',
  status      INT             DEFAULT 0
  COMMENT '账号状态，0激活，1冻结',
  `group`     VARCHAR(32) COMMENT '用户组',
  limits      INT             DEFAULT 0
  COMMENT '权限级别',
  create_time TIMESTAMP       DEFAULT current_timestamp
  COMMENT '创建时间',
  update_time TIMESTAMP       DEFAULT current_timestamp
  ON UPDATE current_timestamp
);
CREATE UNIQUE INDEX user_account_uid_uindex
  ON user_account (uid);
ALTER TABLE user_account
  COMMENT = '员工账号';

CREATE TABLE user_log
(
  id         INT PRIMARY KEY AUTO_INCREMENT,
  uid        VARCHAR(32),
  work_hours DECIMAL
  COMMENT '日工时',
  log_time   TIMESTAMP       DEFAULT current_timestamp
);
CREATE UNIQUE INDEX user_log_uid_uindex
  ON user_log (uid);
ALTER TABLE user_log
  COMMENT = '工时记录';