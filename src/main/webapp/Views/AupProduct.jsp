<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<%@page import="java.util.*" %>
<%@page import="javax.swing.*" %>
<%@page import="Models.HEUser" %>
<%@page import="Models.HEItem" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>商品維護</title>
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
        //填入資料
        function fillItem(itemId, itemName, itemPrice, itemMemo){
        	$("#itemIdUpd").val(itemId);
        	$("#itemNameUpd").val(itemName);
        	$("#itemPriceUpd").val(itemPrice);
        	$("#itemMemoUpd").val(itemMemo);
        	$("#uploadFile").val("");
        	updMode();
        }
        //新增
        function insClick(){
        	if (!checkNotNull()){
        		return;
        	}
        	if ($("#uploadFile").val() == ""){
        		alert("請上傳店家照片");
        		return;
        	}
        	
        	var url = "${pageContext.request.contextPath}/AupProductServlet?" ;
	    	
	    	var data={
	    		"funcType": "1",
	    		"shopId": "<%= session.getAttribute("USER_ID")%>",
	    		"itemName": $("#itemNameUpd").val(),
	    		"itemPrice": $("#itemPriceUpd").val(),
	    		"itemMemo": $("#itemMemoUpd").val(),
	    	};
	    	
	    	url=encodeURI(url);
	    	url=encodeURI(url); //兩次
        	
	    	$.ajaxFileUpload({
	            url : url,
	            secureuri : true ,
	            fileElementId : "uploadFile",
	            dataType : "text" ,
	            data : data,
	            success : function(msg, status) {
	            	if (msg == 1 || msg.equals("1")){
                    	alert("新增成功");
                    	cleanClick();
                    	
                    }else{
                    	alert("新增失敗:" + msg);
                    }
	            },
	            error : function(data, status, e) {
	                alert("新增失敗");
	            }
	        });
	    	
        }
        //更新
        function updClick(){
        	if (!checkNotNull()){
        		return;
        	}
        	
			var url = "${pageContext.request.contextPath}/AupProductServlet?" ;
	    	
	    	var data={
	    		"funcType": "2",
	    		"shopId": "<%= session.getAttribute("USER_ID")%>",
	    		"itemId": $("#itemIdUpd").val(),
	    		"itemName": $("#itemNameUpd").val(),
	    		"itemPrice": $("#itemPriceUpd").val(),
	    		"itemMemo": $("#itemMemoUpd").val(),
	    	};
	    	
        	url=encodeURI(url);
        	url=encodeURI(url); //兩次
        	
        	$.ajaxFileUpload({
	            url : url,
	            secureuri : true ,
	            fileElementId : "uploadFile",
	            dataType : "text" ,
	            data : data,
	            success : function(msg, status) {
	            	if (msg == 1 || msg.equals("1")){
                    	alert("更新成功");
                    	cleanClick();
                    	
                    }else{
                    	alert("更新失敗:" + msg);
                    }
	            },
	            error : function(data, status, e) {
	                alert("更新失敗");
	            }
	        });
        }
        //刪除
        function delClick(){
        	if (!confirm("確定刪除？")){
        		return;
        	}
        	var url = "${pageContext.request.contextPath}/AupProductServlet?" 
        			+ "funcType=3" 
        			+ "&shopId=" + "<%= session.getAttribute("USER_ID")%>"
        			+ "&itemId=" + $("#itemIdUpd").val();
        	url=encodeURI(url);
        	url=encodeURI(url); //兩次
        	$.ajax({
                type: "POST",
                url: url,
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                success: function(msg){      
                    if (msg == 1 || msg.equals("1")){
                    	alert("刪除成功");
                    	cleanClick();
                    	
                    }else{
                    	alert("刪除失敗:" + msg);
                    }
                },
                error:function(err){
                	alert(err);
                },
            });
        	cleanClick();
        }
        //清除
        function cleanClick(){
        	$("#itemIdUpd").attr('disabled', 'disabled');
        	$("#itemIdUpd").val("");
        	$("#itemNameUpd").val("");
        	$("#itemPriceUpd").val("");
        	$("#itemMemoUpd").val("");
        	$("#uploadFile").val("");
        	insMode();
        }
        
        function insMode(){
        	$("#insBTN").removeAttr('disabled');
        	$("#updBTN").attr('disabled', 'disabled');
        	$("#delBTN").attr('disabled', 'disabled');
        }
        
        function updMode(){
        	$("#insBTN").attr('disabled', 'disabled');
        	$("#updBTN").removeAttr('disabled');
        	$("#delBTN").removeAttr('disabled');
        }
        //檢核
        function checkNotNull(){
        	if ($("#itemNameUpd").val() == ""){
        		alert("商品名稱不得為空");
        		return false;
        	}
        	if ($("#itemPriceUpd").val() == ""){
        		alert("價錢不得為空");
        		return false;
        	}
        	if (parseInt($("#itemPriceUpd").val()) < 0){
        		alert("價錢不得為負");
        		return false;
        	}
        	return true;
        }
	</script>
  <body class="tm-main light-gray-bg">

    <%@include file="/Views/HEmenuBarA.jsp" %> 
    
    <!--MAIN-->
       
        <div class="body" style="max-width: 55%; min-width: 800px;">
        	<div class="sky-form">	
            <!--form-->
                <form action="${pageContext.request.contextPath}/AupProductServlet" method="post" name="searchClick" id="sky-form" class="sky-form">
                    <header>商品維護</header>
				
                    <fieldset>	
                        <!--商品編號-->			
                        <section>
                            
                            <label class="input">
                                <span>商品編號：</span>
                                <input type="text" name="itemId" >   
                            </label>
                        </section>
                        <!--商品名稱-->			
                        <section>
                            <label class="input">
                                <span>商品名稱：</span>
                                <input type="text" name="itemName" >  
                                <input type="hidden" name="shopId" value=<%= session.getAttribute("USER_ID")%>>
                                <input type="hidden" name="funcType" value="0">
                                <button type="submit" class="button">查詢商品</button>
                            </label>
                        </section>
                    </fieldset>
                 </form>
                    <% if (request.getAttribute("sent") != null){
					if (request.getAttribute("itemList") != null){
				%>
				  <!--table-->  
                    <fieldset>
                    <div class="table-wrapper" style="height: 300px;overflow-y: auto;">	
                    <table style="width:100%">
                        <thead>
                            <tr>
                                    <th style="text-align:center; width:14%">商品編號</th>
                                    <th style="text-align:center; width:14%;">商品名稱</th>
                                    <th style="text-align:center; width:14%;">價錢</th>
                                    <th>商品敘述</th>
                                    <th style="min-width: 80px;"></th>
                            </tr>
                        </thead>

                        <tbody>
                            <tbody>
				<%
						List<HEItem> itemList = (List<HEItem>)request.getAttribute("itemList");
						for (HEItem item : itemList) {
					
				%>
						<tr>
							<td style="text-align:center;"><%=item.getItemId()%></td>
							<td style="text-align:center;"><%=item.getItemName()%></td>
							<td style="text-align:center;"><%=item.getItemPrice()%></td>
							<td><%=item.getItemMemo()%></td>
							<td style="text-align:center;"><button class="button" style="padding: 0 15px; float:inherit; margin: 10px auto;"  onClick="fillItem('<%=item.getItemId()%>','<%=item.getItemName()%>','<%=item.getItemPrice()%>','<%=item.getItemMemo()%>')">編輯</button></td>
						</tr>
				<%
						}
				%>
					</tbody>
                        
                        </tbody>
                    </table>
                    </div>
                    </fieldset>
				<%
				}
					  else{
				%>
					<label style="color:red;">查無資料</label>
				<%
					  }
					}
				%>
                       <!--商品資訊-->	
                <fieldset>
                    <!--商品編號-->			
                    <section>     
                        <label class="input">
                            <span>商品編號：</span>
                            <input type="number" id="itemIdUpd"> 
                        </label>
                    </section>
                    <section>     
                        <label class="input">
                            <span>商品名稱：</span>
                            <input type="text" id="itemNameUpd">
                        </label>
                    </section>
                    <section>     
                        <label class="input">
                            <span>價錢：</span>
                            <input type="number" id="itemPriceUpd">
                        </label>
                    </section>
                    <section>     
                        <label class="textarea">
                            <span>商品敘述：</span>
                            <textarea id="itemMemoUpd" rows="5" style="width: 460px;" ></textarea>
                        </label>
                    </section>
                    <section>
						
							<span class="text-left" style="color: rgb(131, 131, 131);">上傳商品照片</span>

							<input type="file" name="uploadfile" id="uploadFile"  style="overflow: hidden" />
							<div  class="icon-upload-alt"></div>
							
					</section>
                    <section>
                        <button id="cleanBTN" class="button" onClick="cleanClick()" >清除</button>
						<button id="delBTN" class="button" onClick="delClick()" >刪除</button>
                        <button id="updBTN" class="button" onClick="updClick()" >更新</button>
                        <button id="insBTN" class="button" onClick="insClick()" >新增</button>
                        
                        
                    </section>
                
                   
                </fieldset>
                


                

        	</div>
        </div>
    
     
   
   
 </body>
 </html>