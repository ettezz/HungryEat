package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HEAllModel {
	private String password = "fjusedia";//統一使用的變數
	private String url = "jdbc:mysql://140.134.24.157:53306/HE";
	private String user = "root";
	private String driver = "com.mysql.cj.jdbc.Driver";
	
	public HEAllModel(){
		
	}
	
	public HEUser getLoginUsers(String userId, String passwd) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		HEUser userModel = null;
	    try {
	      
	      Class.forName(driver);
	      con = DriverManager.getConnection(url, user, password);
	      String sql = "SELECT USER_ID, USER_NAME, PHONE, ADDRESS, ROLE_TYPE, IMG_NAME, BELONG_SHOP_ID FROM HE_USERS WHERE USER_ID = ? AND PASSWD = ? ";
	      stmt = con.prepareStatement(sql);
	      stmt.setString(1, userId);
	      stmt.setString(2, passwd);
	      rs = stmt.executeQuery();//執行
	      
	      if(rs.next()){
	      	//String USER_ID = rs.getString("USER_ID");
	      	String userNameSele = rs.getString("USER_NAME");
	      	String phoneSele = rs.getString("PHONE");
	      	String addressSele = rs.getString("ADDRESS");
	      	String roleTypeSele = rs.getString("ROLE_TYPE");
	      	String imgNameSele = rs.getString("IMG_NAME");
	      	String belongShopIdSele = rs.getString("BELONG_SHOP_ID") == null? "" : rs.getString("BELONG_SHOP_ID");
	      	
	      	userModel = new HEUser(userId, userNameSele, phoneSele, addressSele, roleTypeSele, imgNameSele, belongShopIdSele);
	      }
	      return userModel;
	    } catch (Exception ex) {
	    	System.out.println(ex.getMessage());
	      return null;
	    }
		
	}
	
	public int insBregisterUser(String userId, String userName, String passwd, String phone, String address) {
		Connection con = null;
		PreparedStatement stmt = null;
		int rs = -1;
		
	    try {
	    	int paramCount = 0; 
	    	
	      Class.forName(driver);
	      con = DriverManager.getConnection(url, user, password);
	      String sql = " INSERT INTO HE_USERS (USER_ID, USER_NAME, PASSWD, PHONE, ADDRESS, ROLE_TYPE) "
	      		+ "		VALUES(?, ?, ?, ?, ?,'B') ";
	      stmt = con.prepareStatement(sql);
	      stmt.setString(++paramCount, userId);
	      stmt.setString(++paramCount, userName);
	      stmt.setString(++paramCount, passwd);
	      stmt.setString(++paramCount, phone);
	      stmt.setString(++paramCount, address);
	      
	      rs = stmt.executeUpdate();//執行
	      
	      return rs;
	    } catch (Exception ex) {
	    	System.out.println(ex.getMessage());
	    	if (ex.getMessage().indexOf("Duplicate") > -1) {
	    		return -2;
	    	}
	      return -1;
	    }
		
	}
	
	public int insAregisterUser(String userId, String userName, String passwd, String phone, String address, String fileName) {
		Connection con = null;
		PreparedStatement stmt = null;
		int rs = -1;
		
	    try {
	    	int paramCount = 0; 
	    	
	      Class.forName(driver);
	      con = DriverManager.getConnection(url, user, password);
	      String sql = " INSERT INTO HE_USERS (USER_ID, USER_NAME, PASSWD, PHONE, ADDRESS, ROLE_TYPE, IMG_NAME) "
	      		+ "		VALUES(?, ?, ?, ?, ?,'A', ?) ";
	      stmt = con.prepareStatement(sql);
	      stmt.setString(++paramCount, userId);
	      stmt.setString(++paramCount, userName);
	      stmt.setString(++paramCount, passwd);
	      stmt.setString(++paramCount, phone);
	      stmt.setString(++paramCount, address);
	      stmt.setString(++paramCount, fileName);
	      
	      rs = stmt.executeUpdate();//執行
	      
	      return rs;
	    } catch (Exception ex) {
	    	System.out.println(ex.getMessage());
	    	if (ex.getMessage().indexOf("Duplicate") > -1) {
	    		return -2;
	    	}
	      return -1;
	    }
		
	}
	
	public List<HEUser> getAUsers() {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<HEUser> usersList = new ArrayList<HEUser>();
	    try {
	      Class.forName(driver);
	      con = DriverManager.getConnection(url, user, password);
	      String sql = "SELECT * FROM HE_USERS WHERE ROLE_TYPE = 'A' ORDER BY USER_NAME ";
	      stmt = con.prepareStatement(sql);
	      //stmt.setString(1, userId);
	      //stmt.setString(2, userName);
	      //stmt.setString(3, phone);
	      rs = stmt.executeQuery();//執行
	      
	      while(rs.next()){
	      	String userIdSele = rs.getString("USER_ID");
	      	String userNameSele = rs.getString("USER_NAME");
	      	String phoneSele = rs.getString("PHONE");
	      	String addressSele = rs.getString("ADDRESS");
	      	String roleTypeSele = rs.getString("ROLE_TYPE");
	      	String imgNameSele = rs.getString("IMG_NAME") == null? "" : rs.getString("IMG_NAME");
	      	String belongShopIdSele = rs.getString("BELONG_SHOP_ID") == null? "" : rs.getString("BELONG_SHOP_ID");
	      	
	      	usersList.add(new HEUser(userIdSele, userNameSele, phoneSele, addressSele, roleTypeSele, imgNameSele, belongShopIdSele));
	      	
	      }
	      
	      if (usersList.size() > 0) {
	    	  return usersList;
	      }else {
	    	  return null;
	      }
	    } catch (Exception ex) {
	    	System.out.println(ex.getMessage());
	      return null;
	    }
		
	}
	
	public List<HEItem> getAupProductItems(String shopId, String itemId, String itemName) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<HEItem> itemList = new ArrayList<HEItem>();
	    try {
	    	int paramCount = 0; 
	    	
	      Class.forName(driver);
	      con = DriverManager.getConnection(url, user, password);
	      String sql = "SELECT * FROM HE_ITEMS WHERE SHOP_ID = ? AND ITEM_ID like CONCAT('%', ?, '%') AND ITEM_NAME like CONCAT('%', ?, '%') ORDER BY ITEM_ID  ";
	      stmt = con.prepareStatement(sql);
	      stmt.setString(++paramCount, shopId);
	      stmt.setString(++paramCount, itemId);
	      stmt.setString(++paramCount, itemName);
	      rs = stmt.executeQuery();//執行
	      
	      while(rs.next()){
	    	//String SHOP_ID = rs.getString("SHOP_ID");
		    int itemIdSele = rs.getInt("ITEM_ID");
	      	String itemNameSele = rs.getString("ITEM_NAME");
	      	int itemPriceSele = rs.getInt("ITEM_PRICE");
	      	String itemMemoSele = rs.getString("ITEM_MEMO");
	      	String itemImgNameSele = rs.getString("ITEM_IMG_NAME");
	      	
	      	itemList.add(new HEItem(shopId, itemIdSele, itemNameSele, itemPriceSele, itemMemoSele, itemImgNameSele));
	      	
	      }
	      if (itemList.size() > 0) {
	    	  return itemList;
	      }else {
	    	  return null;
	      }
	    } catch (Exception ex) {
	    	System.out.println(ex.getMessage());
	      return null;
	    }
		
	}
	
	public int insAupProductItems(String shopId, String itemName, int itemPrice, String itemMemo, String fileName) {
		Connection con = null;
		PreparedStatement stmt = null;
		int rs = -1;
		
	    try {
	    	int paramCount = 0; 
	    	
	      Class.forName(driver);
	      con = DriverManager.getConnection(url, user, password);
	      String sql = "INSERT INTO HE_ITEMS (SHOP_ID, ITEM_NAME, ITEM_PRICE, ITEM_MEMO, ITEM_IMG_NAME) VALUES (?, ?, ?, ?, ?) ";
	      stmt = con.prepareStatement(sql);
	      stmt.setString(++paramCount, shopId);
	      stmt.setString(++paramCount, itemName);
	      stmt.setInt(++paramCount, itemPrice);
	      stmt.setString(++paramCount, itemMemo);
	      stmt.setString(++paramCount, fileName);
	      rs = stmt.executeUpdate();//執行
	      
	      return rs;
	    } catch (Exception ex) {
	    	System.out.println(ex.getMessage());
	      return -1;
	    }
		
	}
	
	public int updAupProductItems(String shopId, int itemId, String itemName, int itemPrice, String itemMemo, String fileName) {
		Connection con = null;
		PreparedStatement stmt = null;
		int rs = -1;
		
	    try {
	    	int paramCount = 0; 
	    	
	      Class.forName(driver);
	      con = DriverManager.getConnection(url, user, password);
	      String sql = "UPDATE HE_ITEMS SET ITEM_NAME = ?, ITEM_PRICE = ?, ITEM_MEMO = ? ";
	      if (!(fileName.equals("") || fileName == null)) {
	    	  sql += ", ITEM_IMG_NAME = ? "; 
	      }
	      sql += " WHERE SHOP_ID = ? AND ITEM_ID = ? ";
	      
	      stmt = con.prepareStatement(sql);
	      stmt.setString(++paramCount, itemName);
	      stmt.setInt(++paramCount, itemPrice);
	      stmt.setString(++paramCount, itemMemo);
	      
	      if (!(fileName.equals("") || fileName == null)) {
	    	  stmt.setString(++paramCount, fileName);
	      }
	      stmt.setString(++paramCount, shopId);
	      stmt.setInt(++paramCount, itemId);
	      rs = stmt.executeUpdate();//執行
	      
	      return rs;
	    } catch (Exception ex) {
	    	System.out.println(ex.getMessage());
	      return -1;
	    }
		
	}
	
	public int delAupProductItems(String shopId, int itemId) {
		Connection con = null;
		PreparedStatement stmt = null;
		int rs = -1;
		
	    try {
	    	int paramCount = 0; 
	    	
	      Class.forName(driver);
	      con = DriverManager.getConnection(url, user, password);
	      String sql = " DELETE FROM HE_ITEMS WHERE SHOP_ID = ? AND ITEM_ID = ? ";
	      stmt = con.prepareStatement(sql);
	      stmt.setString(++paramCount, shopId);
	      stmt.setInt(++paramCount, itemId);
	      rs = stmt.executeUpdate();//執行
	      
	      return rs;
	    } catch (Exception ex) {
	    	System.out.println(ex.getMessage());
	      return -1;
	    }
		
	}
	
	public int insContactOrder(String shopId, String userId, String shipDate, String shipAddress, String shipPhone, 
			String orderMemo, int totalPrice, String itemId, 
			String num,String orderDtlPrice, String orderDtlMemo) {
		Connection con = null;
		PreparedStatement stmt = null;
		int rs = -1;
			
	    try {
	    	String urlForMulti = url + "?allowMultiQueries=true"; //允許多次指令
	    	
	    	String[] itemIdArr = itemId.split(";");
	    	String[] numArr = num.split(";");
	    	String[] orderDtlPriceArr = orderDtlPrice.split(";");
	    	String[] orderDtlMemoArr = orderDtlMemo.split(";!");
	    	
	    	int paramCount = 0; 
	    	
	      Class.forName(driver);
	      con = DriverManager.getConnection(urlForMulti, user, password);
	      con.setAutoCommit(false);
	      String sql =/* " START TRANSACTION; "
	      		+*/ "INSERT INTO HE_ORDER_TITLE (SHOP_ID, USER_ID, SHIP_DATE, SHIP_ADDRESS, SHIP_PHONE, CR_DATE, CR_USER_ID, PROC_DATE, PROC_USER_ID, ORDER_STATUS, ORDER_TYPE, ORDER_MEMO, TOTAL_PRICE) "
	      		+ "		VALUES (?, ?, ?, ?, ?, NOW(), ?, NOW(), ?, '0', '0', ?, ?); ";
	      
	      sql +=	 "INSERT INTO HE_ORDER_DTL (ORDER_ID, SHOP_ID, ITEM_ID, NUM, ORDERDTL_PRICE, ORDERDTL_MEMO) "
	      		+ "		VALUES  ";
	      		
	      for (int i = 0; i < itemIdArr.length; i++) {
	    	  if (i > 0) {
	    		  sql += ",";
	    	  }
	    	  sql += " ((SELECT (AUTO_INCREMENT - 1) FROM information_schema.TABLES WHERE (TABLE_NAME = 'HE_ORDER_TITLE')), ?, ?, ?, ?, ?) ";
	      }
	      /*sql += "	; "
		      		+ "COMMIT ";*/
	      
	      stmt = con.prepareStatement(sql);
	      stmt.setString(++paramCount, shopId);
	      stmt.setString(++paramCount, userId);
	      stmt.setString(++paramCount, shipDate);
	      stmt.setString(++paramCount, shipAddress);
	      stmt.setString(++paramCount, shipPhone);
	      stmt.setString(++paramCount, userId);
	      stmt.setString(++paramCount, userId);
	      stmt.setString(++paramCount, orderMemo);
	      stmt.setInt(++paramCount, totalPrice);
	      for (int i = 0; i < itemIdArr.length; i++) {
	    	  stmt.setString(++paramCount, shopId);
	    	  stmt.setInt(++paramCount, Integer.parseInt(itemIdArr[i]));
	    	  stmt.setInt(++paramCount, Integer.parseInt(numArr[i]));
	    	  stmt.setInt(++paramCount, Integer.parseInt(orderDtlPriceArr[i]));
	    	  stmt.setString(++paramCount, orderDtlMemoArr[i]);
	      }
	      try {
		      rs = stmt.executeUpdate();//執行
		      if (rs > 0) {
		    	  con.commit();
		      }else{
		    	  con.rollback();
		      }
	      }catch (Exception ex) {
	    	  con.rollback();
	    	  System.out.println(ex.getMessage());
			  return -1;
	      }
	      return rs;
	    } catch (Exception ex) {
	    	System.out.println(ex.getMessage());
	      return -1;
	    }
		
	}
	
	public List<HEOrderTitle> getBorderTitle(String userIdForSearch) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<HEOrderTitle> orderTitleList = new ArrayList<HEOrderTitle>();
		//JSONArray jarr = new JSONArray();
		//String jsonStr = "";
	    try {
	    	int paramCount = 0; 
	    	
	      Class.forName(driver);
	      con = DriverManager.getConnection(url, user, password);
	      String sql = " SELECT A.*, B.USER_NAME, C.USER_NAME SHOP_NAME, D.USER_NAME SHIPPER_NAME,  "
	      		+ "	CASE A.ORDER_STATUS "
	      		+ "		WHEN '0' THEN '店家確認中' "
	      		+ "		WHEN '1' THEN '製作中' "
	      		+ "		WHEN '2' THEN '等待外送' "
	      		+ "		WHEN '3' THEN '外送中' "
	      		+ "		WHEN '4' THEN '已送達' "
	      		+ "		WHEN '5' THEN '已結單' "
	      		+ "		WHEN '6' THEN '訂單退回' "
	      		+ "		ELSE A.ORDER_STATUS "
	      		+ "		END ORDER_CSTATUS, "
	      		+ "	CASE A.ORDER_TYPE "
	      		+ "		WHEN '0' THEN '訂購' "
	      		+ "		WHEN '1' THEN '已取消' "
	      		+ "		ELSE A.ORDER_TYPE "
	      		+ "		END ORDER_CTYPE "
	      		+ "	FROM HE_ORDER_TITLE A "
	      		+ "	JOIN HE_USERS B ON (A.USER_ID = B.USER_ID) "
	      		+ "	JOIN HE_USERS C ON (A.SHOP_ID = C.USER_ID) "
	      		+ "	LEFT JOIN HE_USERS D ON (A.SHIPPER_ID = D.USER_ID) "
	      		+ "	WHERE A.USER_ID = ? ";
	      /*if (notFinish.equals("1")) {*/
	    	  sql += "AND A.ORDER_STATUS != '5' ";
	    	  /*}*/
	      sql += " AND A.ORDER_TYPE = '0' ORDER BY A.ORDER_ID DESC";
	      stmt = con.prepareStatement(sql);
	      stmt.setString(++paramCount, userIdForSearch);
	      rs = stmt.executeQuery();//執行
	      
	      while(rs.next()){
	    	int orderId = rs.getInt("ORDER_ID");
	      	String userId = rs.getString("USER_ID");
	      	String userName = rs.getString("USER_NAME");
	      	String shopId = rs.getString("SHOP_ID");
	      	String shopName = rs.getString("SHOP_NAME");
	      	String shipDate = rs.getString("SHIP_DATE");
	      	String shipAddress = rs.getString("SHIP_ADDRESS");
	      	String shipPhone = rs.getString("SHIP_PHONE");
	      	String crDate = rs.getString("CR_DATE");
	      	String crUserId = rs.getString("CR_USER_ID");
	      	String procDate = rs.getString("PROC_DATE");
	      	String procUserId = rs.getString("PROC_USER_ID");
	      	String orderStatus = rs.getString("ORDER_STATUS");
	      	String orderCStatus = rs.getString("ORDER_CSTATUS");
	      	String orderType = rs.getString("ORDER_TYPE");
	      	String orderCType = rs.getString("ORDER_CTYPE");
	      	String orderMemo = rs.getString("ORDER_MEMO");
	      	int totalPrice = rs.getInt("TOTAL_PRICE");
	      	String shipperId = rs.getString("SHIPPER_ID");
	      	String shipperName = rs.getString("SHIPPER_NAME");
	      	String shipperPhone = rs.getString("SHIPPER_PHONE");
	      	
	      	orderTitleList.add(new HEOrderTitle(orderId, shopId, shopName, userId, userName, shipDate, shipAddress, shipPhone, 
	    			crDate, crUserId, procDate, procUserId, orderStatus, orderCStatus,
	    			orderType, orderCType, orderMemo, totalPrice, shipperId, shipperName, shipperPhone));
	      	//jarr.put(new JSONObject(new Drink(DRINK_ID, DRINK_NAME, DRINK_PRICE, DRINK_MEMO)));
	      }
	      //jsonStr = jarr.toString();
	      if (orderTitleList.size() > 0) {
	    	  return orderTitleList;
	      }else {
	    	  return null;
	      }
	      //return jsonStr;
	    } catch (Exception ex) {
	    	System.out.println(ex.getMessage());
	      return null;
	    	//return ex.getMessage();
	    }
		
	}
	
	public List<HEOrderDtl> getBorderDtl(int orderIdForSearch) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<HEOrderDtl> orderDtlList = new ArrayList<HEOrderDtl>();
		//JSONArray jarr = new JSONArray();
		//String jsonStr = "";
	    try {
	    	int paramCount = 0; 
	    	
	      Class.forName(driver);
	      con = DriverManager.getConnection(url, user, password);
	      String sql = " SELECT A.*, IFNULL(B.ITEM_NAME, A.ITEM_ID) ITEM_NAME, C.USER_NAME SHOP_NAME "
	      		+ "	FROM HE_ORDER_DTL A  "
	      		+ "	LEFT JOIN HE_ITEMS B ON (A.ITEM_ID = B.ITEM_ID) "
	      		+ "	JOIN HE_USERS C ON (A.SHOP_ID = C.USER_ID) "
	      		+ "	WHERE A.ORDER_ID = ? ORDER BY A.ORDERDTL_ID";

	      stmt = con.prepareStatement(sql);
	      stmt.setInt(++paramCount, orderIdForSearch);
	      rs = stmt.executeQuery();//執行
	      
	      while(rs.next()){
	    	int orderId = rs.getInt("ORDER_ID");
	    	int orderDtlId = rs.getInt("ORDERDTL_ID");
	    	String shopId = rs.getString("SHOP_ID");
	    	String shopName = rs.getString("SHOP_NAME");
	      	int itemId = rs.getInt("ITEM_ID");
	      	String itemName = rs.getString("ITEM_NAME");
	      	int num = rs.getInt("NUM");
	      	String orderDtlMemo = rs.getString("ORDERDTL_MEMO");
	      	int orderDtlPrice = rs.getInt("ORDERDTL_PRICE");
	      	
	      	orderDtlList.add(new HEOrderDtl(orderId, orderDtlId, shopId, shopName, itemId, itemName, num,
	    			orderDtlMemo, orderDtlPrice));
	      	//jarr.put(new JSONObject(new Drink(DRINK_ID, DRINK_NAME, DRINK_PRICE, DRINK_MEMO)));
	      }
	      //jsonStr = jarr.toString();
	      if (orderDtlList.size() > 0) {
	    	  return orderDtlList;
	      }else {
	    	  return null;
	      }
	      //return jsonStr;
	    } catch (Exception ex) {
	    	System.out.println(ex.getMessage());
	      return null;
	    	//return ex.getMessage();
	    }
	}
	
	public int updBorderTitle(String userId, int orderId) {
		Connection con = null;
		PreparedStatement stmt = null;
		int rs = -1;
		
	    try {
	    	int paramCount = 0; 
	    	
	      Class.forName(driver);
	      con = DriverManager.getConnection(url, user, password);
	      String sql = " UPDATE HE_ORDER_TITLE SET ORDER_TYPE = '1', PROC_DATE = NOW(), PROC_USER_ID = ? WHERE ORDER_ID = ? ";
	      stmt = con.prepareStatement(sql);
	      stmt.setString(++paramCount, userId);
	      stmt.setInt(++paramCount, orderId);
	      
	      rs = stmt.executeUpdate();//執行
	      
	      return rs;
	    } catch (Exception ex) {
	    	System.out.println(ex.getMessage());
	      return -1;
	    }
		
	}
	
	public int updBmodifyUser(String userId, String passwd, String phone, String address) {
		Connection con = null;
		PreparedStatement stmt = null;
		int rs = -1;
		
	    try {
	    	int paramCount = 0; 
	    	
	      Class.forName(driver);
	      con = DriverManager.getConnection(url, user, password);
	      String sql = " UPDATE HE_USERS SET ";
	      if (!passwd.trim().equals("")) {
	    	  sql +=" PASSWD = ?, ";
	      }
	      sql += " PHONE = ?, ADDRESS = ? WHERE USER_ID = ? ";
	      stmt = con.prepareStatement(sql);
	      if (!passwd.trim().equals("")) {
	    	  stmt.setString(++paramCount, passwd);
	      }
	      stmt.setString(++paramCount, phone);
	      stmt.setString(++paramCount, address);
	      stmt.setString(++paramCount, userId);
	      
	      rs = stmt.executeUpdate();//執行
	      
	      return rs;
	    } catch (Exception ex) {
	    	System.out.println(ex.getMessage());
	      return -1;
	    }
		
	}
}
