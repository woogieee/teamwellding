<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%
   // GNB 번호 (사용자관리)
   request.setAttribute("_gnbNo", 1);
%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<style>
*, ::after, ::before {
   box-sizing: unset;
}
.table-hover th, td{
   border: 1px solid #c4c2c2;
   text-align: center;
}
</style>
<script type="text/javascript" src="../resources/js/jquery.colorbox.js"></script>
<script>

$(document).ready(function(){
	   $("#btn").on("click",function(){
		  
		   $.ajax({
				type:"post",
				url:"mng/payMentProc",
				data:{
					
				},
				 datatype: "JSON",
		   	  beforeSend:function(xhr)
		   	  {
					xhr.setRequestHeader("AJAX", "true");  
		   	  },
		   	  success:function(response)
		   	  {
		   		  if(response.code == 0)
		   		  {
		   			  alert("결제 취소가 승인되었습니다.");
		   			  location.href("/mng/payMentList");
		   		  }
		   		  else if(response.code == 401)
		   		  {
		   			  alert("결제 취소가 신청된 내역이 아닙니다.");
		   		  }
		   		  else if(response.code == 500)
		   		  {
		   			  //파라미터 값이 넘어가지 않음.
		   			  alert("결제 취소 승인 중 오류가 발생했습니다.");
		   		  }
		   		  else
		   		  {
		   			  alert("알 수  없는 오류가 발생했습니다.");
		   		  }
		   	  },
		   	  complete:function(data)
		   	  {
		   		  icia.common.log(data); 
		   	  },
		   	  error:function(xhr, status, error)
		   	  {
		   		  icia.common.error(error);
		   	  }
		     
			});	
		   
	   });
});

function fn_search()
{
	document.searchForm.curPage.value = "1"; //검색한단 이야기는 첨부터 한다는 뜻이라 1부터
	document.searchForm.action = "/mng/payMentList";
}

function fn_paging(curPage)
{
	document.searchForm.curPage.value = curPage; //매개변수로 받은 현재페이지를 가져옴
	document.searchForm.action = "/mng/payMentList";
	document.searchForm.submit();
}

function fn_pageInit() //서치타입과 서치밸유에대한 설정
{
   $("#searchType option:eq(0)").prop("selected", true);//eq(0): 아무것도 선택안함
   $("#searchValue").val("");
   
   fn_search();      
}

</script>
</head>
<body id="school_list">
	
	<jsp:include page="/WEB-INF/views/include/adminNav.jsp" >
       <jsp:param name="userName" value="${wdAdmin.admName}" />
       </jsp:include>
       
