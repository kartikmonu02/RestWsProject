package com.elegant.rest.controller;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.elegant.rest.bean.Employee;
import com.elegant.rest.service.EmployeeRestService;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Component
@Path("/file")
public class EmployeeRestController {

	@Autowired
	EmployeeRestService employeeRestService;

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {
		try {
			employeeRestService.processUpload(uploadedInputStream);
			return Response.status(200).entity("Successfully processed").build();
		} catch (Exception e) {
			return Response.status(200).entity("Error in process:" + e.getMessage()).build();
		}
	}

	@GET
	@Path("/xml/{empid}")
	@Produces(MediaType.APPLICATION_XML)
	public Employee findEmployeeForXmlRequest(@PathParam("empid") long empid) {
		return employeeRestService.findEmployee(empid);
	}

	@GET
	@Path("/json/{empid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Employee findEmployeeForJsonRequest(@PathParam("empid") long empid) {
		return employeeRestService.findEmployee(empid);
	}

}
