<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div class="container" style="width: 90%">
	<div class="row">
		<div class="col-lg-12">
			<div class="jumbotron">
				<h2>Access Denied!&nbsp;<small class="accent-text-theme">&nbsp;You do not have permission to view the requested page!</small></h2>
				<h4 style="color:steelblue">Requested Page: ${pageContext.errorData.requestURI }</h4>
			</div>
		</div>
	</div>
</div>