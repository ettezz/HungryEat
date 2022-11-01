<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>未外送訂單</title>
  <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,400italic,600,700' rel='stylesheet' type='text/css'>
  <link href='http://fonts.googleapis.com/css?family=Damion' rel='stylesheet' type='text/css'>
  <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/css/templatemo-style.css" rel="stylesheet">
 
	<!-- JS -->
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>      <!-- jQuery -->
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/templatemo-script.js"></script>      <!-- Templatemo Script -->

	<!--表單-->
	<link rel="stylesheet" href="https://cdn.datatables.net/1.12.1/css/jquery.dataTables.min.css" type='text/css'>
	 <!-- JS -->
	<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
	<script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
	
	<style type="text/css">
	  button {
	    background-color:rgb(223, 167, 93);
	    color: white;
	    padding: 10px;
	    border-style:none;
	    border-radius:10px;
	      
	  }
	  
	  <!-- Bootstrap -->
		<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
		
		<style type="text/css">
			.rwd-table {
		 	 background: #fff;
		 	 overflow: hidden;
			}

			.rwd-table tr:nth-of-type(2n){
			  background: #eee;
			}
			.rwd-table th, 
			.rwd-table td {
			  margin: 0.5em 1em;
			}
			.rwd-table {
			  min-width: 100%;
			}

			.rwd-table th {
			  display: none;
			}

			.rwd-table td {
			  display: block;
			}

			.rwd-table td:before {
			  content: attr(data-th) "  ";
			  font-weight: bold;
			  width: 6.5em;
			  display: inline-block;
			}

			.rwd-table th, .rwd-table td {
			  text-align: left;
			}

			.rwd-table th, .rwd-table td:before {
			  color: #000000;
			  font-weight: bold;
			}

			@media (min-width: 480px) {
			  .rwd-table td:before {
			    display: none;
			  }
			 .rwd-table th, .rwd-table td {
			    display: table-cell;
			    padding: 0.25em 0.5em;
			  }
			  .rwd-table th:first-child, 
			  .rwd-table td:first-child {
			    padding-left: 0;
			  }
			  .rwd-table th:last-child, 
			  .rwd-table td:last-child {
			    padding-right: 0;
			  }
			   .rwd-table th, 
			   .rwd-table td {
			    padding: 1em !important;
			  }
			}
		</style>
	<style type="text/css">@font-face {
  font-family: 'rbicon';
  src: url(chrome-extension://dipiagiiohfljcicegpgffpbnjmgjcnf/fonts/rbicon.woff2) format("woff2");
  font-weight: normal;
  font-style: normal; }
	  
	</style>	
  </head>
  <script type="text/javascript">
	  $(function () {
	  	
	  	cleanClick();
	  });
	  
	  
      /*$(document).ready(function () {
          $('table').DataTable({
            

              paging: false,
              ordering: false,
              info: false,

              language: {
                  "search": "搜尋:",
              }
            
          });
      });*/

  
	  //清除
	  function cleanClick(){
	  	
	  	/*let chk = document.getElementById("notFinishCBX");
	  	chk.checked = true;
	  	$("#orderIdLB").text("");
	  	$("#totalPriceLB").text("0");*/
	  	
	  	while (orderTitleTB.rows.length > 1) {
	  		orderTitleTB.deleteRow(-1);
	      }
	  	
	  	while (orderDtlTB.rows.length > 1) {
	  		orderDtlTB.deleteRow(-1);
	      }
	  	
	  	searchClick();
	  }
	  //查詢訂單
	  function searchClick(){
	  	while (orderTitleTB.rows.length > 1) {
	  		orderTitleTB.deleteRow(-1);
	      }
	  	
	  	//let chk = document.getElementById("notFinishCBX");
			
	  	var url = "${pageContext.request.contextPath}/RorderServlet?"
	  			+ "funcType=0" 
	  			+ "&shopId=" + "<%= session.getAttribute("USER_ID")%>"; 
	  			//+ "&notFinish=" + (chk.checked ? "1" : "0");
	  	
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
	             		
	                  "<td data-th=\"訂單編號:\">" +
	             		val.orderId +
	                  "</td>" +
	                  "<td data-th=\"顧客姓名:\">" +
	             		val.userName +
	                  "</td>" +
	                  "<td style='color: red;' data-th=\"總價:\">" +
	                  val.totalPrice +
	                  "</td>" +
	                  "<td data-th=\"送貨日期\">" +
	                  val.shipDate +
	                  "</td>" +
	                  "<td data-th=\"送貨地址\">" +
	                  val.shipAddress +
	                  "</td>" +
	                  "<td data-th=\"連絡電話:\">" +
	                  val.shipPhone +
	                  "</td>" +
	                  "<td data-th=\"備註:\">" +
	                  val.orderMemo +
	                  "</td>" +
	                  "<td style='color: blue;' data-th=\"訂單類別:\">" +
	                  val.orderCStatus +
	                  "</td>";
	                  if (val.orderType == '1'){
	                	  str += "<td style='color: red;'>";
		              } else {
		            	  str += "<td>";
		              }
	                  str += val.orderCType +
	                  "</td>" +
	                  "<td style='text-align: center;'>";
	                  
	                  if (val.orderStatus != "5" && val.orderStatus != "6" && val.orderType != "1"){
	                	let updStr = '';
	                	switch (val.orderStatus){
	                		case '2': updStr = '接收訂單'; break;
	                		case '3': updStr = '送達訂單'; break;
	                		case '4': updStr = '完成外送'; break;
	                	}
	                	str += (updStr != '' ? " <button style='color: white;' onclick='takeOrder(this," + (parseInt(val.orderStatus) + 1) + ")' >" + updStr + "</button>" : "");
	                  }

	                  str += "</td>" +
	                  "<td data-th=\"查看明細:\">" +
	             		"<button style='color: white;' onclick='searchDtlClick(this)' >查看明細</button>" +
	                  "</td>" +
	                  "</tr>";

	          	});
	              
	          	$("#orderTitleTB").append(str);
	          },
	          error:function(err){
	          	alert(err);
	          },
	      });
	  	
	  }
	  
	  //查詢訂單明細
	  function searchDtlClick(obj){
	  	while (orderDtlTB.rows.length > 1) {
	  		orderDtlTB.deleteRow(-1);
	      }
	  	
	  	var row = obj.parentNode.parentNode.rowIndex;
	  		
	  	let orderId = $("#orderTitleTB")[0].rows[row].cells[1].innerHTML;
	  	//$("#orderIdLB").text(orderId);
	  	//$("#totalPriceLB").text($("#orderTitleTB")[0].rows[row].cells[2].innerHTML);
	  	
	  	var url = "${pageContext.request.contextPath}/AorderServlet?"
				+ "funcType=1" 
				+ "&orderId=" + orderId;
		
	  	url=encodeURI(url);
	  	url=encodeURI(url); //兩次
	  	$.ajax({
	          type: "POST",
	          url: url,
	          contentType: "application/x-www-form-urlencoded; charset=utf-8",
	          success: function(orderDtls){      
	              let str = "";
	              $.each(orderDtls, function (i, val){
	          		str += "<tr>" +
	             		   "<td data-th=\"序號:\">" +
	             		    val.orderDtlId +
		                  "</td>" +
		                  "<td data-th=\"商品名稱:\">" +
		                  val.itemName +
		                  "</td>" +
		                  "<td data-th=\"數量:\">" +
		                  val.num +
		                  "</td>" +
		                  "<td data-th=\"金額:\">" +
		                  val.orderDtlPrice +
		                  "</td>" +
		                  "<td data-th=\"商品備註:\">" +
		                  val.orderDtlMemo +
		                  "</td>" +
		                  "</tr>";
	                  
	          	});
	          	$("#orderDtlTB").append(str);
	          },
	          error:function(err){
	          	alert(err);
	          },
	      });
	      	
	  }
	  
	  //接收訂單-更新訂單狀態
	  function takeOrder(obj, status) {
	      if (!window.confirm('確定接收訂單?')) {
	          return false;
	      }
	      var row = obj.parentNode.parentNode.rowIndex;
			console.log(status);
	  	let orderId = $("#orderTitleTB")[0].rows[row].cells[1].innerHTML;
	  	
	  	var url = "${pageContext.request.contextPath}/RorderServlet?"
				+ "funcType=2" 
				+ "&shopId=" + "<%= session.getAttribute("USER_ID")%>"
				+ "&orderId=" + orderId
				+ "&status=" + status;
		
	  	url=encodeURI(url);
	  	url=encodeURI(url); //兩次
	  	$.ajax({
	          type: "POST",
	          url: url,
	          contentType: "application/x-www-form-urlencoded; charset=utf-8",
	          success: function(msg){      
	          	if (msg == 1 || parseInt(msg) == 1){
	              	alert("更新成功");
	              	cleanClick();
	              	//searchClick(); //重新查詢
	            }else{
	              	alert("更新失敗:" + msg);
	            }
	          	
	          },
	          error:function(err){
	          	alert(err);
	          },
	      });
	  	
	      /*var table = document.getElementById("orderTitleTB");
	      table.deleteRow(row);*/
	  }
	  
	//訂單退回
