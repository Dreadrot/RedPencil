import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Product {
	DateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

	private BigDecimal lastPrice = new BigDecimal(0);
	private BigDecimal currentPrice = new BigDecimal(0);
	private BigDecimal basePrice = new BigDecimal(0);
	private Date lastDateChanged = new Date();
	private Date lastRedPencilStart;
	private boolean redPencilActive = false;
	private BigDecimal originalPrice = new BigDecimal(0);
	
	
	
	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}
	public BigDecimal getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(BigDecimal basePrice) {
		this.setLastPrice(this.getBasePrice());
		this.basePrice = basePrice;
		this.setLastDateChanged(new Date());
	}
	public boolean isRedPencilActive() {
		return redPencilActive;
	}
	public void setRedPencilActive(boolean redPencilActive) {
		this.redPencilActive = redPencilActive;
	}
	public Date getLastRedPencilStart() {
		return lastRedPencilStart;
	}
	public void setLastRedPencilStart(String lastRedPencilStart) {
		try{
		this.lastRedPencilStart = simpleDateFormat.parse(lastRedPencilStart);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
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
	public void setLastDateChanged(Date lastDateChanged) {
		try{
			this.lastDateChanged = (lastDateChanged);
			}catch(Exception e) {
				e.printStackTrace();
			}
	}
	public BigDecimal getLastPrice() {
		return lastPrice;
	}
	public void setLastPrice(BigDecimal bigDecimal) {
		this.lastPrice = bigDecimal;
	}
	public BigDecimal getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(BigDecimal currentPrice) {
		this.currentPrice = currentPrice;
	}
	
	
}
