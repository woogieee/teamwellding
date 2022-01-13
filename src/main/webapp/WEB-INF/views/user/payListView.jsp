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
                  <h2 style="font-family: 'Bitter'; margin-top: 50px; padding-left: 10px;">결제내역 상세</h2>
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
                                <th>이미지</th>
                                <th>상품정보</th>
                                <th>참고사항</th>
                                <th>가격</th>
                            </tr>
                            
<c:if test="${wdRez.rezStatus eq 'Y'}">
                            <!-- 홀 -->
<c:if test="${!empty wdRez.whCode}">
                            
                            <tr id="wishH">
                               <!-- 예약번호 -->
                               <td>
                                  <div class="col-lg-12" style="text-align:center">
                                       <p><c:out value="${wdRez.rezNo}" /></p>
                                    </div>
                               </td>
                               <!-- 예약날짜 -->
                               <td>
                                  <div class="col-lg-12" style="text-align:center">
                                         <p><c:out value="${wdRez.rezDate}" /></p>
                                      </div>
                               </td>
                               <!-- 이미지 -->
                                <td class="image-prod">
                                   <div class="imgbox" onclick="fn_view1('${wdRez.whCode}','${wdRez.hCode}')">
                                      <img src="../resources/images/hallrepimage/${wdRez.hImgName}" id="prod" class="wishImg">
                                   </div>
                                </td>
                                <!-- 상품정보 -->
                                <td class="product-name alignleft" style="width: 18%;">
                             <p class="my_hall"><c:out value="${wdRez.whName}" /></p>
                             <p><h4>&nbsp;<c:out value="${wdRez.hName}" /></h4></p>
                          </td>
                                <!-- 참고사항 -->
                                <td class="HpriceTd alignleft">
                                   <p class="Hpc">홀대관비&nbsp; <fmt:formatNumber type="number" maxFractionDigits="0" value="${wdRez.hPrice *(1- wdRez.hDiscount*0.01)}" />원
                                   &nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;식비&nbsp; <fmt:formatNumber type="number" maxFractionDigits="0" value="${wdRez.hFood * wdRez.hMin}" />원</p>
                                   <p class="sangsaeP">
                                      <span class="chamgo">
                                         대관료할인: <span id="discountSpan">${wdRez.hDiscount}%</span> &nbsp;&nbsp;</span>
                                         <span id="xPrice"><fmt:formatNumber type="number" maxFractionDigits="0" value="${wdRez.hPrice}" />원 </span>&nbsp;&nbsp;
                                         <span id="nPrice"><fmt:formatNumber type="number" maxFractionDigits="0" value="${wdRez.hPrice *(1- wdRez.hDiscount*0.01)}" />원</span>
                                      
                                   </p>   
                                   <p class="sangsaeP">
                                      <span class="chamgo">                                        
                                         1인당 식비: <fmt:formatNumber type="number" maxFractionDigits="0" value="${wdRez.hFood}" />원 x ${wdRez.hMin}명 = <fmt:formatNumber type="number" maxFractionDigits="0" value="${wdRez.hFood * wdRez.hMin}" />원
                                      </span>
                                   </p>
                                </td>
                                <!-- 가격 -->
                                <td class="HpriceTotal" id="hollprice">
                                   <h4>
                                      <fmt:formatNumber type="number" maxFractionDigits="0" value="${wdRez.hPrice *(1- wdRez.hDiscount*0.01) + (wdRez.hFood * wdRez.hMin)}" />원
                                   </h4>
                                </td>
                                
                            </tr>
</c:if>
                            <!-- 스 -->