//	  function takeOrder(obj) {
//	      if (!window.confirm('確定退回訂單?')) {
//          return false;
//	      }
//	      var row = obj.parentNode.parentNode.rowIndex;
//			
//	  	let orderId = $("#orderTitleTB")[0].rows[row].cells[1].innerHTML;
//	  	
//	  	var url = "${pageContext.request.contextPath}/AorderServlet?"
//				+ "funcType=3" 
				/*+ "&shopId=" + */"<%= session.getAttribute("USER_ID")%>"
//				+ "&orderId=" + orderId;
		
//	  	url=encodeURI(url);
//	  	url=encodeURI(url); //兩次
//	  	$.ajax({
//	          type: "POST",
//	          url: url,
//	          contentType: "application/x-www-form-urlencoded; charset=utf-8",
//	          success: function(msg){      
//	          	if (msg == 1 || parseInt(msg) == 1){
//	              	alert("訂單退回成功");
//	              	cleanClick();
	              	//searchClick(); //重新查詢
//	              }else{
//	              	alert("訂單退回失敗:" + msg);
//	              }
//	          },
//	          error:function(err){
//	          	alert(err);
//	          },
//	      });
	  	
	      /*var table = document.getElementById("orderTitleTB");
	      table.deleteRow(row);*/
//	  }
  </script>
