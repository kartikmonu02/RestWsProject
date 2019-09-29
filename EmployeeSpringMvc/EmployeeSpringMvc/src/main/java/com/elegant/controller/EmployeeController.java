package com.elegant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.elegant.bean.EmployeeBean;
import com.elegant.service.EmployeeService;
import com.elegant.util.EmployeeUtil;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@RequestMapping(value = "/load")
	public ModelAndView loadEmployeeSearchForRestService() {
		ModelAndView modelAndView = new ModelAndView("employee");
		modelAndView.addObject(EmployeeUtil.EMPLOYEE_OBJECT_KEY, new EmployeeBean());
		return modelAndView;
	}

	@RequestMapping(value = "/loadEmpDetails", method = RequestMethod.GET)
	public ModelAndView getEmployeeDetailsFromRestService(@ModelAttribute("employee") EmployeeBean employeeBean) {
		ModelAndView modelAndView = new ModelAndView("employeeDetails");
		EmployeeBean employee = processRequestForRestService(employeeBean, modelAndView);
		processResponse(employee, modelAndView);
		return modelAndView;
	}

	private EmployeeBean processRequestForRestService(EmployeeBean employeeBean, ModelAndView modelAndView) {
		EmployeeBean employee = null;
		try {
			if (employeeBean.getEmpId()!=null&&employeeBean.getDataFormat().equals("JSON")) {
				employee = processJsonRequest(Long.valueOf(employeeBean.getEmpId()).longValue());
			} else if (employeeBean.getEmpId()!=null&&employeeBean.getDataFormat().equals("XML")) {
				employee = processXmlRequest(Long.valueOf(employeeBean.getEmpId()).longValue());
			}
		} catch (Exception e) {
			modelAndView.addObject(EmployeeUtil.EMPLOYEE_MSG_KEY, EmployeeUtil.EMPLOYEE_RECORD_NOT_FOUND_MSG);
		}
		return employee;
	}

	private EmployeeBean processJsonRequest(long empId) throws Exception {
		return employeeService.getEmployeeDetailsByJson(empId);
	}

	private EmployeeBean processXmlRequest(long empId) throws Exception {
		return employeeService.getEmployeeDetailsByXml(empId);
	}

	private void processResponse(EmployeeBean employee, ModelAndView modelAndView) {
		if (employee != null) {
			modelAndView.addObject(EmployeeUtil.EMPLOYEE_OBJECT_KEY, employee);
			modelAndView.addObject(EmployeeUtil.EMPLOYEE_MSG_KEY, "Employee Details as shown below ");
		} else {
			modelAndView.addObject(EmployeeUtil.EMPLOYEE_OBJECT_KEY, null);
			modelAndView.addObject(EmployeeUtil.EMPLOYEE_MSG_KEY, EmployeeUtil.EMPLOYEE_RECORD_NOT_FOUND_MSG);
		}
	}
}
