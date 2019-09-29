package com.elegant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.elegant.bean.EmployeeBean;
import com.elegant.service.endpoint.RestEndPointService;

@Service
public class EmployeeService {

	@Autowired
	RestEndPointService restEndPointService ;

	public EmployeeBean getEmployeeDetailsByXml(long empId) throws Exception {
		return restEndPointService.getEmployeeDetailsByIdForXmlRequest(empId);
	}

	public EmployeeBean getEmployeeDetailsByJson(long empId) throws Exception {
		return restEndPointService.getEmployeeDetailsByIdForJsonRequest(empId);
	}

}