<body>
	<%@include file="/Views/HEmenuBarC.jsp" %>
	
		<div class="container" id="main" style="max-width: 90%; min-width: 960px;">
	        
	      <h2 class="tm-section-header ">未外送訂單</h2>
	      <hr/>
	      
	       <h2 class="tm-section-header ">查詢訂單</h2>
	       
   	     <!-- 表格-->
		<div class="table-wrapper" style="height: 400px;">
	      <table id="orderTitleTB" class="rwd-table" >
	          <thead>
	              <tr>
	                  
	                  <th>訂單編號</th>
	                  <th>顧客姓名</th>
	                  <th>總價</th>
	                  <th>送貨日期</th>
	                  <th>送貨地址</th>
	                  <th>連絡電話</th>
	                  <th>備註</th>
	                  <th>訂單類別</th>
	                  <th>訂單狀態</th>
	                  <th></th>
	                  
	              </tr>
	          </thead>
	          
	          
	     
	      </table>
	   
		</div>
	      <h2 class="tm-section-header ">訂單明細</h2>
	      <hr/>
	            <!-- 表格2-->
	          <table id="orderDtlTB" class="display dataTable no-footer" style="width:100%">
	              <thead>
	                  <tr>
	                      <th>序號</th>
	                      <th>商品名稱</th>
	                      <th>數量</th>
	                      <th>金額</th>
	                      <th>商品備註</th>
	                  </tr>
	              </thead>
	              
	         
	          </table>
		
	 	</div>
    
	      
	    
</body>
</html>