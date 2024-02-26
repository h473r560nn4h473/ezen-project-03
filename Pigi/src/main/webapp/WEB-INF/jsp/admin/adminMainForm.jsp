<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>양많으면댇지</title>
</head>
<body>
<section class="ftco-section ftco-degree-bg">
		<div class="container">
				<!-- 본문 -->		
          		<div class="col-md-10" align="center">
          			<div class="col-md-12 heading-section text-center ftco-animate">
						<h3 class="mb-4">메인화면 배너 변경</h3>
					</div>	
					<form action="adminMainModify.al" method="post" enctype="Multipart/form-data" 
            			class="bg-white p-5 contact-form">
            			
            			<h5 class="mb-4" style="text-align:left;">배너 1</h5>
						<div class="form-group">
                			<input type="file" id="file1" name="file1" class="form-control">
						</div>
						<div class="form-group">
							<input type="text" id="text1" name="text1" class="form-control" placeholder="설명을 입력하세요.">
						</div>
						
						<h5 class="mb-4" style="text-align:left;">배너 2</h5>
						<div class="form-group">
                			<input type="file" id="file2" name="file2" class="form-control">
              			</div>
						<div class="form-group">
							<input type="text" id="text2" name="text2" class="form-control" placeholder="설명을 입력하세요.">
						</div>
						
						<h5 class="mb-4" style="text-align:left;">배너 3</h5>
						<div class="form-group">
                			<input type="file" id="file3" name="file3" class="form-control">
              			</div>
						<div class="form-group">
							<input type="text" id="text3" name="text3" class="form-control" placeholder="설명을 입력하세요.">
						</div>
						
						<h5 class="mb-4" style="text-align:left;">배너 4</h5>
						<div class="form-group">
                			<input type="file" id="file4" name="file4" class="form-control">
              			</div>
						<div class="form-group">
							<input type="text" id="text4" name="text4" class="form-control" placeholder="설명을 입력하세요.">
						</div>
						
						<h5 class="mb-4" style="text-align:left;">배너 5</h5>
						<div class="form-group">
                			<input type="file" id="file5" name="file5" class="form-control">
              			</div>
						<div class="form-group">
							<input type="text" id="text5" name="text5" class="form-control" placeholder="설명을 입력하세요.">
						</div>
						
						<div class="form-group">
							<input type="submit" value="등록" class="btn btn-primary py-3 px-5">
						</div>
					</form>
				</div>
			</div>
	</section>

</body>
</html>