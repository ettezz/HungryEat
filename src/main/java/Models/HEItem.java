package Models;

public class HEItem {
	private String shopId;
	private int itemId;
	private String itemName;
	private int itemPrice;
	private String itemMemo;
	private String itemImgName;
	
	public HEItem(){
		
	}
	
	public HEItem(String shopId, int itemId, String itemName, int itemPrice, String itemMemo, String itemImgName){
		this.shopId = shopId;
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemMemo = itemMemo;
		this.itemImgName = itemImgName;
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

	public int getItemPrice() {
		return itemPrice;
	}

	public String getItemMemo() {
		return itemMemo;
	}

	public String getItemImgName() {
		return itemImgName;
	}
	
	
}
