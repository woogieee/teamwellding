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
<script>
function fn_search()
{
	document.searchForm.curPage.value = "1"; //검색한단 이야기는 첨부터 한다는 뜻이라 1부터
	document.searchForm.action = "/mng/userList";
	document.searchForm.submit();
}

function fn_paging(curPage)
{
	document.searchForm.curPage.value = curPage; //매개변수로 받은 현재페이지를 가져옴
	document.searchForm.action = "/mng/userList";
	document.searchForm.submit();
}

</script>
</head>
<body id="school_list">
	
	<jsp:include page="/WEB-INF/views/include/adminNav.jsp" >
       <jsp:param name="userName" value="${wdAdmin.admName}" />
       </jsp:include>
       
<div class="container">
    <div class="row">
    	<div class="col-lg-12" style="width:100%; height:80px;"></div>
 <!-- /////////////////////////////////////////// --> 
		<div class="col-lg-12">
			<div class="hsdm_nav">
	             <ul class="hsdm_menu">
	                 <li class="hsem_li"><a class="hsem_a" href="">웨딩홀</a></li>
	                 <li class="hsem_li"><a class="hsem_a" href="">스튜디오</a></li>
	                 <li class="hsem_li"><a class="hsem_a" href="">드레스</a></li>
	                 <li class="hsem_li"><a class="hsem_a" href="">메이크업</a></li>
	             </ul>  
			</div>
		</div>
		

  </div>
</div>

</body>
</html>