<div class="container">
    <div class="row">
    	<div class="col-lg-12" style="width:100%; height:20px;"></div>
    	
        <div class="col-lg-12">       
		   <div id="school_list" style="width:90%; margin:auto; margin-top:5rem;">
	      <div class="mnb" style="display:flex; margin-bottom:0.8rem;">
	         <h2 style="margin-right:auto; color: #525252;">결제 내역 리스트</h2>
	         <form class="d-flex" name="searchForm" id="searchForm" method="post" style="place-content: flex-end;">
	            <select id="status" name="status" style="font-size: 1rem; width: 6rem; height: 3rem;">
	               <option value="">상태</option>
	               <option value="Y" <c:if test="${status == 'Y'}">selected</c:if>>결제완료</option>
	               <option value="N" <c:if test="${status == 'N'}">selected</c:if>>예약완료</option>
	               <option value="C" <c:if test="${status == 'C'}">selected</c:if>>취소대기</option>
	               <option value="D" <c:if test="${status == 'D'}">selected</c:if>>취소완료</option>
	            </select>
	            <select id="searchType" name="searchType" style="font-size: 1rem; width: 8rem; height: 3rem; margin-left:.5rem; ">
	               <option value="">검색타입</option>
	               <option value="1" <c:if test="${searchType == '1'}">selected</c:if>>회원아이디</option>
	            </select>
	            <input name="searchValue" id="searchValue" class="form-control me-sm-2" style="width:15rem; margin-left:.5rem;" type="text" value="${searchValue}">
	            <a class="btn my-2 my-sm-0" href="javascript:void(0)" onclick="fn_search()" style="width:7rem; margin-left:.5rem; background-color: rgb(239, 239, 239); border-color:rgb(118, 118, 118);">조회</a>
	            <input type="hidden" name="curPage" value="" />
	         </form>
	      </div>
	      <div class="school_list_excel">
	         <table class="table table-hover" style="border:1px solid #c4c2c2;">
	            <thead style="border-bottom: 1px solid #c4c2c2;">
	            <tr class="table-thead-main" style="background: #ddd;">
	               <th scope="col" style="width:15%;">아이디</th>
	               <th scope="col">상품</th>
	               <th scope="col">가격</th>
	               <th scope="col">상태</th>
	               <th scope="col">관리</th>
	               
	            </tr>
	            </thead>
	            <tbody>
	            <c:if test="${!empty list}">
	            <c:forEach  var="payment" items="${list}" varStatus="status">
	            <tr>
	                <th scope="row" class="table-thead-sub" style="border: 1px solid #c4c2c2;"><a href="/mng/MngUserUpdate?userId=${payment.userId}" name="userUpdate" id="userUpdate">${payment.userId}</a></th>
	                <td>
	                <c:if test="${!empty payment.hCode}">H</c:if>
	                <c:if test="${!empty payment.sCode}">S</c:if>
	                <c:if test="${!empty payment.dNo }">D</c:if>
	                <c:if test="${!empty payment.mCode}"></c:if>
	                </td>
	                <td>${payment.rezFullPrice}</td>
	                <td><c:if test="${payment.rezStatus == 'Y'}">결제완료</c:if><c:if test="${payment.rezStatus == 'N'}">예약완료</c:if><c:if test="${payment.rezStatus == 'C'}">취소신청</c:if><c:if test="${payment.rezStatus == 'D'}">취소완료</c:if></td>
	                <td><c:if test="${payment.rezStatus == 'C'}"><button onclick="">결제 취소</button></c:if></td>
	            </tr>
	            </c:forEach>
	            </c:if>
	            <c:if test="${empty list}">
	            <tr>
	            	<td colspan="5">등록된 결제 내역 정보가 없습니다.</td>
	            </tr>
	            </c:if>
	            </tbody>
	         </table>
	         <div class="paging-right" style="float:right;">
	            <!-- 페이징 샘플 시작 -->
				<c:if test="${!empty paging}">
	               <!--  이전 블럭 시작 -->
					<c:if test="${paging.prevBlockPage gt 0}"> <!-- 0보다 클때 -->
	                  <a href="javascript:void(0)"  class="btn2 btn-primary" onclick="fn_paging(${paging.prevBlockPage})" title="이전 블럭">&laquo;</a>
					</c:if>
	               <!--  이전 블럭 종료 -->
	               <span>
	               <!-- 페이지 시작 -->
				   <c:forEach var="i" begin="${paging.startPage}" end="${paging.endPage}">
	               		<c:choose>
	                        <c:when test="${i ne curPage}">
	                        <a href="javascript:void(0)" class="btn2 btn-primary" onclick="fn_paging(${i})" style="font-size:14px;">${i}</a>
							</c:when>
							<c:otherwise>
	                        <h class="btn2 btn-primary" style="font-size:14px; font-weight:bold;">${i}</h>
				   			</c:otherwise>
				   		</c:choose>
				   </c:forEach>
	               <!-- 페이지 종료 -->
	               </span>
	               <!--  다음 블럭 시작 -->
	      		   <c:if test="${paging.nextBlockPage gt 0}">
	                  <a href="javascript:void(0)" class="btn2 btn-primary" onclick="fn_paging(${paging.nextBlockPage})" title="다음 블럭">&raquo;</a>
				   </c:if>
	               <!--  다음 블럭 종료 -->
				</c:if>
	            <!-- 페이징 샘플 종료 -->
	         </div>
	      </div>
	   </div>
   </div>
  </div>
</div>

</body>
</html>