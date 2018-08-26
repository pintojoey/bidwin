package bidwin.models;

import org.json.JSONObject;

import java.sql.Date;

public class Bid {
	
	private long id;
	private long orderId;
//	private long inventoryId;
	private double price;
	private Date timestamp;
	private String retailerEmail;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
//	public long getInventoryId() {
//		return inventoryId;
//	}
//	public void setInventoryId(long inventoryId) {
//		this.inventoryId = inventoryId;
//	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getRetailerEmail() {
		return retailerEmail;
	}

	public void setRetailerEmail(String retailerEmail) {
		this.retailerEmail = retailerEmail;
	}

	public JSONObject toJSON() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id",this.id);
		jsonObject.put("orderId",this.orderId);
		jsonObject.put("price",this.price);
		jsonObject.put("timestamp",this.timestamp);
		jsonObject.put("retailerEmail",this.retailerEmail);
		return jsonObject;
	}
}
