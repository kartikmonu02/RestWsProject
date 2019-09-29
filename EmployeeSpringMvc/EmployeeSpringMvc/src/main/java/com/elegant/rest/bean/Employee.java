package com.elegant.rest.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Employee implements java.io.Serializable{

	private static final long serialVersionUID = -2649060659504712740L;
	
	private long empId;
	private String empName;
	private double salary;
	
	public Employee(){
		
	}	
	public Employee(long empId,String empName,double salary)
	{
		this.empId=empId;
		this.empName=empName;
		this.salary=salary;
	}
	@XmlElement
	public long getEmpId() {
		return empId;
	}
	@XmlElement
	public String getEmpName() {
		return empName;
	}
	@XmlElement
	public double getSalary() {
		return this.salary;
	}		

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

}
