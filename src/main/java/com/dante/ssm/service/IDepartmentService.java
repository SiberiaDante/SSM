package com.dante.ssm.service;

import com.dante.ssm.bean.Department;
import com.dante.ssm.bean.DepartmentExample;

import java.util.List;

public interface IDepartmentService {

    //插入部门信息
    int insert(Department record);
    int insertSelective(Department record);

    //查询所有部门信息
    List<Department> selectByExample(DepartmentExample example);
}
