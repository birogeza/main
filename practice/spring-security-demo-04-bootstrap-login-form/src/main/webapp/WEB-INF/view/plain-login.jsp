<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>Custom Login Form</title>
	<meta lang="hu" charset="UTF-8" />
	<style>
		.failed{ color: red; }
	</style>
</head>


<body>
	<br />
	<h3>My Custom Login Page</h3>
	<form:form action="${pageContext.request.contextPath}/authenticateTheUser" 
			   method="POST">
		
		<c:if test="${param.error != null}">
			<i class="failed">Sajnos a megadott Felhasználó név / Jelszó hibás!</i>
		</c:if>
		
		<c:if test="${param.logout != null}">
			<i class="failed">Sikeresen kiléptél!</i>
		</c:if>
		
		<p>Felhasználó név: <input type="text" name="username" /></p>
		<p>Jelszó: <input type="password" name="password" /></p>
		<input type="submit" value="Belépés">
		
	</form:form>
	<br />
	<hr />
	<br />
</body>
</html>