<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<style>
.error {
	color: #ff0000;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>

<body>
	<h2>Employee Search Page</h2>

	<form:form method="get" commandName="employee" action="searchEmployeeDetails">
		<form:errors path="*" cssClass="errorblock" element="div" />
		<table>
			<tr>
				<td>Employee Id:</td>
				<td><form:input path="empId" /></td>
				<td><form:errors path="empId" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Employee Name :</td>
				<td><form:input path="empName" /></td>
				<td><form:errors path="empName" cssClass="error" /></td>
				
			</tr>
			<tr>
				<td colspan="3"><input type="submit" value="search" /></td>
			</tr>
		</table>
	</form:form>
	
 
 <c:if test="${not empty empList}">
 <table border="3%">
 <tr>
    <th>Employee Id</th>
    <th>Employee Name</th> 
    <th>Employee Salary</th>
 </tr>
 <c:forEach var="employeeBean" items="${empList}">
			<tr>				
				<td>${employeeBean.empId}</td>	
                <td>${employeeBean.empName}</td>	
                <td>${employeeBean.salary}</td>				
			</tr>
</c:forEach>
			
</table>
</c:if>

<c:if test="${recordFound==false}">
Record Not Found
</c:if>
</body>
</html>