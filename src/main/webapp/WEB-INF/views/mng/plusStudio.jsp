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
	$("#weddinghallName").focus();
});

function fn_userUpdate()
{
	if(icia.common.isEmpty($("#studioName").val()))
	{
		//alert("스튜디오 이름을 선택해주세요.");
		  Swal.fire({ 
			  icon: 'warning',
			  text: '스튜디오 이름을 입력해주세요.'
		  });
		$("#studioName").focus();
		return;
	}
	
	if(icia.common.isEmpty($("#studioPrice").val()))
	{
		//alert("스튜디오 가격을 입력해주세요.");
		Swal.fire({ 
			icon: 'warning',
			text: '스튜디오 가격을 입력해주세요.'
		});
		$("#studioPrice").focus();
		return;
	}
	
	if(icia.common.isEmpty($("#studioLocation").val()))
	{
		//alert("스튜디오 가격을 입력해주세요");
		Swal.fire({ 
			icon: 'warning',
			text: '스튜디오 주소를 입력해주세요.'
		});
		$("#studioLocation").focus();
		return;
	}
	
	if(icia.common.isEmpty($("#studioNumber").val()))
	{
		//alert("스튜디오 전화번호를 입력해주세요.");
		Swal.fire({ 
			icon: 'warning',
			text: '스튜디오 전화번호를 입력해주세요.'
		});
		$("#studioNumber").focus();
		return;
	}
	
	if(icia.common.isEmpty($("#studioContent").val()))
	{
		//alert("스튜디오 설명을 입력해주세요.");
		Swal.fire({ 
			icon: 'warning',
			text: '스튜디오 설명을 입력해주세요.'
		});
		$("#studioContent").focus();
		return;
	}
	
	if(icia.common.isEmpty($("#studioDiscount").val()))
	{
		//alert("할인율을 입력해주세요.");
		Swal.fire({ 
			icon: 'warning',
			text: '할인율을 입력해주세요.'
		});
		$("#studioDiscount").focus();
		return;
	}

	
	//등록 취소
	if(!confirm("스튜디오를 등록 하시겠습니까?"))
	{
		//NO
		return;
	}
	
	var formData = {
		studioName: $("#studioName").val(),
		studioPrice: $("#studioPrice").val(),
		studioLocation: $("#studioLocation").val(),
		studioNumber: $("#studioNumber").val(),
		studioContent: $("#studioContent").val(),
		studioDiscount: $("#studioDiscount").val()
	};
	
	//ajax통신
	icia.ajax.post({
		url: "/mng/studioWrite",
		data: formData,
		success: function(res)
		{
			icia.common.log(res);
			
			if(res.code == 0)
			{
				alert("스튜디오 등록이 완료되었습니다.");
				fn_colorbox_close(parent.fn_pageInit);
			}
			else if(res.code == -1)
			{
				alert("스튜디오 등록 중 오류가 발생하였숩니다.");
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
   <h1 style="font-size: 1.6rem; margin-top: 3rem; margin-bottom: 1.6rem; padding: .5rem 0 .5rem 1rem; background-color: #e0e4fe;">스튜디오 추가</h1>
   <div class="layer-cont">
      <form name="regForm" id="regForm" method="post">
         <table>
            <tbody>
				<tr>
                  <th scope="row">스튜디오 이름</th>
                  <td>
                     <input type="text" style="background-color: #fff;" id="studioName" name="studioName" placeholder="스튜디오 이름을 입력해주세요"/>
                  </td>
               </tr>
               <tr>
                  <th scope="row">스튜디오 가격</th>
                  <td>
                     <input type="text" style="background-color: #fff;" id="studioPrice" name="studioPrice" placeholder="스튜디오 가격을 입력해주세요"/>
                  </td>
               </tr>
               <tr>
                   <th scope="row">스튜디오 주소</th>
                  <td>
                     <input type="text" style="background-color: #fff;" id="studioLocation" name="studioLocation" placeholder="스튜디오 주소를 입력해주세요"/>
                  </td>
               </tr>
               <tr>
                  <th scope="row">스튜디오 전화번호</th>
                  <td>
                  	<input type="text" style="background-color: #fff;" id="studioNumber" name="studioNumber" placeholder="스튜디호 번호를 입력해주세요"/>
                  </td>
               </tr>
               <tr>
                  <th scope="row">스튜디오 설명</th>
                  <td>
                  	<textarea class="form-control" rows="3" name="studioContent" id="studioContent" style="ime-mode: active; resize: none; width:100%; float:left; height:76px; font-size:14px;" placeholder="스튜디오 설명을 입력해주세요" required></textarea>
                  </td>
               </tr>
               <tr>
                  <th scope="row">할인율</th>
                  <td>
                  	<input type="number" style="background-color: #fff;" id="studioDiscount" name="studioDiscount" placeholder="할인율을 입력해주세요"/>
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