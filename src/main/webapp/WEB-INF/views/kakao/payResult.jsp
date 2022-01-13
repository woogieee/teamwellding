<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" href="/resources/css/progress-bar.css" type="text/css">
<script type="text/javascript">
$(document).ready(function() {
   
   $("#btnClose").on("click", function() {
      
      opener.movePage();
      window.close();
   });
});
</script>
</head>
<body>
<div id="kakaopay_con">

<c:choose>
   <c:when test="${!empty kakaoPayApprove}">
   	
   	<!--  div id="nav"></div-->
      <div id="kakaopayh5">
      <img src="../resources/images/kakaopay.jpg" width="200" height="auto"/></div>
	<div id="text">결제완료</div>  
      <div id="margin">
      <div id="approved_at">결제일시: ${kakaoPayApprove.approved_at}</div> <br/>
      <div id="order_id">주문번호: ${kakaoPayApprove.partner_order_id} </div><br/>
      <div id="item_name">상품명  :  ${kakaoPayApprove.item_name}</div><br/>
      <div id="quantity">상품수량: ${kakaoPayApprove.quantity} </div><br/>
      <div id="amount_total">결제금액: ${kakaoPayApprove.amount.total}</div><br/>
      <div id="payment_method_type">결제방법: ${kakaoPayApprove.payment_method_type}</div><br/>    
      <button id="btnClose" type="button">닫기</button>
      </div>
      
   </c:when>
   
   <c:otherwise>
      <h2>카카오페이 결제중 오류가 발생하였습니다.</h2>
   </c:otherwise>
</c:choose>
</div>


 
<form name="kakaoForm" id="kakaoForm" method="post" action="/kakao/payResult">
<<<<<<< HEAD
   <input type="hidden" name="orderId" id="orderId" value="${orderId}" />
   <input type="hidden" name="tId" id="tId" value="${tId}" />
   <input type="hidden" name="userId" id="userId" value="${userId}" />
   <input type="hidden" name="pgToken" id="pgToken" value="" />
   <input type="hidden" name="cCode" id="cCode" value="${cCode}" />   <!-- 동욱 추가 -->
=======
	<input type="hidden" name="orderId" id="orderId" value="${orderId}" />
	<input type="hidden" name="tId" id="tId" value="${tId}" />
	<input type="hidden" name="userId" id="userId" value="${userId}" />
	<input type="hidden" name="pgToken" id="pgToken" value="" />
	<input type="hidden" name="cCode" id="cCode" value="${cCode}" />	<!-- 동욱 추가 -->
	<input type="hidden" name="rezNo" id="rezNo" value="${rezNo}" />
    <input type="hidden" name="rezFullPrice" id="rezFullPrice" value="${rezFullPrice}" />
>>>>>>> 6f0df56216215c5e08a6fccf8ed7b696e8f294c0
</form>
</body>
</html>