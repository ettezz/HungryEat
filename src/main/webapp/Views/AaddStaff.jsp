<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>設定外送員</title>
  <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,400italic,600,700' rel='stylesheet' type='text/css'>
  <link href='http://fonts.googleapis.com/css?family=Damion' rel='stylesheet' type='text/css'>
  <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/css/templatemo-style.css" rel="stylesheet">
 
	<!-- JS -->
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>      <!-- jQuery -->
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/templatemo-script.js"></script>      <!-- Templatemo Script -->

 

  </head>
  <script type="text/javascript">
  	  let isOwn = false;
	  $(function () {
	  	
	  	cleanClick();
	  	searchShipper();
	  });
	
	  //搜尋外送員
	  function searchShipper(){
		  var url = "${pageContext.request.contextPath}/AaddStaffServlet?" 
	  			+ "funcType=1" 
	  			+ "&shopId=" + "<%= session.getAttribute("USER_ID")%>";
	  	
	  	url=encodeURI(url);
	  	url=encodeURI(url); //兩次
	  	$.ajax({
	          type: "POST",
	          url: url,
	          contentType: "application/x-www-form-urlencoded; charset=utf-8",
	          success: function(shipperInfo){ 
	        	  if (shipperInfo.length > 0){
	        		  isOwn = true;
	        		  
		        	  $("#userIdTXT").val(shipperInfo[0].userId);
	       			  $("#userNameTXT").val(shipperInfo[0].userName);
	       			  $("#passwordTXT").val("");
	       			  $("#password2TXT").val("");
	       			  $("#phoneTXT").val(shipperInfo[0].phone);
	        		  $("#addressTXT").val(shipperInfo[0].address);
	        		  
	              	  $("#userIdTXT").attr('disabled', 'disabled');
	              	  $("#userNameTXT").attr('disabled', 'disabled');
        		  }else{
        			  isOwn = false;
        			  
        			  $("#userIdTXT").removeAttr('disabled');
        			  $("#userNameTXT").removeAttr('disabled');
        		  }
	          },
	          error:function(err){
	          	alert(err);
	          },
	      });
	  }
	  
		//註冊
	  function setClick(){
	  	if (!confirm("確定設定？")){
	  		return;
	  	}
	  	if (!checkNotNull()){
	  		return;
	  	}
	  	var url = "${pageContext.request.contextPath}/AaddStaffServlet?" 
	  			+ "funcType=0" 
	  			+ "&isOwn=" + (isOwn ? "1" : "0")
	  			+ "&shopId=" + "<%= session.getAttribute("USER_ID")%>"
	  			+ "&userId=" + $("#userIdTXT").val()
	  			+ "&userName=" + $("#userNameTXT").val()
	  			+ "&passwd=" + $("#passwordTXT").val() 
	  			+ "&phone=" + $("#phoneTXT").val() 
	  			+ "&address=" + $("#addressTXT").val();
	  	
	  	url=encodeURI(url);
	  	url=encodeURI(url); //兩次
	  	$.ajax({
	          type: "POST",
	          url: url,
	          contentType: "application/x-www-form-urlencoded; charset=utf-8",
	          success: function(msg){      
	              if (msg == 1 || msg.equals("1")){
	              	alert("設定成功");
	              	
	              	cleanClick();
	              	searchShipper();
	              	
	              }else{
	              	alert("設定失敗:" + msg);
	              }
	          },
	          error:function(err){
	          	alert(err);
	          },
	      });
	  }
		
		//檢核
	  function checkNotNull(){
	  	if ($("#userIdTXT").val().trim() == ""){
	  		alert("帳號不得為空");
	  		return false;
	  	}
	  	if ($("#userNameTXT").val().trim() == ""){
	  		alert("姓名不得為空");
	  		return false;
	  	}
	  	if ($("#passwordTXT").val() != "" && !/^([0-9a-zA-Z]+)$/.test($("#passwordTXT").val())){
	  		alert("密碼只可輸入英數字");
	  		return false;
	  	}
	  	if ($("#passwordTXT").val().trim() != "" && $("#passwordTXT").val() != $("#password2TXT").val()){
	  		alert("二次密碼不相符");
	  		return false;
	  	}
	  	if ($("#phoneTXT").val().trim() == ""){
	  		alert("電話不得為空");
	  		return false;
	  	}
	  	if ($("#phoneTXT").val().length > 10){
	  		alert("電話最多為10碼");
	  		return false;
	  	}
	  	if ($("#addressTXT").val().trim() == ""){
	  		alert("地址不得為空");
	  		return false;
	  	}
	  	
	  	return true;
	  }
		
	  function cleanClick(){
		  isOwn = false;
		  
  		$("#userIdTXT").val("");
 		$("#userNameTXT").val("");
 		$("#passwordTXT").val("");
 		$("#password2TXT").val("");
 		$("#phoneTXT").val("");
  		$("#addressTXT").val("");
 	  }
		
  </script>
<body class="bg-cyan">
		 <!-- 讀取動畫 -->
		 <div id="loader-wrapper">
			
			<div class="loader-section section-left"></div>
			<div class="loader-section section-right"></div>
		  </div>
		  <!-- 讀取動畫end -->

	<%@include file="/Views/HEmenuBarA.jsp" %>
	
	<div class="body body-s">		
			<div id="sky-form" class="sky-form">
				<header>設定外送員</header>
				
				<fieldset>	
					<!--帳號-->			
					<section>
						
						<label class="input">
							<i class="icon-append icon-user"></i>
							<input type="text" name="account" placeholder="--外送員帳號--" id="userIdTXT" >
							
						</label>
					</section>
					
					<!--姓名-->			
					<section>
						
						<label class="input">
							<i class="icon-append icon-user"></i>
							<input type="text" name="username" placeholder="--外送員姓名--" id="userNameTXT" >
							
						</label>
					</section>
					<!--密碼-->		
					<section>
						<label class="input">
							<i class="icon-append icon-lock"></i>
							<input type="password" name="password" placeholder="--密碼--" id="passwordTXT">
						
						</label>
					</section>
					<!--確認密碼-->	
					<section>
						<label class="input">
							<i class="icon-append icon-lock"></i>
							<input type="password" name="passwordConfirm" placeholder="--確認密碼--" id="password2TXT" >
							
						</label>
					</section>

					<!--電話-->	
					<section>
						<label class="input">
							<i class="icon-append icon-phone"></i>
							<input type="number" name="tel" placeholder="--電話--" id="phoneTXT" >
							
						</label>
					</section>
					<!--地址-->	
					<section>
						<label class="input">
								<i class="icon-append icon-home"></i>
								<input type="text" name="address" placeholder="--地址--" id="addressTXT">
								
						</label>
					</section>
					
				</fieldset>
					
				
				<footer>
					
					<button class="button" onClick="setClick()">設定外送員</button>
				</footer>
			</div>	
			
		</div>
</body>
</html>