package com.elegant.bean;


public class EmployeeBean {
	
	private String empId;
	private String empName;
	private double salary;
	private String dataFormat;
	
	public EmployeeBean()
	{
		
	}

	public EmployeeBean(String empId, String empName, double salary) {
		this.empId = empId;
		this.empName = empName;
		this.salary = salary;
	}

	public String getDataFormat() {
		return dataFormat;
	}


	public void setDataFormat(String dataFormat) {
		this.dataFormat = dataFormat;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

}
