-- 创建建设情况表 5.construction_situation

drop TABLE construction_situation;

SELECT * FROM construction_situation;
CREATE TABLE construction_situation(
	`id` BIGINT(24) NOT NULL AUTO_INCREMENT COMMENT '建设情况id主键',
	`total_invest` float(16,8) COMMENT '工程总投资',
	`State_Invest` float(16,8) COMMENT '国家投资总额',
	`Start_Supply_Water_Time` VARCHAR(24)  COMMENT '开始供水时间',
	`Is_Finish_Project` VARCHAR(8) COMMENT '是否是竣工工程',
	PRIMARY KEY (`id`) USING BTREE 
)ENGINE = INNODB AUTO_INCREMENT = 1 CHARACTER SET = utf8;

-- 创建水源表 6.water_source
CREATE TABLE water_source(
	`id` BIGINT(24) NOT NULL AUTO_INCREMENT COMMENT '水源Id主键',
	`Water_Source_Type` VARCHAR(24) COMMENT  '水源类型',
	`Well_Nums` INT(8) COMMENT '水井个数',
	`Water_Quality` VARCHAR(8) COMMENT '水质质量（优良差）',
	`Is_Protect_Area`  VARCHAR(8) COMMENT '是否建立水质保护区',
	 Is_water_intake_legal VARCHAR(8) COMMENT '取水许可证',
	 Is_legal_In_Hygiene VARCHAR(8) COMMENT '卫生许可证',
	 PRIMARY KEY (`id`) USING BTREE	 
)ENGINE = INNODB AUTO_INCREMENT = 1 CHARACTER SET = utf8;

drop TABLE water_source;

-- 创建净化消毒表 7.purified_and_disinfect
CREATE TABLE purified_and_disinfect(
	`id` BIGINT(24) NOT NULL AUTO_INCREMENT COMMENT '净化消毒Id主键',
	`Is_Purified` VARCHAR(24) COMMENT  '有无净化',
	`Is_Disinfect` VARCHAR(24) COMMENT  '有无消毒',
	`Purified_Situation` VARCHAR(24) COMMENT  '净化运行情况',
	`Disinfect_Situation` VARCHAR(24) COMMENT  '消毒运行情况',
	 PRIMARY KEY (`id`) USING BTREE	
)ENGINE = INNODB AUTO_INCREMENT = 1 CHARACTER SET = utf8;

select * from purified_and_disinfect;

--  供水情况 8.supply_situation
CREATE TABLE supply_situation(
	`id` BIGINT(24) NOT NULL AUTO_INCREMENT COMMENT '供水情况Id主键',
	`Total_Nums_Of_People`  VARCHAR(8) COMMENT '总供水人数',
	`Water_To_Home_Nums`  VARCHAR(8) COMMENT '供水到户人数',
	`Water_To_School_Nums`  VARCHAR(8) COMMENT '供水到师生人数',
	`Water_To_Poor_Nums`  VARCHAR(8) COMMENT '供水到贫困人数',
	`Water_To_Poor_Family_Nums`  VARCHAR(8) COMMENT '供水到贫困户人数',
	`Solved_Exceed_Fu_nums`  VARCHAR(8) COMMENT '解决的饮水型氟超标人数',
	 PRIMARY KEY (`id`) USING BTREE
)ENGINE = INNODB AUTO_INCREMENT = 1 CHARACTER SET = utf8;

select * from supply_situation;


drop TABLE water_price;
-- 水费水价 9.water_price
CREATE TABLE water_price(
	`id` BIGINT(24) NOT NULL AUTO_INCREMENT COMMENT '水费水价Id主键',
	`Full_Cost_Water_Price`  FLOAT(8,3) COMMENT '全成本水价',
	`Operate_Cost_Water_price`  FLOAT(8,3) COMMENT '运行成本价',
	`Household_Water_Price`  FLOAT(8,3) COMMENT '居民生活执行水价',
	`Charge_Method`  VARCHAR(24) COMMENT '计费方式',
	`Income_Expenditure_Situation`  VARCHAR(24) COMMENT '收支情况',
	`Water_Charge_Recovery_Rate` INT(8) COMMENT '水费回收率',
	 PRIMARY KEY (`id`) USING BTREE
)ENGINE = INNODB AUTO_INCREMENT = 1 CHARACTER SET = utf8;

-- 水质化实验室 11.water_quality_lab
CREATE TABLE water_quality_lab(
	`id` BIGINT(24) NOT NULL AUTO_INCREMENT COMMENT '工程位置Id主键',
	`Is_Build_Lab`  VARCHAR(24) COMMENT '建立与否',
	`Is_Work`  VARCHAR(24) COMMENT '运行状况',
	`Water_Index_Out_Factory`  VARCHAR(240) COMMENT '出厂水日检指标',
	`Water_Quality_Result`  VARCHAR(64) COMMENT '水质结果',
	PRIMARY KEY (`id`) USING BTREE
)ENGINE = INNODB AUTO_INCREMENT = 1 CHARACTER SET = utf8;
drop table water_quality_lab;

-- 自动化检测 12. auto_test
CREATE TABLE auto_test(
	`id` BIGINT(24) NOT NULL AUTO_INCREMENT COMMENT '自动化检测Id主键',
	`Have_Computer_monitor`  VARCHAR(24) COMMENT '有计算机监控',
	`Have_video_Monitor`  VARCHAR(24) COMMENT '有视频监控',
	`Computer_Is_Work`  VARCHAR(24) COMMENT '计算机监控运行与否',
	`Video_Is_Work`  VARCHAR(24) COMMENT '视频监控运行与否',
	PRIMARY KEY (`id`) USING BTREE
)ENGINE = INNODB AUTO_INCREMENT = 1 CHARACTER SET = utf8;

