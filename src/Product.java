import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Product {
	DateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

	private BigDecimal lastPrice = new BigDecimal(0);
	private BigDecimal currentPrice = new BigDecimal(0);
	private Date lastDateChanged = new Date();

	
	
	public Date getLastDateChanged() {
		return lastDateChanged;
	}
	public void setLastDateChanged(String lastDateChanged) {
		try{
			this.lastDateChanged = simpleDateFormat.parse(lastDateChanged);
			}catch(Exception e) {
				e.printStackTrace();
			}
	}
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
