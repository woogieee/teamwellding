<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>웨딩플래너 상세보기</title>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
</head>
<body>
    <div class="gosu_page">
        <div class="container">
            <div class="row">
                <div class="col-lg-4">
<c:if test="${!empty list}">
	<c:forEach var="wdExpert" items="${list}" varStatus="status">
                    <div class="left-side">
                        <img src="../resources/images/gosu/${wdExpert.eImgname}" alt="">
                        
                        <div class="com_detail8">
	                        <div class="right-content2">
	                            <h4 class="h44"><c:out value="${wdExpert.eName}" />  플래너</h4>
	                            <span><c:out value="${wdExpert.eContent}" /> </span>
	                            <!--ul  class="loc_li">
				                    <li>입력</li>
				                    <li><p style="margin-right:7px">Tel.</p><p style="font-weight:700">플래너 연락처?</p></li>
				                </ul>-->
	                           
	                            <div class="main-dark-button4"><a href="mailto:${wdExpert.eEmail}">직접 문의하기</a></div>
	                        </div>
                    	</div>
                    </div>
	</c:forEach>
</c:if>                    
                </div>
                
               <div class="col-lg-8">
                 <div class="rent-venue-application">
                    <div class="heading-text">
                        <h4>견적 요청하기</h4>
                    </div>
                    <div class="contact-form">
                        <form id="contact" action="/board/send.do" method="post">
                          <div class="row">
                            <div class="col-lg-12">
                              <fieldset>
                                <input name="senderName" type="text" id="senderName" placeholder="이름을 입력하세요*" required="" />
                              </fieldset>
                            </div>
                            <div class="col-lg-12">
                              <fieldset>
                                <input name="senderMail" type="text" id="senderMail" pattern="[^ @]*@[^ @]*" placeholder="이메일을 입력하세요* ex)id@email.com" required="" />
                              </fieldset>
                            </div>
                            <div class="col-lg-12">
                              <fieldset>
                                <input name="senderNumber" type="text" id="senderNumber" placeholder="연락처를 입력하세요* ex)010-0000-0000" required="" />
                              </fieldset>
                            </div>
                            <!--div class="col-lg-12">
                              <fieldset>
                                <input name="subject" type="text" id="subject" placeholder="제목을 입력하세요*" required="" />
                              </fieldset>
                            </div-->
                            <div class="col-lg-12">
                              <fieldset>
                                <textarea name="message" rows="6" id="message" placeholder="문의내용을 입력해주세요" required=""></textarea>
                              </fieldset>
                            </div>
                            <div class="col-lg-12">
                              <fieldset>
                              	<input type="hidden" name="receiveMail" id="receiveMail" value="${wdExpert.eEmail}"/> <!-- 메일을 받을 플래너의 메일주소 -->
                                <button type="submit" id="form-submit" class="main-dark-button">견적 요청하기</button>
                              	<span style="color:red">${message}</span>
                              </fieldset>
                            </div>
                          </div>
                        </form>
                    </div>
                   </div>
                </div>
                
                
             </div>
          </div>
      </div>
    
    <!-- jQuery -->
    <script src="../resources/js/jquery-2.1.0.min.js"></script>
    <script type="text/javascript" src="/resources/js/jquery.colorbox.js"></script>
   
    
    <!-- 보현추가 -->
	<script type="text/javascript" src="../resources/js/slick.min.js"></script>

    <!-- Bootstrap -->
    <script src="../resources/js/popper.js"></script>
    <script src="../resources/js/bootstrap.min.js"></script>
    <script src="../resources/js/bootstrap.js"></script>

    <!-- Plugins -->

    <script src="../resources/js/jquery.bxslider.js"></script>
    <script src="../resources/js/jquery.bxslider.min.js"></script>
    <script src="../resources/js/scrollreveal.min.js"></script>
    <script src="../resources/js/waypoints.min.js"></script>
    <script src="../resources/js/jquery.counterup.min.js"></script>
    <script src="../resources/js/imgfix.min.js"></script> 
    <script src="../resources/js/mixitup.js"></script> 
    <script src="../resources/js/accordions.js"></script>
    <script src="../resources/js/owl-carousel.js"></script>
</body>
</html>