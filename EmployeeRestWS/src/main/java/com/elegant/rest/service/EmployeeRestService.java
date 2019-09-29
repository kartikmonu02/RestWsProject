package com.elegant.rest.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elegant.rest.bean.Employee;
import com.elegant.rest.dao.EmployeeDao;
import jxl.Sheet;
import jxl.Workbook;

@Service
public class EmployeeRestService {

	@Autowired
	private EmployeeDao employeeDao;

	public void processUpload(InputStream uploadedInputStream) throws Exception {
		List<Employee> employeeList = new ArrayList<>();
		employeeList = processExcel(uploadedInputStream);
		employeeDao.insertEmployeeRecords(employeeList);

	}

	private List<Employee> processExcel(InputStream uploadedInputStream) throws Exception {
		List<Employee> employeeDataFromExcel = new ArrayList<>();
		Workbook workbook = null;
		try {
			workbook = Workbook.getWorkbook(uploadedInputStream);
			Sheet sheet = workbook.getSheet(0);
			long empId = 0;
			String empName;
			double salary = 0;
			for (int i = 1; i < sheet.getRows(); i++) {
				try {
					empId = Long.valueOf(sheet.getCell(0, i).getContents()).longValue();
					empName = sheet.getCell(1, i).getContents(); // first
																	// parameter
																	// is
																	// column,second
																	// param is
																	// row
					salary = Double.valueOf(sheet.getCell(2, i).getContents()).doubleValue();
					employeeDataFromExcel.add(new Employee(empId, empName, salary));
				} catch (Exception e) {
					System.out.println("Error in row:" + i);
				}
			}

			System.out.println("Reading data from excel completed............");
			System.out.println(employeeDataFromExcel.size());

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {

			if (workbook != null) {
				workbook.close();
			}

		}
		return employeeDataFromExcel;
	}

	public Employee findEmployee(long empId) {
		return employeeDao.findEmployee(empId);
	}

}
