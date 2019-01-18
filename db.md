# 数据库设计脚本


```
# 创建数据库  ssm_crud
CREATE DATABASE ssm_crud;

# 创建数据表 tbl_emp
CREATE TABLE tbl_emp
(
  emp_id   int auto_increment
    primary key,
  emp_bane varchar(255) not null,
  gender   varchar(1)   null,
  email    varchar(255) null,
  d_id     int          not null
);

# 创建数据表 tbl_dept
CREATE TABLE tbl_dept
(
  dept_id   int auto_increment
    primary key,
  dept_name varchar(255) not null
);

# 关联主外键
ALTER TABLE tbl_emp ADD FOREIGN KEY (d_id) REFERENCES tbl_dept(dept_id);
```










