package com.elegant.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import com.elegant.pojo.Employee;

@Repository
public class EmployeeDao {
	
	@Autowired
	HibernateTemplate hibernateTemplate;

	public List<Employee> getEmployeeDetails(Employee employee) {
		String query = "from Employee ";
		Map<String, Object> paramMap = prepareQueryForSearch(employee);
		if (!paramMap.isEmpty()) {
			query = query + paramMap.get("query");
			System.out.println("Query:"+query);
			System.out.println("queryParam:"+paramMap.get("queryParam"));
			if (paramMap.get("queryParam")!=null)
			{
				Object[] para=(Object[]) paramMap.get("queryParam");
			}
			return (List<Employee>)hibernateTemplate.find(query, (Object[]) paramMap.get("queryParam"));
		} else {
			return (List<Employee>)hibernateTemplate.find(query);
		}
	}

	private Map<String, Object> prepareQueryForSearch(Employee employee) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<Object> paramValues = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();
		boolean whereClause = false;
		if (employee.getEmpId() != null) {
			whereClause = true;
			query.append(" empId= ?");
			paramValues.add(employee.getEmpId());
		}

		if (employee.getEmpName() != null && employee.getEmpName().trim().length() > 0) {
			if (whereClause) {
				query.append(" and empName=?");
				paramValues.add(employee.getEmpName());
			} else {
				whereClause = true;
				query.append(" empName=?");
				paramValues.add(employee.getEmpName());
			}

		}

		if (whereClause) {
			query.insert(0, " where ");
			paramMap.put("query", query.toString());
			paramMap.put("queryParam", paramValues.toArray());
		}
		return paramMap;
	}

}
