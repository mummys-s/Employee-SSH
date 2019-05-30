package cn.amphobia.ssh02.service;

import java.util.List;

import cn.amphobia.ssh02.dao.EmployeeDao;
import cn.amphobia.ssh02.entities.Employee;

public class EmployeeService {
	
	private EmployeeDao employeeDao;
	
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	
	/*
	 * 	查找所有员工信息
	 */
	public List<Employee> getAll(){
		return employeeDao.getAll();
	}
	
	/*
	 * 		删除员工信息
	 */
	public void delete(Integer id) {
		employeeDao.delete(id);
	}
	
	/*
	 * 	添加成员
	 */
	public void saveOrUpdate(Employee employee) {
		employeeDao.saveOrUpdate(employee);
	}
	
	public boolean lastNameIsValid(String lastName) {
		return employeeDao.getEmployeeByLastName(lastName) == null;
	}
	
	public Employee get(Integer id) {
		return employeeDao.get(id);
	}

}
