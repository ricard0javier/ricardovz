<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:eval expression="@applicationProps['application.version']" var="applicationVersion"/>
<spring:url value="/resources-{applicationVersion}" var="resourceUrl">
    <spring:param name="applicationVersion" value="${applicationVersion}"/>
</spring:url>

<div class="textCenter">
	<header id="header" class="markup">
		<h1>Ricardo Villanueva</h1>
		<a href="<spring:url value='/dashboard'/>"> 
			<img src="${resourceUrl}/css/index/images/welcome.png" border="0" alt="Null">
		</a>
		<p>System Engineer &nbsp;·&nbsp; Intelligence Systems &nbsp;·&nbsp; Programmer</p>
		<span class="right">Click the image...</span>
		${serverTime}
	</header>
</div>