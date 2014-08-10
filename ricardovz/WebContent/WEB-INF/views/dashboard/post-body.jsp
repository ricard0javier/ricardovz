<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:eval expression="@applicationProps['application.version']" var="applicationVersion"/>
<spring:url value="/resources-{applicationVersion}" var="resourceUrl">
    <spring:param name="applicationVersion" value="${applicationVersion}"/>
</spring:url>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>		
<script src="${resourceUrl}/js/dashboard.js"></script>
<script src="${resourceUrl}/js/modules/geo.js"></script>