<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
 response.setHeader("Cache-Control","no-cache");
 response.setHeader("Pragma","no-cache");
 response.setDateHeader("Expires",0);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>양많으면댇지</title>
</head>
<body>
<script>
	if(${! empty msg}) {
	   alert('${msg}');
	}
	location.href='${pageContext.request.contextPath}${url}';
 </script>

</body>
</html>