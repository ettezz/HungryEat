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
				out.println("<script type='text/javascript'>window.location.href='Views/Aorder.jsp';</script>");
			}else if (ROLE_TYPE.equals("B")){
				//跳轉網頁
				out.println("<script type='text/javascript'>window.location.href='Views/Rindex.jsp';</script>");
			}else if (ROLE_TYPE.equals("C")){
				//跳轉網頁
				out.println("<script type='text/javascript'>window.location.href='Views/Rorder.jsp';</script>");
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
		<!-- 
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
		-->
	</head>
                       
                        
                        

	<body class="bg-cyan">
		 <!-- 讀取動畫 -->
		 <div id="loader-wrapper">
			
			<div class="loader-section section-left"></div>
			<div class="loader-section section-right"></div>
		  </div>
		  <!-- 讀取動畫end -->


					<!-- NAV-->
		<div class="tm-top-header  navbar-fixed-top">
						<div class="container">
						<div class="row">
							<div class="tm-top-header-inner">
							<div class="tm-logo-container">
								<img src="${pageContext.request.contextPath}/img/logo.png" alt="Logo" class="tm-site-logo">
				  
				  </div>
				  
				     <!-- rwd選單 -->
		            <div class="mobile-menu-icon">
		              <i class="m-bars"></i>
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
			<div class="body">	
			
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
			
		
		
		
		

<!-- Modal -->
<!-- 
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
-->

	</body>
</html>