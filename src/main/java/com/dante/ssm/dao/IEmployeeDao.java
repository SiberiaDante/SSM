package com.dante.ssm.dao;

import com.dante.ssm.bean.Employee;
import com.dante.ssm.bean.EmployeeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface IEmployeeDao {
    long countByExample(EmployeeExample example);

    int deleteByExample(EmployeeExample example);

    int deleteByPrimaryKey(Integer empId);

    int insert(Employee record);

    int insertSelective(Employee record);

    List<Employee> selectByExample(EmployeeExample example);

    Employee selectByPrimaryKey(Integer empId);

    //查询所有员工带部门
    List<Employee> selectWithDeptByExample(EmployeeExample example);

    //查询指定员工带部门
    Employee selectWithDeptByPrimaryKey(Integer empId);

    void saveEmp(Employee employee);

    int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);
}