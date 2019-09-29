package com.elegant.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.elegant.service.UploadService;

@Controller
public class UploadController {

	@Autowired
	UploadService uploadService;
	
	
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public @ResponseBody String  uploadFile(@RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				// Creating the directory to store file
				String rootPath = "storeFiles";
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				System.out.println("Server File Location=" + serverFile.getAbsolutePath());
				return uploadService.uploadFile(serverFile.getAbsolutePath());
			
			} catch (Exception e) {
				return "Failed to upload " + file.getOriginalFilename() + " => " + e.getMessage();
		
			}
		} else {
			return "Failed to upload " + file.getOriginalFilename() + " because the file was empty.";
			
		}
	}

	@RequestMapping(value = "/uploadFile", method = RequestMethod.GET)
	public String uploadFile() {
		return "upload";
	}

}
