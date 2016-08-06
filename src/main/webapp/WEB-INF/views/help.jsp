<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>

<html>
<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<title><tiles:getAsString name="title" /></title>
	<link rel="shortcut icon" href="${contextPath}/resources/images/favicon.ico" />
	
</head>

<body>
<div class="container" style="width: 90%">
	<div class="row">
		<div class="col-lg-12">
			<div class="jumbotron">
				<h2>Welcome to Fizzek!&nbsp;</h2>
				<h4 style="color:steelblue">Put Login link here or</h4>
				<c:url var="messageUrl" value="/home/time"  />
				<a href="${messageUrl}" class="btn btn-default">Go to home - should prompt a login</a>
			</div>
		</div>
	</div>
</div>
</body>
</html>