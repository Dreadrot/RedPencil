import static org.junit.Assert.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Before;
import org.junit.Test;

public class RedPencilTest {

	Product product;
	Product redPencilProduct;
	Product activeRedPencilProduct;
	@Before
	public void setUp(){
		product = new Product();
		product.setBasePrice(new BigDecimal(88));
		
		redPencilProduct = new Product();
		redPencilProduct.setBasePrice(new BigDecimal(12));
		redPencilProduct.setLastPrice(new BigDecimal(15));
		redPencilProduct.setLastDateChanged("15-05-2015");
		redPencilProduct.setLastRedPencilStart("25-01-2017");
		redPencilProduct.setRedPencilActive(false);
		
		activeRedPencilProduct = new Product();
		activeRedPencilProduct.setBasePrice(new BigDecimal(13));
		activeRedPencilProduct.setLastPrice(new BigDecimal(15));
		activeRedPencilProduct.setLastDateChanged("15-05-2015");
		activeRedPencilProduct.setLastRedPencilStart("25-01-2017");
		activeRedPencilProduct.setRedPencilActive(true);
		
		
	}
	
	@Test
	public void redPencilChecksThatProductIsReducedByAtLeastFivePercentAndLessThanThirtyPercent() {
		assertEquals(false, RedPencil.checkProductEligibility(product));
		assertEquals(true, RedPencil.checkProductEligibility(redPencilProduct));
		assertEquals(true, RedPencil.checkProductEligibility(activeRedPencilProduct));

	}
	@Test
	public void redPencilChecksThatProductPriceHasBeenStableForAtLeastThirtyDays(){
		assertEquals(false, RedPencil.checkProductPriceStability(product));
		assertEquals(true, RedPencil.checkProductPriceStability(redPencilProduct));
		assertEquals(true, RedPencil.checkProductPriceStability(activeRedPencilProduct));

	}
	
	//this test will only work while 25-01-2017 is within 30 days of the actual date!
	@Test
	public void redPencilEventHasntBeenActiveForMoreThanThirtyDays(){
		assertEquals(false, RedPencil.checkRedPencilLength(product));
		assertEquals(true, RedPencil.checkRedPencilLength(redPencilProduct));
		assertEquals(true, RedPencil.checkRedPencilLength(activeRedPencilProduct));

	}
	@Test
	public void redPencilEventReducesProductCostByFivePercent(){
		assertEquals(false, RedPencil.doesItRedPencil(product));
		assertEquals(true, RedPencil.doesItRedPencil(redPencilProduct));
		assertEquals(new BigDecimal(11.40).setScale(2, RoundingMode.HALF_UP), RedPencil.activateRedPencilEvent(redPencilProduct));
	}
	@Test
	public void furtherPriceReductionsDoNotExtendRedPencilEvent(){
		assertEquals(false, RedPencil.doesItRedPencil(product));
		assertEquals(true, RedPencil.doesItRedPencil(redPencilProduct));
		assertEquals(false, RedPencil.doesItRedPencil(activeRedPencilProduct));
	}
	@Test
	public void priceIncreaseWillEndRedPencilEvent(){
		activeRedPencilProduct.setBasePrice(new BigDecimal(35));
		assertEquals(false, RedPencil.doesItRedPencil(activeRedPencilProduct));
		assertEquals(false, RedPencil.doesItRedPencil(product));
		assertEquals(true, RedPencil.doesItRedPencil(redPencilProduct));
	}
	
	/*This was a feature missing from my product class, not so much my RedPencil class.
	 * It uncovered that I needed an additional if clause to check that the last price was
	 * greater than 0, lest I try to divide by 0.
	 */
	@Test
	public void settingANewBasePriceUpdatesTheLastPriceVariable(){
		product.setBasePrice(new BigDecimal(18));
		product.setBasePrice(new BigDecimal(11));
		redPencilProduct.setBasePrice(new BigDecimal(17));
		assertEquals(new BigDecimal(18), product.getLastPrice());
		assertEquals(new BigDecimal(12), redPencilProduct.getLastPrice());
	}
	@Test
	public void furtherPriceReductionsEndRedPencilEventsIfTotalReductionsAreMoreThanThirtyPercent(){
		redPencilProduct.setCurrentPrice(new BigDecimal(2));
		assertEquals(true, RedPencil.checkAgainstOriginalPrice(product));
		assertEquals(true, RedPencil.checkAgainstOriginalPrice(activeRedPencilProduct));
		assertEquals(false, RedPencil.checkAgainstOriginalPrice(redPencilProduct));
	}
	@Test
	public void redPencilEventsAreRepeatable(){
		redPencilProduct.setLastRedPencilStart("25-01-2016");
		assertEquals(true, RedPencil.doesItRedPencil(redPencilProduct));
		assertEquals(false, RedPencil.doesItRedPencil(product));
		assertEquals(false, RedPencil.doesItRedPencil(activeRedPencilProduct));

		Product repeatProduct = new Product();
		repeatProduct.setBasePrice(new BigDecimal(12));
		repeatProduct.setLastPrice(new BigDecimal(15));
		repeatProduct.setLastDateChanged("15-05-2016");
		repeatProduct.setLastRedPencilStart("13-01-2017");
		repeatProduct.setRedPencilActive(false);
		assertEquals(true, RedPencil.doesItRedPencil(repeatProduct));
		repeatProduct.setBasePrice(new BigDecimal(11));
		repeatProduct.setLastDateChanged("17-01-2017");
		assertEquals(true, RedPencil.doesItRedPencil(repeatProduct));
	}
	
}
