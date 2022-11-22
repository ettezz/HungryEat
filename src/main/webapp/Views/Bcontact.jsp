<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>填寫訂單</title>
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
  		var shopId = "";
  		var shopName = "";
  		
  		var tag = 0;    
		var totalPrice = 0;
        
        $(function () {
        	var urlParams = new URLSearchParams(window.location.search);
        	if (urlParams.has('shopId')){
        		shopId = urlParams.get('shopId');
        		shopName = urlParams.get('shopName');
        		
        		$("#shopName").text(shopName);
        	}else{
        		alert('查無店家');
        		window.location.href="${pageContext.request.contextPath}/Views/Rindex.jsp";
        	}
        	
        	var url = "${pageContext.request.contextPath}/BcontactServlet?" + "funcType=0"
        			+ "&shopId=" + shopId;
        	url=encodeURI(url);
        	url=encodeURI(url); //兩次
        	$.ajax({
                type: "POST",
                url: url,
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                dataType : "json",
                success: function(item){
                	var str = '';
                	var selectedValue = ''; 
                	if (item.length == 0){
                		str = '<option  value="" data-memo="" data-price="0" data-img="" selected="selected">請選擇商品</option>';
                	}else{
                		$.each(item, function (i, val){
                			if (i === 0){
                				selectedValue = val.itemId;
                			}
                			str += '<option value="' + val.itemId 
                    		+ '" data-memo="' + val.itemMemo 
                    		+ '" data-price="' + val.itemPrice 
                    		+ '" data-img="' + val.itemImgName 
                    		+ '">'
                            + val.itemName + '</option>';
                    	});
                	}
                	$("#itemInfoSEL").append(str);
                	
                	$("#itemInfoSEL").val(selectedValue);
                	itemInfoChange();
                },
                error:function(err){
                	//alert(err);
                },
            });
        	
        	let str2 = "";
        	for (var i = 1; i <= 50; i++){
        		str2 += '<option value="' + i + '">' + i + '</option>';
        	}
        	$("#numSEL").append(str2);
        	
        	cleanClick();
        });
        
        //清除
        function cleanClick(){
        	//$("#drinkIdUpd").attr('disabled', 'disabled');
        	$("#phoneTXT").val("<%= session.getAttribute("USER_PHONE")%>");
        	$("#addressTXT").val("<%= session.getAttribute("USER_ADDRESS")%>");
        	$("#dateTXT").val("");
        	$("#timeXT").val("");
        	$("#memoTXT").val("");
        	$("#itemInfoSEL").val("");
        	itemInfoChange();
        	$("#numSEL").val("1");
        	$("#itemMemoTXT").val("");
        	
        	$("#totalPriceLB").text("0");
        	while (orderDtlTB.rows.length > 1) {
        		orderDtlTB.deleteRow(-1);
            }
        	tag = 0;
        	totalPrice = 0;
        }
      //訂購
        function insClick(){
        	if (!checkNotNull()){
        		return;
        	}
        	
        	let itemIdStr = "";
        	let numStr = "";
        	let orderDtlPriceStr = "";
        	let orderDtlMemoStr = "";
        	for (var i = 1; i < orderDtlTB.rows.length; i++){
        		if (i > 1){
        			itemIdStr += ";";
                	numStr += ";";
                	orderDtlPriceStr += ";";
                	orderDtlMemoStr += ";!";
        		}
        		itemIdStr += $("#orderDtlTB")[0].rows[i].cells[6].innerHTML;
        		numStr += $("#orderDtlTB")[0].rows[i].cells[2].innerHTML;
        		orderDtlPriceStr += $("#orderDtlTB")[0].rows[i].cells[3].innerHTML;
        		orderDtlMemoStr += $("#orderDtlTB")[0].rows[i].cells[4].innerHTML;
        	}
        	
        	let date = $("#dateTXT").val() + " " + $("#timeTXT").val() + ":00";
        	
        	
        	var url = "${pageContext.request.contextPath}/BcontactServlet?"
        			+ "funcType=1" 
        			+ "&shopId=" + shopId 
        			+ "&userId=" + "<%= session.getAttribute("USER_ID")%>" 
        			+ "&shipDate=" + date 
        			+ "&shipAddress=" + $("#addressTXT").val() 
        			+ "&shipPhone=" + $("#phoneTXT").val() 
        			+ "&orderMemo=" + $("#memoTXT").val() 
        			+ "&totalPrice=" + $("#totalPriceLB").text() 
        			+ "&itemId=" + itemIdStr
        			+ "&num=" + numStr 
        			+ "&orderDtlPrice=" + orderDtlPriceStr
        			+ "&orderDtlMemo=" + orderDtlMemoStr;
        	
        	
        	url=encodeURI(url);
        	url=encodeURI(url); //兩次
        	$.ajax({
                type: "POST",
                url: url,
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                success: function(msg){      
                    if (msg > 0 || parseInt(msg) > 0){
                    	alert("訂購成功");
                    	cleanClick();
                    }else{
                    	alert("訂購失敗:" + msg);
                    }
                },
                error:function(err){
                	alert(err);
                },
            });
        	
        }
        //檢核
        function checkNotNull(){
        	if ($("#phoneTXT").val() == ""){
        		alert("連絡電話不得為空");
        		return false;
        	}
        	if ($("#phoneTXT").val().length > 10){
        		alert("連絡電話最多為10碼");
        		return false;
        	}
        	if ($("#addressTXT").val() == ""){
        		alert("送貨地址不得為空");
        		return false;
        	}
        	if ($("#dateTXT").val() == ""){
        		alert("送貨日期不得為空");
        		return false;
        	}
        	if ($("#timeTXT").val() == ""){
        		alert("請選擇送貨時間");
        		return false;
        	}
        	let date = $("#dateTXT").val();
        	date = date.replace(/-/g,'/');
        	let date1 = new Date(); 
        	let date2 = new Date(date + " " + $("#timeTXT").val() + ":00");
        	if (date1.getTime() >= date2.getTime()) {
        		alert("送貨時間不得為過去");  
        		return false;
        	}
        	if ($("#totalPriceLB").text() == "0"){
        		alert("訂單明細不得為空");
        		return false;
        	}
        	
        	return true;
        }
        
      //添加
        function addClick(){
        	if (!chkOrderDtl()){
        		return;
        	}
        		
        	tag++;
        	
        	let cancelBtnID = "cancelBtn" + tag;
        	let itemId = $("#itemInfoSEL option:selected").val();
        	let itemName = $("#itemInfoSEL option:selected").text();
        	let itemPrice = $("#itemInfoSEL option:selected").data('price');
        	let itemMemo = $("#itemMemoTXT").val();
        	let money = (parseInt(itemPrice, 10) *  parseInt($("#numSEL").val(), 10));
        	
            $('#orderDtlTB').append("<tr>" +
           		"<td>" +
           		tag +
                "</td>" +
                "<td>" +
                itemName +
                "</td>" +
                "<td>" +
                $("#numSEL").val() +
                "</td>" +
                "<td>" +
                money +
                "</td>" +
                "<td>" +
                itemMemo +
                "</td>" +
                "<td>" +
                "<button id=" + cancelBtnID + " class='buttonA' onclick='cancelClick(this)'>取消</button>" +
                "</td>" +
                "<td style='display: none;'>" +
                itemId +
                "</td>" +
                "</tr>");

            totalPrice += money;
            $("#totalPriceLB").text(totalPrice);
        }
        
        function chkOrderDtl(){
        	if ($("#itemInfoSEL").val() == ""){
        		alert("請選擇飲品");
        		return false;
        	}
        	/*if ($("#itemPriceLB").text() == "0"){
        		alert("請選擇飲品");
        		return false;
        	}*/
        	return true;
        }
        
      //取消
        function cancelClick(obj) {
            /*if (!window.confirm('確定刪除?')) {
                return false;
            }*/

            var row = obj.parentNode.parentNode.rowIndex;
            let money = parseInt($("#orderDtlTB")[0].rows[row].cells[3].innerHTML);
            totalPrice -= money;
            $("#totalPriceLB").text(totalPrice);
            
            var table = document.getElementById("orderDtlTB");
            table.deleteRow(row);
            
            
        }
      
        function itemInfoChange(){
        	//console.log($("#itemInfoSEL option:selected").data('price'));
        	let itemName = $("#itemInfoSEL option:selected").text();
        	let itemPrice = $("#itemInfoSEL option:selected").data('price');
        	let itemImg = "${pageContext.request.contextPath}/upload/" + $("#itemInfoSEL option:selected").data('img');
        	let itemMemo = $("#itemInfoSEL option:selected").data('memo');
        	$("#itemName").text(itemName);
        	$("#itemPrice").text(itemPrice);
        	$("#itemImg").attr("src", itemImg);
        	$("#itemMemo").text(itemMemo);
        }
        
        /* Google map
        ------------------------------------------------*/
        /*var map = '';
        var center;

        function initialize() {
          var mapOptions = {
            zoom: 16,
            center: new google.maps.LatLng(13.758468,100.567481),
            scrollwheel: false
          };
          
          map = new google.maps.Map(document.getElementById('google-map'),  mapOptions);

          google.maps.event.addDomListener(map, 'idle', function() {
            calculateCenter();
          });
          
          google.maps.event.addDomListener(window, 'resize', function() {
            map.setCenter(center);
          });
        }

        function calculateCenter() {
          center = map.getCenter();
        }

        function loadGoogleMap(){
          var script = document.createElement('script');
          script.type = 'text/javascript';
          script.src = 'https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&' + 'callback=initialize';
          document.body.appendChild(script);
        }
        $(document).ready(function(){                
          loadGoogleMap();                
        });*/
        
	</script>
  <body>

    <%@include file="/Views/HEmenuBarB.jsp" %>
    

    <div class="tm-main-section light-gray-bg">
      <div class="container" style="max-width: 90%; min-width: 900px; width:70%;" id="main">
        <section class="tm-section row">
          <h2 class="col-lg-12 margin-bottom-30">填寫訂單</h2>
          <h3 class="col-lg-12 margin-bottom-30" id="shopName"></h3>
        
          <!--表單-->
          <div class="tm-contact-form" >
            <div class="col-lg-6 col-md-6" style="width: 30%;">
            
             <div class="form-group">
             	<select id="itemInfoSEL" style="width: 100%;" onchange="itemInfoChange()"></select>
             </div>
           	  <div class="tm-product" style="justify-content:normal;">
                <img id="itemImg" src="" width='200' alt="店家尚未上傳圖片">
                <div class="tm-product-text">
                  <h3 class="tm-product-title" id="itemName"></h3>
                  <div class="tm-product-price-currency ">
                    <a href="#">NT.<span id="itemPrice">0</span></a>
                  </div>
                  <hr>
                  <p class="tm-product-description" id="itemMemo"></p>
                </div>
              </div>
              
              <div class="form-group">
	              <input type="text" id="itemMemoTXT" class="form-control" placeholder="商品備註" />
	          </div>
	          <div class="form-group">
              數量：<select id="numSEL" ></select>個
              <div style="float:right;">
	         	<button class="buttonA" onClick="addClick()">添加</button> 
	         </div>
          
          	<div style="clear: both;"></div>
              
              </div> 
              
            
            </div>
            <div class="col-lg-6 col-md-6" style="width: 40%; background-color: #FFF;">
            	
					<label style="font-size: 21px;">訂單明細</label>
					<br />
					總價：<label id="totalPriceLB" style="color:red; font-size: 24px;"></label>元
					<br/>
					<div class="table-wrapper" style="height: 300px;overflow-y: auto;">
						<table id="orderDtlTB" style="width:100%">
							<tr>
								<th style="width: 10%;">序號</th>
								<th style="width: 15%;">商品名稱</th>
								<th style="width: 10%;">數量</th>
								<th style="width: 15%;">金額</th>
								<th>備註</th>
								<th style="min-width: 100px;width: 120px;">　　</th>
								<th style="display: none;">商品ID</th>
							</tr>
						</table>
					</div>
            </div>
            <div class="col-lg-6 col-md-6" style="width: 30%;">
            <!--建立訂單-->
              <!--電話-->
              <div class="form-group">
                <input type="tel" id="phoneTXT" class="form-control" placeholder="電話" />
              </div>
              <!--地址-->
               <div class="form-group">
                <input type="text" id="addressTXT" class="form-control" placeholder="地址" />
              </div>
               <!--送貨日期-->
              <div class="form-group">
                <input type="date" id="dateTXT" name="appt" class="form-control"
                 required>
              </div>

              <!--送貨時間-->
              <div class="form-group">
                
                <input type="time" id="timeTXT" name="appt" class="form-control"
                min="09:00" max="22:00" required>
                <!--<small>上午9點至晚上10點</small>-->
              </div>

               <!--備註-->
              <div class="form-group">
                <textarea id="memoTXT" class="form-control" rows="6" placeholder="備註資訊"></textarea>
              </div>
              
           	 <div style="float:right;">
	         	<button class="buttonA"  onClick="insClick()" >訂購</button>
	         </div>
          
          <div style="clear: both;"></div>
            </div>
          </div>
        </section>
      </div>
    </div> 
    
 </body>
 </html>