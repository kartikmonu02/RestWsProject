package com.elegant.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ElEGANT_EMPLOYEES")
public class Employee {

	@Id
	@Column(name = "EMP_ID")
	private Long empId;

	@Column(name = "EMP_NAME")
	private String empName;

	@Column(name = "EMP_SALARY")
	private Double salary;

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

}
