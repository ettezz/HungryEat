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
	
	public HEUser getLogin_Users(String userId, String passwd) {
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
	      	String USER_ID = rs.getString("USER_ID");
	      	String USER_NAME = rs.getString("USER_NAME");
	      	String PHONE = rs.getString("PHONE");
	      	String ADDRESS = rs.getString("ADDRESS");
	      	String ROLE_TYPE = rs.getString("ROLE_TYPE");
	      	String IMG_NAME = rs.getString("IMG_NAME");
	      	String BELONG_SHOP_ID = rs.getString("BELONG_SHOP_ID");
	      	
	      	userModel = new HEUser(USER_ID, USER_NAME, PHONE, ADDRESS, ROLE_TYPE, IMG_NAME, BELONG_SHOP_ID);
	      }
	      return userModel;
	    } catch (Exception ex) {
	    	System.out.println(ex.getMessage());
	      return null;
	    }
		
	}
	
	public int insBregister_User(String userId, String userName, String passwd, String phone, String address) {
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
	
	public int insAregister_User(String userId, String userName, String passwd, String phone, String address, String fileName) {
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
	
	public List<HEUser> getA_Users() {
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
	      	String USER_ID = rs.getString("USER_ID");
	      	String USER_NAME = rs.getString("USER_NAME");
	      	String PHONE = rs.getString("PHONE");
	      	String ADDRESS = rs.getString("ADDRESS");
	      	String ROLE_TYPE = rs.getString("ROLE_TYPE");
	      	String IMG_NAME = rs.getString("IMG_NAME") == null? "" : rs.getString("IMG_NAME");
	      	String BELONG_SHOP_ID = rs.getString("BELONG_SHOP_ID") == null? "" : rs.getString("BELONG_SHOP_ID");
	      	
	      	usersList.add(new HEUser(USER_ID, USER_NAME, PHONE, ADDRESS, ROLE_TYPE, IMG_NAME, BELONG_SHOP_ID));
	      	
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
	
	public List<HEItem> getAupProduct_Items(String shopId, String itemId, String itemName) {
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
	    	String SHOP_ID = rs.getString("SHOP_ID");
		    int ITEM_ID = rs.getInt("ITEM_ID");
	      	String ITEM_NAME = rs.getString("ITEM_NAME");
	      	int ITEM_PRICE = rs.getInt("ITEM_PRICE");
	      	String ITEM_MEMO = rs.getString("ITEM_MEMO");
	      	String ITEM_IMG_NAME = rs.getString("ITEM_IMG_NAME");
	      	
	      	itemList.add(new HEItem(SHOP_ID, ITEM_ID, ITEM_NAME, ITEM_PRICE, ITEM_MEMO, ITEM_IMG_NAME));
	      	
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
	
	public int insAupProduct_Items(String shopId, String itemName, int itemPrice, String itemMemo, String fileName) {
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
	
	public int updAupProduct_Items(String shopId, int itemId, String itemName, int itemPrice, String itemMemo, String fileName) {
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
	
	public int delAupProduct_Items(String shopId, int itemId) {
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
}
