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
}
