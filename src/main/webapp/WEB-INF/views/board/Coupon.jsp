<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp" %>

<script>
$(document).ready(function(){
	$("#btnSearch").on("click", function(){
		//새로 조회버튼을 누를 때에는 신규로 넣은 값을 가져가야 함.
		document.bbsForm.bSeq.value = "";
		document.bbsForm.searchType.value = $("#searchTypeR").val();
		document.bbsForm.searchValue.value = $("#searchValueR").val();
		//조회를 했을 때 무조건 1페이지로 가야 함. 결색 결과가 몇페이지까지 나올지 모르니깐
		document.bbsForm.curPage.value = 1;
		document.bbsForm.action = "/board/fBoard";
		document.bbsForm.submit();
	});
	
	$("#btnWrite").on("click",function(){
		document.bbsForm.bSeq.value = "";
		document.bbsForm.action = "/board/fBoardWrite";
		document.bbsForm.submit();
	});

});

</script>
</head>
<body>

    <div class="shows-events-schedule2">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-heading">
                        <div class="category2">
                            <!-- h2>wellding Free Board</h2 -->
                            <p>내 쿠폰</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>        

    <div class="shows-events-schedule2">
        <div class="container">
            <div class="row">
              <div class="col-lg-12">
                  <ul style="cursor:default;">
                      <li class="coupon_title" style="background: #efefef;" style="cursor:default;">
                          <div class="row" style="cursor:default;">
                          	<div class="col-lg-1" style="text-align: center;cursor:default;">
                                  <div class="divB_tb"><span>번호</span></div>
                              </div>
                              <div class="col-lg-3" style="text-align: center;cursor:default;">
                                  <div class="divB_tb"><span>쿠폰 명</span></div>
                              </div>
                              <div class="col-lg-4" style="text-align: center;cursor:default;">
                                  <div class="divB_tb"><span>쿠폰 내용</span></div>
                              </div>
                              <div class="col-lg-2" style="text-align: center;cursor:default;">
                                  <div class="divB_tb"><span>할인가</span></div>
                              </div>
                              <div class="col-lg-2" style="text-align: center;cursor:default;">
                                      <div class="divB_tb" ><span>사용여부</span></div>
                              </div>
                          </div>
                      </li>
                      
                    <c:forEach var="coupon" items="${list}" varStatus="status">                                
                      <li style="cursor:default;">
                           <div class="row">
                           	   <div class="col-lg-1">
                                   <div class="divB_tb2 tbstyle"><span>${coupon.cCode}</span></div>
                               </div>
                               <div class="col-lg-3">
                                   <div class="divB_tb2"><span>${coupon.cName}</span></div>
                               </div>
                               <div class="col-lg-4">
                                   <div class="divB_tb2 tbstyle2"><span>${coupon.cContent}</span></div>
                               </div>
                               <div class="col-lg-2">
                                   <div class="divB_tb2 tbstyle5"><span>
                                   <fmt:formatNumber type="number" maxFractionDigits="3" value="${coupon.cPrice}" />원 할인
                                   </span></div>
                               </div>
                               <div class="col-lg-2">
                               <c:if test="${coupon.cStatus eq 'Y'}">
                                   <div class="divB_tb2 tbstyle4 tbstyle6">
                                       <span>사용 완료</span>
                                   </div>
                               </c:if>
                               <c:if test="${coupon.cStatus eq 'N'}">
                                   <div class="divB_tb2 tbstyle4 tbstyle7">
                                       <span>사용 가능</span>
                                   </div>
                               </c:if>
                              </div>
                            </div>
                      </li>
                    </c:forEach>
                  </ul>
                  
                  <div class="c_btnbox">
                  	<button type="button" id="closebtn" class="btn btn-secondary" onclick="self.close();">닫기</button>
                  </div>
                  
              </div>
          </div>
      </div>
  </div>

</body>
</html>