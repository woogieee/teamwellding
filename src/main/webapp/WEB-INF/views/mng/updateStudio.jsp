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
  background-color: #e9e9ed;
  width: 250px;
}
input[type=text], input[type=password]{
  height:2rem;
  width: 100%;
  border-radius: .2rem;
  border: .2px solid rgb(204,204,204);
  background-color: rgb(246,246,246);
  font-size:15px;
}

input::placeholder 
{
  font-size: 14px;
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

function fn_studioDelete()
{
	if(confirm("정말 삭제 하시겠습니까?") == true)
	{
		$.ajax({
			type: "POST",
			url: "/mng/studioDelete",
			data:{
				sCode: <c:out value="${wdStudio.sCode}" />
			},
			datatype: "JSON",
			beforeSend: function(xhr)
			{
				xhr.setRequestHeader("AJAX", "true");
			},
			success: function(response)
			{
				alert("여기는 뜰까요")
				if(response.code == 0)
				{
					alert("스튜디오가 삭제되었습니다.");
					fn_colorbox_close(parent.fn_pageInit);
				}
				else if(response.code == 400)
				{
					alert("파라미터 값이 올바르지 않습니다.");
				}
				else if(response.code == 404)
				{
					alert("스튜디오를 찾을수 없습니다.");
					fn_colorbox_close();
				}
				else
				{
					alert("스튜디오 삭제 중 오류가 발생했습니다.");
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
}

</script>
</head>
<body>

<div class="layerpopup" style="width:1123px; margin:auto; margin-top:5%;">
	<h1 style="font-size: 1.6rem; margin-top: 3rem; margin-bottom: 1.6rem; padding: .5rem 0 .5rem 1rem; background-color: #e0e4fe;">공지사항 게시글 수정</h1>
	<div class="layer-cont">
      <form name="regForm" id="regForm" method="post">
         <table>
            <tbody>
                <input type="hidden" id="studioCode" name="studioCode" value="${wdStudio.sCode}"/>
            	<tr>
                  <th scope="row">스튜디오 이름</th>
                  <td>
                     <input type="text" id="studioName" name="studioName" value="${wdStudio.sName}"/>
                  </td>
               </tr>
               <tr>
                  <th scope="row">스튜디오 가격</th>
                  <td>
                     <input type="text" id="studioPrice" name="studioPrice" value="${wdStudio.sPrice}"/>
                  </td>
               </tr>
               <tr>
                   <th scope="row">스튜디오 주소</th>
                  <td>
                     <input type="text" id="studioLocation" name="studioLocation" value="${wdStudio.sLocation}"/>
                  </td>
               </tr>
               <tr>
                  <th scope="row">스튜디오 전화번호</th>
                  <td>
                  	<input type="text" id="studioNumber" name="studioNumber" value="${wdStudio.sNumber}"/>
                  </td>
               </tr>
               <tr>
                  <th scope="row">스튜디오 설명</th>
                  <td>
                  	<textarea class="form-control" rows="3" name="studioContent" id="studioContent" style="ime-mode: active; resize: none; width:100%; float:left; height:76px; font-size:14px;" required>${wdStudio.sContent}</textarea>
                  </td>
               </tr>
               <tr>
                  <th scope="row">할인율</th>
                  <td>
                  	<input type="number" id="studioDiscount" name="studioDiscount" value="${wdStudio.sDiscount}"/>
                  </td>
               </tr>
            </tbody>
         </table>
      </form>
      <div class="pop-btn-area" style="float: right;">
         <button onclick="fn_studioUpdate()" class="btn-type01"><span>수정</span></button>
         <button onclick="fn_studioDelete()" class="btn-type01" style="margin-left: 1rem;"><span>삭제</span></button>
         <button onclick="fn_colorbox_close()" id="colorboxClose" class="btn-type01" style="margin-left: 1rem;"><span>닫기</span></button>
      </div>
   </div>
</div>

	<%@ include file="/WEB-INF/views/include/footer3.jsp" %>
</body>
</html>