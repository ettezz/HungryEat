<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

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
		//登出
        function logout(){
        	//sessionStorage.clear();
        	$.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/HELogoutServlet",
                success: function(msg){      
                    alert('登出成功');
                },
                error:function(err){
                	alert(message);
                },
            });
            
        	window.location.href="${pageContext.request.contextPath}/Views/Bindex.jsp";
        }
	</script>
	<body>
		<% 
			if (session.getAttribute("ROLE_TYPE") == null){
				out.println("<script type='text/javascript'>alert('查無使用者權限');</script>");
				out.println("<script type='text/javascript'>window.location.href='" + request.getContextPath() + "/Views/Bindex.jsp';</script>");
			}
			else{
				if (!session.getAttribute("ROLE_TYPE").toString().equals("C")){
					out.println("<script type='text/javascript'>alert('查無使用者權限');</script>");
					out.println("<script type='text/javascript'>window.location.href='" + request.getContextPath() + "/Views/Bindex.jsp';</script>");
				}
				else{
		%>
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
                        <li><a href="${pageContext.request.contextPath}/Views/Rorder.jsp">未外送訂單</a></li>
                        <li><a href="#" onClick="logout()">登出</a></li>
                    
                    </ul>
	  
				  </nav>   
				</div>           
			  </div>    
			</div>
		</div>
		<%
				}
			}
		%>
	</body>
</html>