package cn.amphobia.ssh02.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import cn.amphobia.ssh02.entities.Employee;

public class EmployeeDao extends BaseDao{
	
	/*
	 * 
	 * 	查找所有员工信息
	 */
	public List<Employee> getAll(){
		String hql = "FROM Employee e LEFT OUTER JOIN FETCH e.department";
		return getSession().createQuery(hql).list();
	}
	
	/*
	 * 		删除员工信息
	 */
	public void delete(Integer id) {
		String hql = "DELETE FROM Employee e WHERE e.id = ?";
		getSession().createQuery(hql).setInteger(0, id).executeUpdate();
	}
	/*
	 * 	添加操作
	 */
	public void saveOrUpdate(Employee employee) {
		getSession().saveOrUpdate(employee);
	}
	
	public Employee getEmployeeByLastName(String lastName) {
		String hql = "FROM Employee e WHERE e.lastName = ?";
		Query query = getSession().createQuery(hql).setString(0, lastName);
		Employee employee = (Employee) query.uniqueResult();
		System.out.println(employee.getDepartment().getClass().getName());
		return employee;
	}
	
	public Employee get(Integer id) {
		return (Employee) getSession().get(Employee.class, id);
	}

}