<c:if test="${!empty wdRez.sCode}">
                         
                            <tr id="wishS">
                               
                               <!-- 예약번호 -->
                               <td>
                                  <div class="col-lg-12" style="text-align:center">
                                       <p><c:out value="${wdRez.rezNo}" /></p>
                                    </div>
                               </td>
                               <!-- 예약날짜 -->
                               <td>
                                  <div class="col-lg-12" style="text-align:center">
                                         <p><c:out value="${wdRez.rezDate}" /></p>
                                      </div>
                               </td>
                               <!-- 이미지 -->
                                <td class="image-prod">
                                   <div class="imgbox"  onclick="fn_view2('${wdRez.sCode}')">
                                      <img src="../resources/images/studio/${wdRez.sImgName}" id="prod" class="wishImg">
                                   </div>
                                </td>
                                <!-- 상품정보 -->
                                <td class="product-name alignleft">
                             <p><h4>&nbsp;<c:out value="${wdRez.sName}" /></h4></p>
                             <!-- 주소 넣어야 할까유?? ㄴㄴ필요없을듯-->
                          </td>
                                <!-- 참고사항 -->
                                <td class="HpriceTd alignleft">
                                   <p class="Spc">스튜디오 예약일자 &nbsp<span style="font-weight:700; color: #333;"><c:out value="${wdRez.sDate}"/></span></p>
                                   <p class="sangsaeP">
                                      <span class="chamgo">
                                         할인: <span id="discountSpan">${wdRez.sDiscount}%</span> &nbsp;&nbsp;</span>
                                         <span id="xPrice"><fmt:formatNumber type="number" maxFractionDigits="0" value="${wdRez.sPrice}" />원 </span>&nbsp;&nbsp;
                                         <span id="nPrice"><fmt:formatNumber type="number" maxFractionDigits="0" value="${wdRez.sPrice *(1- wdRez.sDiscount*0.01)}" />원</span>
                                      
                                   </p>
                                   <p><span class="chamgo">
                                      예약일 변경 시 스튜디오와 직접 상의하시면 더 빠르게 처리가 가능합니다.
                                   </span></p>
                                </td>
                                <!-- 가격 -->
                                <td class="HpriceTotal" style="width: 18%;">
                                   <h4>
                                      <fmt:formatNumber type="number" maxFractionDigits="0" value="${wdRez.sPrice *(1- wdRez.sDiscount*0.01)}" />원
                                   </h4>
                                </td>
                                
                            </tr>
</c:if>

                            <!-- 드 -->
<c:if test="${!empty wdRez.dNo}">
                            <tr id="wishD">
                               
                               <!-- 예약번호 -->
                               <td>
                                  <div class="col-lg-12" style="text-align:center">
                                       <p><c:out value="${wdRez.rezNo}" /></p>
                                    </div>
                               </td>
                               <!-- 예약날짜 -->
                               <td>
                                  <div class="col-lg-12" style="text-align:center">
                                         <p><c:out value="${wdRez.rezDate}" /></p>
                                      </div>
                               </td>
                               <!-- 이미지 -->
                                <td class="image-prod">
                                   <div class="imgbox" onclick="fn_view3('${wdRez.dNo}')">
                                      <img src="../resources/images/dress/${wdRez.dImgName}" id="prod" class="wishImg">
                                   </div>
                                </td>
                                <!-- 상품정보 -->
                                <td class="product-name alignleft">
                                   <p class="my_hall"><c:out value="${wdRez.dcName}" /></p>
                             <p><h4>&nbsp;<c:out value="${wdRez.dName}" /></h4></p>
                             <!-- 주소 넣어야 할까유?? -->
                          </td>
                                <!-- 참고사항 -->
                                <td class="HpriceTd alignleft">
                                   <p class="Dpc Hpc"><c:out value="${wdRez.dContent}" /></p>
                                                                      <p class="sangsaeP">
                                   <span class="chamgo">
                                      할인: <span id="discountSpan">${wdRez.dDiscount}%</span> &nbsp;&nbsp;</span>
                                      <span id="xPrice"><fmt:formatNumber type="number" maxFractionDigits="0" value="${wdRez.dPrice}" />원 </span>&nbsp;&nbsp;
                                      <span id="nPrice"><fmt:formatNumber type="number" maxFractionDigits="0" value="${wdRez.dPrice *(1- wdRez.dDiscount*0.01)}" />원</span>   
                                   </p>
                                   <p><span class="chamgo">
                                   </span></p>
                                </td>
                                <!-- 가격 -->
                                <td class="HpriceTotal">
                                   <h4>
                                      <fmt:formatNumber type="number" maxFractionDigits="0" value="${wdRez.dPrice *(1- wdRez.dDiscount*0.01)}" />원
                                   </h4>
                                </td>
                                
                            </tr>
</c:if>

                            <!-- 메 -->
