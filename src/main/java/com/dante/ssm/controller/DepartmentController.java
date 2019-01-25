package com.dante.ssm.controller;

import com.dante.ssm.bean.Department;
import com.dante.ssm.bean.base.Msg;
import com.dante.ssm.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/dept")
public class DepartmentController {

    private final IDepartmentService departmentService;

    @Autowired
    public DepartmentController(IDepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @RequestMapping("/depts")
    @ResponseBody
    public Msg getDepartment() {
        List<Department> allDepartments = departmentService.getAllDepartments();
        return Msg.success().add("depts", allDepartments);
    }
}
