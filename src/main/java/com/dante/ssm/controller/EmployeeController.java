package com.dante.ssm.controller;


import com.dante.ssm.bean.Employee;
import com.dante.ssm.bean.EmployeeExample;
import com.dante.ssm.bean.base.BaseResult;
import com.dante.ssm.bean.base.Msg;
import com.dante.ssm.constants.PageConstant;
import com.dante.ssm.service.IEmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class EmployeeController {

    public static Logger logger = Logger.getLogger(EmployeeController.class);

    private final IEmployeeService employeeService;

    @Autowired
    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * 分页查询员工信息
     *
     * @param pn 页码
     */
//    @RequestMapping(method = RequestMethod.GET, name = "/emps")
    public String getEmployeeByPage(@RequestParam(value = "pn", defaultValue = PageConstant.START_DEFAULT_PAGE) Integer pn, Model model) {
        PageHelper.startPage(pn, PageConstant.PAGE_SIZE);
        List<Employee> employeeList = employeeService.getAllEmployeeWithDepartment();
        PageInfo<Employee> employeePageInfo = new PageInfo<>(employeeList);
        model.addAttribute("pageInfo", employeePageInfo);
        return "list";
    }

    /**
     * 分页查询员工信息,
     *
     * @param pn 页码
     * @return Msg json数据
     */
    @RequestMapping(method = RequestMethod.GET, value = "/emps")
    @ResponseBody
    public Msg getEmployeeByPage(@RequestParam(value = "pn", defaultValue = PageConstant.START_DEFAULT_PAGE) Integer pn) {
        PageHelper.startPage(pn, PageConstant.PAGE_SIZE);
        List<Employee> employeeList = employeeService.getAllEmployeeWithDepartment();
        PageInfo<Employee> employeePageInfo = new PageInfo<>(employeeList);
        return Msg.success().add("pageInfo", employeePageInfo);
    }

    /**
     * 分页查询员工信息,不返回分页信息
     *
     * @param pn
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getEmps")
    @ResponseBody
    public BaseResult getEmployeeByPageNoPageInfo(@RequestParam(value = "pn", defaultValue = PageConstant.START_DEFAULT_PAGE) Integer pn) {
        PageHelper.startPage(pn, PageConstant.PAGE_SIZE);
        List<Employee> employeeList = employeeService.getAllEmployeeWithDepartment();
        PageInfo<Employee> employeePageInfo = new PageInfo<>(employeeList);
        List<Employee> list = employeePageInfo.getList();
        BaseResult msg = new BaseResult();
        msg.setMsg("success");
        msg.setCode(200);
        msg.setData(list);
//        HashMap<String,Object> hashMap=new HashMap<>();
//        hashMap.put("code",200);
//        hashMap.put("info","success");
//        hashMap.put("data",list);
//        return JSON.toJSONString(hashMap);
        return msg;
    }


    /**
     * 检查员工姓名是否可用
     *
     * @param empName 姓名
     * @return 200可用
     */
    @RequestMapping(method = RequestMethod.POST, value = "/checkEmpName")
    @ResponseBody
    public Msg checkEmpNameCanUse(@RequestParam("empName") String empName) {
        String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
        if (!empName.matches(regx)) {
            return Msg.fail("用户名必须是2-5位中文或者6-16位英文和数字的组合");
        }
        if (employeeService.isEmpNameCanUse(empName)) {
            return Msg.success();
        }
        return Msg.fail("用户名已存在");
    }

    /**
     * 保存员工
     *
     * @param employee 员工信息
     * @param result   表单信息
     * @return
     */

    @RequestMapping(method = RequestMethod.POST, value = "/saveEmp")
    @ResponseBody
    public Msg saveEmployee(@Valid Employee employee, BindingResult result) {
        logger.info("---------saveEmployee------------" + result.hasErrors());
        if (result.hasErrors()) {
            Map<String, Object> map = new HashMap<>();
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError fieldError : errors) {
                logger.info("---------错误的字段名：------------" + fieldError.getField());
                logger.info("---------错误信息：：------------" + fieldError.getDefaultMessage());
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return Msg.fail().add("errorFields", map);
        }
        employeeService.saveEmployee(employee);
        return Msg.success("保存成功");
    }


    /**
     * 删除员工
     *
     * @param empIds
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/deleteEmp")
    @ResponseBody
    public Msg deleteEmp(@RequestParam("empIds") String empIds) {
        //1.根据empId查询是否有数据

        if (empIds.contains("-")) {//批量删除
            employeeService.deleteBatch(empIds);
        } else {//单个删除
            employeeService.deleteEmpById(empIds);
        }
        return Msg.success("删除成功");
    }


    /**
     * 根据id查询员工
     *
     * @param empId
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/getEmp")
    @ResponseBody
    public Msg getEmployee(@RequestParam("empId") String empId) {
        Employee employee = employeeService.getEmployeeById(Integer.valueOf(empId));
        return Msg.success().add("data", employee);
    }

    /**
     * 更新员工信息
     *
     * @param employee
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT, value = "/updateEmp/{empId}")
    public Msg updateEmployee(Employee employee, HttpServletRequest request) {
        logger.info("-----" + request.getParameter("gender"));
        logger.info("----" + employee);
        employeeService.updateEmployee(employee);
        return Msg.success("更新成功");
    }
}