<c:if test="${!empty wdRez.mCode}">
                            <tr id="wishM">
                               
                               <!-- 예약번호 -->
                               <td>
                                  <div class="col-lg-12" style="text-align:center">
                                       <p><c:out value="${wdRez.rezNo}" /></p>
                                    </div>
                               </td>
                               <!-- 예약날짜 -->
                               <td>
                                  <div class="col-lg-12" style="text-align:center">
                                         <p><c:out value="${wdRez.rezDate}" /></p>
                                      </div>
                               </td>
                               <!-- 이미지 -->
                                <td class="image-prod">
                                   <div class="imgbox"  onclick="fn_view4('${wdRez.mCode}')">
                                      <img src="../resources/images/makeup/${wdRez.mImgName}" id="prod" class="wishImg">
                                   </div>
                                </td>
                                <!-- 상품정보 -->
                                <td class="product-name alignleft">
                             <p><h4>&nbsp;<c:out value="${wdRez.mName}" /></h4></p>
                             <!-- 주소 넣어야 할까유?? -->
                          </td>
                                <!-- 참고사항 -->
                                <td class="HpriceTd alignleft">
                                   <p class="Hpc">비용&nbsp; <fmt:formatNumber type="number" maxFractionDigits="0" value="${wdRez.mPrice *(1- wdRez.mDiscount*0.01)}" />원
<c:if test="${!empty wdRez.mPlusNum}"> 
                                   &nbsp;&nbsp;/&nbsp;&nbsp; 추가비용&nbsp; <fmt:formatNumber type="number" maxFractionDigits="0" value="${wdRez.mPlus * wdRez.mPlusNum}" />원</p>
</c:if>                                   
                                   <p class="sangsaeP">
                                      <span class="chamgo">
                                         할인: <span id="discountSpan">${wdRez.mDiscount}% &nbsp;&nbsp;</span></span>
                                         <span id="xPrice"><fmt:formatNumber type="number" maxFractionDigits="0" value="${wdRez.mPrice}" />원 </span>&nbsp;&nbsp;
                                         <span id="nPrice"><fmt:formatNumber type="number" maxFractionDigits="0" value="${wdRez.mPrice *(1- wdRez.mDiscount*0.01)}" />원</span>
                                      
                                   </p>   
<c:if test="${!empty wdRez.mPlusNum}"> 
                                   <p class="sangsaeP">
                                      <span class="chamgo">                                        
                                         추가비용: <fmt:formatNumber type="number" maxFractionDigits="0" value="${wdRez.mPlus}" />원 x ${wdRez.mPlusNum}명 = <fmt:formatNumber type="number" maxFractionDigits="0" value="${wdRez.mPlus * wdRez.mPlusNum}" />원
                                      </span>
                                   </p>
</c:if>
                                </td>
                                <!-- 가격 -->
                                <td class="HpriceTotal">
                                   <h4>
                                      <fmt:formatNumber type="number" maxFractionDigits="0" value="${wdRez.mPrice *(1- wdRez.mDiscount*0.01)+ (wdRez.mPlus*wdRez.mPlusNum)}" />원
                                   </h4>
                                </td>
                                
                            </tr>
</c:if>
</c:if>
                            
                            
               </div>
               <!-- 경계선 종료 -->
               <div class="col-lg-1"></div>
            
               <div class="col-lg-1"></div>
               <div class="col-lg-10">
               <!-- 쿠폰 가져오기 -->
               <div class="col-lg-10">
               </div>
                                <tr>
                                    <!-- 총가격 -->
                                    <th colspan="5">
                                    <div class="col-lg-12" style="text-align:center">
                                       총 금액 : <fmt:formatNumber type="number" maxFractionDigits="3" value="${wdRez.rezFullPrice}" />
                                    </div>
                                    </th>

                                    <th colspan="6">
                                 <div style="text-align: center;">
                              <button value="예약취소">예약취소</button>
                           </div>
                                    </th>
                                 </tr>
                        </table>

               </div>
               
               <div class="col-lg-1"></div>
            
            </div>
         </div>         
      </div>
   </div>

      <%@ include file="/WEB-INF/views/include/footer.jsp" %>
  </body>

</html>