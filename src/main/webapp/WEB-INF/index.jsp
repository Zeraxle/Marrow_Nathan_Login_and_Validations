<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isErrorPage="true" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- Formatting (dates) --> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/style.css">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Welcome</h1>
	<p>Join our growing community</p>
	<p>
		<c:out value="${notLoggedIn}"></c:out>
	</p>
	<main>
		<div>
			<h2>Register</h2>
			<form:form action="/register" method="POST" modelAttribute="newUser">
				<div>
					<form:label path="username">Username: </form:label>
					<form:errors path="username"></form:errors>
					<form:input path="username"></form:input>
				</div>
				<div>
					<form:label path="email">Email: </form:label>
					<form:errors path="email"></form:errors>
					<form:input path="email"></form:input>
				</div>
				<div>
					<form:label path="password">Password: </form:label>
					<form:errors path="password"></form:errors>
					<form:input path="password"></form:input>
				</div>
				<div>
					<form:label path="confirmPassword">Confirm Password: </form:label>
					<form:errors path="confirmPassword"></form:errors>
					<form:input path="confirmPassword"></form:input>
				</div>
				<input type="submit" value="Create User" />
			</form:form>
		</div>
		<div>
			<h2>Login</h2>
			<form:form action="/login" method="POST" modelAttribute="newLogin">
				<div>
					<form:label path="email">Email: </form:label>
					<form:errors path="email"></form:errors>
					<form:input path="email"></form:input>
				</div>
				<div>
					<form:label path="password">Password: </form:label>
					<form:errors path="password"></form:errors>
					<form:input path="password"></form:input>
				</div>
				<input type="submit" value="Login" />
			</form:form>
		</div>	
	</main>
</body>
</html>