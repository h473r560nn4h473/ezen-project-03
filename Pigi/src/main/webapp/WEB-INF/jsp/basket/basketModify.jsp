<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>양많으면댇지 수정</title>
</head>
<body>
<script>
   if(${! empty msg}) {
      alert('${msg}');
      onClick='/Pigi/basketList.al';
   }
    location.href='/Pigi/basketList.al';
    </script>
</body>
</html>