<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<script type="text/javascript" src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
<script>
$(document).ready(function(){

	//다른화보보기 슬라이더
	$('.post-wrapper').slick({
		  slidesToShow: 1,
		  slidesToScroll: 1,
		  autoplay : true,			// 자동 스크롤 사용 여부
		  autoplaySpeed : 2000, 		// 자동 스크롤 시 다음으로 넘어가는데 걸리는 시간 (ms)
		  pauseOnHover : true,		// 슬라이드 이동	시 마우스 호버하면 슬라이더 멈추게 설정
		  prevArrow : "<button type='button' class='slick-prev'></button>",		// 이전 화살표 모양 설정
		  nextArrow : "<button type='button' class='slick-next'></button>"		// 다음 화살표 모양 설정
		});
	$(".btnListt").on("click", function(){
		document.bbsForm.action = "/hsdm/studio";
		document.bbsForm.submit();
	});
	
	//홀 담기 버튼 시작
	$("#chae").on("click",function(){
		
		if($("#year").val() == "")
		{
			alert("예약할 연도를 입력해주세요.");
			return;
		}
		else if($("#month").val() == "")
		{
			alert("예약할 달을 입력해주세요.");
			return;
		}
		else if($("#day").val() == "")
		{
			alert("예약할 날짜를 입력해주세요.");
			return;
		}
		
		else if(confirm("해당 스튜디오를 장바구니에 담으시겠습니까?"))
		{
			//ajax통신 시작
			$.ajax({
			type:"POST",
			url:"/hsdm/studioRezProc",
			data:
			{
				sCode: $("#sCode").val(),
				year: $("#year").val(),
				month:$("#month").val(),
				day:$("#day").val()
			},
			datatype:"JSON",
			beforeSend:function(xhr){
				xhr.setRequestHeader("AJAX", "true");
			},
			success:function(response){
				if(response.code == 0)
				{
					alert("장바구니에 해당 상품을 담았습니다.");
					if(confirm("장바구니로 이동하시겠습니까?"))
					{
						location.href = "/user/wishlist";
					}
				}
				else if(response.code == 402)
				{
					alert("스튜디오 예약날짜를 입력해주세요.");
				}
				else if(response.code == 403)
				{
					alert("서버와의 연결 상태를 확인해주세요.");
				}
				else if(response.code == 502)
				{
					alert("장바구니에 이미 다른 홀이 담겨 있습니다.");
					if(confirm("장바구니로 이동하시겠습니까?"))
					{
						location.href = "/user/wishlist";
					}
				}
				else
				{
					alert("장바구니에 상품을 담는 중 오류가 발생했습니다.");
				}
			},
			complete:function(data){
				icia.common.log(data);
			},
			error:function(xhr, status, error)
			{
				icia.common.error(error);
			}
			});
			//ajax통신 종료
		}
		
	});
	//홀 담기 버튼 종료
	
});
</script>
</head> 
<body>
	<!-- 메뉴바 시작 -->
    	<jsp:include page="/WEB-INF/views/include/navigation.jsp" >
    	<jsp:param name="userName" value="${wdUser.userNickname}" />
    	</jsp:include>
	<!-- 메뉴바 종료 -->
<!-- ############################ 여기부터 내용 시작 ############################ -->
<section class="top">
	<div class="back"></div>
