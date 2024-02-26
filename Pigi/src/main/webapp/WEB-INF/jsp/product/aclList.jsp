<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
.name{
  width        : 225px;
  text-overflow: ellipsis;
  white-space  : nowrap;
  overflow     : hidden;
  display      : block;
  color   : black;
}
div.custom-radio {
  display: inline-flex;
  align-items: center;
}
input[type='radio'],
input[type='radio']:checked {
  appearance: none;
  width: 0.9rem;
  height: 0.9rem;
  border-radius: 100%;
  margin-right: 0.1rem;
}

input[type='radio'] {
  background-color: white;
  border: 2px solid gray;
}
input[type='radio']:checked {
  background-color: #ff4a4a;
}
</style>
<title>양많으면댇지</title>
<script>
function radioReset(e) {
	e.checked = false;	
}
</script>
<script>
function pOrderCheck(stock, pid) {		
	if(stock<=0) {
		alert("재고가 없습니다");
		return false;
	}
	location.href = "pOrderForm.al?PID=" + pid + "&PCOUNT=1"
}	  
function putBasketConfirm(PID) {
	let BID = PID;
	if(confirm("장바구니에 넣으시겠습니까?") == true) {
		location.href = "putBasket.al?BID=" + BID + "&BCOUNT=1&list=acl";
	}	
	return false;
}
</script>	
<script>
window.onload = function() {
	var urlStr = window.location.href;
	const url = new URL(urlStr);
	
	const urlParams = url.searchParams;
	
	var pkind = urlParams.get("PKIND");
	var dMax = urlParams.get("dMax");
	var pMax = urlParams.get("pMax");
	
	if(pkind != null) {
		if(pkind == '육류') {
			document.getElementById("PKIND1").checked = true;
		} else if(pkind == '채소') {
			document.getElementById("PKIND2").checked = true;
		} else if(pkind == '과일') {
			document.getElementById("PKIND3").checked = true;
		} else if(pkind == '냉장') {
			document.getElementById("PKIND4").checked = true;
		} else if(pkind == '냉동') {
			document.getElementById("PKIND5").checked = true;
		}
	}
	if(dMax != null) {
		if(dMax == 0) {
			document.getElementById("dMax1").checked = true;
		} else if(dMax == 1) {
			document.getElementById("dMax2").checked = true;
		} else if(dMax == 2) {
			document.getElementById("dMax3").checked = true;
		} else if(dMax == 3) {
			document.getElementById("dMax4").checked = true;
		} else if(dMax == 4) {
			document.getElementById("dMax5").checked = true;
		}
	}
	if(pMax != null) {
		if(pMax == 0) {
			document.getElementById("pMax1").checked = true;
		} else if(pMax == 1) {
			document.getElementById("pMax2").checked = true;
		} else if(pMax == 2) {
			document.getElementById("pMax3").checked = true;
		} else if(pMax == 3) {
			document.getElementById("pMax4").checked = true;
		} else if(pMax == 4) {
			document.getElementById("pMax5").checked = true;
		}
	}	
}
</script>
</head>
<body>
	<p/>
	<div class="col-md-12">
		<div>
			<div>
				<form action="aclList.al">
				<div style="text-align:center">
					<table style="margin-left:auto; margin-right:auto; width:600; font-size: 0.9vw;">
						<tr>
							<td style="text-align:left;">
								<b>분류</b>&emsp;
							</td>
							<td style="text-align:left;">
								<label><input type="radio" id="PKIND1" name="PKIND" class="mr-2" value="육류"
									ondblclick="radioReset(this)">육류</label>
							</td>
							<td style="text-align:left;">
								<label><input type="radio" id="PKIND2" name="PKIND" class="mr-2" value="채소"
									ondblclick="radioReset(this)">채소</label>
							</td>
							<td style="text-align:left;">						
								<label><input type="radio" id="PKIND3" name="PKIND" class="mr-2" value="과일"
									ondblclick="radioReset(this)">과일</label>
							</td>
							<td style="text-align:left;">
								<label><input type="radio" id="PKIND4" name="PKIND" class="mr-2" value="냉장"
									ondblclick="radioReset(this)">냉장</label>
							</td>
							<td style="text-align:left;">
								<label><input type="radio" id="PKIND5" name="PKIND" class="mr-2" value="냉동"
									ondblclick="radioReset(this)">냉동</label>
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">
								<b>무게</b>&emsp;
							</td>
							<td style="text-align:left;">
								<label><input type="radio" id="dMax1" name="dMax" class="mr-2" value="0"
									ondblclick="radioReset(this)">0-3kg</label>
							</td>
							<td style="text-align:left;">
								<label><input type="radio" id="dMax2" name="dMax" class="mr-2" value="1"
									ondblclick="radioReset(this)">3-6kg</label>
							</td>
							<td style="text-align:left;">
								<label><input type="radio" id="dMax3" name="dMax" class="mr-2" value="2"
									ondblclick="radioReset(this)">6-9kg</label>
							</td>
							<td style="text-align:left;">
								<label><input type="radio" id="dMax4" name="dMax" class="mr-2" value="3"
									ondblclick="radioReset(this)">9-12kg</label>
							</td>
							<td style="text-align:left;">
								<label><input type="radio" id="dMax5" name="dMax" class="mr-2" value="4"
									ondblclick="radioReset(this)">12kg이상</label>
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">
								<b>가격</b>&emsp;
							</td>
							<td style="text-align:left;">
								<label><input type="radio" id="pMax1" name="pMax" class="mr-2" value="0"
									ondblclick="radioReset(this)"> 0-5천원</label>
							</td>
							<td style="text-align:left;">
								<label><input type="radio" id="pMax2" name="pMax" class="mr-2" value="1"
									ondblclick="radioReset(this)">5천-1만원</label>
							</td>
							<td style="text-align:left;">
								<label><input type="radio" id="pMax3" name="pMax" class="mr-2" value="2"
									ondblclick="radioReset(this)">1만-2만원</label>
							</td>
							<td style="text-align:left;">
								<label><input type="radio" id="pMax4" name="pMax" class="mr-2" value="3"
									ondblclick="radioReset(this)">2만-3만원</label>
							</td>
							<td style="text-align:left;">
								<label><input type="radio" id="pMax5" name="pMax" class="mr-2" value="4"
									ondblclick="radioReset(this)">3만원이상</label>
							</td>
						</tr>
					 </table>
				</div>	
			
				<div class="info bg-white p-4" style="text-align:center;">
					<input type="submit" value="검색" class="btn btn-primary py-2 px-4">
					<input type="reset" value="리셋" class="btn btn-black py-2 px-4">
				</div>
				<div class="text text-center">
					<%-- ${searchPrint} --%>
				</div>
				</form>
			</div>
		</div>
	</div>

    <section class="ftco-section">
       	<div class="row justify-content-center">
   			<div class="col-md-10 mb-5 text-center">
   				<ul class="product-category">
   					<li><a href="aclList.al">전체</a></li>
   					<li><a href="aclList.al?PSELL=A">인기도순</a></li>
   					<li><a href="aclList.al?pOrder=LOW">낮은가격순</a></li>
   					<li><a href="aclList.al?pOrder=HIGH">높은가격순</a></li>
   					<li><a href="aclList.al?PDATE=A">최신순</a></li>
   				</ul>
   			</div>
   		</div>
    	<div class="container">
    		<div class="row">
    		
    			<c:forEach var="product" items="${productBeanList}">
    			<div class="col-md-6 col-lg-3">
    				<div class="product">
    					<a href="pDetail.al?PID=${product.PID}" class="img-prod"><img class="img-fluid" src="img/${product.PIMAGE}" alt="이미지 없음" style="height:250px;">
    						<c:if test="${product.PSALE != 0}">
    						<span class="status">${product.PSALE}%</span>
    						</c:if>
    						<div class="overlay"></div>
    					</a>
    					<div class="text py-3 pb-4 px-3 text-center">
    						<div class="name"><a href="pDetail.al?PID=${product.PID}" style="color:black;">${product.PNAME}</a></div>
    						<div class="d-flex">
    							<div class="pricing">
    								<c:if test="${product.PSALE == 0}">
    								<p class="price"><span class="mr-2">${product.PPRICE}원</span></p>
    								</c:if>
    								<c:if test="${product.PSALE != 0}">
		    						<p class="price"><span class="mr-2 price-dc">${product.PPRICE}원</span>
		    							<c:set var="salePrice" value="${product.PPRICE * (100-product.PSALE) * 0.01}" /> 
		    							<span class="price-sale"><fmt:formatNumber value="${salePrice}" pattern="#.#" />원</span></p>
		    						</c:if>
		    								    						
		    					</div>
	    					</div>
	    					<div class="bottom-area d-flex px-3">
	    						<div class="m-auto d-flex">
	    							<a href="javascript:putBasketConfirm(${product.PID});" class="add-to-cart d-flex justify-content-center align-items-center text-center">
	    								<span><i class="ion-ios-cart"></i></span>
	    							</a>
	    							<a href="javascript:pOrderCheck(${product.PSTOCK}, ${product.PID});" class="buy-now d-flex justify-content-center align-items-center mx-1">
	    								<span><i class="ion-ios-menu"></i></span>
	    							</a>
    							</div>
    						</div>
    					</div>
    				</div>
    			</div>
    			
    			</c:forEach>
    			
    		</div>
			
    	</div>
    	
    	${paging.pageHtml}    	

	</section>
	
</body>
</html>