package com.dante.ssm.service;

import com.dante.ssm.bean.Employee;
import com.dante.ssm.bean.EmployeeExample;

import java.util.List;

public interface IEmployeeService {

    List<Employee> getAllEmployee(EmployeeExample example);

    int insertSelective(Employee record);

    //查询所有员工带部门
    List<Employee> getAllEmployeeWithDepartment();

    //查询指定员工带部门
    Employee getAllEmployeeWithDepartmentById(Integer empId);
}
