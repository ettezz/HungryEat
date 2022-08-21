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
}
