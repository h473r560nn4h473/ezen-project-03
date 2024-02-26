<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ include file="/WEB-INF/include/include-header.jspf" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>양많으면댇지</title>
<!-- 우편번호 API -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
function getCount() {
	var size = document.getElementById('listSize').value;
	var sum = 0; // 원가의 전체 합
	var saleSum = 0; // 할인된 총 전체 합
	for(var i=0;i<size;i++){
		var count = document.getElementById('OCOUNT'+i).value;
		var price = document.getElementById('OPRICE'+i).value;
		var sale = document.getElementById('OSALE'+i).value;
		var salePrice = price * (100-sale) / 100;
		var totalPrice = salePrice * count; // 할인된 상품 가격
		
		sum += price * count;
		saleSum += (price*sale/100)*count;// 상품이 할인받은 가격 
	}
	document.getElementById("originalSum").innerText = sum + '원'; // 페이지의 sum값 출력
	document.getElementById("saleSum").innerText = saleSum + '원'; // 페이지의 saleSum값 출력
	
	var finalSum = sum - saleSum + 3000;
	document.getElementById("finalSum").innerText = finalSum + '원'; // 페이지의 finalSum값 출력
	
	return;
}

    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다 (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다
                    document.getElementById("ADDRESS2").value = extraAddr;
                
                } else {
                    document.getElementById("ADDRESS2").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다
                document.getElementById('POSTCODE').value = data.zonecode;
                document.getElementById("ADDRESS1").value = addr;
                // 커서를 상세주소 필드로 이동한다
                document.getElementById("ADDRESS2").focus();
            }
        }).open();
    }
    
    /* 체크박스 체크 여부에 따라 값 입력/삭제 */
