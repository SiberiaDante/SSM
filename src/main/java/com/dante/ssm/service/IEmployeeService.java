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
    Employee getEmployeeById(Integer empId);

    //查询name是否存在
    boolean isEmpNameCanUse(String name);

    //保存员工
    void saveEmployee(Employee employee);

    //删除员工
    void deleteEmpById(String empId);

    //批量删除
    void deleteBatch(String empIds);

    //更新员工信息
    void updateEmployee(Employee employee);


}
