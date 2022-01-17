<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<style>
html, body{
  color:  #525252;
}
table{
  width:100%;
  border: 1px solid #c4c2c2;
}
table th, td{
  border-right: 1px solid #c4c2c2;
  border-bottom: 1px solid #c4c2c2;
  height: 4rem;
  padding-left: .5rem;
  padding-right: 1rem;
}
table th{
  background-color: #e0e4fe;
}
input[type=text], input[type=password]{
  height:2rem;
  width: 100%;
  border-radius: .2rem;
  border: .2px solid rgb(204,204,204);
  background-color: rgb(246,246,246);
}
button{
  width: 5rem;
  margin-top: 1rem;
  border: .1rem solid rgb(204,204,204);
  border-radius: .2rem;
  /*box-shadow: 1px 1px #666;*/
}
button:active {
  background-color: rgb(186,186,186);
  box-shadow: 0 0 1px 1px #666;
  transform: translateY(1px);
}
</style>
<script type="text/javascript" src="../resources/js/jquery.colorbox.js"></script>
<script type="text/javascript" src="../resources/js/colorBox.js"></script>
<script>
$(document).ready(function(){
	$("#userName").focus();
});

</script>
</head>
<body>
<div class="layerpopup" style="width:1123px; margin:auto; margin-top:5%;">
   <h1 style="font-size: 1.6rem; margin-top: 3rem; margin-bottom: 1.6rem; padding: .5rem 0 .5rem 1rem; background-color: #e0e4fe;">사용자 수정</h1>
   <div class="layer-cont">
      <form name="regForm" id="regForm" method="post">
         <table>
            <tbody>

               <tr>
                  <th scope="row">아이디</th>
                  <td>
                 ${wdAdminUser.userId}
                     <input type="hidden" id="userId" name="userId" value="${wdAdminUser.userId}" />
                  </td>
                  <th scope="row">비밀번호</th>
                  <td>
                     <input type="text" id="userPwd" name="userPwd" value="${wdAdminUser.userPwd}" style="font-size:1rem;;" maxlength="15" placeholder="비밀번호" />
                  </td>
               </tr>
               <tr>
                  <th scope="row">이름</th>
                  <td>
                     <input type="text" id="userName" name="userName" value="${wdAdminUser.userName}" style="font-size:1rem;;" maxlength="50" placeholder="이름" />
                  </td>
                  <th scope="row">닉네임</th>
                  <td>
                     <input type="text" id="userNickname" name="userNickname" value="${wdAdminUser.userNickname}" style="font-size:1rem;;" maxlength="50" placeholder="이메일" />
                  </td>
               </tr>
               <tr>
                   <th scope="row">이메일</th>
                  <td>
                 ${wdAdminUser.userEmail}
                     <input type="hidden" id="userEmail" name="userEmail" value="${wdAdminUser.userEmail}" />
                  </td>
                  <th scope="row">상태</th>
                  <td>
                     <select id="status" name="status" style="font-size: 1rem; width: 7rem; height: 2rem;">
                        <option value="Y" <c:if test="${wdAdminUser.status == 'Y'}">selected</c:if>>정상</option>
                        <option value="N" <c:if test="${wdAdminUser.status == 'N'}">selected</c:if>>정지</option>
                     </select>
                  </td>
               </tr>
               <tr>
                  <th scope="row">등록일</th>
                  <td>${wdAdminUser.regDate}</td>
               </tr>

            </tbody>
         </table>
      </form>
      <div class="pop-btn-area" style="float: right;">
         <button onclick="fn_userUpdate()" class="btn-type01"><span>수정</span></button>
         <button onclick="fn_colorbox_close()" id="colorboxClose" class="btn-type01" style="margin-left: 1rem;"><span>닫기</span></button>
      </div>
   </div>
</div>


	<%@ include file="/WEB-INF/views/include/footer3.jsp" %>
</body>
</html>