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
		<!-- Új link csak manager-ek részére /leaders -->
		<p><a href="${pageContext.request.contextPath}/leaders">Leader Meeting</a> Csak managereknek</p>
	</security:authorize>
	
	<security:authorize access="hasAnyRole('ADMIN')">
		<!-- Új link csak rendszergazdák részére /systems -->
		<p><a href="${pageContext.request.contextPath}/systems">Admin Holiday Cruise</a> Csak rendszergazdáknak</p>
		<hr />
		<br />
	</security:authorize>
	
	<form:form action="${pageContext.request.contextPath}/logout" method="POST">
		<input type="submit" value="Kilépés" />
	</form:form>
	<br />
	<hr />
</body>

</html>