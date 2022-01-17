<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/include/head2.jsp"%>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Gamja+Flower&display=swap"
	rel="stylesheet">
<style>
.Wtitle {
	font-family: 'Gamja Flower', cursive;
	font-size: 64px;
	text-align: center;
}
</style>
<script>
$(document).ready(function(){
	
	    
});

</script>
</head>
<body id="school_list" class="light-theme || dark-theme">

	<div class="container">
		<div class="row">
		
			
			<div class="col-lg-12">
			
				<table class="table">
					<thead>
						<tr class="dongdong2">
							<td style="width: 60%; padding-left: 25px; font-size: 18px; color: #222; font-weight: 600;">
								<c:out value="${wdFBoard.bTitle}" />
							</td>
							<td style="width: 40%; padding-right: 25px; color: #444; font-size: 16px; text-align: right;">
								${wdFBoard.regDate}</td>
						</tr>
						<tr style="height: 46px;">
							<td style="width: 60%; padding-left: 20px; font-size: 15px; color: #666;">
								<c:out value="${wdFBoard.userNickname}" />
							</td>
							<td style="width: 40%; padding-right: 20px; font-size: 15px; color: #666;"
								class="text-right">
									조회 : <fmt:formatNumber type="number" maxFractionDigits="3" value="${wdFBoard.bReadCnt}" />
							</td>
						</tr>
					</thead>
					<tbody>

					<c:if test="${!empty wdFBoard.wdBoardFile}">
						<tr>
							<td colspan="2" style="text-align: center; padding-bottom: 10px; background:#efefef;">
								<!-- 첨부파일은 있을 때만 보여주면 됨 -->
									<div style="width: 100%; border:none; font-size: 14px; text-align: right; padding-right: 10px; padding-top: 10px;">
										<!-- GET방식으로 넘어감 --> 
										<a href="/board/download?bSeq=${wdFBoard.wdBoardFile.bSeq}" style="color: #0080ff;">
											[첨부파일]&nbsp; ${wdFBoard.wdBoardFile.fileOrgName}
										</a>
									<div>
							</td>
						</tr>
					</c:if>
						<tr>
							<td colspan="2" style="text-align: center">	
								<div style="padding: 30px 20px; text-align: left; font-size: 16px;">
										<c:out value="${wdFBoard.bContent}" />
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>

			

			</div>

		</div>
	</div>


	<form name="bbsForm" id="bbsForm" method="post">
		<input type="hidden" name="bSeq" value="${bSeq}" /> 
		<input type="hidden" name="searchType" value="${searchType}" /> 
		<input type="hidden" name="searchValue" value="${searchValue}" /> 
		<input type="hidden" name="curPage" value="${curPage}" />
	</form>

</body>
</html>