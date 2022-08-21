package Models;

public class HEOrderDtl {
	private int orderId;
	private int orderDtlId;
	private String shopId;
	private int itemId;
	private String itemName;
	private int num;
	private String orderDtlMemo;
	private int orderDtlPrice;
	
	public HEOrderDtl() {
		
	}
	
	public HEOrderDtl(int orderId, int orderDtlId, String shopId, int itemId, String itemName, int num,
			String orderDtlMemo, int orderDtlPrice) {
		this.orderId = orderId;
		this.orderDtlId = orderDtlId;
		this.shopId = shopId;
		this.itemId = itemId;
		this.itemName = itemName;
		this.num = num;
		this.orderDtlMemo = orderDtlMemo;
		this.orderDtlPrice = orderDtlPrice;
	}

	public int getOrderId() {
		return orderId;
	}
	public int getOrderDtlId() {
		return orderDtlId;
	}
	public String getShopId() {
		return shopId;
	}
	public int getItemId() {
		return itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public int getNum() {
		return num;
	}
	public String getOrderDtlMemo() {
		return orderDtlMemo;
	}
	public int getOrderDtlPrice() {
		return orderDtlPrice;
	}
	
	
}
