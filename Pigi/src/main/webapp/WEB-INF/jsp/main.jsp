<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body class="goto-here">

    <section id="home-section" class="hero">
		  <div class="home-slider owl-carousel">
	      <div class="slider-item" style="background-image: url(img/main_1.jpg);">
	      	<div class="overlay"></div>
	        <div class="container">
	          <div class="row slider-text justify-content-center align-items-center" data-scrollax-parent="true">

	            <div class="col-md-12 ftco-animate text-center">
	              <h1 class="mb-2">좋은 고기, 위대한 고기</h1>
	            </div>

	          </div>
	        </div>
	      </div>

	      <div class="slider-item" style="background-image: url(img/main_2.jpg);">
	      	<div class="overlay"></div>
	        <div class="container">
	          <div class="row slider-text justify-content-center align-items-center" data-scrollax-parent="true">

	            <div class="col-sm-12 ftco-animate text-center">
	              <h1 class="mb-2">밭의 신선함을 문 앞에서</h1>
	            </div>

	          </div>
	        </div>
	      </div>
	      
	      <div class="slider-item" style="background-image: url(img/main_3.jpg);">
	      	<div class="overlay"></div>
	        <div class="container">
	          <div class="row slider-text justify-content-center align-items-center" data-scrollax-parent="true">

	            <div class="col-sm-12 ftco-animate text-center">
	              <h1 class="mb-2">수입농산물 말씀이십니까?<br>맡겨만 주세요</h1>
	            </div>

	          </div>
	        </div>
	      </div>
	      
	      <div class="slider-item" style="background-image: url(img/main_4.jpg);">
	      	<div class="overlay"></div>
	        <div class="container">
	          <div class="row slider-text justify-content-center align-items-center" data-scrollax-parent="true">

	            <div class="col-sm-12 ftco-animate text-center">
	              <h1 class="mb-2">식자재에만 진심인 건 아닙니다</h1>
	            </div>

	          </div>
	        </div>
	      </div>
	      
	      <div class="slider-item" style="background-image: url(img/main_5.jpg);">
	      	<div class="overlay"></div>
	        <div class="container">
	          <div class="row slider-text justify-content-center align-items-center" data-scrollax-parent="true">

	            <div class="col-sm-12 ftco-animate text-center">
	              <h1 class="mb-2">전담 배송업체를 통하여<br>빠르고 안전하게 보내드립니다</h1>
	            </div>

	          </div>
	        </div>
	      </div>
	    </div>
    </section>

    <section class="ftco-section">

    	<div class="container">
				<div class="row justify-content-center mb-3 pb-3">
          <div class="col-md-12 heading-section text-center ftco-animate">
            <h2 class="mb-4">최다 판매 상품</h2>
            </div>
        </div>   		
        </div>
    	<form action="main.al" method="GET">
    	
    	<div class="container">
    		<div class="row">
    		
    		<div class="container" align="center">
    		<div class="row">
    			<c:forEach var="product" items="${mainBList}">
    			<div class="col-md-6 col-lg-3 ftco-animate" style="float:left; width:33%; padding:10px;">
    				<div class="product">
    					<a href="pDetail.al?PID=${product.PID}" class="img-prod"><img class="img-fluid" src="img/${product.PIMAGE}" style="height:250px;">
    						<div class="overlay"></div>
    					</a>
    					<div class="text py-3 pb-4 px-3 text-center">
    						<h3><a href="pDetail.al?PID=${product.PID}">${product.PNAME}</a></h3>
    						<div class="d-flex">
    							<div class="pricing">
		    						<p class="price"><span>${product.PPRICE}원</span></p>
		    					</div>
	    					</div>
    					</div>
    				</div>
    			</div>
    			</c:forEach>
				<div class="col-md-6 col-lg-3 ftco-animate" style="padding-top:10px;">
    				<div class="product">
    					<a href="allList.al" class="img-prod"><img src="img/main_bp.png" style="height:336px;">
    						<div class="overlay"></div>
    					</a>
    				</div>
    			</div>
    			</div>
    		</div>    		
    	</div>
    </div>
    </form>
</section>

</body>
</html>