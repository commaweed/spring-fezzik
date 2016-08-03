<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!-- use dev/prod technique -->
<script src="${contextPath}/resources/vendor/jquery.js"></script>
<script src="${contextPath}/resources/vendor/bootstrap/js/bootstrap.js"></script>

<!-- maybe move to specific page.javascript -->
<script type="text/javascript">
	$(document).ready(function()) {
		console.log("page ready!");
	}
</script>