<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>양많으면댇지</title>
<style type="text/css">
p { word-break: break-all;}
</style>
</head>
<body>
<br>
<div style="text-align:center">
	<h5>공지사항</h5>
	<hr width="80%">
</div>

<!-- 수정 후 ver2-->
<br>
<section class="ftco-section ftco-degree-bg">
	<div class="w-100"></div>
		<div class="container">
			<div style="text-align:center">
				<div class="blog-entry align-self-stretch d-md-flex">
					<div class="text d-block pl-md-4" style="text-align:left; margin-left:auto; margin-right:auto; width:600; font-size: 0.9vw;">
						<h3><p style="width: 100%;">${communityBean.CTITLE}<p></h3>
						<div class="meta mb-3">
							<div>${communityBean.CWRITER}</div>
							<div>${communityBean.CDATE}</div>
						</div><hr>
	      				<p class="mt-5" style="font-size:large; width: 100%;">${communityBean.CCONTENT}</p>
	    			</div>
				</div>
			</div>
		</div>
</section>


<%-- 
<!-- 수정 후 ver1-->
<br>
<section class="ftco-section ftco-degree-bg">
	<div class="container">
		<div style="text-align:center">
			<div class="blog-entry align-self-stretch d-md-flex">
				<div class="text d-block pl-md-4" style="margin-left:auto; margin-right:auto; width:600; font-size: 0.9vw;">
					<h3>${communityBean.CTITLE}</h3>
					<div class="meta mb-3">
						<div>${communityBean.CWRITER}</div>
						<div>${communityBean.CDATE}</div>
					</div><hr>
      				<p class="mt-5" style="font-size:large;">${communityBean.CCONTENT}</p>
    			</div>
			</div>
		</div>
	</div>
</section>


<!-- 수정 전 -->
<br>
<div style="text-align:center">
	<h5>공지사항</h5>
	<hr width="80%">
</div>
<br>
<br>
<section class="ftco-section ftco-degree-bg">
	<div class="container">
		<div class="row">
			<div class="blog-entry align-self-stretch d-md-flex">
				<div class="text d-block pl-md-4">
					<h3>${communityBean.CTITLE}</h3>
					<div class="meta mb-3">
						<div>${communityBean.CWRITER}</div>
						<div>${communityBean.CDATE}</div>
					</div><hr>
      				<p class="mt-5" style="font-size:large;">${communityBean.CCONTENT}</p>
    			</div>
			</div>
		</div>
	</div>
</section>

 --%>
</body>
</html>