$(document).ready(function() {
    $('#infoCheck').change(function() {
    	var NAME = document.getElementById("NAME");
    	var MOBILE = document.getElementById("MOBILE");
    	var POSTCODE = document.getElementById("POSTCODE");
    	var ADDRESS1 = document.getElementById("ADDRESS1");
    	var ADDRESS2 = document.getElementById("ADDRESS2");
    	
    	var ONAME = document.getElementById("ONAME");
    	var OMOBILE = document.getElementById("OMOBILE");
    	var OPOSTCODE = document.getElementById("OPOSTCODE");
    	var OADDRESS1 = document.getElementById("OADDRESS1");
    	var OADDRESS2 = document.getElementById("OADDRESS2");
    	
    	if($('#infoCheck').is(":checked")) {
    		NAME.value = ONAME.value;
    		MOBILE.value = OMOBILE.value;
    		POSTCODE.value = OPOSTCODE.value;
    		ADDRESS1.value = OADDRESS1.value;
    		ADDRESS2.value = OADDRESS2.value;
    	} else {
    		NAME.value = "";
    		MOBILE.value = "";
    		POSTCODE.value = "";
    		ADDRESS1.value = "";
    		ADDRESS2.value = "";
    	}		
   	});
    
	/* 쿠폰 태그 변경시 작동 */
	$('#coupon').change(function() {
		
		var size = $('#listSize').val();
		var sum = 0; // 원가의 전체 합
		var saleSum = 0; // 할인된 총 전체 합
		for(var i=0;i<size;i++){
			var count = $('#OCOUNT'+i).val();
			var price = $('#OPRICE'+i).val();
			var sale = $('#OSALE'+i).val();
			var salePrice = price * (100-sale) / 100;
			var totalPrice = salePrice * count; // 할인된 상품 가격
			
			sum += price * count;
			saleSum += (price*sale/100)*count; // 상품이 할인받은 가격
		}
		
		/* 주문금액, 할인금액 구하기 */
		var totalPrice = sum; // 주문금액
		var saled = saleSum; // 할인금액
		
		/* select 태그의 값 */
		var couponValue;
		if($('#coupon').val() == '1K') {
			couponValue = 1000;
		} else if($('#coupon').val() == '2K') {
			couponValue = 2000;
		} else if($('#coupon').val() == '3K') {
			couponValue = 3000;
		} else if($('#coupon').val() == '5K') {
			couponValue = 5000;
		} else if($('#coupon').val() == '10K') {
			couponValue = 10000;
		} else {
			couponValue = 0;
		}
		
		/* id="point" number 태그의 값 */ 
		var pointValue = $('#point').val();
		if(pointValue == null || pointValue == "") {
			pointValue = 0;
		}
		
		/* 쿠폰값을 변경할 때 입력한 point의 값이 포인트 보유량보다 클 경우 변경 */ 
		var maxPoint = $('#maxPoint').val();
		if(Number(pointValue) > Number(maxPoint)) {
			pointValue = maxPoint;
			$('#point').val(pointValue);
		}
		
		var eventValue = Number(couponValue) + Number(pointValue);
		
		/* 쿠폰 + 포인트 값이 주문금액 - 할인금액 + 배송비보다 많으면 초기화 */
		if(Number(eventValue) > (parseInt(totalPrice) - parseInt(saled) + parseInt("3000"))) {
			alert("쿠폰과 포인트 사용금액이 주문금액보다 많습니다");
			$('#coupon').val("---");
			$('#point').val("0");
			couponValue = 0;
			pointValue = 0;
			eventValue = 0;
		}
		
		/* 쿠폰/포인트 항목 출력 */
		$('#eventPoint').text(eventValue + "원");
		
		/* 총 금액 : totalPrice(주문금액) - saled(할인금액) - eventValue(쿠폰/포인트) + 3000(배송비) */
		$('#finalSum').text(Number(totalPrice) - Number(saled) - Number(eventValue) + Number(3000) + "원");		
	});
	
	$('#point').change(function(){
		
		var size = $('#listSize').val();
		var sum = 0; // 원가의 전체 합
		var saleSum = 0; // 할인된 총 전체 합
		for(var i=0;i<size;i++){
			var count = $('#OCOUNT'+i).val();
			var price = $('#OPRICE'+i).val();
			var sale = $('#OSALE'+i).val();
			var salePrice = price * (100-sale) / 100;
			var totalPrice = salePrice * count; // 할인된 상품 가격
			
			sum += price * count;
			saleSum += (price*sale/100)*count;// 상품이 할인받은 가격 
		}
		
		/* 주문금액, 할인금액 구하기 */
		var totalPrice = sum; // 주문금액
		var saled = saleSum; // 할인금액
		
		/* select 태그의 값 */
		var couponValue;
		if($('#coupon').val() == '1K') {
			couponValue = 1000;
		} else if($('#coupon').val() == '2K') {
			couponValue = 2000;
		} else if($('#coupon').val() == '3K') {
			couponValue = 3000;
		} else if($('#coupon').val() == '5K') {
			couponValue = 5000;
		} else if($('#coupon').val() == '10K') {
			couponValue = 10000;
		} else {
			couponValue = 0;
		}
		
		/* 포인트 입력시 100단위가 아니면 자동 변환 */
		var pointValue = $('#point').val();
	    pointValue = Math.floor(pointValue/100) * 100; 
		if(pointValue == null || pointValue == "") {
			pointValue = 0;
		}
		$('#point').val(pointValue);
		
		/* 입력한 point의 값이 포인트 보유량보다 클 경우 변경 */ 
		var maxPoint = $('#maxPoint').val();
		if(Number(pointValue) > Number(maxPoint)) {
			alert("보유하신 포인트는 " + maxPoint + "Point입니다");
			pointValue = Math.floor(maxPoint / 100) * 100;
			$('#point').val(pointValue);
		}
		
		var eventValue = Number(couponValue) + Number(pointValue);
		
	 	/* 쿠폰 + 포인트 값이 주문금액 - 할인금액 + 배송비보다 많으면 초기화 */
		if(Number(eventValue) > (parseInt(totalPrice) - parseInt(saled) + parseInt("3000"))) {
			alert("쿠폰과 포인트 사용금액이 주문금액보다 많습니다");
			$('#coupon').val("---");
			$('#point').val("0");
			couponValue = 0;
			pointValue = 0;
			eventValue = 0;
		}

		/* 쿠폰/포인트 항목 출력 */
		$('#eventPoint').text(eventValue + "원");
		
		/* 총 금액 : totalPrice(주문금액) - saled(할인금액) - eventValue(쿠폰/포인트) + 3000(배송비) */
		$('#finalSum').text(Number(totalPrice) - Number(saled) - Number(eventValue) + Number(3000) + "원");			
	});	
})

    function orderCheck() {
    	var basketOrderForm = document.getElementById("basketOrderForm"); 	
    	var NAME = document.getElementById("NAME");
    	var ADDRESS1 = document.getElementById("ADDRESS1");
    	var ADDRESS2 = document.getElementById("ADDRESS2");
    	var POSTCODE = document.getElementById("POSTCODE");
    	var MOBILE = document.getElementById("MOBILE");
    	
    	if(confirm("주문하시겠습니까?") == true) {
    				
    		if(NAME.value.trim() == ""){
    			alert("이름을 입력해주세요.");
    			NAME.focus();
    			return false;
    		}
    		if(MOBILE.value.trim() == ""){
    			alert("핸드폰 번호를 입력해주세요.");
    			MOBILE.focus();
    			return false;
    		}
    		if(POSTCODE.value.trim() == ""){
    			alert("우편번호를 입력해주세요.");
    			POSTCODE.focus();
    			return false;
    		}
    		if(ADDRESS1.value.trim() == ""){
    			alert("주소를 입력해주세요.");
    			ADDRESS1.focus();
    			return false;
    		}
    		if(ADDRESS2.value.trim() == ""){
    			alert("상세 주소를 입력해주세요.");
    			ADDRESS2.focus();
    			return false;
    		}
    		
    		basketOrderForm.submit();
    	}
    }
