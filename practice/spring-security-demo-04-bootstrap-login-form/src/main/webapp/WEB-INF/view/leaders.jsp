<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>

<head>
	<title>luv2code LEADERS Home Page</title>
</head>

<body>
	<h2>luv2code LEADERS Home Page</h2>
	<hr>
	
	<div>
		<p>User: <u><security:authentication property="principal.username" /></u></p>
		<p>Welcome to the luv2code LEADERS home page!</p>
		<p>User Role(s): <security:authentication property="principal.authorities" /></p>
	</div>
	<br />
	<hr />
	<br />
	<!-- Vissza a home page-re -->
	<p><a href="${pageContext.request.contextPath}/">Vissza a home-ra</a></p>
	
	<form:form action="${pageContext.request.contextPath}/logout" method="POST">
		<input type="submit" value="Kilépés" />
	</form:form>
	<br />
	<hr />
</body>

</html>