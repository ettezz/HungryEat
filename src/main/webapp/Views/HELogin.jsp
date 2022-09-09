<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<%@page import="java.util.*" %>
<%@page import="javax.swing.*" %>
<%@page import="Models.HEUser" %>
    
<%  


	if (request.getAttribute("sent") != null){
		if (request.getAttribute("userModel") != null){
			HEUser user = (HEUser)request.getAttribute("userModel");
			String USER_ID = user.getUserId();
			String USER_NAME = user.getUserName();
			String USER_PHONE = user.getPhone();
			String USER_ADDRESS = user.getAddress();
			String ROLE_TYPE = user.getRoleType();
			
			session.setAttribute("USER_ID", USER_ID);
			session.setAttribute("USER_NAME", USER_NAME);
			session.setAttribute("USER_PHONE", USER_PHONE);
			session.setAttribute("USER_ADDRESS", USER_ADDRESS);
			session.setAttribute("ROLE_TYPE", ROLE_TYPE);
			
			out.println("<script type='text/javascript'>alert('歡迎 " + USER_NAME + " 登入');</script>");
			if (ROLE_TYPE.equals("A")){
				//跳轉網頁
				out.println("<script type='text/javascript'>window.location.href='Views/AupProduct.jsp';</script>");
			}else if (ROLE_TYPE.equals("B")){
				//跳轉網頁
				out.println("<script type='text/javascript'>window.location.href='Views/Rindex.jsp';</script>");
			}
			//out.println("<script type='text/javascript'>$('#resultModal').modal('show');</script>");
		}
		else{
			out.println("<script type='text/javascript'>alert('登入失敗');</script>");
			//out.println("<script type='text/javascript'>$('#resultModal').modal('show');</script>");
		}
	}
	
%>
    
<!DOCTYPE html> 
<html>
	<head>
		<title>巴豆妖 登入</title>
		
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
		<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,400italic,600,700' rel='stylesheet' type='text/css'>
		<link href='http://fonts.googleapis.com/css?family=Damion' rel='stylesheet' type='text/css'>
		<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/css/templatemo-style.css" rel="stylesheet">
		
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/demo.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/sky-forms.css">
		
		<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>      <!-- jQuery -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/templatemo-script.js"></script>      <!-- Templatemo Script -->

		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	</head>
	<script type="text/javascript">
		/*$(function () {
			$('#resultModal').modal('show');
	    	
	    });*/
    
	  //查詢訂單
        /*function loginClick(){
        	
        	var url = "${pageContext.request.contextPath}/HELoginServlet?"
        			+ "funcType=0" 
        			+ "&account=" + $("#account").val()
        			+ "&password=" + $("#password").val();
        	
        	url=encodeURI(url);
        	url=encodeURI(url); //兩次
        	$.ajax({
                type: "POST",
                url: url,
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                success: function(orderTitles){      
                    let str = "";
                    $.each(orderTitles, function (i, val){
                		str += "<tr>" +
                   		"<td>" +
                   		"<input type='button' value='查看明細' onclick='searchDtlClick(this)' />" +
                        "</td>" +
                        "<td>" +
                   		val.orderId +
                        "</td>" +
                        "<td style='color: red;'>" +
                        val.totalPrice +
                        "</td>" +
                        "<td>" +
                        val.userName +
                        "</td>" +
                        "<td>" +
                        val.shipDate +
                        "</td>" +
                        "<td>" +
                        val.shipAddress +
                        "</td>" +
                        "<td>" +
                        val.shipPhone +
                        "</td>" +
                        "<td>" +
                        val.orderMemo +
                        "</td>" +
                        "<td>" +
                        "<select>";
                        
                        str += "<option value='0'";
                        if (val.orderStatus == "0"){
                        	str += " selected ";
                        }
                        str += ">店家確認中</option>";
                        str += "<option value='1'";
                        if (val.orderStatus == "1"){
                        	str += " selected ";
                        }
                        str += ">製作中</option>";
                        str += "<option value='2'";
                        if (val.orderStatus == "2"){
                        	str += " selected ";
                        }
                        str += ">外送中</option>";
                        str += "<option value='3'";
                        if (val.orderStatus == "3"){
                        	str += " selected ";
                        }
                        str += ">已送達</option>";
                        str += "<option value='4'";
                        if (val.orderStatus == "4"){
                        	str += " selected ";
                        }
                        str += ">已結單</option>";
                        str += "<option value='5'";
                        if (val.orderStatus == "5"){
                        	str += " selected ";
                        }
                        str += ">訂單退回</option>";
                        
                        str += "</select>" + 
                        "</td>" +
                        "<td style='color: blue;'>" +
                        val.orderType +
                        "</td>" +
                        "<td>";
                        
                        if (val.orderStatus !== "4" && val.orderStatus !== "5"){
                        	str += "<input type='button' value='更新訂單狀態' onclick='updClick(this)' />";
                        }
                        
                        str += "</td>" +
                        "</tr>";
                        
                	});
                	$("#orderTitleTB").append(str);
                },
                error:function(err){
                	alert(err);
                },
            });
        	*/
        }
			/*$(function()
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
			});	*/		
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
							<!--<h1 class="tm-site-name tm-handwriting-font">Cafe House</h1>--> 
							</div>
							
							<!-- 選單-->
							<nav class="tm-nav">
								<ul>
								<li><a href="${pageContext.request.contextPath}/Views/Bindex.jsp">首頁</a></li>
								<li><a href="${pageContext.request.contextPath}/Views/ChooseRegister.jsp">註冊</a></li>
								
							
								</ul>
				
							</nav>   
							</div>           
						</div>    
						</div>
					</div>
		 
			
			<!--表單right-->
			<div class="body body-s">	
			
				<form method="POST" action="${pageContext.request.contextPath}/HELoginServlet" id="sky-form" class="sky-form">
					<header>登入</header>
					
					<fieldset>	
						<!--帳號-->			
						<section>
							
							<label class="input">
								<i class="icon-append icon-user"></i>
								<input type="text" name="account" placeholder="--帳號--" id="account">
								
							</label>
						</section>
						
						
						<!--密碼-->		
						<section>
							<label class="input">
								<i class="icon-append icon-lock"></i>
								<input type="password" name="password" placeholder="--密碼--" id="password">
							
							</label>
						</section>
						
					</fieldset>
						
					
					<footer>
						
						<button type="submit" class="button">登入</button>
					</footer>
				</form>	
				
			</div>
			
		
		
		
		
<div style="text-align:center;clear:both">

</div>
<!-- Modal -->
<div class="modal fade" id="resultModal" tabindex="-1" aria-labelledby="resultModalTitleLB" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="resultModalTitleLB">
		</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body" id="resultModalBodyLB">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

	</body>
</html>