</script>
<script>
window.onload = function() {
	getCount();
};
</script>
<script>
$(document).on("keyup", "input[numberOnly]", function() {$(this).val( $(this).val().replace(/[^0-9]/gi,"") );})
$(document).on("keyup", "input[noSpecial]", function() {$(this).val( $(this).val().replace(/[^ㄱ-힣a-zA-Z0-9@]/gi,"") );})
$(document).on("keyup", "input[noBlank]", function() {$(this).val( $(this).val().replace(/\s/gi,"") );})//문자제한
</script>
<script>
    function numberMaxLength(e){
        if(e.value.length > e.maxLength){
            e.value = e.value.slice(0, e.maxLength);
        }
    } // 글자수제한
</script>
</head>
<body>
<hr>
<form method="post" id="basketOrderForm" action="basketOrder.al">
<div class="col-md-12 ftco-animate">
	<div style="text-align:center">
		<h3> 주문 페이지 </h3>
	</div>
	<br>
	 <div class="container" style="text-align:center">
		<div class="cart-list">
			<table class="table">
			
				<thead class="thead-primary">
						<tr>
						   <!-- <th>&nbsp;</th> -->
						   <th colspan="2">상품명</th>
						   <th>수량</th>
						   <th>할인 금액</th>
						   <th>결제 금액</th>
						</tr>
				</thead>
			<c:if test="${Size>=1}">
				<c:forEach var="i" begin="0" end="${Size-1}">
					<tbody>
						<tr class="text-center">
							
							<td class="image-prod">
								<div id="OPID" class="img" style="background-image:url(img/product-${basketBeanList[i].BID}.jpg);"></div>
							</td>
    						
    						<td class="product-name">
						    	<h3><a href="pDetail.al?PID=${basketBeanList[i].BID}">${basketBeanList[i].BNAME}</a></h3>
						    	<input type="hidden" id="OIDX${i}" name="OIDX" value="${basketBeanList[i].BIDX}">
				          		<input type="hidden" id="OSALE${i}" name="OSALE" value="${basketBeanList[i].BSALE}">
						    </td>
							
							<td>
						        <input type="number" style="width: 3em;" min="0" max="${proInfoList[i].PSTOCK}" id="OCOUNT${i}" value="${basketBeanList[i].BCOUNT}" readonly>
					        </td>
						        
						    <%-- <td>
						        
		    					<b><fmt:formatNumber value="${salePrice}" pattern="#.#" />원</b>
		    					
				          	</td> --%>
				          	<td id="saled${i}" style="color:Crimson">
				          	<fmt:formatNumber value="${(basketBeanList[i].BPRICE*basketBeanList[i].BSALE/100)*basketBeanList[i].BCOUNT}" pattern="#.#"/>원
				          	<c:set var="salePrice" value="${basketBeanList[i].BPRICE * (100-basketBeanList[i].BSALE) * 0.01}" />
				          	<input type="hidden" id="OPRICE${i}" name="OPRICE" value="${basketBeanList[i].BPRICE}"></td>
				          							        
						    <td style="font-weight : bold;">
								<c:set var="total" value="${salePrice * basketBeanList[i].BCOUNT}"/>
								<fmt:parseNumber var="totalPrice" integerOnly="true" value="${total}"/>
								<b><fmt:formatNumber value="${totalPrice}" pattern="#.#"/>원</b>
								<input type="hidden" name="OTOTAL" value="${totalPrice}">
						    </td>
						      
						</tr>
				</c:forEach>
			</c:if>
				<c:if test="${Size<=0}">
					<tr><td style="text-align:center" colspan="9">장바구니에 상품이 없습니다</td></tr>
				</c:if>
			</tbody>
		</table>
