package com.dante.ssm.service.impl;

import com.dante.ssm.bean.Employee;
import com.dante.ssm.bean.EmployeeExample;
import com.dante.ssm.dao.IEmployeeDao;
import com.dante.ssm.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    private final IEmployeeDao employeeDao;

    @Autowired
    public EmployeeServiceImpl(IEmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    //查询所有员工信息
    @Override
    public List<Employee> getAllEmployee(EmployeeExample example) {
        return this.employeeDao.selectByExample(example);
    }

    //插入员工信息
    @Override
    public int insertSelective(Employee record) {
        return this.employeeDao.insertSelective(record);
    }

    //查询所有员工带部门信息
    @Override
    public List<Employee> getAllEmployeeWithDepartment() {
        return this.employeeDao.selectWithDeptByExample(null);
    }

    //根据id查询员工带部门信息
    @Override
    public Employee getAllEmployeeWithDepartmentById(Integer empId) {
        return this.employeeDao.selectWithDeptByPrimaryKey(empId);
    }
}
