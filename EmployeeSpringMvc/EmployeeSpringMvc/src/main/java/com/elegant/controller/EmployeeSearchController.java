package com.elegant.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.elegant.bean.EmployeeBean;
import com.elegant.service.EmployeeSearchService;
import com.elegant.util.EmployeeUtil;

@Controller
public class EmployeeSearchController {
	
	@Autowired
	EmployeeSearchService employeeSearchService;
	
	@RequestMapping(value = "/loadEmpSearch")
	public ModelAndView loadSearchPage() {
		ModelAndView modelAndView = new ModelAndView("empSearch");
		modelAndView.addObject(EmployeeUtil.EMPLOYEE_OBJECT_KEY, new EmployeeBean());
		return modelAndView;
	}
	
	@RequestMapping(value = "/searchEmployeeDetails")
	public ModelAndView searchEmployeeDetails(@ModelAttribute("employee") EmployeeBean employeeBean) {
		ModelAndView modelAndView = new ModelAndView("empSearch");
		modelAndView.addObject(EmployeeUtil.RECORD_FOUND_KEY, "false");
		List<EmployeeBean> empList=employeeSearchService.getEmployeeDetails(EmployeeUtil.convertEmployeeBeanToEmployeePojo(employeeBean));
		modelAndView.addObject("empList", empList);
		if (empList!=null&&empList.size()>0)
		{
			modelAndView.addObject(EmployeeUtil.RECORD_FOUND_KEY, "true");
		}
		return modelAndView;
	}
	
	

}
