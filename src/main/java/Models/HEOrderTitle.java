package Models;

public class HEOrderTitle {
	private int orderId;
	private String shopId;
	private String shopName;
	private String userId;
	private String userName;
	private String shipDate;
	private String shipAddress;
	private String shipPhone;
	private String crDate;
	private String crUserId;
	private String procDate;
	private String procUserId;
	private String orderStatus;
	private String orderType;
	private String orderMemo;
	private int totalPrice;
	private String shipperId;
	private String shipperName;
	private String shipperPhone;
	
	public HEOrderTitle() {
		
	}
	
	
	
	public HEOrderTitle(int orderId, String shopId, String shopName, String userId, String userName, String shipDate, String shipAddress, String shipPhone, 
			String crDate, String crUserId, String procDate, String procUserId, String orderStatus,
			String orderType, String orderMemo, int totalPrice, String shipperId, String shipperName, String shipperPhone) {
		
		this.orderId = orderId;
		this.shopId = shopId;
		this.shopName = shopName;
		this.userId = userId;
		this.userName = userName;
		this.shipDate = shipDate;
		this.shipAddress = shipAddress;
		this.shipPhone = shipPhone;
		this.crDate = crDate;
		this.crUserId = crUserId;
		this.procDate = procDate;
		this.procUserId = procUserId;
		this.orderStatus = orderStatus;
		this.orderType = orderType;
		this.orderMemo = orderMemo;
		this.totalPrice = totalPrice;
		this.shipperId = shipperId;
		this.shipperName = shipperName;
		this.shipperPhone = shipperPhone;
	}

	public int getOrderId() {
		return orderId;
	}
	public String getShopId() {
		return shopId;
	}
	public String getShopName() {
		return shopName;
	}
	public String getUserId() {
		return userId;
	}
	public String getUserName() {
		return userName;
	}
	public String getShipDate() {
		return shipDate;
	}
	public String getShipAddress() {
		return shipAddress;
	}
	public String getShipPhone() {
		return shipPhone;
	}
	public String getCrDate() {
		return crDate;
	}
	public String getCrUserId() {
		return crUserId;
	}
	public String getProcDate() {
		return procDate;
	}
	public String getProcUserId() {
		return procUserId;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public String getOrderType() {
		return orderType;
	}
	public String getOrderMemo() {
		return orderMemo;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public String getShipperId() {
		return shipperId;
	}public String getShipperName() {
		return shipperName;
	}public String getShipperPhone() {
		return shipperPhone;
	}
}
