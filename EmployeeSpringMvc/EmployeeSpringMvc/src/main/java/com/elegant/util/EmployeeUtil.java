package com.elegant.util;

import com.elegant.bean.EmployeeBean;


public class EmployeeUtil {
	
	public static final String EMPLOYEE_RECORD_NOT_FOUND_MSG="Employee Record Not Found";
	public static final String EMPLOYEE_MSG_KEY="msg";
	public static final String EMPLOYEE_OBJECT_KEY="employee";
	public static final String RECORD_FOUND_KEY="recordFound";
	
	public static com.elegant.pojo.Employee convertEmployeeBeanToEmployeePojo(EmployeeBean employeeBean) {
		com.elegant.pojo.Employee employee = new com.elegant.pojo.Employee();
		if (employeeBean.getEmpId() !=null &&employeeBean.getEmpId().trim().length()>0
				&& Long.valueOf(employeeBean.getEmpId()).longValue()>0) {
			employee.setEmpId(new Long(employeeBean.getEmpId()));
		}
		if (employeeBean.getEmpName() != null && employeeBean.getEmpName().trim().length() > 0) {
			employee.setEmpName(employeeBean.getEmpName());
		}
		return employee;
	}
	
}
