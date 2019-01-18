package com.dante.ssm.controller;


import com.dante.ssm.bean.Employee;
import com.dante.ssm.constants.PageConstant;
import com.dante.ssm.service.IEmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EmployeeController {

    private final IEmployeeService employeeService;

    @Autowired
    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * 分页查询员工信息
     * @param pn
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, name = "/emps")
    public String getEmployeeByPage(@RequestParam(value = "pn", defaultValue = PageConstant.START_DEFAULT_PAGE) Integer pn, Model model) {
        PageHelper.startPage(pn, PageConstant.PAGE_SIZE);
        List<Employee> employeeList = employeeService.getAllEmployeeWithDepartment();
        PageInfo<Employee> employeePageInfo = new PageInfo<>(employeeList);
        model.addAttribute("pageInfo", employeePageInfo);
        return "list";
    }
}
