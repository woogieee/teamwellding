<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>

<%
   if(com.icia.web.util.CookieUtil.getCookie(request, (String)request.getAttribute("AUTH_COOKIE_NAME")) != null)
   {
%>
   <c:set var = "name" value="${param.userName }" />
    <!-- ***** 맨뒤 HEader ***** -->
    <div class="pre-header">
        <div class="container">
            <div class="row" style="width: 100%;">
                <div class="col-lg-6">
                    <span><span class="span1">♥</span>Welcome Wellding! &nbsp;&nbsp;<span class="span1">♥</span><span class="span2">${name }</span>님 환영합니다!</span>
                </div>
				<div class="col-lg-5">
                    <div class="ourperson" style="text-align:right;">
                        <a href="/loginOut" style="position:relative; left:55px;">로그아웃</a>
                    </div>
				</div>

            </div>
        </div>
    </div>
<%
   }
%>
    <!-- ***** 보현수정 Header Area Start ***** -->
    <header class="header-area header-sticky">
        <div class="container">
            <div class="row" style="width: 100%;">
                <div class="col-12">
                    <nav class="admmain_nav">
                        <!-- ***** Menu Start ***** -->
                        <ul class="admin_nav">
                            <li class="adm_sub"><a class="adm_ss" href="/mng/userList">회원목록</a></li>
                            <li class="adm_sub"><a class="adm_ss" href="/mng/hsdmList">홀/스/드/메 관리</a></li>
                            <li class="adm_sub"><a class="adm_ss" href="">결제내역 관리</a></li>
                        </ul>        
                        <!-- ***** Menu End ***** -->
                    </nav>
                </div>
            </div>
        </div>
    </header>
    <!-- ***** Header Area End ***** -->