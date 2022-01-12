<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">

<head>
    	<%@ include file="/WEB-INF/views/include/head.jsp" %>
    	<link rel="preconnect" href="https://fonts.googleapis.com">
		<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
		<link href="https://fonts.googleapis.com/css2?family=Bitter:ital@0;1&family=The+Nautigal&display=swap" rel="stylesheet">
<script>
$(document).ready(function() {

});
</script>
</head>
    
<body>
    <jsp:include page="/WEB-INF/views/include/navigation.jsp" >
    <jsp:param name="userName" value="${wdUser.userNickname}" />
    </jsp:include>
    
    <div class="page-heading-rent-venue2">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                </div>
            </div>
        </div>
    </div>
    
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12 bcline">
				
				<div class="row">
					<div class="col-lg-1">
					</div>
					<div class="col-lg-10">
						<h2 style="font-family: 'Bitter'; margin-top: 50px; padding-left: 10px;">결제내역</h2>
						<nav class="bcItem">
							<ol class="breadcrumb bc" >
								<li class="breadcrumb-item active">
									<a href="/user/wishlist">장바구니</a>
								</li>
								<li class="breadcrumb-item" >
									<a style="font-size: large; font-weight: bold;">결제내역</a>
								</li>
								<li class="breadcrumb-item">
									<a href="javascript:void(0)" id="cou">쿠폰보유현황</a>
								</li>
								<li class="breadcrumb-item">
									<a href="/user/modify">회원정보수정</a>
								</li>
							</ol>
						</nav>
					
					</div>
					<div class="col-lg-1">
					</div>
					
					<!-- 다음 라인 -->
					<div class="col-lg-1">
					
					</div>
					<!-- 경계선 및 내용 -->
					<div class="col-lg-10 lineListMypage">
					
						
                        <table class="table tableWish">
                            <tr style="border-top: 3px solid #444;">
                                <th>예약번호</th>
                                <th>예약날짜</th>
                                <th>금액</th>
                            </tr>

                        </table>

					
					</div>
					<!-- 경계선 종료 -->
					<div class="col-lg-1"></div>
				`
					<div class="col-lg-1"></div>
					<div class="col-lg-10">
					<!-- 쿠폰 가져오기 -->
					<div class="col-lg-10">
					</div>
                    <tbody>
                        <c:if test="${!empty list}">
                           <c:forEach var="hiBoard" items="${list}" varStatus="status">   
                                <tr>
                                    <td style="text-align:center">
                                        <a href="javascript:void(0)" onclick="fn_view(${hiBoard.bSeq})">
                                            <c:out value="${hiBoard.bSeq}" />
                                        </a>
                                    </td>
                                   	<td style="text-align:left">
                                   		<a href="javascript:void(0)" onclick="fn_view(${hiBoard.bSeq})">${hiBoard.bTitle}</a>
                                   	</td>
                                   	<td style="text-align:center">${hiBoard.adminId}</td>
                                    <td style="text-align:center">${hiBoard.regDate}</td>
                                    <td style="text-align:center"><fmt:formatNumber type="number" maxFractionDigits="3" value="${hiBoard.bReadCnt}" /></td>
                                </tr>
                           </c:forEach>
                        </c:if>      
                    </tbody>
						<div class="rez_sum">
							<button type="button" id="btnComplete" class="btn btn-primary" title="결제내역보러가기" style="border: solid 1px black; background:white; position:relative; top:-18px; color:black;">결제내역 보러가기</button>
							<button type="button" id="btnMyPage" class="btn btn-primary" title="마이페이지" style="border: solid 1px black; background:white; position:relative; top:-18px; color:black;">마이페이지</button>
						</div>
					</div>
					
					<div class="col-lg-1"></div>
				
				</div>
			</div>			
		</div>
	</div>	

		<%@ include file="/WEB-INF/views/include/footer.jsp" %>
  </body>

</html>
