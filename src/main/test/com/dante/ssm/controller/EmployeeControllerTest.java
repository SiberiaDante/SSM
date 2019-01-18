package com.dante.ssm.controller;


import com.dante.ssm.base.BaseJunitMVCTest;
import com.dante.ssm.bean.Employee;
import com.dante.ssm.constants.PageConstant;
import com.github.pagehelper.PageInfo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

public class EmployeeControllerTest extends BaseJunitMVCTest {

    @Autowired
    WebApplicationContext context;

    // 虚拟mvc请求，获取到处理结果。
    private MockMvc mockMvc;

    @Before
    public void initMokcMvc() {
        logger.info("-------------------initMokcMvc--------------");
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void getEmployeeByPage() throws Exception {
        logger.info("-------------------initMokcMvc--------------");
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        //模拟请求拿到返回值
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn", PageConstant.START_DEFAULT_PAGE)).andReturn();
        MockHttpServletRequest resultRequest = mvcResult.getRequest();
        PageInfo pageInfo = (PageInfo) resultRequest.getAttribute("pageInfo");

        logger.info("当前页码：" + pageInfo.getPageNum() + "---总页码：" + pageInfo.getPages()
                + "---总记录数：" + pageInfo.getTotal() + "---在页面需要连续显示的页码");

        int[] nums = pageInfo.getNavigatepageNums();
        for (int i : nums) {
            logger.info("：" + i);
        }

        //获取员工数据
        List<Employee> list = pageInfo.getList();
        for (Employee employee : list) {
            logger.info("ID："+employee.getEmpId()+"---Name:"+employee.getEmpName());
        }
    }

}