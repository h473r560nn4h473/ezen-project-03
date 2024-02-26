<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>양많으면댇지</title>

</head>
<body>
<header style="height: 10%;">
	<tiles:insertAttribute name="header"/>
</header>


<nav style="width: 17.5%; float: left; margin-top: 70px">
<tiles:insertAttribute name="left"/> 
</nav>
<article>


<div style="width: 82.5%; float: left;">
<tiles:insertAttribute name="body" ignore="true"/> 
</div>
</article>
<footer style="width: 100%; float: left;">
 	<tiles:insertAttribute name="footer"/> 
 </footer> 
      
</body>
</html>