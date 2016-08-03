<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<h1>Welcome to the secured Fezzik home page!</h1>

<P>The time on the server is ${serverTime}.</P>

<div class="container">
	<h1>This is a secured page!</h1>
	<p>
		Hello <b><c:out value="${pageContext.request.remoteUser}" /></b>
</p>
<c:url var="logoutUrl" value="/" />

<form class="form-inline" action="${logoutUrl}" method="post">
	<input type="submit" value="Log out" /> 
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
</div>

