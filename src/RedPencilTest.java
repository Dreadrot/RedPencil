import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class RedPencilTest {

	Product product;
	Product redPencilProduct;
	@Before
	public void setUp(){
		product = new Product();
		redPencilProduct = new Product();
		redPencilProduct.setLastPrice(15);
		redPencilProduct.setCurrentPrice(12);
		redPencilProduct.setLastDateChanged("15-05-2015");
	}
	
	@Test
	public void redPencilChecksThatProductIsReducedByAtLeastFivePercentAndLessThanThirtyPercent() {
		assertEquals(false, RedPencil.checkProductEligibility(product));
		assertEquals(true, RedPencil.checkProductEligibility(redPencilProduct));

	}
	@Test
	public void redPencilChecksThatProductPriceHasBeenStableForAtLeastThirtyDays(){
		assertEquals(false, RedPencil.checkProductPriceStability(product));
		assertEquals(true, RedPencil.checkProductPriceStability(redPencilProduct));
	}
	
}