</div> <!-- end cart-list div -->
			</div>
	<input type="hidden" id="listSize" name="listSize" value="${Size}">

	<input type="hidden" name="OMAIL" value="${memberBean.EMAIL}">
	<input type="hidden" id="ONAME" value="${memberBean.NAME}">
	<input type="hidden" id="OMOBILE" value="${memberBean.MOBILE}">
	<input type="hidden" id="OPOSTCODE" value="${memberBean.POSTCODE}">
	<input type="hidden" id="OADDRESS1" value="${memberBean.ADDRESS1}">
	<input type="hidden" id="OADDRESS2" value="${memberBean.ADDRESS2}">
	
    			 <br>
    			 <section class="ftco-section" style="margin-left:auto; margin-right:auto; text-align:center;">
		<div class="container" style="margin-left:auto; margin-right:auto; text-align:center;">
			<div class="row justify-content-center">
				<div class="col-xl-7 ftco-animate">
				
					<!-- 주문자 정보 -->
					<h2 class="mb-4 billing-heading">주문자 정보</h2>
					<div class="row slider-text justify-content-center align-items-center">
					
						<!-- 이름 -->
						<div class="form-group">
							<h6 class="mb-4" style="text-align:left;">이름</h6>
							<input type="text" class="form-control" style="width:400px;"
								value="${memberBean.NAME}" readonly>
						</div>
						<div class="w-100"></div>
	
						<!-- 핸드폰 번호 -->
						<div class="form-group">
							<h6 class="mb-4" style="text-align:left;">핸드폰 번호</h6>
							<input type="text" class="form-control"
								style="width:400px;" value="${memberBean.MOBILE}" readonly> 
							<div class="w-100"></div>
						</div>
						<div class="w-100"></div>
						<br/><br/>
					</div>
						
					<hr>
					
					<br><br>
					<!-- 주문자 정보와 동일한지 체크-->
					<div class="row slider-text justify-content-center align-items-center">
						<div class="form-group">
							<h6 class="mb-4" style="text-align:center;">
							주문자 정보와 동일
							<input type="checkbox" style="width:50px;"
								id="infoCheck" name="infoCheck" checked>
							</h6>
						</div>
						<div class="w-100"></div>
					</div>
						
					<hr>
						
					<!-- 받는 사람 정보 -->
					<h2 class="mb-4 billing-heading">배송 정보</h2>
					<div class="row slider-text justify-content-center align-items-center">
					
						<!-- 받는 사람 이름 -->
						<div class="form-group">
							<h6 class="mb-4" style="text-align:left;">받으시는 분</h6>
							<input type="text" id="NAME" name="ONAME"  class="form-control" style="width:400px;"
								value="${memberBean.NAME}">
						</div>
						<div class="w-100"></div>
	
						<!-- 핸드폰 번호 -->
						<div class="form-group">
							<h6 class="mb-4" style="text-align:left;">핸드폰 번호</h6>
							<input type="text" id="MOBILE" name="OMOBILE" class="form-control"
								onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" size="24" style="width:400px;"
								value="${memberBean.MOBILE}" maxlength="11" oninput="numberMaxLength(this);"> 
							<div class="w-100"></div>
							<h6 class="mb-4" style="text-align:left;">'-'는 빼고 숫자만 입력해주세요.</h6>
						</div>
						<div class="w-100"></div>
						<br/><br/>
						
						<!-- 주소 -->
						<h6 class="mb-4" style="text-align:left;">우편번호</h6>
						<div class="w-100"></div>
						<div class="form-group d-flex">
							<input type="text" class="form-control" name="OPOSTCODE" id="POSTCODE" placeholder="우편번호" style="width:270px;"
								value="${memberBean.POSTCODE}" maxlength="7"
								onKeyup="this.value=this.value.replace(/[^0-9]/g,'');">
							<input type="button" class="submit px-3" onclick="sample6_execDaumPostcode()" value="우편번호 찾기">
						</div>
						<div class="w-100"></div>
						<h6 class="mb-4" style="text-align:left;">주소</h6>
						<div class="w-100"></div>
						<div class="form-group">
							<input type="text" name="OADDRESS1" id="ADDRESS1" placeholder="주소" class="form-control" style="width:190px;"
								value="${memberBean.ADDRESS1}">
						</div>
						&emsp;
						<div class="form-group">
							<input type="text" name="OADDRESS2" id="ADDRESS2" placeholder="상세주소" class="form-control" style="width:190px;"
								value="${memberBean.ADDRESS2}">
						</div>			
						<div class="w-100"></div>
					</div>

					<hr>
					<h2 class="mb-4 billing-heading">쿠폰/포인트</h2>
					<div class="row slider-text justify-content-center align-items-center">
					
						<!-- 쿠폰 -->
						<div class="form-group">
							<h6 class="mb-4" style="text-align:left;">쿠폰</h6>
								<div class="select-wrap">
									<select name="coupon" id="coupon" class="form-control" style="width:400px;">			
										<option value="---">---</option>
										<c:if test="${eventBean.COUPON1K == 'Y' }">
											<option id="1K" value="1K">1천원</option>
										</c:if>
										<c:if test="${eventBean.COUPON2K == 'Y' }">
											<option id="2K" value="2K">2천원</option>
										</c:if>
										<c:if test="${eventBean.COUPON3K == 'Y' }">
											<option id="3K" value="3K">3천원</option>
										</c:if>
										<c:if test="${eventBean.COUPON5K == 'Y' }">
											<option id="5K" value="5K">5천원</option>
										</c:if>
										<c:if test="${eventBean.COUPON10K == 'Y' }">
											<option id="10K" value="10K">1만원</option>
										</c:if>
									</select>
								</div>
						</div>
						<div class="w-100"></div>
	
						<!-- 포인트 -->
						<div class="form-group">
							<h6 class="mb-4" style="text-align:left;">포인트</h6>
							<p>현재 보유 포인트는 ${eventBean.PIGI_POINT} Point입니다</p>
							<input type="number" id="point" name="point" class="form-control"
								size="24" style="width:400px;" numberOnly step="100" min="0" max="${eventBean.PIGI_POINT}"
								placeholder="100단위 포인트를 입력해주세요. " maxlength="11" oninput="numberMaxLength(this);"> 
							<input type="hidden" id="maxPoint" value="${eventBean.PIGI_POINT}">
							<div class="w-100"></div>
							<p class="mb-4" style="text-align:left;">포인트는 최대 10만까지 사용할 수 있습니다<br>100이하는 계산되지 않습니다</p>				
						</div>
						<div class="w-100"></div>
						<br/><br/>
					</div>

					<hr>
					
					<!-- 결제 정보 -->
					<h2 class="mb-4 billing-heading">결제 정보</h2>
					
					<br>	
					<div class="container">  
						<div class="cart-total mb-3" style="text-align:center">
	    					<h2>결제 금액</h2><br>
	    					<p class="d-flex">
	    						<span>주문금액</span>
	    						<span id="originalSum"></span>
	    					</p>
	    					<p class="d-flex">
	    						<span>할인금액</span>
	    						<span id="saleSum" style="color:Crimson"></span>
	    					</p>
	    					<p class="d-flex">
	    						<span>쿠폰/포인트</span>
	    						<span id="eventPoint" style="color:Crimson">0원</span>
	    					</p>
	    					<p class="d-flex">
	    						<span>배송비</span>
	    						<span>3000원</span>
	    					</p>
	    					<hr>
	    					<p class="d-flex total-price">
	    						<span>총 금액</span>
	    						<span id="finalSum"></span>
	    					</p>
	    				</div>
	    			</div>
	    			
					<div class="row slider-text justify-content-center align-items-center">
					
						<!-- 결제 방법 -->
						<div class="form-group">
							<h6 class="mb-4" style="text-align:left;">무통장 입금</h6>
							<input type="text" class="form-control" style="width:400px;"
								value="Bank of England 00000001" readonly>
						</div>
						<div class="w-100"></div>
					</div>
					<br>	
					<br>	
			
					<!-- 버튼 -->
					<div style="text-align:center">
						<input type="button" class="btn btn-primary py-3 px-5" onClick="orderCheck()" value="주문하기"/>
							&emsp;&emsp;
						<input type="button" class="btn btn-dark py-3 px-5" onClick="history.back()" value="주문취소"/>
					</div>
					</div>



				</div>
				</div>
			</section>
		<br>
	<br>
<br><br>	
</div>		
</form>


</body>
</html>