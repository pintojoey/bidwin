package bidwin.models;

import bidwin.database.QueryProducts;
import org.json.JSONObject;

import java.sql.Date;
import java.text.SimpleDateFormat;

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
	private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

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
	public void setDuration(long duration) {
		this.duration = duration;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}


    public JSONObject toJSON() {
	    JSONObject jsonObject = new JSONObject();
	    jsonObject.put("id",this.id);
	    jsonObject.put("productId",this.id);
        Product product = QueryProducts.getProduct(this.productId);
        if(product!=null){
            jsonObject.put("productName",product.getName());
            jsonObject.put("productDescription",product.getDescription());
        }
	    jsonObject.put("marketId",this.marketId);
	    jsonObject.put("customerId",this.customerId);
	    jsonObject.put("buyNow",this.buyNow);
	    jsonObject.put("startBid",this.startBid);
	    jsonObject.put("minRating",this.minRating);
	    jsonObject.put("duration",new SimpleDateFormat("HH:mm").format(this.duration));
	    jsonObject.put("timestamp",new SimpleDateFormat().format(this.timestamp));
	    jsonObject.put("status",this.status);
	    return jsonObject;
    }
}
