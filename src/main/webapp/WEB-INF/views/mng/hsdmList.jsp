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
      $("#userUpdate").colorbox({
            iframe:true, 
            innerWidth:1235,
            innerHeight:420,
            scrolling:false,
            onComplete:function()
            {
               $("#colorbox").css("width", "1235px");
               $("#colorbox").css("height", "420px");
               $("#colorbox").css("border-radius", "10px");
               
               $('html').css("overflow","hidden");
            } , 
              onClosed: function()
          {
            $('html').css("overflow","auto");
          }  
      });
});

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
    <div class="row" style="width: 100%;">
    	<div class="col-lg-12" style="width:100%; height:80px;"></div>
 <!-- /////////////////////////////////////////// --> 
		<div class="col-lg-12">
			<div class="hsdm_nav">
	             <ul class="hsdm_menu">
	                 <li class="hsem_li sel" id="id1" onclick="classChange(this)"><a class="hsem_a" href="">웨딩홀</a></li>
	                 <li class="hsem_li" id="id2" onclick="classChange(this)"><a class="hsem_a" href="">스튜디오</a></li>
	                 <li class="hsem_li" id="id3" onclick="classChange(this)"><a class="hsem_a" href="">드레스</a></li>
	                 <li class="hsem_li" id="id4" onclick="classChange(this)"><a class="hsem_a" href="">메이크업</a></li>
	             </ul>  
			</div>
		</div>
		
		<!-- 홀 시작 -->
		<div class="col-lg-12" width="100%">
			<ul>

				<li class="wdhth">
					<div class="wdhtitle" style="width:10%;"><p>웨딩홀명</p></div>
					<div class="wdhtitle" style="width:17%;"><p>웨딩홀설명</p></div>
					<div class="wdhtitle" style="width:12%;"><p>주소</p></div>
					<div class="wdhtitle" style="width:7%;"><p>전화번호</p></div>
					<div class="wdhtitle" style="width:7%;"><p>대표이미지명</p></div>
					<div class="wdhtitle" style="width:8%;"><p>홀명</p></div>
					<div class="wdhtitle" style="width:7%;"><p>홀대관비</p></div>
					<div class="wdhtitle" style="width:5%;"><p>1인당식비</p></div>
					<div class="wdhtitle" style="width:5%;"><p>최소인원</p></div>
					<div class="wdhtitle" style="width:5%;"><p>최대수용인원</p></div>
					<div class="wdhtitle" style="width:16%;"><p>설명</p></div>
				</li>
				<li class="wdhtd">
					<div class="wdhcon" style="width:10%;"><p>웨딩홀명</p></div>
					<div class="wdhcon" style="width:17%;"><p>웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명</p></div>
					<div class="wdhcon" style="width:12%;"><p>주소</p></div>
					<div class="wdhcon" style="width:7%;"><p>전화번호</p></div>
					<div class="wdhcon" style="width:7%;"><p>대표이미지명</p></div>
					<div class="wdhcon" style="width:8%;"><p>홀명</p></div>
					<div class="wdhcon" style="width:7%;"><p>홀대관비</p></div>
					<div class="wdhcon" style="width:5%;"><p>1인당식비</p></div>
					<div class="wdhcon" style="width:5%;"><p>최소인원</p></div>
					<div class="wdhcon" style="width:5%;"><p>최대수용인원</p></div>
					<div class="wdhcon" style="width:16%;"><p>설웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명명</p></div>
				</li>

			</ul>
		</div>
		<!-- 홀 끝 -->
		
		<!-- 스튜디오 시작 -->
		<div class="col-lg-12" width="100%">
			<ul>
				<li class="wdhth">
					<div class="wdhtitle" style="width:16%;"><p>스튜디오명</p></div>
					<div class="wdhtitle" style="width:18%;"><p>주소</p></div>
					<div class="wdhtitle" style="width:13%;"><p>전화번호</p></div>
					<div class="wdhtitle" style="width:10%;"><p>스튜디오 가격</p></div>
					<div class="wdhtitle" style="width:10%;"><p>대표이미지명</p></div>
					<div class="wdhtitle" style="width:32%;"><p>스튜디오설명</p></div>
				</li>
				<li class="wdhtd">
					<div class="wdhcon" style="width:16%;"><p>스튜디오명</p></div>
					<div class="wdhcon" style="width:18%;"><p>주소</p></div>
					<div class="wdhcon" style="width:13%;"><p>전화번호</p></div>
					<div class="wdhcon" style="width:10%;"><p>스튜디오 가격</p></div>
					<div class="wdhcon" style="width:10%;"><p>대표이미지명</p></div>
					<div class="wdhcon" style="width:32%;"><p>설웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명명</p></div>
				</li>
			</ul>
		</div>
		<!-- 스튜디오 끝 -->
		
		<!-- 드레스 시작 -->
		<div class="col-lg-12" width="100%">
			<ul>
				<li class="wdhth">
					<div class="wdhtitle" style="width:9%;"><p>드레스샵 이름</p></div>
					<div class="wdhtitle" style="width:13%;"><p>주소</p></div>
					<div class="wdhtitle" style="width:8%;"><p>전화번호</p></div>
					<div class="wdhtitle" style="width:7%;"><p>대표 이미지명</p></div>
					<div class="wdhtitle" style="width:15%;"><p>업체설명</p></div>
					<div class="wdhtitle" style="width:14%;"><p>드레스명</p></div>
					<div class="wdhtitle" style="width:8%;"><p>드레스 이미지명</p></div>
					<div class="wdhtitle" style="width:8%;"><p>가격</p></div>
					<div class="wdhtitle" style="width:17%;"><p>드레스설명</p></div>
				</li>
				<li class="wdhtd">
					<div class="wdhcon" style="width:9%;"><p>업체이름</p></div>
					<div class="wdhcon" style="width:13%;"><p>주소</p></div>
					<div class="wdhcon" style="width:8%;"><p>전화번호</p></div>
					<div class="wdhcon" style="width:7%;"><p>대표이미지명</p></div>
					<div class="wdhcon" style="width:15%;"><p>대웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명표이미지명</p></div>
					<div class="wdhcon" style="width:14%;"><p>드레스명</p></div>
					<div class="wdhcon" style="width:8%;"><p>드레스 이미지명</p></div>
					<div class="wdhcon" style="width:8%;"><p>가격</p></div>
					<div class="wdhcon" style="width:17%;"><p>웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명</p></div>
				</li>
			</ul>
		</div>
		<!-- 드레스 끝 -->
		
		<!-- 메이크업 시작 -->
		<div class="col-lg-12" width="100%">
			<ul>
				<li class="wdhth">
					<div class="wdhtitle" style="width:14%;"><p>메이크업샵 이름</p></div>
					<div class="wdhtitle" style="width:18%;"><p>주소</p></div>
					<div class="wdhtitle" style="width:13%;"><p>전화번호</p></div>
					<div class="wdhtitle" style="width:10%;"><p>대표이미지명</p></div>
					<div class="wdhtitle" style="width:10%;"><p>기본가격</p></div>
					<div class="wdhtitle" style="width:24%;"><p>설명</p></div>
					<div class="wdhtitle" style="width:10%;"><p>추가인원당 가격</p></div>
				</li>
				<li class="wdhtd">
					<div class="wdhcon" style="width:14%;"><p>메이크업샵이름</p></div>
					<div class="wdhcon" style="width:18%;"><p>주소</p></div>
					<div class="wdhcon" style="width:13%;"><p>전화번호</p></div>
					<div class="wdhcon" style="width:10%;"><p>대표이미지명</p></div>
					<div class="wdhcon" style="width:10%;"><p>기본가격</p></div>
					<div class="wdhcon" style="width:24%;"><p>설웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명웨딩홀설명명</p></div>
					<div class="wdhcon" style="width:10%;"><p>추가인원당 가격</p></div>
				</li>
			</ul>
		</div>
		<!-- 메이크업 끝 -->

			<div class="col-lg-12">
				<div>
					<form id="subscribe" action="" method="get">
						<div class="row" style="width: 100%;">
							<div class="col-lg-12">
									<button type="button" id="btnWrite" class="hsdm_btn">추가</button>
							</div>
						</div>
					</form>
				</div>
			</div>
			
			<div class="col-lg-12">
                <div class="pagination">
					<ul class="pagination justify-content-center">
						<c:if test="${!empty paging}">
							<c:if test="${paging.prevBlockPage gt 0}">	<!-- prevBlockPage이 0 보다 크냐 -->
							<li class="page-item"><a class="page-link" href="javascript:void(0)" onclick="fn_list(${paging.prevBlockPage})">이전</a></li>
							</c:if>
							<c:forEach var="i" begin="${paging.startPage}" end="${paging.endPage}">
								<c:choose>
									<c:when test="${i ne curPage}">
										<li class="page-item"><a class="page-link" href="javascript:void(0)" onclick="fn_list(${i})">${i}</a></li>
									</c:when>
									<c:otherwise>
										<li class="page-item active"><a class="page-link" href="javascript:void(0)" style="cursor:default">${i}</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:if test="${paging.nextBlockPage gt 0}">         
								<li class="page-item"><a class="page-link" href="javascript:void(0)" onclick="fn_list(${paging.nextBlockPage})">다음</a></li>
							</c:if>       
						</c:if> 
					</ul>
                  </div>
              </div>
		
  </div>
</div>

</body>
</html>