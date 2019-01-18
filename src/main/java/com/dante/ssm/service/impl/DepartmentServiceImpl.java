package com.dante.ssm.service.impl;

import com.dante.ssm.bean.Department;
import com.dante.ssm.bean.DepartmentExample;
import com.dante.ssm.dao.IDepartmentDao;
import com.dante.ssm.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements IDepartmentService {

    @Autowired
    private IDepartmentDao departmentDao;

    @Override
    public int insert(Department record) {
        return 0;
    }

    @Override
    public int insertSelective(Department record) {
        return this.departmentDao.insertSelective(record);
    }

    @Override
    public List<Department> selectByExample(DepartmentExample example) {
        return this.departmentDao.selectByExample(example);
    }
}
