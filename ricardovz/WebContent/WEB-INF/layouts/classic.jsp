<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
	<head>
		<title>RicardoVZ.com!</title>
		<link rel="stylesheet" type="text/css" href="resources/css/global/main.css">
		<!--[if lt IE 9]>
			<script src="resources/dist/html5shiv.js"></script>
		<![endif]-->
		<script src="resources/js/global/google-analytics.js"></script>
    	<tiles:insertAttribute name="post-head" />
    </head>
	<body>
		<div class="container">
			<tiles:insertAttribute name="body" />
			
			<script src="resources/js/global/main.js"></script>	    
			<tiles:insertAttribute name="post-body" />
		</div>
	</body>
</html>