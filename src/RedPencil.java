import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
public class RedPencil {
	
	public static boolean checkProductEligibility(Product product){
		if(product.getCurrentPrice().compareTo(BigDecimal.ZERO)<=0){
			return false;
		}
		BigDecimal decreaseDifference = (product.getLastPrice()
				.subtract(product.getCurrentPrice()))
				.divide(product.getLastPrice(), 2, RoundingMode.HALF_UP)
				.multiply(new BigDecimal(100));
		System.out.println(decreaseDifference+"% Change");
		if(decreaseDifference.compareTo(new BigDecimal (5)) > 0 && decreaseDifference.compareTo(new BigDecimal (30)) < 0){
		return true;
		}else{
			System.out.println(decreaseDifference);
			return false;
		}
	}
	
	public static boolean checkProductPriceStability(Product product){
		DateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		Date currentDate = new Date();

				Date date2 = null;

		try {
								String endDate = simpleDateFormat.format(currentDate);
					
			date2 = simpleDateFormat.parse(endDate);

			long getDiff = date2.getTime() - product.getLastDateChanged().getTime();

						long getDaysDiff = TimeUnit.MILLISECONDS.toDays(getDiff);

			System.out.println("Differance between date " + product.getLastDateChanged() + " and " + endDate + " is " + getDaysDiff + " days.");
			if(getDaysDiff>30){
			return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean checkRedPencilLength(Product product) {
		DateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		if (product.getLastRedPencilStart() == null){
			return false;
		}
		Date currentDate = new Date();

		Date date2 = null;

		try {
			String endDate = simpleDateFormat.format(currentDate);
					
			date2 = simpleDateFormat.parse(endDate);

			long getDiff = date2.getTime() - product.getLastRedPencilStart().getTime();
			long getDaysDiff = TimeUnit.MILLISECONDS.toDays(getDiff);
			System.out.println("Differance between redpencil date " + product.getLastRedPencilStart()+ " and " + endDate + " is " + getDaysDiff + " days.");
			
			if(getDaysDiff <= 30 && getDaysDiff >=0){
			return true;
			}else{
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
}
