package com.elegant.service.endpoint;

import java.io.File;
import java.io.IOException;
import javax.ws.rs.core.MediaType;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import com.elegant.bean.EmployeeBean;
import com.elegant.rest.bean.Employee;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;
import com.sun.jersey.multipart.impl.MultiPartWriter;

@Component
public class RestEndPointService {

	public static final String UPLOAD_URL = "http://localhost:7001/EmployeeRestWS/rest/file/upload";
	public static final String GET_EMPID_DETAILS_FOR_XML_URL = "http://localhost:7001/EmployeeRestWS/rest/file/xml/";
	public static final String GET_EMPID_DETAILS_FOR_JSON_URL = "http://localhost:7001/EmployeeRestWS/rest/file/json/";

	public EmployeeBean getEmployeeDetailsByIdForXmlRequest(long empId) throws Exception {
		Client client = Client.create();
		WebResource webResource = client.resource(GET_EMPID_DETAILS_FOR_XML_URL+empId);
		ClientResponse response = webResource.get(ClientResponse.class);
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}
		System.out.println("Output from Server .... \n");
		Employee output = response.getEntity(Employee.class);
		if (output != null) {
			return convertEmployeeToEmployeeBean(output);
		}
		return null;
	}

	public EmployeeBean getEmployeeDetailsByIdForJsonRequest(long empId) throws Exception {
		Client client = Client.create();
		WebResource webResource = client.resource(GET_EMPID_DETAILS_FOR_JSON_URL+empId);
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}
		String output = response.getEntity(String.class);
		ObjectMapper mapper = new ObjectMapper();
		try {
			Employee employee = mapper.readValue(output, Employee.class);
			System.out.println(employee);
			return convertEmployeeToEmployeeBean(employee);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private EmployeeBean convertEmployeeToEmployeeBean(Employee employee) {
		EmployeeBean employeeBean = new EmployeeBean(employee.getEmpId()+"", employee.getEmpName(),
				employee.getSalary());
		return employeeBean;
	}
	
		
	public String uploadFile(String filePath) throws Exception {
		final ClientConfig config = new DefaultClientConfig();
		config.getClasses().add(MultiPartWriter.class);
        final Client client = Client.create(config);
        final WebResource resource = client.resource(UPLOAD_URL);              
        final File fileToUpload = new File(filePath);
        final FormDataMultiPart multiPart = new FormDataMultiPart();
        if (fileToUpload != null) 
        {
            multiPart.bodyPart(new FileDataBodyPart("file", fileToUpload,
                    MediaType.MULTIPART_FORM_DATA_TYPE));
        } 
        final ClientResponse clientResp = resource.type(
                MediaType.MULTIPART_FORM_DATA).post(ClientResponse.class,
                multiPart);
        String output=clientResp.getEntity(String.class); 
        if (output.contains("error"))
        {
        	return "Error in the process... please check the logs";
        }
        client.destroy();
        return output;       
		
	}

}
