package cn.amphobia.ssh02.dao;

import java.util.List;

import cn.amphobia.ssh02.entities.Department;

public class DepartmentDao extends BaseDao{
	
	/*
	 * 
	 * 	显示员工的添加信息
	 */
	public List<Department> getAll(){
		String hql = "from Department";
		return getSession().createQuery(hql).list();
	}
	
	

}
