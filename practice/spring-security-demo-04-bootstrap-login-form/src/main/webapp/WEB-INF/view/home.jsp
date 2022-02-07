<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>

<head>
	<title>luv2code Company Home Page</title>
</head>

<body>
	<h2>luv2code Company Home Page</h2>
	<hr>
	
	<div>
		<p>User: <u><security:authentication property="principal.username" /></u></p>
		<p>Welcome to the luv2code company home page!</p>
		<p>User Role(s): <security:authentication property="principal.authorities" /></p>
	</div>
	<br />
	<hr />
	<br />
	<security:authorize access="hasAnyRole('MANAGER','ADMIN')">
		<!-- �j link csak manager-ek r�sz�re /leaders -->
		<p><a href="${pageContext.request.contextPath}/leaders">Leader Meeting</a> Csak managereknek</p>
	</security:authorize>
	
	<security:authorize access="hasAnyRole('ADMIN')">
		<!-- �j link csak rendszergazd�k r�sz�re /systems -->
		<p><a href="${pageContext.request.contextPath}/systems">Admin Holiday Cruise</a> Csak rendszergazd�knak</p>
		<hr />
		<br />
	</security:authorize>
	
	<form:form action="${pageContext.request.contextPath}/logout" method="POST">
		<input type="submit" value="Kil�p�s" />
	</form:form>
	<br />
	<hr />
</body>

</html>