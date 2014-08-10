<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:eval expression="@applicationProps['application.version']" var="applicationVersion"/>
<spring:url value="/resources-{applicationVersion}" var="resourceUrl">
    <spring:param name="applicationVersion" value="${applicationVersion}"/>
</spring:url>

<!DOCTYPE html>
<html>
	<head>
		<title>RicardoVZ.com!</title>
		<link rel="stylesheet" type="text/css" href="${resourceUrl}/css/global/main.css">
		<!--[if lt IE 9]>
			<script src="${resourceUrl}/dist/html5shiv.js"></script>
		<![endif]-->
		<script src="${resourceUrl}/js/global/google-analytics.js"></script>
    	<tiles:insertAttribute name="post-head" />
    </head>
	<body>
		<div class="container">
			<tiles:insertAttribute name="body" />
		</div>
		<script src="${resourceUrl}/js/global/main.js"></script>	    
		<tiles:insertAttribute name="post-body" />
	</body>
</html>