-- 1.负责人表 supervisor
CREATE TABLE supervisor(
	`id` BIGINT(24) NOT NULL AUTO_INCREMENT COMMENT '负责人id主键',
	`management_department_id` BIGINT(24) COMMENT '管理部门Id外键',
	`Phone_Number` VARCHAR(48) COMMENT  '管理单位服务热线',
	`Name` VARCHAR(48) COMMENT  '负责人名字',
	`Address` VARCHAR(64) COMMENT  '负责人住址',
	`Position` VARCHAR(24) COMMENT  '负责人职位',
	PRIMARY KEY (`id`) USING BTREE,
	FOREIGN KEY(management_department_id) REFERENCES management_department(id)
)ENGINE = INNODB AUTO_INCREMENT = 1 CHARACTER SET = utf8;

drop table supervisor;


-- 3.供水工程 supply_water_project 
CREATE TABLE supply_water_project(
	`id` BIGINT(24) NOT NULL AUTO_INCREMENT COMMENT '供水工程d主键',
	
	`management_department_id` BIGINT(24) COMMENT '管理部门Id外键',
	`construction_situation_id` BIGINT(24) COMMENT '建设情况Id外键',
	`water_source_id` BIGINT(24) COMMENT '水源Id外键',
	`purified_and_disinfect_id` BIGINT(24) COMMENT '净化消毒Id外键',
	`supply_situation_id` BIGINT(24) COMMENT '供水情况Id外键',
	`location_id` BIGINT(24) COMMENT '工程位置Id外键',
	`water_quality_lab_id` BIGINT(24) COMMENT '水质化实验室Id外键',
	`water_price_id` BIGINT(24) COMMENT '水价Id外键',
	`auto_test_id` BIGINT(24) COMMENT '自动化测试Id外键',
	
	`Name` VARCHAR(400) COMMENT  '供水工程名称',
	`Location` VARCHAR(800) COMMENT  '供水工程位置',
	`Project_Situation` VARCHAR(24) COMMENT  '供水工程状态性质',
	`Supply_Water_Rate` INT(8) COMMENT '供水保证率',
	`Write_Time` VARCHAR(800) COMMENT  '填报年份',
	`Design_Supply_Scale` INT(8) COMMENT '设计供水规模',
	`Actual_Supply_Water_Annual` INT(8) COMMENT '年实际供水',
	`Surface_Water_Name` VARCHAR(64) COMMENT  '地表水源名',
	`Pipe_Lack_Rate` INT(8) COMMENT '管网漏损率',
	`Project_Manage_Nums` INT(8) COMMENT '供水工程管理人数',
	
	FOREIGN KEY(management_department_id) REFERENCES management_department(id),
	FOREIGN KEY(construction_situation_id) REFERENCES construction_situation(id),
	FOREIGN KEY(water_source_id) REFERENCES water_source(id),
	FOREIGN KEY(purified_and_disinfect_id) REFERENCES purified_and_disinfect(id),
	FOREIGN KEY(supplaswy_situation_id) REFERENCES supply_situation(id),
	FOREIGN KEY(location_id) REFERENCES location(id),
	FOREIGN KEY(water_quality_lab_id) REFERENCES water_quality_lab(id),
	FOREIGN KEY(water_price_id) REFERENCES water_price(id),
	FOREIGN KEY(auto_test_id) REFERENCES auto_test(id),
	
	PRIMARY KEY (`id`) USING BTREE
)ENGINE = INNODB AUTO_INCREMENT = 1 CHARACTER SET = utf8;

select count(*) from supply_water_project;
select count(*) from supply_situation;
select count(*) from water_price;

-- 2.管理表 manage  id 25号以后的数都是乱写的
CREATE TABLE  manage(
	`id` BIGINT(24) NOT NULL AUTO_INCREMENT COMMENT '管理Id主键',
	`supervisor_id` BIGINT(24) COMMENT '管理者Id外键',
	`supply_water_project_id` BIGINT(24) COMMENT '供水工程Id外键',
	
	FOREIGN KEY(supervisor_id) REFERENCES supervisor(id),
	FOREIGN KEY(supply_water_project_id) REFERENCES supply_water_project(id),
	PRIMARY KEY (`id`) USING BTREE
)ENGINE = INNODB AUTO_INCREMENT = 1 CHARACTER SET = utf8;

-- 将一字段拷贝到另一字段   UPDATE 表名 SET 新字段 = 原字段
update supply_water_project set construction_situation_id =id;
update supply_water_project set supply_situation_id =id;
update supply_water_project set water_price_id =id;


-- 一个个关联太慢了，瞎编
update supply_water_project set auto_test_id =location_id;
update supply_water_project set water_quality_lab_id =location_id;
update supply_water_project set purified_and_disinfect_id =location_id;
update supply_water_project set water_source_id =location_id;



-- 查询每年写了多少工程
select  write_time as '录入时间',count(*)  as '年'
FROM supply_water_project
GROUP BY write_time
HAVING count(*)>10;

-- 按照工程所在县级区域计算个数
select location.county as '县' ,count(*) as '工程个数'
from location,supply_water_project
where location.id=supply_water_project.location_id
GROUP BY location.county
HAVING count(*)>4;








