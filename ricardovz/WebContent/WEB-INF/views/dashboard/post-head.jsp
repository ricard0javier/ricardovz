<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:eval expression="@applicationProps['application.version']" var="applicationVersion"/>
<spring:url value="/resources-{applicationVersion}" var="resourceUrl">
    <spring:param name="applicationVersion" value="${applicationVersion}"/>
</spring:url>

<link rel="stylesheet" type="text/css" href="${resourceUrl}/css/global/dashboard.css">