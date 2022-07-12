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
  <body>

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
                <li><a href="Cindex.html">首頁</a></li>
                
                <li><a href="Cmodify.html">修改會員資料</a></li>
                <li><a href="Cregister.html">註冊</a><p>|</p>  <a href="${pageContext.request.contextPath}/Views/HELogin.jsp">登入</a></li>
            
              </ul>
               
              
              <!--購物車-->
              <div class="shopping-cart">
                <a href="Corder.html"><img src="${pageContext.request.contextPath}/img/shoopingcar-01.png" alt=""></a>
              </div>
        
            </nav>   
          </div>           
        </div>    
      </div>
    </div>
    

    <div class="tm-main-section light-gray-bg">
     
      
      <div class="container" id="main">
        
          <h2 class="tm-section-header ">店家資訊</h2>

        <!-- 照片選單-->      
        <section class="tm-section tm-section-margin-bottom-0 row">
          <!--並排-->
          <div class="col-lg-12 tm-popular-items-container">
            

            <!-- 1 -->
            <div class="tm-popular-item" onclick="window.open('menu.html)">
              <img src="${pageContext.request.contextPath}/img/drink01.jpg" alt="Popular" class="tm-popular-item-img">
              <div class="tm-popular-item-description">
                <h3 class="tm-popular-item-title">梅子兔生活飲品專賣店(西門總店)</h3>
                  <hr class="tm-popular-item-hr">
                <p>梅子兔生活飲品專賣創立於2006年，為創新的好喝手搖台灣茶加盟連鎖創業</p>
                
              </div>              
            </div>
             <!-- 2 -->
             <div class="tm-popular-item" onclick="menu.html">
              <img src="${pageContext.request.contextPath}/img/drink01.jpg" alt="Popular" class="tm-popular-item-img">
              <div class="tm-popular-item-description">
                <h3 class="tm-popular-item-title">梅子兔生活飲品專賣店(西門總店)</h3>
                  <hr class="tm-popular-item-hr">
                <p>梅子兔生活飲品專賣創立於2006年，為創新的好喝手搖台灣茶加盟連鎖創業</p>
                
              </div>              
            </div>
             <!-- 3 -->
             <div class="tm-popular-item" onclick="menu.html">
              <img src="${pageContext.request.contextPath}/img/drink01.jpg" alt="Popular" class="tm-popular-item-img">
              <div class="tm-popular-item-description">
                <h3 class="tm-popular-item-title">梅子兔生活飲品專賣店(西門總店)</h3>
                  <hr class="tm-popular-item-hr">
                <p>梅子兔生活飲品專賣創立於2006年，為創新的好喝手搖台灣茶加盟連鎖創業</p>
                
              </div>              
            </div>
             <!-- 4 -->
             <div class="tm-popular-item" onclick="location.href='menu.html">
              <img src="${pageContext.request.contextPath}/img/drink01.jpg" alt="Popular" class="tm-popular-item-img">
              <div class="tm-popular-item-description">
                <h3 class="tm-popular-item-title">梅子兔生活飲品專賣店(西門總店)</h3>
                  <hr class="tm-popular-item-hr">
                <p>梅子兔生活飲品專賣創立於2006年，為創新的好喝手搖台灣茶加盟連鎖創業</p>
                
              </div>              
            </div>
              <!-- 5 -->
              <div class="tm-popular-item" onclick="location.href='https://www.google.com.tw/?hl=zh_TW';">
                <img src="${pageContext.request.contextPath}/img/drink01.jpg" alt="Popular" class="tm-popular-item-img">
                <div class="tm-popular-item-description">
                  <h3 class="tm-popular-item-title">梅子兔生活飲品專賣店(西門總店)</h3>
                    <hr class="tm-popular-item-hr">
                  <p>梅子兔生活飲品專賣創立於2006年，為創新的好喝手搖台灣茶加盟連鎖創業</p>
                  
                </div>              
              </div>
                <!-- 6-->
             <div class="tm-popular-item" onclick="location.href='https://www.google.com.tw/?hl=zh_TW';">
              <img src="${pageContext.request.contextPath}/img/drink01.jpg" alt="Popular" class="tm-popular-item-img">
              <div class="tm-popular-item-description">
                <h3 class="tm-popular-item-title">梅子兔生活飲品專賣店(西門總店)</h3>
                  <hr class="tm-popular-item-hr">
                <p>梅子兔生活飲品專賣創立於2006年，為創新的好喝手搖台灣茶加盟連鎖創業</p>
                
              </div>              
            </div>

  


           
            
           
          </div>          
        </section>
       
        
      </div>
    </div> 
    
        
     </div>
   
   
 </body>
 </html>