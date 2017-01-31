import java.math.BigDecimal;

public class Product {
	private BigDecimal lastPrice = new BigDecimal(0);
	private BigDecimal currentPrice = new BigDecimal(0);
	public BigDecimal getLastPrice() {
		return lastPrice;
	}
	public void setLastPrice(int lastPrice) {
		this.lastPrice = new BigDecimal(lastPrice);
	}
	public BigDecimal getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(int currentPrice) {
		this.currentPrice = new BigDecimal(currentPrice);
	}
	
	
}
