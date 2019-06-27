package cn.amphobia.ssh02.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import cn.amphobia.ssh02.entities.Employee;
import cn.amphobia.ssh02.service.DepartmentService;
import cn.amphobia.ssh02.service.EmployeeService;

public class EmployeeAction extends ActionSupport implements RequestAware,
ModelDriven<Employee>,Preparable{
	
	private EmployeeService employeeService;
	private DepartmentService departmentService;
	private Map<String, Object> request;
	private Integer id;
	
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	//重写setRequest方法，将获取到的值存放到request域中
	public void setRequest(Map<String, Object> map) {
		this.request = map;
	}
	
	private InputStream inputStream;
	
	public InputStream getInputStream() {
		return inputStream;
	}
	
	/*
	 * 	显示所有员工信息
	 * 	
	 */
	public String list() {
		//将信息保存到request中，方便jsp页面获取
		request.put("employees", employeeService.getAll());
		return "list";
	}
	
	/*
	 * 		删除员工信息
	 */
	public String delete() {
		try {
			employeeService.delete(id);
			inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			try {
				inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
		return "ajax-success";
	}
	
	/*
	 * 	添加员工信息时显示的表单
	 */
	public String input() {
		request.put("departments", departmentService.getAll());
		return INPUT;
	}
	
	private String lastName;
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String validateLastName() throws UnsupportedEncodingException {
		if(employeeService.lastNameIsValid(lastName)) {
			inputStream = new ByteArrayInputStream("1".getBytes("UTF-8")); 
		}else{
			inputStream = new ByteArrayInputStream("0".getBytes("UTF-8")); 
		}
		return "ajax-success";
	}

	
	public String save() {
		if(id == null) {
			model.setCreateTime(new Date());
		}
		employeeService.saveOrUpdate(model);
//		System.out.println(model);
		System.out.println();
		return SUCCESS;
	}
	
	
	public void prepareInput() {
		if(id != null) {
			model = employeeService.get(id);
		}
	}
	
	//根据id来判断save方法的model是new的还是从数据库获取的
	public void prepareSave() {
		if(id == null) {
			model = new Employee();
		}else {
			model = employeeService.get(id);
		}
		
	}

	public void prepare() throws Exception {}

	private Employee model;
	public Employee getModel() {
		return model;
	}


}
