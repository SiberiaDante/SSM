package com.dante.ssm.service.impl;

import com.dante.ssm.bean.Employee;
import com.dante.ssm.bean.EmployeeExample;
import com.dante.ssm.dao.IEmployeeDao;
import com.dante.ssm.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public Employee getEmployeeById(Integer empId) {
        return this.employeeDao.selectWithDeptByPrimaryKey(empId);
    }

    /**
     * @param name 员工姓名
     * @return true：可用
     */
    @Override
    public boolean isEmpNameCanUse(String name) {
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andEmpNameEqualTo(name);
        long count = this.employeeDao.countByExample(employeeExample);
        return count == 0;
    }

    //保存员工
    @Override
    public void saveEmployee(Employee employee) {
        this.employeeDao.insertSelective(employee);
    }

    //根据ID删除员工
    @Override
    public void deleteEmpById(String empId) {
        this.employeeDao.deleteByPrimaryKey(Integer.valueOf(empId));
    }

    //批量删除
    @Override
    public void deleteBatch(String empIds) {
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        String[] split = empIds.split("-");
        List<Integer> ids = new ArrayList<>();
        for (String id : split) {
            ids.add(Integer.valueOf(id));

        }
        criteria.andEmpIdIn(ids);
        this.employeeDao.deleteByExample(employeeExample);
    }

    //根据id更新员工
    @Override
    public void updateEmployee(Employee emp) {
        this.employeeDao.updateByPrimaryKeySelective(emp);
    }
}
