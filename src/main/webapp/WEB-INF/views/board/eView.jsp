<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Gamja+Flower&display=swap"
	rel="stylesheet">
<script>
	$(document).ready(function() {
		$("#btnList").on("click", function() {
			document.eBoardForm.action = "/board/eBoard";
			document.eBoardForm.submit();
		});
	});

</script> 	
<style>
#img{width:100%;}
</style>

</head>
<body>
	<jsp:include page="/WEB-INF/views/include/navigation.jsp">
		<jsp:param name="userName" value="${wdUser.userNickname}" />
	</jsp:include>

	<section class="top">
		<div class="back"></div>
	</section>

    <!-- ***** About Us Page ***** -->
    <div class="page-heading-shows-events5">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h2>Event</h2>
                    <!-- span>Check out upcoming and past shows & events and grab your ticket right now.</span -->
                </div>
            </div>
        </div>
    </div>
    
<div class="container">
   <div class="row" style="margin-right:0; margin-left:0;">
      <table class="table">
         <thead>
            <tr class="table-active dongdong2">
               <td scope="col" style="width:60%">
                  <c:out value="${eBoard.eBTitle}"/>
               </td>
               
               <td scope="col" style="width:40%" class="text-right">
                                         조회 : <fmt:formatNumber type="number" maxFractionDigits="3" value="${eBoard.eBReadCnt}" />
               </td>
            </tr>
               <td scope="col" style="width:60%">
               	작성자 : <c:out value="${eBoard.adminId}"/>
               </td>
               <td scope="col" style="width:40%" class="text-right">
                  <div>${eBoard.regDate}</div>
               </td>
         </thead>
         <tbody>
            <tr>
               <td colspan="2" style="text-align:center">
               <div style="padding:10px"><pre><img src="/resources/board/${eBoard.eBImgName}" id="img"><br /><br /><c:out value="${eBoard.eBContent}" /></pre></div></td>
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

	<h2 class="e_title">EVENT</h2>
	<p style="text-align: center; margin-bottom: 20px;">웰딩 이벤트를 확인해보세요!</p>
	<br />

	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<table class="table">
					<thead>
						<tr class="dongdong2">
							<td
								style="width: 60%; padding-left: 25px; font-size: 18px; color: #222; font-weight: 600;">
								<c:out value="${eBoard.eBTitle}" />
							</td>

							<td
								style="width: 40%; padding-right: 25px; color: #444; font-size: 16px;"
								class="text-right">${eBoard.regDate}</td>
						</tr>
						<tr style="height: 46px;">
							<td
								style="width: 60%; padding-left: 20px; font-size: 15px; color: #666;">
								<c:out value="${eBoard.adminId}" />
							</td>
							<td
								style="width: 40%; padding-right: 20px; font-size: 15px; color: #666;"
								class="text-right">
								<div>
									조회 :
									<fmt:formatNumber type="number" maxFractionDigits="3"
										value="${eBoard.eBReadCnt}" />
								</div>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="2" style="text-align: center; width: 100%; border-bottom: 1px solid #555;">
								<div style="padding: 10px">
									<div class="e_imgbox">
										<img src="/resources/board/${eBoard.eBImgName}"> <br />
										<br />
										<c:out value="${eBoard.eBContent}" />
									</div>
								</div>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2" style="padding: 20px 0 50px;">
							<button type="button" id="btnList"
									class="w-btn w-btn-green2" style="float: right">리스트</button></td>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>

	<form name="eBoardForm" id="eBoardForm" method="post">
		<input type="hidden" name="eBSeq" value="" /> <input type="hidden"
			name="searchType" value="${searchType}" /> <input type="hidden"
			name="searchValue" value="${searchValue}" /> <input type="hidden"
			name="curPage" value="${curPage}" />
	</form>

	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>