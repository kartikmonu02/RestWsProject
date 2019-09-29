<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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

	<form:form method="get" commandName="employee" action="loadEmpDetails">
		<form:errors path="*" cssClass="errorblock" element="div" />
		<table>
			<tr>
				<td>Enter employee Id:</td>
				<td><form:input path="empId" /></td>
				<td><form:errors path="empId" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Select Format :</td>
				<td><form:select path="dataFormat" >
				      <form:option value="JSON" label="JSON"  />
				      <form:option value="XML" label="XML"  />
				     </form:select>			
				</td>
				
			</tr>
			<tr>
				<td colspan="3"><input type="submit" /></td>
			</tr>
		</table>
	</form:form>

</body>
</html>