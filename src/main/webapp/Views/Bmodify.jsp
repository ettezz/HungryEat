<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	request.setCharacterEncoding("UTF-8");
	if (request.getParameter("phone") != null){
		session.setAttribute("USER_PHONE", request.getParameter("phone"));
		session.setAttribute("USER_ADDRESS", request.getParameter("address"));
		session.removeAttribute("phone");
		session.removeAttribute("address");
	}
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>修改會員資料</title>
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
	  $(function () {
	  	
	  	cleanClick();
	  });
		
		//註冊
	  function updClick(){
	  	if (!confirm("確定修改？")){
	  		return;
	  	}
	  	if (!checkNotNull()){
	  		return;
	  	}
	  	var url = "${pageContext.request.contextPath}/BmodifyServlet?" 
	  			+ "funcType=0" 
	  			+ "&userId=" + "<%= session.getAttribute("USER_ID")%>"
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
	              	alert("修改成功");
	              	//虛擬表單提交 
	              	//js 轉 Java
	              	var temp = document.createElement("form");
	              	temp.action = "#";//提交的地址
	              	temp.method = "post";//也可指定為get
	              	temp.style.display = "none";
	              	var opt = document.createElement("textarea");
	              	opt.name = "phone";
	              	opt.value = $("#phoneTXT").val();
	              	temp.appendChild(opt);
	              	opt = document.createElement("textarea");
	              	opt.name = "address";
	              	opt.value = $("#addressTXT").val();
	              	temp.appendChild(opt);
	              	document.body.appendChild(temp);
	              	temp.submit();
	              	
	              	
	              	cleanClick();
	              	
	              }else{
	              	alert("修改失敗:" + msg);
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
  		$("#userIdTXT").val("<%= session.getAttribute("USER_ID")%>");
 		$("#userNameTXT").val("<%= session.getAttribute("USER_NAME")%>");
 		$("#passwordTXT").val("");
 		$("#password2TXT").val("");
 		$("#phoneTXT").val("<%= session.getAttribute("USER_PHONE")%>");
  		$("#addressTXT").val("<%= session.getAttribute("USER_ADDRESS")%>");
 	  }
		
  
  </script>
<body class="bg-cyan">
		 <!-- 讀取動畫 -->
		 <div id="loader-wrapper">
			
			<div class="loader-section section-left"></div>
			<div class="loader-section section-right"></div>
		  </div>
		  <!-- 讀取動畫end -->

	<%@include file="/Views/HEmenuBarB.jsp" %>
	
	<div class="body body-s">		
			<div id="sky-form" class="sky-form">
				<header>修改會員資料</header>
				
				<fieldset>	
					<!--帳號-->			
					<section>
						
						<label class="input">
							<i class="icon-append icon-user"></i>
							<input type="text" name="account" id="userIdTXT" disabled="disabled">
							
						</label>
					</section>
					
					<!--姓名-->			
					<section>
						
						<label class="input">
							<i class="icon-append icon-user"></i>
							<input type="text" name="username" id="userNameTXT" disabled="disabled">
							
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
					
					<button class="button" onClick="updClick()">修改資料</button>
				</footer>
			</div>	
			
		</div>
</body>
</html>