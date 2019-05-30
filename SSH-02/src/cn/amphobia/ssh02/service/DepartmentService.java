package cn.amphobia.ssh02.service;

import java.util.List;

import cn.amphobia.ssh02.dao.DepartmentDao;
import cn.amphobia.ssh02.entities.Department;

public class DepartmentService {
	
	private DepartmentDao departmentDao;
	
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	
	public List<Department> getAll(){
		return departmentDao.getAll();
	}

}
