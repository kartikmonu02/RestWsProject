package com.elegant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.elegant.service.endpoint.RestEndPointService;

@Service
public class UploadService {
	
	@Autowired
	RestEndPointService restEndPointService ;
	
	public String uploadFile(String filePath) throws Exception {
		return restEndPointService.uploadFile(filePath);
	}

}
