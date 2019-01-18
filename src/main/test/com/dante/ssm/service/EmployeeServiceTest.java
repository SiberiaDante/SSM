package com.dante.ssm.service;

import com.dante.ssm.base.BaseJunitTest;
import com.dante.ssm.bean.Employee;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class EmployeeServiceTest extends BaseJunitTest {

    @Autowired
    private IEmployeeService employeeService;

    //插入员工信息
    @Test
    public void insertEmployee() {
        for (int i = 0; i < 1000; i++) {
            String text = UUID.randomUUID().toString().substring(0, 5) + i;
            String gender = "男";
            if (i % 2 == 0) {
                gender = "女";
            }
            logger.info("------------text:" + text + "-------gender:" + gender);
            employeeService.insertSelective(new Employee(null, text, gender, text + "@qq.com", (i % 2) + 1));
        }
    }

    //查询所有员工信息
    @Test
    public void getAllEmployee() {
        List<Employee> allEmployee = employeeService.getAllEmployee(null);
        for (Employee employee : allEmployee) {
            logger.info("---" + employee.getEmpId() + "---" + employee.getdId() + "---" + employee.getEmpName() + "---" + employee.getEmail() + "---" + employee.getGender());
        }
    }

    //查询所有员工带部门信息
    @Test
    public void getAllEmployeeWithDept() {
        List<Employee> allEmployee = employeeService.getAllEmployeeWithDepartment();
        for (Employee employee : allEmployee) {
            logger.info("---" + employee.getEmpId() + "---" + employee.getdId() + "---" + employee.getEmpName() + "---" + employee.getEmail() + "---" + employee.getGender() +
                    "---" + employee.getDepartment().getDeptName());
        }
    }

    //根据id查询员工带部门信息
    @Test
    public void getEmployeeWithDeptById() {
        Employee employee = employeeService.getAllEmployeeWithDepartmentById(20);
        logger.info("---" + employee.getEmpId() + "---" + employee.getdId() + "---" + employee.getEmpName() + "---" + employee.getEmail() + "---" + employee.getGender() +
                "---" + employee.getDepartment().getDeptName());
    }
}
