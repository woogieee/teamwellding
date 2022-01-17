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
   $("#makeupName").focus();
});

function fn_userUpdate()
{
   if(icia.common.isEmpty($("#makeupName").val()))
   {
      alert("메이크업 이름을 입력해주세요");
      $("#makeupName").focus();
      return;
   }
   
   if(icia.common.isEmpty($("#makeupLocation").val()))
   {
      alert("메이크업 주소를 입력해주세요");
      $("#makeupLocation").focus();
      return;
   }
   
   if(icia.common.isEmpty($("#makeupnumber").val()))
   {
      alert("메이크업 전화번호를 입력해주세요");
      $("#makeupnumber").focus();
      return;
   }
   
   if(icia.common.isEmpty($("#makeupContent").val()))
   {
      alert("메이크업 설명을 입력해주세요.");
      $("#makeupContent").focus();
      return;
   }
   
   //등록 취소
   if(!confirm("메이크업을 등록 하시겠습니까?"))
   {
      //NO
      return;
   }
   
   var formData = {
      mkName: $("#makeupName").val(),
      mkLocation: $("#makeupLocation").val(),
      mkNumber: $("#makeupnumber").val(),
      mkContent: $("#makeupContent").val()
   };
   
   //ajax통신
   icia.ajax.post({
      url: "/mng/makeupWrite",
      data: formData,
      success: function(res)
      {
         icia.common.log(res);
         
         if(res.code == 0)
         {
            alert("메이크업 등록이 완료되었습니다.");
            fn_colorbox_close(parent.fn_pageInit);
         }
         else if(res.code == -1)
         {
            alert("메이크업 등록 중 오류가 발생하였숩니다.");
         }
         else if(res.code == 400)
         {
            alert("파라미터 값이 잘못되었습니다.");
         }
         else if(res.code == 404)
         {
            alert("오류가 발생하였습니다.");
            ///칼라박스 내용이 잘못됬다는거니까 칼라박스를 닫게하자
            fn_colorbox_close();
         }
      },
      complete: function(data)
      {
         icia.common.log(data);
      },
      error: function(xhr, status, error)
      {
         icia.common.error(error);
      }
   });
   
}

</script>
</head>
<body>   
<div class="container">
    <div class="row" style="width: 100%; text-align: center;">
 <!-- /////////////////////////////////////////// --> 
<div class="layerpopup" style="width:1123px; margin:auto;">
   <h1 style="font-size: 1.6rem; margin-top: 3rem; margin-bottom: 1.6rem; padding: .5rem 0 .5rem 1rem; background-color: #e0e4fe;">메이크업 추가</h1>
   <div class="layer-cont">
      <form name="regForm" id="regForm" method="post">
         <table>
            <tbody>

               <tr>
                  <th scope="row">메이크업 이름</th>
                  <td>
                     <input type="text" style="background-color: #fff;" id="makeupName" name="makeupName" placeholder="메이크업 이름을 입력해주세요"/>
                  </td>
               </tr>
               <tr>
                  <th scope="row">메이크업 주소</th>
                  <td>
                     <input type="text" style="background-color: #fff;" id="makeupLocation" name="makeupLocation" placeholder="메이크업 주소를 입력해주세요"/>
                  </td>
               </tr>
               <tr>
                   <th scope="row">메이크업 번호</th>
                  <td>
                     <input type="text" style="background-color: #fff;" id="makeupnumber" name="makeupnumber" placeholder="메이크업 번호를 입력해주세요"/>
                  </td>
               </tr>
               <tr>
                  <th scope="row">메이크업 설명</th>
                  <td>
                  <textarea class="form-control" rows="3" name="makeupContent" id="makeupContent" style="ime-mode: active; resize: none; width:100%; float:left; height:76px; font-size:14px;" placeholder="메이크업 설명을 입력해주세요" required></textarea>
                  </td>
               </tr>

            </tbody>
         </table>
      </form>
      <div class="pop-btn-area" style="float: right;">
         <button onclick="fn_userUpdate()" class="btn-type01"><span>등록</span></button>
         <button onclick="fn_colorbox_close()" id="colorboxClose" class="btn-type01" style="margin-left: 1rem;"><span>닫기</span></button>
      </div>
   </div>
</div>


   <%@ include file="/WEB-INF/views/include/footer3.jsp" %>
 <!-- ///////////////////////////////////////////// -->
   </div>
</div>
       
</body>
</html>