<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gamja+Flower&display=swap" rel="stylesheet">
<style>
.Wtitle
{
	/*font-family: 'Gamja Flower', cursive;*/
	font-size: 40px;
	text-align: center;
	padding-top: 20px;

}
</style>
<script>
$(document).ready(function(){
	$("#btnList").on("click", function(){
		document.bbsForm.action = "/board/nBoard";
		document.bbsForm.submit();
	});
});
</script>
</head>
<body>
   	<jsp:include page="/WEB-INF/views/include/navigation.jsp" >
       <jsp:param name="userName" value="${wdUser.userNickname}" />
       </jsp:include>
       
    <div class="page-heading-rent-venue">
        <div class="container">
            <div class="row">
            </div>
        </div>
    </div>
    <br />
    <h2 class="Wtitle">Wellding Notice</h2>
    <p style="text-align:center">우리들의 웨딩이야기를 지금 들려드려요</p>
    <br />
<div class="container">
   <div class="row" style="margin-right:0; margin-left:0;">
      <table class="table">
         <thead>
            <tr class="dongdong">
               <td style="width: 60%; padding-left: 25px; font-size: 18px; color: #222; font-weight: 600;">
                  <c:out value="${nBoard.bTitle}"/>
               </td>
               <td scope="col" style="width: 40%; padding-right: 25px; color: #444; font-size: 16px;" class="text-right">
                                         조회 : <fmt:formatNumber type="number" maxFractionDigits="3" value="${nBoard.bReadCnt}" />
               </td>
            </tr>
               <td style="width: 60%; padding-left: 20px; font-size: 15px; color: #666;">
               	작성자 : <c:out value="${nBoard.adminId}"/>
               </td>
               <td style="width: 40%; padding-right: 20px; font-size: 15px; color: #666;" class="text-right">
                  <div>${nBoard.regDate}</div>
               </td>
         </thead>
         <tbody>
            <tr>
               <td colspan="2" style="text-align:center">
               <div style="padding:10px"><div><c:out value="${nBoard.bContent}" /></div></div></td>
            </tr>
         </tbody>
         <tfoot>
         <tr>
               <td colspan="2"><button type="button" id="btnList" class="w-btn w-btn-green2" style="float: right">리스트</button></td>
         </tr>
         </tfoot>
      </table>
   </div>
</div>

<form name="bbsForm" id="bbsForm" method="post">
   <input type="hidden" name="bSeq" value="${bSeq}" />
   <input type="hidden" name="searchType" value="${searchType}" />
   <input type="hidden" name="searchValue" value="${searchValue}" />
   <input type="hidden" name="curPage" value="${curPage}" />
</form>

 <!-- *** 욱채수정Footer 시작 *** -->
	<%@ include file="/WEB-INF/views/include/footer.jsp" %>
 <!-- *** 욱채수정Footer 종료 *** -->
</body>
</html>