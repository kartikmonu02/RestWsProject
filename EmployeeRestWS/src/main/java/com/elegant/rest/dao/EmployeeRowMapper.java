package com.elegant.rest.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.elegant.rest.bean.Employee;

public class EmployeeRowMapper implements RowMapper
{
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee employee = new Employee();
		employee.setEmpId(rs.getInt("EMP_ID"));
		employee.setEmpName(rs.getString("EMP_NAME"));
		employee.setSalary(rs.getDouble("EMP_SALARY"));
		return employee;
	}
	
}
