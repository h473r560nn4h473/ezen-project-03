<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	if(confirm("장바구니로 가시겠습니까?") == true) {
		location.href='basketList.al';
	} else {
		location.href='${pageContext.request.contextPath}${url}';
	}
	 </script>
</body>
</html>