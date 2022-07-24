package Models;

public class HEUser {
	private String userId;
	private String userName;
	private String phone;
	private String address;
	private String roleType;
	private String imageName;
	private String belongShopId;
	
	public HEUser(){
		
	}
	
	public HEUser(String userId, String userName, String phone, String address, String roleType, String imageName, String belongShopId){
		this.userId = userId;
		this.userName = userName;
		this.phone = phone;
		this.address = address;
		this.roleType = roleType;
		this.imageName = imageName;
		this.belongShopId = belongShopId;
	}
	
	public String getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

	public String getRoleType() {
		return roleType;
	}
	
	public String getImageName() {
		return imageName;
	}
	
	public String getBelongShopId() {
		return belongShopId;
	}


}