/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 8.0.17 : Database - equipment
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`equipment` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `equipment`;

/*Table structure for table `borrow` */

DROP TABLE IF EXISTS `borrow`;

CREATE TABLE `borrow` (
  `id` bigint(20) NOT NULL COMMENT '借用id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(20) DEFAULT NULL COMMENT '用户姓名',
  `equipment_id` bigint(20) DEFAULT NULL COMMENT '设备id',
  `equipment_name` varchar(50) DEFAULT NULL COMMENT '设备名称',
  `created_at` datetime DEFAULT NULL COMMENT '借用日期',
  `return_at` datetime DEFAULT NULL COMMENT '归还日期',
  `examine_at` datetime DEFAULT NULL COMMENT '审批时间',
  `sys_user_id` bigint(20) DEFAULT NULL COMMENT '审批人id',
  `sys_user_name` varchar(20) DEFAULT NULL COMMENT '审批人',
  `flag` int(1) DEFAULT NULL COMMENT '借用状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `borrow` */

/*Table structure for table `equipment` */

DROP TABLE IF EXISTS `equipment`;

CREATE TABLE `equipment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '设备id',
  `name` varchar(30) DEFAULT NULL COMMENT '设备名称',
  `type_id` bigint(20) DEFAULT NULL COMMENT '设备类型id',
  `model_num` varchar(30) DEFAULT NULL COMMENT '设备型号',
  `quantity` int(10) DEFAULT NULL COMMENT '设备数量',
  `flag` int(1) DEFAULT NULL COMMENT '设备状态：0正常；1报销',
  `created_at` datetime DEFAULT NULL,
  `created_by` varchar(20) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='设备管理';

/*Data for the table `equipment` */

insert  into `equipment`(`id`,`name`,`type_id`,`model_num`,`quantity`,`flag`,`created_at`,`created_by`,`updated_at`,`updated_by`) values (2,'11111',4,'34234',1231212,0,'2021-02-15 22:14:26','超级管理员','2021-02-15 22:14:26','超级管理员');

/*Table structure for table `equipment_type` */

DROP TABLE IF EXISTS `equipment_type`;

CREATE TABLE `equipment_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '设备类型id',
  `type_name` varchar(50) DEFAULT NULL COMMENT '设备类型名称',
  `deleted` int(1) DEFAULT NULL COMMENT '0正常；1删除',
  `created_at` datetime DEFAULT NULL,
  `created_by` varchar(20) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='类型管理';

/*Data for the table `equipment_type` */

insert  into `equipment_type`(`id`,`type_name`,`deleted`,`created_at`,`created_by`,`updated_at`,`updated_by`) values (3,'类型1号',NULL,'2021-02-15 21:51:46','超级管理员','2021-02-15 21:51:46','超级管理员'),(4,'类型2号',NULL,'2021-02-15 21:51:54','超级管理员','2021-02-15 21:51:54','超级管理员');

/*Table structure for table `gen_table` */

DROP TABLE IF EXISTS `gen_table`;

CREATE TABLE `gen_table` (
  `table_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(200) DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) DEFAULT '' COMMENT '表描述',
  `sub_table_name` varchar(64) DEFAULT NULL COMMENT '关联子表的表名',
  `sub_table_fk_name` varchar(64) DEFAULT NULL COMMENT '子表关联的外键名',
  `class_name` varchar(100) DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作 sub主子表操作）',
  `package_name` varchar(100) DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) DEFAULT NULL COMMENT '生成功能作者',
  `gen_type` char(1) DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
  `gen_path` varchar(200) DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
  `options` varchar(1000) DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='代码生成业务表';

/*Data for the table `gen_table` */

insert  into `gen_table`(`table_id`,`table_name`,`table_comment`,`sub_table_name`,`sub_table_fk_name`,`class_name`,`tpl_category`,`package_name`,`module_name`,`business_name`,`function_name`,`function_author`,`gen_type`,`gen_path`,`options`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values (1,'equipment','设备管理',NULL,NULL,'Equipment','crud','com.equipment.system','system','equipment','设备管理','equipment','0','/',NULL,'admin','2021-01-05 02:55:32','',NULL,NULL),(2,'equipment_type','类型管理',NULL,NULL,'EquipmentType','crud','com.equipment.system','system','type','类型管理','equipment','0','/',NULL,'admin','2021-01-05 02:55:32','',NULL,NULL);

/*Table structure for table `gen_table_column` */

DROP TABLE IF EXISTS `gen_table_column`;

CREATE TABLE `gen_table_column` (
  `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` varchar(64) DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) DEFAULT '' COMMENT '字典类型',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='代码生成业务表字段';

/*Data for the table `gen_table_column` */

insert  into `gen_table_column`(`column_id`,`table_id`,`column_name`,`column_comment`,`column_type`,`java_type`,`java_field`,`is_pk`,`is_increment`,`is_required`,`is_insert`,`is_edit`,`is_list`,`is_query`,`query_type`,`html_type`,`dict_type`,`sort`,`create_by`,`create_time`,`update_by`,`update_time`) values (1,'1','id','设备id','bigint(20)','Long','id','1','1',NULL,'1',NULL,NULL,NULL,'EQ','input','',1,'admin','2021-01-05 02:55:32','',NULL),(2,'1','type_id','设备类型id','bigint(20)','Long','typeId','0','0',NULL,'1','1','1','1','EQ','input','',2,'admin','2021-01-05 02:55:32','',NULL),(3,'1','model_num','设备型号','varchar(30)','String','modelNum','0','0',NULL,'1','1','1','1','EQ','input','',3,'admin','2021-01-05 02:55:32','',NULL),(4,'1','quantity','设备数量','int(10)','Integer','quantity','0','0',NULL,'1','1','1','1','EQ','input','',4,'admin','2021-01-05 02:55:32','',NULL),(5,'1','flag','设备状态：0正常；1报销','int(1)','Integer','flag','0','0',NULL,'1','1','1','1','EQ','input','',5,'admin','2021-01-05 02:55:32','',NULL),(6,'1','created_at',NULL,'datetime','Date','createdAt','0','0',NULL,'1','1','1','1','EQ','datetime','',6,'admin','2021-01-05 02:55:32','',NULL),(7,'1','created_by',NULL,'varchar(20)','String','createdBy','0','0',NULL,'1','1','1','1','EQ','input','',7,'admin','2021-01-05 02:55:32','',NULL),(8,'1','updated_at',NULL,'datetime','Date','updatedAt','0','0',NULL,'1','1','1','1','EQ','datetime','',8,'admin','2021-01-05 02:55:32','',NULL),(9,'1','updated_by',NULL,'varchar(20)','String','updatedBy','0','0',NULL,'1','1','1','1','EQ','input','',9,'admin','2021-01-05 02:55:32','',NULL),(10,'2','id','设备类型id','bigint(20)','Long','id','1','1',NULL,'1',NULL,NULL,NULL,'EQ','input','',1,'admin','2021-01-05 02:55:32','',NULL),(11,'2','type_name','设备类型名称','varchar(50)','String','typeName','0','0',NULL,'1','1','1','1','LIKE','input','',2,'admin','2021-01-05 02:55:32','',NULL),(12,'2','deleted','0正常；1删除','int(1)','Integer','deleted','0','0',NULL,'1','1','1','1','EQ','input','',3,'admin','2021-01-05 02:55:32','',NULL),(13,'2','created_at',NULL,'datetime','Date','createdAt','0','0',NULL,'1','1','1','1','EQ','datetime','',4,'admin','2021-01-05 02:55:32','',NULL),(14,'2','created_by',NULL,'varchar(20)','String','createdBy','0','0',NULL,'1','1','1','1','EQ','input','',5,'admin','2021-01-05 02:55:32','',NULL),(15,'2','updated_at',NULL,'datetime','Date','updatedAt','0','0',NULL,'1','1','1','1','EQ','datetime','',6,'admin','2021-01-05 02:55:32','',NULL),(16,'2','updated_by',NULL,'varchar(20)','String','updatedBy','0','0',NULL,'1','1','1','1','EQ','input','',7,'admin','2021-01-05 02:55:32','',NULL);

/*Table structure for table `qrtz_blob_triggers` */

DROP TABLE IF EXISTS `qrtz_blob_triggers`;

CREATE TABLE `qrtz_blob_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `blob_data` blob,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_blob_triggers` */

/*Table structure for table `qrtz_calendars` */

DROP TABLE IF EXISTS `qrtz_calendars`;

CREATE TABLE `qrtz_calendars` (
  `sched_name` varchar(120) NOT NULL,
  `calendar_name` varchar(200) NOT NULL,
  `calendar` blob NOT NULL,
  PRIMARY KEY (`sched_name`,`calendar_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_calendars` */

/*Table structure for table `qrtz_cron_triggers` */

DROP TABLE IF EXISTS `qrtz_cron_triggers`;

CREATE TABLE `qrtz_cron_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `cron_expression` varchar(200) NOT NULL,
  `time_zone_id` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_cron_triggers` */

insert  into `qrtz_cron_triggers`(`sched_name`,`trigger_name`,`trigger_group`,`cron_expression`,`time_zone_id`) values ('EquipmentScheduler','TASK_CLASS_NAME1','DEFAULT','0/10 * * * * ?','Asia/Shanghai'),('EquipmentScheduler','TASK_CLASS_NAME2','DEFAULT','0/15 * * * * ?','Asia/Shanghai'),('EquipmentScheduler','TASK_CLASS_NAME3','DEFAULT','0/20 * * * * ?','Asia/Shanghai');

/*Table structure for table `qrtz_fired_triggers` */

DROP TABLE IF EXISTS `qrtz_fired_triggers`;

CREATE TABLE `qrtz_fired_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `entry_id` varchar(95) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `instance_name` varchar(200) NOT NULL,
  `fired_time` bigint(13) NOT NULL,
  `sched_time` bigint(13) NOT NULL,
  `priority` int(11) NOT NULL,
  `state` varchar(16) NOT NULL,
  `job_name` varchar(200) DEFAULT NULL,
  `job_group` varchar(200) DEFAULT NULL,
  `is_nonconcurrent` varchar(1) DEFAULT NULL,
  `requests_recovery` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`sched_name`,`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_fired_triggers` */

/*Table structure for table `qrtz_job_details` */

DROP TABLE IF EXISTS `qrtz_job_details`;

CREATE TABLE `qrtz_job_details` (
  `sched_name` varchar(120) NOT NULL,
  `job_name` varchar(200) NOT NULL,
  `job_group` varchar(200) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `job_class_name` varchar(250) NOT NULL,
  `is_durable` varchar(1) NOT NULL,
  `is_nonconcurrent` varchar(1) NOT NULL,
  `is_update_data` varchar(1) NOT NULL,
  `requests_recovery` varchar(1) NOT NULL,
  `job_data` blob,
  PRIMARY KEY (`sched_name`,`job_name`,`job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_job_details` */

insert  into `qrtz_job_details`(`sched_name`,`job_name`,`job_group`,`description`,`job_class_name`,`is_durable`,`is_nonconcurrent`,`is_update_data`,`requests_recovery`,`job_data`) values ('EquipmentScheduler','TASK_CLASS_NAME1','DEFAULT',NULL,'com.equipment.quartz.util.QuartzDisallowConcurrentExecution','0','1','0','0','��\0sr\0org.quartz.JobDataMap���迩��\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap�����](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap�.�(v\n�\0Z\0dirtyL\0mapt\0Ljava/util/Map;xpsr\0java.util.HashMap���`�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0TASK_PROPERTIESsr\0\"com.equipment.quartz.domain.SysJob\0\0\0\0\0\0\0\0L\0\nconcurrentt\0Ljava/lang/String;L\0cronExpressionq\0~\0	L\0invokeTargetq\0~\0	L\0jobGroupq\0~\0	L\0jobIdt\0Ljava/lang/Long;L\0jobNameq\0~\0	L\0\rmisfirePolicyq\0~\0	L\0statusq\0~\0	xr\0+com.equipment.common.core.domain.BaseEntity\0\0\0\0\0\0\0\0L\0createByq\0~\0	L\0\ncreateTimet\0Ljava/util/Date;L\0paramsq\0~\0L\0remarkq\0~\0	L\0searchValueq\0~\0	L\0updateByq\0~\0	L\0\nupdateTimeq\0~\0xpt\0adminsr\0java.util.Datehj�KYt\0\0xpw\0\0vβ�xpt\0\0pppt\01t\00/10 * * * * ?t\0ryTask.ryNoParamst\0DEFAULTsr\0java.lang.Long;��̏#�\0J\0valuexr\0java.lang.Number������\0\0xp\0\0\0\0\0\0\0t\0系统默认（无参）t\03t\01x\0'),('EquipmentScheduler','TASK_CLASS_NAME2','DEFAULT',NULL,'com.equipment.quartz.util.QuartzDisallowConcurrentExecution','0','1','0','0','��\0sr\0org.quartz.JobDataMap���迩��\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap�����](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap�.�(v\n�\0Z\0dirtyL\0mapt\0Ljava/util/Map;xpsr\0java.util.HashMap���`�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0TASK_PROPERTIESsr\0\"com.equipment.quartz.domain.SysJob\0\0\0\0\0\0\0\0L\0\nconcurrentt\0Ljava/lang/String;L\0cronExpressionq\0~\0	L\0invokeTargetq\0~\0	L\0jobGroupq\0~\0	L\0jobIdt\0Ljava/lang/Long;L\0jobNameq\0~\0	L\0\rmisfirePolicyq\0~\0	L\0statusq\0~\0	xr\0+com.equipment.common.core.domain.BaseEntity\0\0\0\0\0\0\0\0L\0createByq\0~\0	L\0\ncreateTimet\0Ljava/util/Date;L\0paramsq\0~\0L\0remarkq\0~\0	L\0searchValueq\0~\0	L\0updateByq\0~\0	L\0\nupdateTimeq\0~\0xpt\0adminsr\0java.util.Datehj�KYt\0\0xpw\0\0vβ�xpt\0\0pppt\01t\00/15 * * * * ?t\0ryTask.ryParams(\'ry\')t\0DEFAULTsr\0java.lang.Long;��̏#�\0J\0valuexr\0java.lang.Number������\0\0xp\0\0\0\0\0\0\0t\0系统默认（有参）t\03t\01x\0'),('EquipmentScheduler','TASK_CLASS_NAME3','DEFAULT',NULL,'com.equipment.quartz.util.QuartzDisallowConcurrentExecution','0','1','0','0','��\0sr\0org.quartz.JobDataMap���迩��\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap�����](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap�.�(v\n�\0Z\0dirtyL\0mapt\0Ljava/util/Map;xpsr\0java.util.HashMap���`�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0TASK_PROPERTIESsr\0\"com.equipment.quartz.domain.SysJob\0\0\0\0\0\0\0\0L\0\nconcurrentt\0Ljava/lang/String;L\0cronExpressionq\0~\0	L\0invokeTargetq\0~\0	L\0jobGroupq\0~\0	L\0jobIdt\0Ljava/lang/Long;L\0jobNameq\0~\0	L\0\rmisfirePolicyq\0~\0	L\0statusq\0~\0	xr\0+com.equipment.common.core.domain.BaseEntity\0\0\0\0\0\0\0\0L\0createByq\0~\0	L\0\ncreateTimet\0Ljava/util/Date;L\0paramsq\0~\0L\0remarkq\0~\0	L\0searchValueq\0~\0	L\0updateByq\0~\0	L\0\nupdateTimeq\0~\0xpt\0adminsr\0java.util.Datehj�KYt\0\0xpw\0\0vβ�xpt\0\0pppt\01t\00/20 * * * * ?t\08ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)t\0DEFAULTsr\0java.lang.Long;��̏#�\0J\0valuexr\0java.lang.Number������\0\0xp\0\0\0\0\0\0\0t\0系统默认（多参）t\03t\01x\0');

/*Table structure for table `qrtz_locks` */

DROP TABLE IF EXISTS `qrtz_locks`;

CREATE TABLE `qrtz_locks` (
  `sched_name` varchar(120) NOT NULL,
  `lock_name` varchar(40) NOT NULL,
  PRIMARY KEY (`sched_name`,`lock_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_locks` */

insert  into `qrtz_locks`(`sched_name`,`lock_name`) values ('EquipmentScheduler','STATE_ACCESS'),('EquipmentScheduler','TRIGGER_ACCESS');

/*Table structure for table `qrtz_paused_trigger_grps` */

DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;

CREATE TABLE `qrtz_paused_trigger_grps` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  PRIMARY KEY (`sched_name`,`trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_paused_trigger_grps` */

/*Table structure for table `qrtz_scheduler_state` */

DROP TABLE IF EXISTS `qrtz_scheduler_state`;

CREATE TABLE `qrtz_scheduler_state` (
  `sched_name` varchar(120) NOT NULL,
  `instance_name` varchar(200) NOT NULL,
  `last_checkin_time` bigint(13) NOT NULL,
  `checkin_interval` bigint(13) NOT NULL,
  PRIMARY KEY (`sched_name`,`instance_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_scheduler_state` */

insert  into `qrtz_scheduler_state`(`sched_name`,`instance_name`,`last_checkin_time`,`checkin_interval`) values ('EquipmentScheduler','Yayo1613925130807',1613925358983,15000);

/*Table structure for table `qrtz_simple_triggers` */

DROP TABLE IF EXISTS `qrtz_simple_triggers`;

CREATE TABLE `qrtz_simple_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `repeat_count` bigint(7) NOT NULL,
  `repeat_interval` bigint(12) NOT NULL,
  `times_triggered` bigint(10) NOT NULL,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_simple_triggers` */

/*Table structure for table `qrtz_simprop_triggers` */

DROP TABLE IF EXISTS `qrtz_simprop_triggers`;

CREATE TABLE `qrtz_simprop_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `str_prop_1` varchar(512) DEFAULT NULL,
  `str_prop_2` varchar(512) DEFAULT NULL,
  `str_prop_3` varchar(512) DEFAULT NULL,
  `int_prop_1` int(11) DEFAULT NULL,
  `int_prop_2` int(11) DEFAULT NULL,
  `long_prop_1` bigint(20) DEFAULT NULL,
  `long_prop_2` bigint(20) DEFAULT NULL,
  `dec_prop_1` decimal(13,4) DEFAULT NULL,
  `dec_prop_2` decimal(13,4) DEFAULT NULL,
  `bool_prop_1` varchar(1) DEFAULT NULL,
  `bool_prop_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_simprop_triggers` */

/*Table structure for table `qrtz_triggers` */

DROP TABLE IF EXISTS `qrtz_triggers`;

CREATE TABLE `qrtz_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `job_name` varchar(200) NOT NULL,
  `job_group` varchar(200) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `next_fire_time` bigint(13) DEFAULT NULL,
  `prev_fire_time` bigint(13) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `trigger_state` varchar(16) NOT NULL,
  `trigger_type` varchar(8) NOT NULL,
  `start_time` bigint(13) NOT NULL,
  `end_time` bigint(13) DEFAULT NULL,
  `calendar_name` varchar(200) DEFAULT NULL,
  `misfire_instr` smallint(2) DEFAULT NULL,
  `job_data` blob,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  KEY `sched_name` (`sched_name`,`job_name`,`job_group`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `job_name`, `job_group`) REFERENCES `qrtz_job_details` (`sched_name`, `job_name`, `job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_triggers` */

insert  into `qrtz_triggers`(`sched_name`,`trigger_name`,`trigger_group`,`job_name`,`job_group`,`description`,`next_fire_time`,`prev_fire_time`,`priority`,`trigger_state`,`trigger_type`,`start_time`,`end_time`,`calendar_name`,`misfire_instr`,`job_data`) values ('EquipmentScheduler','TASK_CLASS_NAME1','DEFAULT','TASK_CLASS_NAME1','DEFAULT',NULL,1613925130000,-1,5,'PAUSED','CRON',1613925130000,0,NULL,2,''),('EquipmentScheduler','TASK_CLASS_NAME2','DEFAULT','TASK_CLASS_NAME2','DEFAULT',NULL,1613925135000,-1,5,'PAUSED','CRON',1613925131000,0,NULL,2,''),('EquipmentScheduler','TASK_CLASS_NAME3','DEFAULT','TASK_CLASS_NAME3','DEFAULT',NULL,1613925140000,-1,5,'PAUSED','CRON',1613925131000,0,NULL,2,'');

/*Table structure for table `sys_config` */

DROP TABLE IF EXISTS `sys_config`;

CREATE TABLE `sys_config` (
  `config_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COMMENT='参数配置表';

/*Data for the table `sys_config` */

insert  into `sys_config`(`config_id`,`config_name`,`config_key`,`config_value`,`config_type`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values (1,'主框架页-默认皮肤样式名称','sys.index.skinName','skin-blue','Y','admin','2021-01-05 02:39:59','',NULL,'蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow'),(2,'用户管理-账号初始密码','sys.user.initPassword','123456','Y','admin','2021-01-05 02:39:59','',NULL,'初始化密码 123456'),(3,'主框架页-侧边栏主题','sys.index.sideTheme','theme-dark','Y','admin','2021-01-05 02:39:59','',NULL,'深黑主题theme-dark，浅色主题theme-light，深蓝主题theme-blue'),(4,'账号自助-是否开启用户注册功能','sys.account.registerUser','false','Y','admin','2021-01-05 02:39:59','',NULL,'是否开启注册用户功能（true开启，false关闭）'),(5,'用户管理-密码字符范围','sys.account.chrtype','0','Y','admin','2021-01-05 02:39:59','',NULL,'默认任意字符范围，0任意（密码可以输入任意字符），1数字（密码只能为0-9数字），2英文字母（密码只能为a-z和A-Z字母），3字母和数字（密码必须包含字母，数字）,4字母数字和特殊字符（目前支持的特殊字符包括：~!@#$%^&*()-=_+）'),(6,'用户管理-初始密码修改策略','sys.account.initPasswordModify','0','Y','admin','2021-01-05 02:39:59','',NULL,'0：初始密码修改策略关闭，没有任何提示，1：提醒用户，如果未修改初始密码，则在登录时就会提醒修改密码对话框'),(7,'用户管理-账号密码更新周期','sys.account.passwordValidateDays','0','Y','admin','2021-01-05 02:39:59','',NULL,'密码更新周期（填写数字，数据初始化值为0不限制，若修改必须为大于0小于365的正整数），如果超过这个周期登录系统时，则在登录时就会提醒修改密码对话框'),(8,'主框架页-菜单导航显示风格','sys.index.menuStyle','default','Y','admin','2021-01-05 02:39:59','',NULL,'菜单导航显示风格（default为左侧导航菜单，topnav为顶部导航菜单）'),(9,'主框架页-是否开启页脚','sys.index.ignoreFooter','true','Y','admin','2021-01-05 02:39:59','',NULL,'是否开启底部页脚显示（true显示，false隐藏）');

/*Table structure for table `sys_dept` */

DROP TABLE IF EXISTS `sys_dept`;

CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父部门id',
  `ancestors` varchar(50) DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `leader` varchar(20) DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `status` char(1) DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=200 DEFAULT CHARSET=utf8 COMMENT='部门表';

/*Data for the table `sys_dept` */

insert  into `sys_dept`(`dept_id`,`parent_id`,`ancestors`,`dept_name`,`order_num`,`leader`,`phone`,`email`,`status`,`del_flag`,`create_by`,`create_time`,`update_by`,`update_time`) values (100,0,'0','若依科技',0,'若依','15888888888','ry@qq.com','0','0','admin','2021-01-05 02:39:29','',NULL),(101,100,'0,100','深圳总公司',1,'若依','15888888888','ry@qq.com','0','0','admin','2021-01-05 02:39:29','',NULL),(102,100,'0,100','长沙分公司',2,'若依','15888888888','ry@qq.com','0','0','admin','2021-01-05 02:39:29','',NULL),(103,101,'0,100,101','研发部门',1,'若依','15888888888','ry@qq.com','0','0','admin','2021-01-05 02:39:29','',NULL),(104,101,'0,100,101','市场部门',2,'若依','15888888888','ry@qq.com','0','0','admin','2021-01-05 02:39:29','',NULL),(105,101,'0,100,101','测试部门',3,'若依','15888888888','ry@qq.com','0','0','admin','2021-01-05 02:39:29','',NULL),(106,101,'0,100,101','财务部门',4,'若依','15888888888','ry@qq.com','0','0','admin','2021-01-05 02:39:29','',NULL),(107,101,'0,100,101','运维部门',5,'若依','15888888888','ry@qq.com','0','0','admin','2021-01-05 02:39:29','',NULL),(108,102,'0,100,102','市场部门',1,'若依','15888888888','ry@qq.com','0','0','admin','2021-01-05 02:39:29','',NULL),(109,102,'0,100,102','财务部门',2,'若依','15888888888','ry@qq.com','0','0','admin','2021-01-05 02:39:29','',NULL);

/*Table structure for table `sys_dict_data` */

DROP TABLE IF EXISTS `sys_dict_data`;

CREATE TABLE `sys_dict_data` (
  `dict_code` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(4) DEFAULT '0' COMMENT '字典排序',
  `dict_label` varchar(100) DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COMMENT='字典数据表';

/*Data for the table `sys_dict_data` */

insert  into `sys_dict_data`(`dict_code`,`dict_sort`,`dict_label`,`dict_value`,`dict_type`,`css_class`,`list_class`,`is_default`,`status`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values (1,1,'男','0','sys_user_sex','','','Y','0','admin','2021-01-05 02:39:56','',NULL,'性别男'),(2,2,'女','1','sys_user_sex','','','N','0','admin','2021-01-05 02:39:56','',NULL,'性别女'),(3,3,'未知','2','sys_user_sex','','','N','0','admin','2021-01-05 02:39:56','',NULL,'性别未知'),(4,1,'显示','0','sys_show_hide','','primary','Y','0','admin','2021-01-05 02:39:56','',NULL,'显示菜单'),(5,2,'隐藏','1','sys_show_hide','','danger','N','0','admin','2021-01-05 02:39:56','',NULL,'隐藏菜单'),(6,1,'正常','0','sys_normal_disable','','primary','Y','0','admin','2021-01-05 02:39:56','',NULL,'正常状态'),(7,2,'停用','1','sys_normal_disable','','danger','N','0','admin','2021-01-05 02:39:56','',NULL,'停用状态'),(8,1,'正常','0','sys_job_status','','primary','Y','0','admin','2021-01-05 02:39:56','',NULL,'正常状态'),(9,2,'暂停','1','sys_job_status','','danger','N','0','admin','2021-01-05 02:39:56','',NULL,'停用状态'),(10,1,'默认','DEFAULT','sys_job_group','','','Y','0','admin','2021-01-05 02:39:56','',NULL,'默认分组'),(11,2,'系统','SYSTEM','sys_job_group','','','N','0','admin','2021-01-05 02:39:56','',NULL,'系统分组'),(12,1,'是','Y','sys_yes_no','','primary','Y','0','admin','2021-01-05 02:39:56','',NULL,'系统默认是'),(13,2,'否','N','sys_yes_no','','danger','N','0','admin','2021-01-05 02:39:56','',NULL,'系统默认否'),(14,1,'通知','1','sys_notice_type','','warning','Y','0','admin','2021-01-05 02:39:56','',NULL,'通知'),(15,2,'公告','2','sys_notice_type','','success','N','0','admin','2021-01-05 02:39:56','',NULL,'公告'),(16,1,'正常','0','sys_notice_status','','primary','Y','0','admin','2021-01-05 02:39:56','',NULL,'正常状态'),(17,2,'关闭','1','sys_notice_status','','danger','N','0','admin','2021-01-05 02:39:57','',NULL,'关闭状态'),(18,99,'其他','0','sys_oper_type','','info','N','0','admin','2021-01-05 02:39:57','',NULL,'其他操作'),(19,1,'新增','1','sys_oper_type','','info','N','0','admin','2021-01-05 02:39:57','',NULL,'新增操作'),(20,2,'修改','2','sys_oper_type','','info','N','0','admin','2021-01-05 02:39:57','',NULL,'修改操作'),(21,3,'删除','3','sys_oper_type','','danger','N','0','admin','2021-01-05 02:39:57','',NULL,'删除操作'),(22,4,'授权','4','sys_oper_type','','primary','N','0','admin','2021-01-05 02:39:57','',NULL,'授权操作'),(23,5,'导出','5','sys_oper_type','','warning','N','0','admin','2021-01-05 02:39:57','',NULL,'导出操作'),(24,6,'导入','6','sys_oper_type','','warning','N','0','admin','2021-01-05 02:39:57','',NULL,'导入操作'),(25,7,'强退','7','sys_oper_type','','danger','N','0','admin','2021-01-05 02:39:57','',NULL,'强退操作'),(26,8,'生成代码','8','sys_oper_type','','warning','N','0','admin','2021-01-05 02:39:57','',NULL,'生成操作'),(27,9,'清空数据','9','sys_oper_type','','danger','N','0','admin','2021-01-05 02:39:57','',NULL,'清空操作'),(28,1,'成功','0','sys_common_status','','primary','N','0','admin','2021-01-05 02:39:57','',NULL,'正常状态'),(29,2,'失败','1','sys_common_status','','danger','N','0','admin','2021-01-05 02:39:57','',NULL,'停用状态');

/*Table structure for table `sys_dict_type` */

DROP TABLE IF EXISTS `sys_dict_type`;

CREATE TABLE `sys_dict_type` (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`),
  UNIQUE KEY `dict_type` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COMMENT='字典类型表';

/*Data for the table `sys_dict_type` */

insert  into `sys_dict_type`(`dict_id`,`dict_name`,`dict_type`,`status`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values (1,'用户性别','sys_user_sex','0','admin','2021-01-05 02:39:54','',NULL,'用户性别列表'),(2,'菜单状态','sys_show_hide','0','admin','2021-01-05 02:39:54','',NULL,'菜单状态列表'),(3,'系统开关','sys_normal_disable','0','admin','2021-01-05 02:39:54','',NULL,'系统开关列表'),(4,'任务状态','sys_job_status','0','admin','2021-01-05 02:39:54','',NULL,'任务状态列表'),(5,'任务分组','sys_job_group','0','admin','2021-01-05 02:39:54','',NULL,'任务分组列表'),(6,'系统是否','sys_yes_no','0','admin','2021-01-05 02:39:54','',NULL,'系统是否列表'),(7,'通知类型','sys_notice_type','0','admin','2021-01-05 02:39:54','',NULL,'通知类型列表'),(8,'通知状态','sys_notice_status','0','admin','2021-01-05 02:39:54','',NULL,'通知状态列表'),(9,'操作类型','sys_oper_type','0','admin','2021-01-05 02:39:54','',NULL,'操作类型列表'),(10,'系统状态','sys_common_status','0','admin','2021-01-05 02:39:54','',NULL,'登录状态列表');

/*Table structure for table `sys_job` */

DROP TABLE IF EXISTS `sys_job`;

CREATE TABLE `sys_job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
  `invoke_target` varchar(500) NOT NULL COMMENT '调用目标字符串',
  `cron_expression` varchar(255) DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `concurrent` char(1) DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1暂停）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`job_id`,`job_name`,`job_group`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COMMENT='定时任务调度表';

/*Data for the table `sys_job` */

insert  into `sys_job`(`job_id`,`job_name`,`job_group`,`invoke_target`,`cron_expression`,`misfire_policy`,`concurrent`,`status`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values (1,'系统默认（无参）','DEFAULT','ryTask.ryNoParams','0/10 * * * * ?','3','1','1','admin','2021-01-05 02:40:03','',NULL,''),(2,'系统默认（有参）','DEFAULT','ryTask.ryParams(\'ry\')','0/15 * * * * ?','3','1','1','admin','2021-01-05 02:40:03','',NULL,''),(3,'系统默认（多参）','DEFAULT','ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)','0/20 * * * * ?','3','1','1','admin','2021-01-05 02:40:03','',NULL,'');

/*Table structure for table `sys_job_log` */

DROP TABLE IF EXISTS `sys_job_log`;

CREATE TABLE `sys_job_log` (
  `job_log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name` varchar(64) NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL COMMENT '任务组名',
  `invoke_target` varchar(500) NOT NULL COMMENT '调用目标字符串',
  `job_message` varchar(500) DEFAULT NULL COMMENT '日志信息',
  `status` char(1) DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(2000) DEFAULT '' COMMENT '异常信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务调度日志表';

/*Data for the table `sys_job_log` */

/*Table structure for table `sys_logininfor` */

DROP TABLE IF EXISTS `sys_logininfor`;

CREATE TABLE `sys_logininfor` (
  `info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `login_name` varchar(50) DEFAULT '' COMMENT '登录账号',
  `ipaddr` varchar(50) DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT '' COMMENT '操作系统',
  `status` char(1) DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) DEFAULT '' COMMENT '提示消息',
  `login_time` datetime DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=185 DEFAULT CHARSET=utf8 COMMENT='系统访问记录';

/*Data for the table `sys_logininfor` */

insert  into `sys_logininfor`(`info_id`,`login_name`,`ipaddr`,`login_location`,`browser`,`os`,`status`,`msg`,`login_time`) values (100,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-05 02:41:36'),(101,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-05 03:00:23'),(102,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-05 03:18:11'),(103,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-05 03:19:20'),(104,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-05 03:20:07'),(105,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-05 03:21:04'),(106,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-05 03:21:32'),(107,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-11 15:40:38'),(108,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-14 16:21:03'),(109,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-14 18:42:07'),(110,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-14 22:10:00'),(111,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-14 22:13:51'),(112,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-02-14 22:15:43'),(113,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-14 22:15:45'),(114,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-14 23:01:14'),(115,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-15 10:54:12'),(116,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-15 21:04:31'),(117,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-15 21:10:02'),(118,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-15 21:39:09'),(119,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-15 21:43:35'),(120,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-15 21:46:34'),(121,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-15 21:48:00'),(122,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-15 21:50:57'),(123,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-15 22:13:21'),(124,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-15 22:14:08'),(125,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-15 23:28:11'),(126,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-16 21:49:24'),(127,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-16 22:31:25'),(128,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-16 22:35:10'),(129,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-16 22:41:01'),(130,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-16 22:42:05'),(131,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-16 22:44:28'),(132,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-16 22:55:47'),(133,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-16 23:16:52'),(134,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-16 23:36:45'),(135,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-16 23:50:10'),(136,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-16 23:52:17'),(137,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-16 23:52:57'),(138,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-16 23:53:23'),(139,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-16 23:56:43'),(140,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-17 13:23:34'),(141,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-17 14:06:11'),(142,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-02-17 14:07:45'),(143,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-17 21:17:22'),(144,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-17 21:37:07'),(145,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-17 22:29:59'),(146,'ry','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-17 23:47:39'),(147,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-21 14:06:21'),(148,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-21 15:30:08'),(149,'stu','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-21 15:50:42'),(150,'stu','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-21 15:51:04'),(151,'stu','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-21 15:53:19'),(152,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-21 15:55:13'),(153,'stu','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-21 15:55:20'),(154,'stu','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-21 15:56:01'),(155,'stu','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-21 15:56:13'),(156,'stu','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-21 15:56:43'),(157,'stu','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-21 15:57:24'),(158,'stu','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-21 15:57:44'),(159,'stu','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-21 15:58:47'),(160,'stu','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-21 16:02:09'),(161,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-21 16:02:19'),(162,'stu','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-21 16:02:36'),(163,'stu','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-21 16:02:51'),(164,'stu','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-21 16:03:27'),(165,'stu','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-21 16:04:30'),(166,'stu','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-21 16:05:39'),(167,'stu','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-21 16:07:44'),(168,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-21 16:07:55'),(169,'stu','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-21 16:08:59'),(170,'stu','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-21 16:19:25'),(171,'stu','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-21 16:21:40'),(172,'stu','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-21 16:24:08'),(173,'stu','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-21 16:25:47'),(174,'stu','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-21 16:26:43'),(175,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-21 17:14:17'),(176,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-21 19:39:18'),(177,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-21 21:52:26'),(178,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-21 22:51:41'),(179,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-21 23:19:36'),(180,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-21 23:22:29'),(181,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','验证码错误','2021-02-22 00:29:59'),(182,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-22 00:30:02'),(183,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','验证码错误','2021-02-22 00:32:55'),(184,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-22 00:32:57');

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `url` varchar(200) DEFAULT '#' COMMENT '请求地址',
  `target` varchar(20) DEFAULT '' COMMENT '打开方式（menuItem页签 menuBlank新窗口）',
  `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2012 DEFAULT CHARSET=utf8 COMMENT='菜单权限表';

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`menu_id`,`menu_name`,`parent_id`,`order_num`,`url`,`target`,`menu_type`,`visible`,`perms`,`icon`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values (1,'系统管理',0,1,'#','','M','0','','fa fa-gear','admin','2021-01-05 02:39:35','',NULL,'系统管理目录'),(2,'系统监控',0,2,'#','','M','0','','fa fa-video-camera','admin','2021-01-05 02:39:35','',NULL,'系统监控目录'),(3,'系统工具',0,3,'#','','M','0','','fa fa-bars','admin','2021-01-05 02:39:35','',NULL,'系统工具目录'),(4,'若依官网',0,4,'http://equipment.vip','menuBlank','C','0','','fa fa-location-arrow','admin','2021-01-05 02:39:35','',NULL,'若依官网地址'),(100,'用户管理',1,1,'/system/user','','C','0','system:user:view','fa fa-user-o','admin','2021-01-05 02:39:35','',NULL,'用户管理菜单'),(101,'角色管理',1,2,'/system/role','','C','0','system:role:view','fa fa-user-secret','admin','2021-01-05 02:39:35','',NULL,'角色管理菜单'),(102,'菜单管理',1,3,'/system/menu','','C','0','system:menu:view','fa fa-th-list','admin','2021-01-05 02:39:35','',NULL,'菜单管理菜单'),(103,'部门管理',1,4,'/system/dept','menuItem','C','1','system:dept:view','fa fa-outdent','admin','2021-01-05 02:39:35','admin','2021-02-17 21:19:38','部门管理菜单'),(104,'岗位管理',1,5,'/system/post','menuItem','C','1','system:post:view','fa fa-address-card-o','admin','2021-01-05 02:39:35','admin','2021-02-17 21:20:27','岗位管理菜单'),(105,'字典管理',1,6,'/system/dict','','C','0','system:dict:view','fa fa-bookmark-o','admin','2021-01-05 02:39:35','',NULL,'字典管理菜单'),(106,'参数设置',1,7,'/system/config','','C','0','system:config:view','fa fa-sun-o','admin','2021-01-05 02:39:35','',NULL,'参数设置菜单'),(107,'通知公告',1,8,'/system/notice','','C','0','system:notice:view','fa fa-bullhorn','admin','2021-01-05 02:39:35','',NULL,'通知公告菜单'),(108,'日志管理',1,9,'#','','M','0','','fa fa-pencil-square-o','admin','2021-01-05 02:39:35','',NULL,'日志管理菜单'),(109,'在线用户',2,1,'/monitor/online','','C','0','monitor:online:view','fa fa-user-circle','admin','2021-01-05 02:39:35','',NULL,'在线用户菜单'),(110,'定时任务',2,2,'/monitor/job','','C','0','monitor:job:view','fa fa-tasks','admin','2021-01-05 02:39:35','',NULL,'定时任务菜单'),(111,'数据监控',2,3,'/monitor/data','','C','0','monitor:data:view','fa fa-bug','admin','2021-01-05 02:39:35','',NULL,'数据监控菜单'),(112,'服务监控',2,4,'/monitor/server','','C','0','monitor:server:view','fa fa-server','admin','2021-01-05 02:39:35','',NULL,'服务监控菜单'),(113,'缓存监控',2,5,'/monitor/cache','','C','0','monitor:cache:view','fa fa-cube','admin','2021-01-05 02:39:35','',NULL,'缓存监控菜单'),(114,'表单构建',3,1,'/tool/build','','C','0','tool:build:view','fa fa-wpforms','admin','2021-01-05 02:39:36','',NULL,'表单构建菜单'),(115,'代码生成',3,2,'/tool/gen','','C','0','tool:gen:view','fa fa-code','admin','2021-01-05 02:39:36','',NULL,'代码生成菜单'),(116,'系统接口',3,3,'/tool/swagger','','C','0','tool:swagger:view','fa fa-gg','admin','2021-01-05 02:39:36','',NULL,'系统接口菜单'),(500,'操作日志',108,1,'/monitor/operlog','','C','0','monitor:operlog:view','fa fa-address-book','admin','2021-01-05 02:39:36','',NULL,'操作日志菜单'),(501,'登录日志',108,2,'/monitor/logininfor','','C','0','monitor:logininfor:view','fa fa-file-image-o','admin','2021-01-05 02:39:36','',NULL,'登录日志菜单'),(1000,'用户查询',100,1,'#','','F','0','system:user:list','#','admin','2021-01-05 02:39:36','',NULL,''),(1001,'用户新增',100,2,'#','','F','0','system:user:add','#','admin','2021-01-05 02:39:36','',NULL,''),(1002,'用户修改',100,3,'#','','F','0','system:user:edit','#','admin','2021-01-05 02:39:36','',NULL,''),(1003,'用户删除',100,4,'#','','F','0','system:user:remove','#','admin','2021-01-05 02:39:36','',NULL,''),(1004,'用户导出',100,5,'#','','F','0','system:user:export','#','admin','2021-01-05 02:39:36','',NULL,''),(1005,'用户导入',100,6,'#','','F','0','system:user:import','#','admin','2021-01-05 02:39:36','',NULL,''),(1006,'重置密码',100,7,'#','','F','0','system:user:resetPwd','#','admin','2021-01-05 02:39:36','',NULL,''),(1007,'角色查询',101,1,'#','','F','0','system:role:list','#','admin','2021-01-05 02:39:36','',NULL,''),(1008,'角色新增',101,2,'#','','F','0','system:role:add','#','admin','2021-01-05 02:39:36','',NULL,''),(1009,'角色修改',101,3,'#','','F','0','system:role:edit','#','admin','2021-01-05 02:39:36','',NULL,''),(1010,'角色删除',101,4,'#','','F','0','system:role:remove','#','admin','2021-01-05 02:39:36','',NULL,''),(1011,'角色导出',101,5,'#','','F','0','system:role:export','#','admin','2021-01-05 02:39:36','',NULL,''),(1012,'菜单查询',102,1,'#','','F','0','system:menu:list','#','admin','2021-01-05 02:39:36','',NULL,''),(1013,'菜单新增',102,2,'#','','F','0','system:menu:add','#','admin','2021-01-05 02:39:36','',NULL,''),(1014,'菜单修改',102,3,'#','','F','0','system:menu:edit','#','admin','2021-01-05 02:39:37','',NULL,''),(1015,'菜单删除',102,4,'#','','F','0','system:menu:remove','#','admin','2021-01-05 02:39:37','',NULL,''),(1016,'部门查询',103,1,'#','','F','0','system:dept:list','#','admin','2021-01-05 02:39:37','',NULL,''),(1017,'部门新增',103,2,'#','','F','0','system:dept:add','#','admin','2021-01-05 02:39:37','',NULL,''),(1018,'部门修改',103,3,'#','','F','0','system:dept:edit','#','admin','2021-01-05 02:39:37','',NULL,''),(1019,'部门删除',103,4,'#','','F','0','system:dept:remove','#','admin','2021-01-05 02:39:37','',NULL,''),(1020,'岗位查询',104,1,'#','','F','0','system:post:list','#','admin','2021-01-05 02:39:37','',NULL,''),(1021,'岗位新增',104,2,'#','','F','0','system:post:add','#','admin','2021-01-05 02:39:37','',NULL,''),(1022,'岗位修改',104,3,'#','','F','0','system:post:edit','#','admin','2021-01-05 02:39:37','',NULL,''),(1023,'岗位删除',104,4,'#','','F','0','system:post:remove','#','admin','2021-01-05 02:39:37','',NULL,''),(1024,'岗位导出',104,5,'#','','F','0','system:post:export','#','admin','2021-01-05 02:39:37','',NULL,''),(1025,'字典查询',105,1,'#','','F','0','system:dict:list','#','admin','2021-01-05 02:39:37','',NULL,''),(1026,'字典新增',105,2,'#','','F','0','system:dict:add','#','admin','2021-01-05 02:39:37','',NULL,''),(1027,'字典修改',105,3,'#','','F','0','system:dict:edit','#','admin','2021-01-05 02:39:37','',NULL,''),(1028,'字典删除',105,4,'#','','F','0','system:dict:remove','#','admin','2021-01-05 02:39:37','',NULL,''),(1029,'字典导出',105,5,'#','','F','0','system:dict:export','#','admin','2021-01-05 02:39:37','',NULL,''),(1030,'参数查询',106,1,'#','','F','0','system:config:list','#','admin','2021-01-05 02:39:37','',NULL,''),(1031,'参数新增',106,2,'#','','F','0','system:config:add','#','admin','2021-01-05 02:39:37','',NULL,''),(1032,'参数修改',106,3,'#','','F','0','system:config:edit','#','admin','2021-01-05 02:39:37','',NULL,''),(1033,'参数删除',106,4,'#','','F','0','system:config:remove','#','admin','2021-01-05 02:39:37','',NULL,''),(1034,'参数导出',106,5,'#','','F','0','system:config:export','#','admin','2021-01-05 02:39:37','',NULL,''),(1035,'公告查询',107,1,'#','','F','0','system:notice:list','#','admin','2021-01-05 02:39:38','',NULL,''),(1036,'公告新增',107,2,'#','','F','0','system:notice:add','#','admin','2021-01-05 02:39:38','',NULL,''),(1037,'公告修改',107,3,'#','','F','0','system:notice:edit','#','admin','2021-01-05 02:39:38','',NULL,''),(1038,'公告删除',107,4,'#','','F','0','system:notice:remove','#','admin','2021-01-05 02:39:38','',NULL,''),(1039,'操作查询',500,1,'#','','F','0','monitor:operlog:list','#','admin','2021-01-05 02:39:38','',NULL,''),(1040,'操作删除',500,2,'#','','F','0','monitor:operlog:remove','#','admin','2021-01-05 02:39:38','',NULL,''),(1041,'详细信息',500,3,'#','','F','0','monitor:operlog:detail','#','admin','2021-01-05 02:39:38','',NULL,''),(1042,'日志导出',500,4,'#','','F','0','monitor:operlog:export','#','admin','2021-01-05 02:39:38','',NULL,''),(1043,'登录查询',501,1,'#','','F','0','monitor:logininfor:list','#','admin','2021-01-05 02:39:38','',NULL,''),(1044,'登录删除',501,2,'#','','F','0','monitor:logininfor:remove','#','admin','2021-01-05 02:39:38','',NULL,''),(1045,'日志导出',501,3,'#','','F','0','monitor:logininfor:export','#','admin','2021-01-05 02:39:38','',NULL,''),(1046,'账户解锁',501,4,'#','','F','0','monitor:logininfor:unlock','#','admin','2021-01-05 02:39:38','',NULL,''),(1047,'在线查询',109,1,'#','','F','0','monitor:online:list','#','admin','2021-01-05 02:39:38','',NULL,''),(1048,'批量强退',109,2,'#','','F','0','monitor:online:batchForceLogout','#','admin','2021-01-05 02:39:38','',NULL,''),(1049,'单条强退',109,3,'#','','F','0','monitor:online:forceLogout','#','admin','2021-01-05 02:39:38','',NULL,''),(1050,'任务查询',110,1,'#','','F','0','monitor:job:list','#','admin','2021-01-05 02:39:38','',NULL,''),(1051,'任务新增',110,2,'#','','F','0','monitor:job:add','#','admin','2021-01-05 02:39:38','',NULL,''),(1052,'任务修改',110,3,'#','','F','0','monitor:job:edit','#','admin','2021-01-05 02:39:38','',NULL,''),(1053,'任务删除',110,4,'#','','F','0','monitor:job:remove','#','admin','2021-01-05 02:39:39','',NULL,''),(1054,'状态修改',110,5,'#','','F','0','monitor:job:changeStatus','#','admin','2021-01-05 02:39:39','',NULL,''),(1055,'任务详细',110,6,'#','','F','0','monitor:job:detail','#','admin','2021-01-05 02:39:39','',NULL,''),(1056,'任务导出',110,7,'#','','F','0','monitor:job:export','#','admin','2021-01-05 02:39:39','',NULL,''),(1057,'生成查询',115,1,'#','','F','0','tool:gen:list','#','admin','2021-01-05 02:39:39','',NULL,''),(1058,'生成修改',115,2,'#','','F','0','tool:gen:edit','#','admin','2021-01-05 02:39:39','',NULL,''),(1059,'生成删除',115,3,'#','','F','0','tool:gen:remove','#','admin','2021-01-05 02:39:39','',NULL,''),(1060,'预览代码',115,4,'#','','F','0','tool:gen:preview','#','admin','2021-01-05 02:39:39','',NULL,''),(1061,'生成代码',115,5,'#','','F','0','tool:gen:code','#','admin','2021-01-05 02:39:39','',NULL,''),(2000,'设备管理',3,1,'/system/equipment','','C','0','system:equipment:view','#','admin','2021-01-05 02:58:00','',NULL,'设备管理菜单'),(2001,'设备管理查询',2000,1,'#','','F','0','system:equipment:list','#','admin','2021-01-05 02:58:00','',NULL,''),(2002,'设备管理新增',2000,2,'#','','F','0','system:equipment:add','#','admin','2021-01-05 02:58:00','',NULL,''),(2003,'设备管理修改',2000,3,'#','','F','0','system:equipment:edit','#','admin','2021-01-05 02:58:01','',NULL,''),(2004,'设备管理删除',2000,4,'#','','F','0','system:equipment:remove','#','admin','2021-01-05 02:58:01','',NULL,''),(2005,'设备管理导出',2000,5,'#','','F','0','system:equipment:export','#','admin','2021-01-05 02:58:01','',NULL,''),(2006,'类型管理',3,1,'/system/type','','C','0','system:type:view','#','admin','2021-01-05 03:00:03','',NULL,'类型管理菜单'),(2007,'类型管理查询',2006,1,'#','','F','0','system:type:list','#','admin','2021-01-05 03:00:03','',NULL,''),(2008,'类型管理新增',2006,2,'#','','F','0','system:type:add','#','admin','2021-01-05 03:00:03','',NULL,''),(2009,'类型管理修改',2006,3,'#','','F','0','system:type:edit','#','admin','2021-01-05 03:00:03','',NULL,''),(2010,'类型管理删除',2006,4,'#','','F','0','system:type:remove','#','admin','2021-01-05 03:00:03','',NULL,''),(2011,'类型管理导出',2006,5,'#','','F','0','system:type:export','#','admin','2021-01-05 03:00:03','',NULL,'');

/*Table structure for table `sys_notice` */

DROP TABLE IF EXISTS `sys_notice`;

CREATE TABLE `sys_notice` (
  `notice_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) NOT NULL COMMENT '公告标题',
  `notice_type` char(1) NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` varchar(2000) DEFAULT NULL COMMENT '公告内容',
  `status` char(1) DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='通知公告表';

/*Data for the table `sys_notice` */

insert  into `sys_notice`(`notice_id`,`notice_title`,`notice_type`,`notice_content`,`status`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values (1,'温馨提醒：2018-07-01 若依新版本发布啦','2','新版本内容','0','admin','2021-01-05 02:40:06','',NULL,'管理员'),(2,'维护通知：2018-07-01 若依系统凌晨维护','1','维护内容','0','admin','2021-01-05 02:40:06','',NULL,'管理员');

/*Table structure for table `sys_oper_log` */

DROP TABLE IF EXISTS `sys_oper_log`;

CREATE TABLE `sys_oper_log` (
  `oper_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) DEFAULT '' COMMENT '请求方式',
  `operator_type` int(1) DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(50) DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) DEFAULT '' COMMENT '返回参数',
  `status` int(1) DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`oper_id`)
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8 COMMENT='操作日志记录';

/*Data for the table `sys_oper_log` */

insert  into `sys_oper_log`(`oper_id`,`title`,`business_type`,`method`,`request_method`,`operator_type`,`oper_name`,`dept_name`,`oper_url`,`oper_ip`,`oper_location`,`oper_param`,`json_result`,`status`,`error_msg`,`oper_time`) values (100,'代码生成',6,'com.equipment.generator.controller.GenController.importTableSave()','POST',1,'admin','研发部门','/tool/gen/importTable','127.0.0.1','内网IP','{\"tables\":[\"equipment,equipment_type\"]}','{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}',0,NULL,'2021-01-05 02:55:32'),(101,'代码生成',8,'com.equipment.generator.controller.GenController.download()','GET',1,'admin','研发部门','/tool/gen/download/equipment','127.0.0.1','内网IP',NULL,'null',0,NULL,'2021-01-05 02:55:37'),(102,'代码生成',8,'com.equipment.generator.controller.GenController.download()','GET',1,'admin','研发部门','/tool/gen/download/equipment_type','127.0.0.1','内网IP',NULL,'null',0,NULL,'2021-01-05 02:55:42'),(103,'类型管理',1,'com.equipment.web.controller.system.EquipmentTypeController.addSave()','POST',1,'admin','研发部门','/system/type/add','127.0.0.1','内网IP','{\"typeName\":[\"测试类型名称\"]}','{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}',0,NULL,'2021-02-14 22:14:15'),(104,'类型管理',2,'com.equipment.web.controller.system.EquipmentTypeController.editSave()','POST',1,'admin','研发部门','/system/type/edit','127.0.0.1','内网IP','{\"id\":[\"1\"],\"typeName\":[\"测试类型名称111\"]}','{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}',0,NULL,'2021-02-14 22:14:33'),(105,'类型管理',3,'com.equipment.web.controller.system.EquipmentTypeController.remove()','POST',1,'admin','研发部门','/system/type/remove','127.0.0.1','内网IP','{\"ids\":[\"1\"]}','{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}',0,NULL,'2021-02-14 22:14:44'),(106,'个人信息',2,'com.equipment.web.controller.system.SysProfileController.update()','POST',1,'admin','研发部门','/system/user/profile/update','127.0.0.1','内网IP','{\"id\":[\"\"],\"userName\":[\"超级管理员\"],\"phonenumber\":[\"15888888888\"],\"email\":[\"ry@163.com\"],\"sex\":[\"1\"]}','{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}',0,NULL,'2021-02-14 22:15:36'),(107,'类型管理',1,'com.equipment.web.controller.system.EquipmentTypeController.addSave()','POST',1,'admin','研发部门','/system/type/add','127.0.0.1','内网IP','{\"typeName\":[\"13123123\"]}','{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}',0,NULL,'2021-02-14 22:16:01'),(108,'类型管理',3,'com.equipment.web.controller.system.EquipmentTypeController.remove()','POST',1,'admin','研发部门','/system/type/remove','127.0.0.1','内网IP','{\"ids\":[\"2\"]}','{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}',0,NULL,'2021-02-15 21:51:26'),(109,'类型管理',1,'com.equipment.web.controller.system.EquipmentTypeController.addSave()','POST',1,'admin','研发部门','/system/type/add','127.0.0.1','内网IP','{\"typeName\":[\"类型1号\"]}','{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}',0,NULL,'2021-02-15 21:51:46'),(110,'类型管理',1,'com.equipment.web.controller.system.EquipmentTypeController.addSave()','POST',1,'admin','研发部门','/system/type/add','127.0.0.1','内网IP','{\"typeName\":[\"类型2号\"]}','{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}',0,NULL,'2021-02-15 21:51:54'),(111,'设备管理',1,'com.equipment.web.controller.system.EquipmentController.addSave()','POST',1,'admin','研发部门','/system/equipment/add','127.0.0.1','内网IP','{\"typeName\":[\"3\"],\"modelNum\":[\"神州7号\"],\"quantity\":[\"10\"]}','{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}',0,NULL,'2021-02-15 21:55:38'),(112,'设备管理',3,'com.equipment.web.controller.system.EquipmentController.remove()','POST',1,'admin','研发部门','/system/equipment/remove','127.0.0.1','内网IP','{\"ids\":[\"1\"]}','{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}',0,NULL,'2021-02-15 21:58:05'),(113,'设备管理',1,'com.equipment.web.controller.system.EquipmentController.addSave()','POST',1,'admin','研发部门','/system/equipment/add','127.0.0.1','内网IP','{\"typeId\":[\"4\"],\"typeName\":[\"4\"],\"modelNum\":[\"34234\"],\"quantity\":[\"1231212\"]}','{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}',0,NULL,'2021-02-15 22:14:26'),(114,'菜单管理',2,'com.equipment.web.controller.system.SysMenuController.editSave()','POST',1,'admin','研发部门','/system/menu/edit','127.0.0.1','内网IP','{\"menuId\":[\"103\"],\"parentId\":[\"1\"],\"menuType\":[\"C\"],\"menuName\":[\"部门管理\"],\"url\":[\"/system/dept\"],\"target\":[\"menuItem\"],\"perms\":[\"system:dept:view\"],\"orderNum\":[\"4\"],\"icon\":[\"fa fa-outdent\"],\"visible\":[\"1\"]}','{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}',0,NULL,'2021-02-17 21:19:38'),(115,'菜单管理',2,'com.equipment.web.controller.system.SysMenuController.editSave()','POST',1,'admin','研发部门','/system/menu/edit','127.0.0.1','内网IP','{\"menuId\":[\"104\"],\"parentId\":[\"1\"],\"menuType\":[\"C\"],\"menuName\":[\"岗位管理\"],\"url\":[\"/system/post\"],\"target\":[\"menuItem\"],\"perms\":[\"system:post:view\"],\"orderNum\":[\"5\"],\"icon\":[\"fa fa-address-card-o\"],\"visible\":[\"1\"]}','{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}',0,NULL,'2021-02-17 21:20:27'),(116,'用户管理',1,'com.equipment.web.controller.system.SysUserController.addSave()','POST',1,'admin','研发部门','/system/user/add','127.0.0.1','内网IP','{\"deptId\":[\"\"],\"userName\":[\"stu\"],\"deptName\":[\"\"],\"phonenumber\":[\"\"],\"email\":[\"\"],\"loginName\":[\"stu\"],\"sex\":[\"0\"],\"role\":[\"2\"],\"remark\":[\"\"],\"status\":[\"0\"],\"roleIds\":[\"2\"],\"postIds\":[\"\"]}','{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}',0,NULL,'2021-02-21 15:45:44'),(117,'个人信息',2,'com.equipment.web.controller.system.SysProfileController.update()','POST',1,'admin','研发部门','/system/user/profile/update','127.0.0.1','内网IP','{\"id\":[\"\"],\"userName\":[\"超级管理员\"],\"phonenumber\":[\"15888888888\"],\"email\":[\"466644431@qq.com\"],\"sex\":[\"1\"]}','{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}',0,NULL,'2021-02-21 22:52:10');

/*Table structure for table `sys_post` */

DROP TABLE IF EXISTS `sys_post`;

CREATE TABLE `sys_post` (
  `post_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) NOT NULL COMMENT '岗位名称',
  `post_sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1) NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='岗位信息表';

/*Data for the table `sys_post` */

insert  into `sys_post`(`post_id`,`post_code`,`post_name`,`post_sort`,`status`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values (1,'ceo','董事长',1,'0','admin','2021-01-05 02:39:32','',NULL,''),(2,'se','项目经理',2,'0','admin','2021-01-05 02:39:32','',NULL,''),(3,'hr','人力资源',3,'0','admin','2021-01-05 02:39:32','',NULL,''),(4,'user','普通员工',4,'0','admin','2021-01-05 02:39:32','',NULL,'');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `status` char(1) NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COMMENT='角色信息表';

/*Data for the table `sys_role` */

insert  into `sys_role`(`role_id`,`role_name`,`role_key`,`role_sort`,`data_scope`,`status`,`del_flag`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values (1,'超级管理员','admin',1,'1','0','0','admin','2021-01-05 02:39:33','',NULL,'超级管理员'),(2,'普通角色','common',2,'2','0','0','admin','2021-01-05 02:39:33','',NULL,'普通角色');

/*Table structure for table `sys_role_dept` */

DROP TABLE IF EXISTS `sys_role_dept`;

CREATE TABLE `sys_role_dept` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`,`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色和部门关联表';

/*Data for the table `sys_role_dept` */

insert  into `sys_role_dept`(`role_id`,`dept_id`) values (2,100),(2,101),(2,105);

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色和菜单关联表';

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`role_id`,`menu_id`) values (2,1),(2,2),(2,3),(2,4),(2,100),(2,101),(2,102),(2,103),(2,104),(2,105),(2,106),(2,107),(2,108),(2,109),(2,110),(2,111),(2,112),(2,113),(2,114),(2,115),(2,116),(2,500),(2,501),(2,1000),(2,1001),(2,1002),(2,1003),(2,1004),(2,1005),(2,1006),(2,1007),(2,1008),(2,1009),(2,1010),(2,1011),(2,1012),(2,1013),(2,1014),(2,1015),(2,1016),(2,1017),(2,1018),(2,1019),(2,1020),(2,1021),(2,1022),(2,1023),(2,1024),(2,1025),(2,1026),(2,1027),(2,1028),(2,1029),(2,1030),(2,1031),(2,1032),(2,1033),(2,1034),(2,1035),(2,1036),(2,1037),(2,1038),(2,1039),(2,1040),(2,1041),(2,1042),(2,1043),(2,1044),(2,1045),(2,1046),(2,1047),(2,1048),(2,1049),(2,1050),(2,1051),(2,1052),(2,1053),(2,1054),(2,1055),(2,1056),(2,1057),(2,1058),(2,1059),(2,1060),(2,1061);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `login_name` varchar(30) NOT NULL COMMENT '登录账号',
  `user_name` varchar(30) DEFAULT '' COMMENT '用户昵称',
  `user_type` varchar(2) DEFAULT '00' COMMENT '用户类型（00系统用户 01注册用户）',
  `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) DEFAULT '' COMMENT '手机号码',
  `sex` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) DEFAULT '' COMMENT '头像路径',
  `password` varchar(50) DEFAULT '' COMMENT '密码',
  `salt` varchar(20) DEFAULT '' COMMENT '盐加密',
  `status` char(1) DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(50) DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
  `pwd_update_date` datetime DEFAULT NULL COMMENT '密码最后更新时间',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

/*Data for the table `sys_user` */

insert  into `sys_user`(`user_id`,`dept_id`,`login_name`,`user_name`,`user_type`,`email`,`phonenumber`,`sex`,`avatar`,`password`,`salt`,`status`,`del_flag`,`login_ip`,`login_date`,`pwd_update_date`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values (1,103,'admin','超级管理员','00','466644431@qq.com','15888888888','1','','29c67a30398638269fe600f73a054934','111111','0','0','127.0.0.1','2021-02-22 00:32:58','2021-01-05 02:39:31','admin','2021-01-05 02:39:31','','2021-02-22 00:32:57','管理员'),(2,105,'ry','管理员','00','ry@qq.com','15666666666','1','','8e6d98b90472783cc73c17047ddccf36','222222','0','0','127.0.0.1','2021-02-17 23:47:39','2021-01-05 02:39:31','admin','2021-01-05 02:39:31','','2021-02-17 23:47:39','测试员'),(100,NULL,'stu','stu','00','','','0','','778614f5f8ae7e0ce6d1a3d547220d3f','7ac4fa','0','0','127.0.0.1','2021-02-21 16:26:44',NULL,'admin','2021-02-21 15:45:44','','2021-02-21 16:26:43',NULL);

/*Table structure for table `sys_user_online` */

DROP TABLE IF EXISTS `sys_user_online`;

CREATE TABLE `sys_user_online` (
  `sessionId` varchar(50) NOT NULL DEFAULT '' COMMENT '用户会话id',
  `login_name` varchar(50) DEFAULT '' COMMENT '登录账号',
  `dept_name` varchar(50) DEFAULT '' COMMENT '部门名称',
  `ipaddr` varchar(50) DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT '' COMMENT '操作系统',
  `status` varchar(10) DEFAULT '' COMMENT '在线状态on_line在线off_line离线',
  `start_timestamp` datetime DEFAULT NULL COMMENT 'session创建时间',
  `last_access_time` datetime DEFAULT NULL COMMENT 'session最后访问时间',
  `expire_time` int(5) DEFAULT '0' COMMENT '超时时间，单位为分钟',
  PRIMARY KEY (`sessionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='在线用户记录';

/*Data for the table `sys_user_online` */

insert  into `sys_user_online`(`sessionId`,`login_name`,`dept_name`,`ipaddr`,`login_location`,`browser`,`os`,`status`,`start_timestamp`,`last_access_time`,`expire_time`) values ('9f2b5778-3c3d-4ca3-84c3-4d264a32e784','admin','研发部门','127.0.0.1','内网IP','Chrome 8','Windows 10','on_line','2021-02-22 00:29:58','2021-02-22 00:32:58',1800000);

/*Table structure for table `sys_user_post` */

DROP TABLE IF EXISTS `sys_user_post`;

CREATE TABLE `sys_user_post` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`,`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与岗位关联表';

/*Data for the table `sys_user_post` */

insert  into `sys_user_post`(`user_id`,`post_id`) values (1,1),(2,2);

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户和角色关联表';

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`user_id`,`role_id`) values (1,1),(2,2),(100,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
