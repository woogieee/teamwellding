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
            <div class="row">
                <div class="col-lg-6 col-sm-6">
                    <span><span class="span1">♥</span>Welcome Wellding! &nbsp;&nbsp;<span class="span1">♥</span><span class="span2">${name }</span>님 환영합니다!</span>
                </div>
                <div class="col-lg-6 col-sm-6">
                    <div class="text-button ourperson">
                        <a href="/loginOut">로그아웃</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
<%
   }
   else
   {
%>
    <!-- ***** 맨뒤 HEader ***** -->

    <div class="pre-header">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-sm-6">
                    <span><span>♥</span>Welcome Wellding! &nbsp;&nbsp;<span>♥</span>웰딩에 오신 여러분 환영합니다!</span>
                </div>
                <div class="col-lg-6 col-sm-6">
                    <div class="text-button ourperson">

                        <a href="/board/regform">회원가입</a>
                        <a href="/board/login">로그인</a>

                    </div>
                </div>
            </div>
        </div>
    </div>

<%
   }
%>