package bidwin.models;

public class Inventory {
	
	private long id;
	private long retailerId;
	private long marketId;
	private double sellNow;
	private double minPrice;
	private int count;
	private int scores;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getRetailerId() {
		return retailerId;
	}
	public void setRetailerId(long retailerId) {
		this.retailerId = retailerId;
	}
	public long getMarketId() {
		return marketId;
	}
	public void setMarketId(long marketId) {
		this.marketId = marketId;
	}
	public double getSellNow() {
		return sellNow;
	}
	public void setSellNow(double sellNow) {
		this.sellNow = sellNow;
	}
	public double getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getScores() {
		return scores;
	}
	public void setScores(int scores) {
		this.scores = scores;
	}
	
	
	

}
