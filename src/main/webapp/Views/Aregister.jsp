<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
	<head>
		<title>巴豆妖 店家註冊</title>
		
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
		<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,400italic,600,700' rel='stylesheet' type='text/css'>
		<link href='http://fonts.googleapis.com/css?family=Damion' rel='stylesheet' type='text/css'>
		<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/css/templatemo-style.css" rel="stylesheet">
		
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/demo.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/sky-forms.css">
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>      <!-- jQuery -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/templatemo-script.js"></script>      <!-- Templatemo Script -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>      <!-- Templatemo Script -->
		
		
		
	</head>
	<script type="text/javascript">
			$(function()
			{
				// Validation		
				$("#sky-form").validate(
				{					
					// Rules for form validation
					rules:
					{
						username:
						{
							required: true
						},
						email:
						{
							required: true,
							email: true
						},
						password:
						{
							required: true,
							minlength: 3,
							maxlength: 20
						},
						passwordConfirm:
						{
							required: true,
							minlength: 3,
							maxlength: 20,
							equalTo: '#password'
						},
						
					},
					
					// Messages for form validation
					messages:
					{
						login:
						{
							required: 'Please enter your login'
						},
						email:
						{
							required: '請輸入email',
							email: '請輸入有效信箱'
						},
						password:
						{
							required: '請輸入密碼'
						},
						passwordConfirm:
						{
							required: 'Please enter your password one more time',
							equalTo: 'Please enter the same password as above'
						},
						
						
						
					},					
					
					// Do not change code below
					errorPlacement: function(error, element)
					{
						error.insertAfter(element.parent());
					}
				});
			});		
			
			//註冊
		    function insClick(){
		    	if (!checkNotNull()){
		    		return;
		    	}
		    	var url = "${pageContext.request.contextPath}/AregisterServlet?" ;
		    	
		    	var data={
		    		"funcType": "0",
		    		"userId": $("#userIdTXT").val(),
		    		"userName": $("#userNameTXT").val(),
		    		"passwd": $("#passwordTXT").val(),
		    		"phone": $("#phoneTXT").val(),
		    		"address": $("#addressTXT").val(),
		    	};
		    	
		    	url=encodeURI(url);
		    	url=encodeURI(url); //兩次
		    	/*$.ajax({
		            type: "POST",
		            url: url,
		            contentType: "application/x-www-form-urlencoded; charset=utf-8",
		            success: function(msg){     
		            	console.log(msg);
		                if (msg == 1){
		                	alert("註冊成功");
		                	backClick();
		                }else if (msg == -2){
		                	alert("註冊失敗:帳號已存在");
		                }else{
		                	alert("註冊失敗");
		                }
		            },
		            error:function(err){
		            	alert(err);
		            },
		        });*/
		    	$.ajaxFileUpload({
		            url : url,
		            secureuri : true ,
		            fileElementId : "uploadFile",
		            dataType : "text" ,
		            data : data,
		            success : function(msg, status) {
		            	if (msg == 1){
		                	alert("註冊成功");
		                	backClick();
		                }else if (msg == -2){
		                	alert("註冊失敗:帳號已存在");
		                }else{
		                	alert("註冊失敗");
		                }
		            },
		            error : function(data, status, e) {
		                alert("註冊失敗");
		            }
		        });
		    }
			
		  	//檢核
	        function checkNotNull(){
	        	if ($("#userIdTXT").val().trim() == ""){
	        		alert("帳號不得為空");
	        		return false;
	        	}
	        	if (!/^([0-9a-zA-Z]+)$/.test($("#userIdTXT").val())){
	        		alert("帳號只可輸入英數字");
	        		return false;
	        	}
	        	if ($("#userNameTXT").val().trim() == ""){
	        		alert("店名不得為空");
	        		return false;
	        	}
	        	if ($("#passwordTXT").val().trim() == ""){
	        		alert("密碼不得為空");
	        		return false;
	        	}
	        	if (!/^([0-9a-zA-Z]+)$/.test($("#passwordTXT").val())){
	        		alert("密碼只可輸入英數字");
	        		return false;
	        	}
	        	if ($("#passwordTXT").val() != $("#password2TXT").val()){
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
	        	if ($("#uploadFile").val() == ""){
	        		alert("請上傳店家照片");
	        	}
	        	return true;
	        }
		  	
	      //回首頁
	        function backClick(){
	        	window.location.href='${pageContext.request.contextPath}/Views/HELogin.jsp';
	        }
		</script>
	<body class="bg-cyan">
		 <!-- 讀取動畫 -->
		 <div id="loader-wrapper">
			
			<div class="loader-section section-left"></div>
			<div class="loader-section section-right"></div>
		  </div>
		  <!-- 讀取動畫end -->


		<!-- NAV-->
		<div class="tm-top-header">
			<div class="container">
			  <div class="row">
				<div class="tm-top-header-inner">
				  <div class="tm-logo-container">
					<img src="${pageContext.request.contextPath}/img/logo.png" alt="Logo" class="tm-site-logo">
				  
				  </div>
				  
				  <!-- 選單-->
				  <nav class="tm-nav">
					<ul>
					  <li><a href="${pageContext.request.contextPath}/Views/Bindex.jsp">首頁</a></li>
					  <li><a href="${pageContext.request.contextPath}/Views/HELogin.jsp">登入</a></li>
					 
				  
					</ul>
	  
				  </nav>   
				</div>           
			  </div>    
			</div>
		  </div>





		<div class="body body-s">		
			<div id="sky-form" class="sky-form">
				<header>填寫店家資料</header>
				
				<fieldset>	
					<!--帳號-->			
					<section>
						
						<label class="input">
							<i class="icon-append icon-user"></i>
							<input type="text" name="account" id="userIdTXT" placeholder="--帳號--">
							
						</label>
					</section>
					
					<!--姓名-->			
					<section>
						
						<label class="input">
							<i class="icon-append icon-user"></i>
							<input type="text" name="username" id="userNameTXT" placeholder="--店名--">
							
						</label>
					</section>
					<!--密碼-->		
					<section>
						<label class="input">
							<i class="icon-append icon-lock"></i>
							<input type="password" name="password" id="passwordTXT" placeholder="--密碼--" id="password">
						
						</label>
					</section>
					<!--確認密碼-->	
					<section>
						<label class="input">
							<i class="icon-append icon-lock"></i>
							<input type="password" name="passwordConfirm" id="password2TXT" placeholder="--確認密碼--" >
							
						</label>
					</section>

					<!--電話-->	
					<section>
						<label class="input">
							<i class="icon-append icon-phone"></i>
							<input type="tel" name="tel" id="phoneTXT" placeholder="--電話--" >
							
						</label>
					</section>
					<!--地址-->	
					<section>
						<label class="input">
								<i class="icon-append icon-home"></i>
								<input type="text" name="address" id="addressTXT" placeholder="--地址--" >
								
						</label>
					</section>
					<section>
						
							<span class="text-left" style="color: rgb(131, 131, 131);">上傳店家照片</span>

							<input type="file" name="uploadfile" id="uploadFile"  style="overflow: hidden" />
							<div  class="icon-upload-alt"></div>
							
					</section>
				</fieldset>
					
				
				<footer>
					
					<button id="insBTN" class="button" onClick="insClick()">註冊</button>
				</footer>
			</div>	
			
		</div>
		
		
<div style="text-align:center;clear:both">

</div>

	</body>
</html>