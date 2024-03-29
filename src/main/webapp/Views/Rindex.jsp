<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<%@page import="java.util.*" %>
<%@page import="javax.swing.*" %>
<%@page import="Models.HEUser" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>首頁</title>
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
        	
        	var url = "${pageContext.request.contextPath}/RindexServlet?"
        			+ "funcType=0";
        	
        	url=encodeURI(url);
        	url=encodeURI(url); //兩次
        	$.ajax({
                type: "POST",
                url: url,
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                success: function(shopList){   
                	let str = "";
                    $.each(shopList, function (i, val){
                		str += "<div class='tm-popular-item' onclick='openShopClick(\"" + val.userId + "\",\"" + val.userName + "\")'>";
                		if (val.imageName == ""){
                			str+=  "   <img src='${pageContext.request.contextPath}/upload/noName.jpg' alt='店家尚未上傳' width='272' class='tm-popular-item-img'>";
                		}else{
                			str+=  "   <img src='${pageContext.request.contextPath}/upload/" + val.imageName + "' alt='店家尚未上傳' width='272' class='tm-popular-item-img'>";                    	    	
                		}
                		str+=  "   <div class='tm-popular-item-description'>" + 
                	           "     <h3 class='tm-popular-item-title'>" + val.userName + "</h3>" + 
                	           "       <hr class='tm-popular-item-hr'>" + 
                	           "       <p>" + val.address + "</p>" + 
                	            "  </div>" +           
                	            "</div>";
                        
                	});
                	$("#showShopDIV").append(str);
                },
                error:function(err){
                	//alert(err);
                },
            });
        	
        });
        
        function openShopClick(shopId, shopName){
        	window.location.href="${pageContext.request.contextPath}/Views/Bcontact.jsp?" +
        			"shopId=" + shopId +
        			"&shopName=" + shopName;
        }
        
	</script>
  <body>

    <%@include file="/Views/HEmenuBarB.jsp" %>
    

    <div class="tm-main-section light-gray-bg">
     
      
      <div class="container" id="main">
        
          <h2 class="tm-section-header ">店家資訊</h2>

        <!-- 照片選單-->      
        <section class="tm-section tm-section-margin-bottom-0 row">
          <!--並排-->
          <div class="col-lg-12 tm-popular-items-container" id="showShopDIV">
            

            
           
          </div>          
        </section>
       
        
      </div>
    </div> 
    
      
   
   
 </body>
 </html>