import java.math.BigDecimal;
import java.math.RoundingMode;

public class RedPencil {
	
	public static boolean checkProductEligibility(Product product){
		if(product.getCurrentPrice().compareTo(BigDecimal.ZERO)<=0){
			return false;
		}
		BigDecimal decreaseDifference = (product.getLastPrice()
				.subtract(product.getCurrentPrice()))
				.divide(product.getLastPrice(), 2, RoundingMode.HALF_UP)
				.multiply(new BigDecimal(100));
		System.out.println(decreaseDifference);
		if(decreaseDifference.compareTo(new BigDecimal (5)) > 0){
		return true;
		}else{
			System.out.println(decreaseDifference);
			return false;
		}
	}
	
}
