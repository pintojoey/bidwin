package bidwin.models;

import java.sql.Date;

public class Order {
	
	private long id;
	private long productId;
	private long marketId;
	private long customerId;
	
	private double buyNow;
	private double startBid;
	
	private int minRating;
	
	private long duration;
	private Date timestamp;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public long getMarketId() {
		return marketId;
	}
	public void setMarketId(long marketId) {
		this.marketId = marketId;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public double getBuyNow() {
		return buyNow;
	}
	public void setBuyNow(double buyNow) {
		this.buyNow = buyNow;
	}
	public double getStartBid() {
		return startBid;
	}
	public void setStartBid(double startBid) {
		this.startBid = startBid;
	}
	public int getMinRating() {
		return minRating;
	}
	public void setMinRating(int minRating) {
		this.minRating = minRating;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
	
	
	

}
