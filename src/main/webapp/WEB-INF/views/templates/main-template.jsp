<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<spring:eval var="fezzik_environment" expression="@environment.getProperty('web.application.environment')" />

<!DOCTYPE html>

<html>
<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<title><tiles:getAsString name="title" /></title>
	<link rel="shortcut icon" href="${contextPath}/resources/images/favicon.ico" />
	
	<tiles:insertAttribute name="common.css" ignore="true" />
	<tiles:insertAttribute name="page.css" ignore="true" />
</head>

<body>
	<div id="mainnavbar" class="navbar navbar-theme">
		<tiles:insertAttribute name="common.header" />
		<tiles:insertAttribute name="page.header" ignore="true" />
	</div>
	
	<tiles:insertAttribute name="content" />
	
	<tiles:insertAttribute name="common.footer" />
	<tiles:insertAttribute name="page.footer" ignore="true" />
	
	<!-- javascript goes at the end -->
	<tiles:insertAttribute name="common.javascript" />
	<tiles:insertAttribute name="page.javascript" ignore="true" />
</body>
</html>