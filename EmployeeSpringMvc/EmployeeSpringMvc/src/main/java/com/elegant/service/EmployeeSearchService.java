package com.elegant.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.elegant.bean.EmployeeBean;
import com.elegant.dao.EmployeeDao;
import com.elegant.pojo.Employee;

@Service
public class EmployeeSearchService {

	@Autowired
	EmployeeDao employeeDao;

	public List<EmployeeBean> getEmployeeDetails(Employee employee) {
		List<EmployeeBean> employeeBeanList = new ArrayList<EmployeeBean>();
		List<Employee> employeeDBList = employeeDao.getEmployeeDetails(employee);
		if (employeeDBList != null && employeeDBList.size() > 0) {
			employeeBeanList = convertEmpDbListToEmpBeanList(employeeDBList);
		}
		return employeeBeanList;
	}

	private List<EmployeeBean> convertEmpDbListToEmpBeanList(List<Employee> employeeDBList) {
		List<EmployeeBean> employeeBeanList = new ArrayList<EmployeeBean>();
		for (Employee employee : employeeDBList) {
			EmployeeBean employeeBean = new EmployeeBean();
			employeeBean.setEmpId(employee.getEmpId() + "");
			employeeBean.setEmpName(employee.getEmpName());
			employeeBean.setSalary(employee.getSalary());
			employeeBeanList.add(employeeBean);
		}
		return employeeBeanList;
	}

}
