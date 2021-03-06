import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.security.auth.DestroyFailedException;
public class RedPencil {
	public static boolean checkProductEligibility(Product product){
		if(product.getBasePrice().compareTo(BigDecimal.ZERO)<=0){
			return false;
		}
		if(product.getLastPrice().compareTo(BigDecimal.ZERO)<=0){
			return false;
		}
		BigDecimal decreaseDifference = (product.getLastPrice()
				.subtract(product.getBasePrice()))
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
		if(!product.isRedPencilActive()){
			return true;
		}
		Date currentDate = new Date();

		Date date2 = null;

		try {
			String endDate = simpleDateFormat.format(currentDate);
					
			date2 = simpleDateFormat.parse(endDate);

			long getDiff = date2.getTime() - product.getLastRedPencilStart().getTime();
			long getDaysDiff = TimeUnit.MILLISECONDS.toDays(getDiff);
			System.out.println("Differance between redpencil date " + product.getLastRedPencilStart() + " and " + endDate + " is " + getDaysDiff + " days.");
			
			if(getDaysDiff <= 30 && getDaysDiff >=0){
			return true;
			}else{
				product.setRedPencilActive(false);
				product.setCurrentPrice(product.getBasePrice());
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static BigDecimal activateRedPencilEvent(Product product) {
		BigDecimal redPencilDiscount = new BigDecimal(.05);
		redPencilDiscount = redPencilDiscount.multiply(product.getBasePrice(), new MathContext(2));
		product.setCurrentPrice(product.getBasePrice().subtract(redPencilDiscount));
		product.setRedPencilActive(true);
		if(!checkAgainstOriginalPrice(product)){
			product.setRedPencilActive(false);
			product.setCurrentPrice(product.getBasePrice());
		}
		return product.getCurrentPrice();
	}
	
	public static boolean doesItRedPencil(Product product){
		DateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		if(!checkAgainstOriginalPrice(product)){
			product.setRedPencilActive(false);
			product.setCurrentPrice(product.getBasePrice());

			return false;
		}
		if(RedPencil.checkProductEligibility(product) && RedPencil.checkProductPriceStability(product)
		   && RedPencil.checkRedPencilLength(product) && !product.isRedPencilActive()){
				if(product.getLastRedPencilStart() == null){
				product.setLastRedPencilStart(simpleDateFormat.format(new Date()));
				}
				RedPencil.activateRedPencilEvent(product);
				return true;
				
			}
		System.out.println("This does not match all the criteria for a red pencil event.");
		product.setCurrentPrice(product.getBasePrice());
		return false;
	}

	public static boolean checkAgainstOriginalPrice(Product product){
		if(product.isRedPencilActive() == false){
			product.setOriginalPrice(product.getBasePrice());
		}
			if(product.getOriginalPrice().compareTo(new BigDecimal(0))==0){
				product.setOriginalPrice(product.getBasePrice());
			}
			
			if(product.getCurrentPrice().compareTo(new BigDecimal(0))==0){
				product.setCurrentPrice(product.getBasePrice());
			}
			BigDecimal decreaseDifference = (product.getOriginalPrice()
					.subtract(product.getCurrentPrice()))
					.divide(product.getOriginalPrice(), 2, RoundingMode.HALF_UP)
					.multiply(new BigDecimal(100));
			
			System.out.println(decreaseDifference+"% Change");
			
			if(decreaseDifference.compareTo(new BigDecimal (30)) > 0){
			return false;
			}
		
		return true;
	}

}
