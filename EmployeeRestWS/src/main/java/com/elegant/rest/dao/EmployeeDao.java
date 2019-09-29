package com.elegant.rest.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.elegant.rest.bean.Employee;

@Repository
public class EmployeeDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insertEmployeeRecords(final List<Employee> employees) {

		String sql = "INSERT INTO ElEGANT_EMPLOYEES" + "(EMP_ID,EMP_NAME,EMP_SALARY) VALUES (?, ?, ?)";

		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Employee employee = employees.get(i);
				ps.setLong(1, employee.getEmpId());
				ps.setString(2, employee.getEmpName());
				ps.setDouble(3, employee.getSalary());
			}

			@Override
			public int getBatchSize() {
				return employees.size();
			}
		});
	}

	public Employee findEmployee(long empId) {
		try {
			String sql = "SELECT * FROM ElEGANT_EMPLOYEES WHERE EMP_ID = ?";
			Employee employee = (Employee) jdbcTemplate.queryForObject(sql, new Object[] { empId },
					new EmployeeRowMapper());
			return employee;
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}

}
