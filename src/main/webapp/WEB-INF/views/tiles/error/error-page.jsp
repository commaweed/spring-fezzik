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
				<h2>Server Error!&nbsp;<small class="accent-text-theme">&nbsp;Error Code: ${pageContext.errorData.statusCode}</small></h2>
				<h4 style="color:steelblue">Requested Page: ${pageContext.errorData.throwable }</h4>
				
				<div class="panel panel-default">
					<div class="panel-heading">
						<!-- TODO: figure out why this does not expand any more -->
						<h4 class="panel-title">
							<a data-toggle="collapse" data-target="#stacktrace" href="#stacktrace" class="collapsed">
								View Stack Trace
							</a>
						</h4>
					</div>
					<div id="stacktrace" class="panel-collapse collapse">
						<div class="panel-body" style="color: black;">
							<c:forEach var="trace" items="${ pageContext.exception.stackTrace }">
							${trace}
							</c:forEach>
						</div>
					</div>
				</div>
				
				<a href="<c:url value='/' />" class="btn btn-default">Go to Welcome Page</a>
			</div>
		</div>
	</div>
</div>