</section>
<c:if test="${!empty wdStudio}">
<div class="big_title">
</div>
    <div class="ticket-details-page">
        <div class="container">
            <div class="row">
                <div class="col-lg-8">
                    <div class="left-image2">
                        <img src="../resources/images/studio/${wdStudio.sImgname}" alt="">
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="com_detail9">
                        <div class="right-content2">
                            <h4>
                            	<c:out value="${wdStudio.sName}" />
				             	<button type="button" id="btnList" class="btnListt listButton3">
				        			<img src="../resources/images/icons/leftButton.png" class="listIcon" alt="리스트" width="32" height="32">
				        		</button>
                            </h4>

                            <span><c:out value="${wdStudio.sContent}" /></span>
                            <ul  class="loc_li">
			                    <li><i class="fa fa-map-marker"></i> <c:out value="${wdStudio.sLocation}" /></li>
			                    <li><p style="margin-right:7px">Tel.</p><p style="font-weight:700"><c:out value="${wdStudio.sNumber}" /></p></li>
			                </ul>
                            <div class="det_price9">
	                            <ul>
	                                <li class="dis_price">
	                            		<div class="discount"><c:out value="${wdStudio.sDiscount}" />%</div>
		                                <div class="price"><fmt:formatNumber type="number" maxFractionDigits="3" value="${wdStudio.sPrice}" /></div>
	                                </li>
	                            </ul>
	                            <div class="dis-price"><fmt:formatNumber type="number" maxFractionDigits="0" value="${wdStudio.sPrice * (1-wdStudio.sDiscount*0.01)}" />원</div>
                            </div>
                            <div class="banner2">
                                <!--img src="../resources/images/tour.png" width="100%"-->
                            </div>
                            
                            <!--  스튜디오 예약일 추가 시작 -->
							<dl class="st_dl">
								 <dt class="st_dt">스튜디오 예약일&nbsp;:</dt>
				                  <dd class="date st_dd">
									<select id="year" class="year select_date">
										<option value="">년도</option>
										<option value="2022">2022</option>
										<option value="2023">2023</option>
									</select> 
									<select id="month" class="month select_date2">
										<option value="">월</option>
										<option value="01">1</option>
										<option value="02">2</option>
										<option value="03">3</option>
										<option value="04">4</option>
										<option value="05">5</option>
										<option value="06">6</option>
										<option value="07">7</option>
										<option value="08">8</option>
										<option value="09">9</option>
										<option value="10">10</option>
										<option value="11">11</option>
										<option value="12">12</option>
									</select>
									<select id="day" class="day select_date2">
										<option value="">일</option>
										<option value="01">1</option>
										<option value="02">2</option>
										<option value="03">3</option>
										<option value="04">4</option>
										<option value="05">5</option>
										<option value="06">6</option>
										<option value="07">7</option>
										<option value="08">8</option>
										<option value="09">9</option>
										<option value="10">10</option>
										<option value="11">11</option>
										<option value="12">12</option>
										<option value="13">13</option>
										<option value="14">14</option>
										<option value="15">15</option>
										<option value="16">16</option>
										<option value="17">17</option>
										<option value="18">18</option>
										<option value="19">19</option>
										<option value="20">20</option>
										<option value="21">21</option>
										<option value="22">22</option>
										<option value="23">23</option>
										<option value="24">24</option>
										<option value="25">25</option>
										<option value="26">26</option>
										<option value="27">27</option>
										<option value="28">28</option>
										<option value="29">29</option>
										<option value="30">30</option>
										<option value="31">31</option>
									</select>
								</dd>
							</dl>
							<!-- 스튜디오 예약일 추가 끝 -->
							
                            <div class="main-dark-button2"><a href="#" id="chae">이 스튜디오 담기</a></div>
                        </div>
                    </div>
                </div>
                
                <div class="col-lg-12">
                	<div class="det_navi2">
                		<ul>
                			<li><a href=""><c:out value="${wdStudio.sName}" />의 다른 화보 보기</a></li>
                			<li><a href="">이용 후기</a></li>
                		</ul>
                	</div>
                </div>

			    <div class="col-lg-12">
				   <div class="post-slider">
				        <div class="post-wrapper">
				        	<c:forEach var="i" items="${subImg }" varStatus="status">
				          		<div class="post"><img src="../resources/images/studio_det/${i}" alt=""></div>
				            </c:forEach>
				        </div>
					</div>
			    </div>
			    
				<div class="col-lg-12" style="width: 100%; height: 1px; background:#eee; margin-top:50px;"></div>
            	<div class="alignleft2">
	             	<button type="button" id="btnList" class="btnListt listButton">
	        			<img src="../resources/images/icons/leftButton.png" class="listIcon" alt="리스트" width="32" height="32">
	        		</button>
            	</div>
            	
            </div>
        </div>

    </div>
</c:if>

<form name="bbsForm" id="bbsForm" method="post">
   <input type="hidden" name="sCode" id="sCode" value="${sCode}" />
   <input type="hidden" name="searchType" value="${searchType}" />
   <input type="hidden" name="searchValue" value="${searchValue}" />
   <input type="hidden" name="curPage" value="${curPage}" />
</form>

    
<!-- ############################ 여기까지 내용 끝 ############################ -->
	<!-- *** 욱채수정Footer 시작 *** -->
	<%@ include file="/WEB-INF/views/include/footer.jsp" %>
	<!-- *** 욱채수정Footer 종료 *** -->
</body>
</html>