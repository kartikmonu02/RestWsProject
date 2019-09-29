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
	<h2>Employee Details Page </h2>
	
	 <c:if test="${msg != null}">
        ${msg}
     </c:if>
     <br>
     <br>
     
  <c:if test="${employee != null}">
	<table>
			<tr>
				<td>Employee Id:</td>
				<td>${employee.empId}</td>				
			</tr>
			
			<tr>
				<td>Employee Name:</td>
				<td>${employee.empName}</td>				
			</tr>
			
			<tr>
				<td>Employee Salary:</td>
				<td>${employee.salary}</td>				
			</tr>
			
				
		</table>
 </c:if>

</body>
</html>