package com.dante.ssm.service;

import com.dante.ssm.base.BaseJunitTest;
import com.dante.ssm.bean.Department;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DepartmentServiceTest extends BaseJunitTest {

    @Autowired
    private IDepartmentService departmentService;

    @Test
    public void test() {
        //插入部门信息
        departmentService.insertSelective(new Department(null, "开发部"));
        departmentService.insertSelective(new Department(null, "测试部"));
        //

        List<Department> departmentList = departmentService.selectByExample(null);
        for (Department department : departmentList) {
            logger.info("---" + department.getDeptId() + "---" + department.getDeptName());
        }

    }
}
