<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
	<head>
		<title>巴豆妖 註冊</title>
		
		<meta http-equiv="Content-Type" content="text/css; charset=utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
		<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,400italic,600,700' rel='stylesheet' type='text/css'>
		<link href='http://fonts.googleapis.com/css?family=Damion' rel='stylesheet' type='text/css'>
		<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/css/templatemo-style.css" rel="stylesheet">
		
		
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/sky-forms.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/demo.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>      <!-- jQuery -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/templatemo-script.js"></script>      <!-- Templatemo Script -->

		
		
	</head>
	<script type="text/javascript">
			
	      //我是顧客
	        function BClick(){
	        	window.location.href='${pageContext.request.contextPath}/Views/Bregister.jsp';
	        }
	      
	      //我是店家
	        function AClick(){
	        	window.location.href='${pageContext.request.contextPath}/Views/Aregister.jsp';
	        }
		</script>
	<body class="bg-cyan">



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
					  <li><a href="${pageContext.request.contextPath}/Views/HELogin.jsp">登入</a></li>
					 
				  
					</ul>
	  
				  </nav>   
				</div>           
			  </div>    
			</div>
		  </div>





		<div class="divforCenter">		
			<div class="div-inner">
				
				<button class="buttonC" style="display:inline; float:inherit;" onClick="BClick()">我是顧客</button>
				&nbsp;&nbsp;
				<button class="buttonC" style="display:inline; float:inherit;" onClick="AClick()">我是店家</button>
				
			
			</div>
		</div>
		


	</body>
</html>